package com.racoonair.mapper

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiNetworkSpecifier
import android.os.Build
import android.os.PatternMatcher
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.max
import kotlin.math.tan

/**
 * Sequoia por libre (Disco FPV: el dron no la gobierna). La app se une a la WiFi de la Sequoia solo para
 * ella (el SkyController sigue por USB y los datos móviles siguen dando internet), la configura con la misma
 * API HTTP que usa su web y arranca la captura en modo GPS (dispara cada X metros con su propio GPS).
 *
 * API sacada del firmware 1.7.1 de la Sequoia (usr/share/www/js/config.js):
 *   GET  /status, /config, /capture, /storage, /gpsfix
 *   POST /config  {"capture_mode":"gps_position"} · {"gps_param":"18"} (metros, mín. 5) · {"sensors_mask":31}
 *   GET  /capture/start · /capture/stop
 */
object SequoiaCam {
    const val IP = "192.168.47.1"
    /** Campo de visión vertical de las cámaras mono según la propia web de la Sequoia. */
    const val VFOV_DEG = 50.1

    /** Metros entre disparos para un solape frontal dado (misma fórmula que la web de la Sequoia). */
    fun gpsDistance(altM: Double, frontOverlap: Double): Double =
        max(5.0, (1 - frontOverlap) * 2 * altM * tan(Math.toRadians(VFOV_DEG / 2)))

    class Session(private val ctx: Context) {
        private val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        private var cb: ConnectivityManager.NetworkCallback? = null
        @Volatile var network: Network? = null

        /**
         * Pide la WiFi de la Sequoia (Android muestra la red para aprobarla la primera vez).
         * [ssidPrefix]: «Sequoia» por defecto. [pass]: solo si se le puso contraseña.
         */
        fun connect(ssidPrefix: String, pass: String?, ok: (Network?) -> Unit, fail: (String) -> Unit) {
            if (Build.VERSION.SDK_INT < 29) { ok(null); return } // Android 9 o menos: conectar a mano en Ajustes
            val spec = WifiNetworkSpecifier.Builder()
                .setSsidPattern(PatternMatcher(ssidPrefix, PatternMatcher.PATTERN_PREFIX))
                .apply { if (!pass.isNullOrEmpty()) setWpa2Passphrase(pass) }
                .build()
            val req = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .setNetworkSpecifier(spec).build()
            var done = false
            val c = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(n: Network) { if (!done) { done = true; network = n; ok(n) } }
                override fun onUnavailable() { if (!done) { done = true; fail(tr("no se encontró la WiFi de la Sequoia (o se canceló)")) } }
            }
            cb = c
            try { cm.requestNetwork(req, c, 60_000) } catch (t: Throwable) { done = true; fail(t.message ?: t.javaClass.simpleName) }
        }

        fun release() {
            cb?.let { try { cm.unregisterNetworkCallback(it) } catch (_: Throwable) {} }
            cb = null; network = null
        }

        private fun http(method: String, path: String, body: JSONObject? = null): JSONObject {
            val u = URL("http://$IP$path")
            val c = (network?.openConnection(u) ?: u.openConnection()) as HttpURLConnection
            c.connectTimeout = 5000; c.readTimeout = 8000; c.requestMethod = method
            c.useCaches = false
            if (body != null) {
                c.doOutput = true
                c.setRequestProperty("Content-Type", "json") // igual que su web
                c.outputStream.use { it.write(body.toString().toByteArray()) }
            }
            try {
                val code = c.responseCode
                val txt = (if (code in 200..299) c.inputStream else c.errorStream)?.use { String(it.readBytes()) } ?: ""
                if (code !in 200..299) throw java.io.IOException("HTTP $code $path")
                return if (txt.trim().startsWith("{")) JSONObject(txt) else JSONObject().put("raw", txt)
            } finally { c.disconnect() }
        }

        fun get(path: String) = http("GET", path)
        fun post(body: JSONObject) = http("POST", "/config", body)

        /** [state]: «armed» = captura en marcha, «stopped» = parada, «» = sin cambio. */
        data class Report(val ok: Boolean, val lines: List<String>, val state: String = "")

        /**
         * Bloqueante. Deja la Sequoia capturando con 5 cámaras:
         *  - [byDistance] = true → modo GPS, un disparo cada [value] metros (solo dispara al moverse).
         *  - [byDistance] = false → time-lapse, un disparo cada [value] segundos (dispara también parada).
         */
        fun arm(byDistance: Boolean, value: Double): Report {
            val l = ArrayList<String>()
            fun line(s: String) { l.add(s); Crumbs.write("sequoia: $s") }
            try {
                get("/status")
                line(trf("✔ Sequoia responde (%1\$s)", IP))
            } catch (t: Throwable) { line(trf("✘ La Sequoia no responde: %1\$s", t.message)); return Report(false, l) }
            try {
                if (get("/capture").optString("capture") != "stopped") {
                    get("/capture/stop"); line(tr("· Estaba capturando: parada para reconfigurar"))
                    // La parada no es instantánea (vacía a la tarjeta lo que tiene en memoria): mientras tanto
                    // la Sequoia ignora el cambio de modo. Tras un time-lapse largo puede tardar bastante.
                    val until = System.currentTimeMillis() + 30_000
                    while (System.currentTimeMillis() < until) {
                        Thread.sleep(500)
                        if (get("/capture").optString("capture") == "stopped") break
                    }
                }
            } catch (_: Throwable) {}
            try {
                val s = get("/storage")
                val sd = s.optJSONObject("sd")
                if (sd != null && sd.optString("read_only") == "no" && sd.optString("corrupted") == "no")
                    line(trf("✔ Tarjeta SD: %1\$s GB libres", "%.1f".format(sd.optDouble("free") / 1073741824.0)))
                else if (sd != null) line(tr("⚠ Tarjeta SD de solo lectura o dañada: guardará en la memoria interna"))
                else line(tr("⚠ Sin tarjeta SD: guardará en la memoria interna"))
            } catch (_: Throwable) { line(tr("· No se pudo leer el almacenamiento")) }
            try {
                line(if (get("/gpsfix").optString("fix") == "yes") tr("✔ GPS de la Sequoia con posición")
                     else tr("⚠ GPS de la Sequoia SIN posición todavía: déjala al cielo abierto un par de minutos"))
            } catch (_: Throwable) {}
            val m = Math.round(max(5.0, value)).toInt()
            val secs = Math.round(max(1.0, value) * 10) / 10.0
            val wantMode = if (byDistance) "gps_position" else "timelapse"
            try {
                // Cambiar de modo puede tardar (o no tomarse a la primera justo tras parar): reintentar.
                // Modo y parámetro en la MISMA petición (como hace su web); si no lo toma, reenviar la
                // configuración completa que tiene la cámara con esos dos campos cambiados.
                fun modeBody(base: JSONObject) = base.put("capture_mode", wantMode).apply {
                    if (byDistance) put("gps_param", m.toString()) else put("timelapse_param", secs.toString())
                }
                var tries = 0
                while (true) {
                    val body = if (tries < 2) modeBody(JSONObject()) else modeBody(try { get("/config") } catch (_: Throwable) { JSONObject() })
                    post(body); Thread.sleep(700)
                    if (get("/config").optString("capture_mode") == wantMode || ++tries >= 6) break
                    // ¿Sigue parándose? Esperar a que termine antes de reintentar.
                    try { if (get("/capture").optString("capture") != "stopped") { get("/capture/stop"); Thread.sleep(2000) } } catch (_: Throwable) {}
                    line(tr("· La Sequoia aún no cambia de modo: reintentando…")); Thread.sleep(1500)
                }
                if (byDistance) post(JSONObject().put("gps_param", m.toString()))
                else post(JSONObject().put("timelapse_param", secs.toString()))
                Thread.sleep(300)
                post(JSONObject().put("sensors_mask", 31)); Thread.sleep(300)
                val cfg = get("/config")
                val mode = cfg.optString("capture_mode")
                if (mode != wantMode) {
                    line(trf("✘ No aceptó el modo %1\$s (está en «%2\$s»)", if (byDistance) "GPS" else "time-lapse", mode))
                    val capt = try { get("/capture").optString("capture") } catch (_: Throwable) { "?" }
                    val fix = try { get("/gpsfix").optString("fix") } catch (_: Throwable) { "?" }
                    line(trf("· Estado de la captura: %1\$s · GPS: %2\$s", capt, fix))
                    line(if (byDistance && fix != "yes") tr("→ Puede que no admita «por distancia» sin posición GPS: sácala al cielo abierto, espera el fix y vuelve a enviar.")
                         else tr("→ Desenchufa la Sequoia 10 s y vuelve a enviar el plan."))
                    return Report(false, l)
                }
                line(if (byDistance) trf("✔ Modo GPS: un disparo cada %1\$s m · 5 cámaras", cfg.optString("gps_param").ifEmpty { m.toString() })
                     else trf("✔ Time-lapse: un disparo cada %1\$s s · 5 cámaras", cfg.optString("timelapse_param").ifEmpty { secs.toString() }))
            } catch (t: Throwable) { line(trf("✘ Error al configurar: %1\$s", t.message)); return Report(false, l) }
            try {
                val r = get("/capture/start")
                Thread.sleep(1000)
                val st = get("/capture").optString("capture")
                if (st == "running") line(if (byDistance) trf("✔ Captura armada: empezará a disparar sola en cuanto el dron avance (cada %1\$s m)", m)
                                          else trf("✔ Captura EN MARCHA: dispara ya cada %1\$s s (también en tierra)", secs))
                else { line(trf("✘ La captura no arrancó (%1\$s)", r.optString("status", st))); return Report(false, l) }
            } catch (t: Throwable) { line(trf("✘ Error al arrancar la captura: %1\$s", t.message)); return Report(false, l) }
            return Report(true, l, "armed")
        }

        /** Bloqueante. Lee lo que tiene programado (lo mismo que enseña su web). */
        fun inspect(): Report {
            val l = ArrayList<String>()
            val cfg = try { get("/config") } catch (t: Throwable) { return Report(false, listOf(trf("✘ La Sequoia no responde: %1\$s", t.message))) }
            val mode = cfg.optString("capture_mode")
            l.add(tr("Modo: ") + when (mode) {
                "gps_position" -> trf("📍 por distancia, cada %1\$s m", cfg.optString("gps_param"))
                "timelapse" -> trf("⏱ por tiempo, cada %1\$s s", cfg.optString("timelapse_param"))
                "single" -> tr("foto suelta (manual)")
                "auto" -> trf("automático por solape (%1\$s %%)", cfg.optString("overlap_param"))
                "" -> "?"
                else -> mode
            })
            val mask = cfg.optInt("sensors_mask", -1)
            if (mask >= 0) {
                val names = listOf("RGB", "verde", "rojo", tr("red edge"), "infrarrojo")
                val on = names.filterIndexed { i, _ -> mask and (1 shl i) != 0 }
                l.add(trf("Cámaras: %1\$s", if (on.size == 5) "las 5" else on.joinToString(", ").ifEmpty { "ninguna" }))
            }
            try {
                l.add(when (get("/capture").optString("capture")) {
                    "running" -> tr("Estado: ✔ CAPTURANDO / armada")
                    "ready" -> tr("Estado: parada (no hará fotos)")
                    else -> tr("Estado: ?")
                })
            } catch (_: Throwable) {}
            try { l.add(if (get("/gpsfix").optString("fix") == "yes") tr("GPS: con posición") else tr("GPS: sin posición")) } catch (_: Throwable) {}
            try {
                val sd = get("/storage").optJSONObject("sd")
                l.add(if (sd != null) trf("SD: %1\$s GB libres", "%.1f".format(sd.optDouble("free") / 1073741824.0)) else tr("SD: no hay (memoria interna)"))
            } catch (_: Throwable) {}
            Crumbs.write("sequoia config: $cfg")
            return Report(true, l)
        }

        /** Bloqueante. Para la captura y devuelve lo que se sepa. */
        fun stop(): Report {
            val l = ArrayList<String>()
            try {
                get("/capture/stop"); Thread.sleep(800)
                val st = get("/capture").optString("capture")
                l.add(if (st == "running") tr("⚠ Sigue capturando") else tr("✔ Captura parada"))
                if (st != "running") return Report(true, l, "stopped")
            } catch (t: Throwable) { l.add(trf("✘ La Sequoia no responde: %1\$s", t.message)); return Report(false, l) }
            return Report(true, l)
        }
    }
}
