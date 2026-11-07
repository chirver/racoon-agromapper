package com.racoonair.mapper

import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Límites de cada dron. Viento máximo: manuales de Parrot (Bluegrass «no volar con más de 8 m/s»;
 * Disco-Pro AG «no volar con viento de más de 12 m/s o 43 km/h»; Disco 0–40 °C).
 * El resto son criterios de la app (prudentes) y se dicen como tales.
 */
enum class Airframe(
    val label: String,
    /** Viento máximo del fabricante, m/s. */
    val maxWind: Double,
    /** Ámbar a partir de este viento medio, m/s. Criterio de la app. */
    val windAmber: Double,
    /** Rojo a partir de este viento medio, m/s. */
    val windRed: Double,
    /** Ráfaga a partir de la cual es rojo. */
    val gustRed: Double,
    val tempMin: Double, val tempMax: Double,
    /** Autonomía nominal (min) y la parte usable hasta la vuelta a casa por batería. */
    val enduranceMin: Double,
    /** Velocidad de crucero (m/s); solo ala fija. */
    val cruise: Double?,
    val fixedWing: Boolean
) {
    // Bluegrass: 25 min (Parrot). Temperatura: Parrot no la publica para la Bluegrass → 0–40 °C como la Disco.
    BLUEGRASS("Bluegrass (multirrotor)", 8.0, 6.0, 8.0, 10.0, 0.0, 40.0, 25.0, null, false),
    // Disco: 45 min (Parrot). Crucero 40–50 km/h → 12,5 m/s. Rojo antes del límite del fabricante (12 m/s),
    // porque con 10 m/s de cara el avance contra el viento es de ~2,5 m/s y la vuelta se eterniza.
    DISCO("Disco (ala fija)", 12.0, 7.0, 10.0, 12.0, 0.0, 40.0, 45.0, 12.5, true);

    /**
     * Cuánto se alarga el vuelo con viento W (m/s) respecto a la calma.
     * Ala fija (física): ida y vuelta a velocidad de aire V → V²/(V²−W²). Multirrotor: estimación de la app,
     * +40 % al límite de 8 m/s (más inclinación = más consumo), sin datos de Parrot.
     */
    fun windTimeFactor(w: Double): Double {
        val v = cruise
        return if (v != null) { if (w >= v * 0.97) Double.POSITIVE_INFINITY else v * v / (v * v - w * w) }
        else 1.0 + 0.4 * (w / maxWind) * (w / maxWind)
    }
}

object Weather {

    data class Hour(
        val epochS: Long, val label: String,
        val temp: Double, val precipProb: Double, val precip: Double, val code: Int, val visibility: Double,
        val wind10: Double, val wind80: Double, val wind120: Double, val dir10: Double, val gust10: Double
    ) {
        /** Viento a la altura de vuelo (interpolando 10/80/120 m). */
        fun windAt(alt: Double): Double = when {
            alt <= 10 -> wind10
            wind80.isNaN() -> if (wind10.isNaN()) Double.NaN else wind10 * Math.pow(alt / 10.0, 0.14) // ley potencial (campo abierto)
            alt <= 80 -> wind10 + (wind80 - wind10) * (alt - 10) / 70
            wind120.isNaN() -> wind80
            else -> wind80 + (wind120 - wind80) * ((alt - 80) / 40).coerceAtMost(1.0)
        }
        /** Ráfaga en altura: el viento medio en altura más el mismo exceso de ráfaga que a 10 m
         *  (la turbulencia relativa baja con la altura, así que escalar la proporción exageraría). */
        fun gustAt(alt: Double): Double {
            if (gust10.isNaN()) return Double.NaN
            val w = windAt(alt)
            return if (w.isNaN()) gust10 else max(gust10, w + max(0.0, gust10 - wind10))
        }
    }

    data class Kp(val startEpochS: Long, val kp: Double, val observed: Boolean)

    data class Report(val lat: Double, val lon: Double, val hours: List<Hour>, val kp: List<Kp>, val fetchedAt: Long,
                      val kpError: String?) {
        fun kpAt(epochS: Long): Kp? = kp.lastOrNull { it.startEpochS <= epochS && epochS < it.startEpochS + 3 * 3600 }
    }

    data class Verdict(val level: Int, val reasons: List<String>, val windAlt: Double, val gustAlt: Double, val timeFactor: Double) {
        val icon get() = when (level) { 0 -> "🟢"; 1 -> "🟠"; else -> "🔴" }
        val word get() = when (level) { 0 -> "VOLABLE"; 1 -> "PRECAUCIÓN"; else -> "NO VOLAR" }
    }

    // ---------------------------------------------------------------- descarga

    fun meteoUrl(lat: Double, lon: Double) = String.format(Locale.ROOT,
        "https://api.open-meteo.com/v1/forecast?latitude=%.4f&longitude=%.4f" +
            "&hourly=temperature_2m,precipitation_probability,precipitation,weather_code,visibility," +
            "wind_speed_10m,wind_speed_80m,wind_speed_120m,wind_direction_10m,wind_gusts_10m" +
            "&wind_speed_unit=ms&timezone=auto&timeformat=unixtime&forecast_days=2", lat, lon)

    const val KP_URL = "https://services.swpc.noaa.gov/products/noaa-planetary-k-index-forecast.json"

    /** Bloqueante. La meteo es obligatoria; el Kp, si falla, se informa pero no impide el informe. */
    fun fetch(lat: Double, lon: Double): Report {
        val hours = parseMeteo(Net.getText(meteoUrl(lat, lon)))
        var kpErr: String? = null
        val kp = try { parseKp(Net.getText(KP_URL)) } catch (t: Throwable) { kpErr = t.message ?: t.javaClass.simpleName; emptyList() }
        return Report(lat, lon, hours, kp, System.currentTimeMillis(), kpErr)
    }

    fun parseMeteo(json: String): List<Hour> {
        val o = JSONObject(json)
        val off = ZoneOffset.ofTotalSeconds(o.optInt("utc_offset_seconds", 0))
        val h = o.getJSONObject("hourly")
        val time = h.getJSONArray("time")
        fun arr(k: String) = h.optJSONArray(k)
        fun v(a: JSONArray?, i: Int) = if (a == null || a.isNull(i)) Double.NaN else a.optDouble(i, Double.NaN)
        val t2 = arr("temperature_2m"); val pp = arr("precipitation_probability"); val pr = arr("precipitation")
        val wc = arr("weather_code"); val vis = arr("visibility")
        val w10 = arr("wind_speed_10m"); val w80 = arr("wind_speed_80m"); val w120 = arr("wind_speed_120m")
        val d10 = arr("wind_direction_10m"); val g10 = arr("wind_gusts_10m")
        val out = ArrayList<Hour>()
        val tz = try { java.time.ZoneId.of(o.optString("timezone", "")) } catch (_: Throwable) { off }
        val fmt = DateTimeFormatter.ofPattern("EEE HH'h'", Locale("es", "ES"))
        for (i in 0 until time.length()) {
            // timeformat=unixtime → segundos UTC (bien también el día del cambio de hora); si viene como texto, hora local.
            val raw = time.get(i)
            val epoch = if (raw is Number) raw.toLong() else LocalDateTime.parse(raw.toString()).toEpochSecond(off)
            val label = java.time.Instant.ofEpochSecond(epoch).atZone(tz).format(fmt)
            out.add(Hour(epoch, label,
                v(t2, i), v(pp, i), v(pr, i), v(wc, i).let { if (it.isNaN()) -1 else it.toInt() }, v(vis, i),
                v(w10, i), v(w80, i), v(w120, i), v(d10, i), v(g10, i)))
        }
        return out
    }

    /** NOAA: admite tanto el formato antiguo (lista de listas con cabecera) como lista de objetos. */
    fun parseKp(json: String): List<Kp> {
        val a = JSONArray(json)
        val out = ArrayList<Kp>()
        if (a.length() == 0) return out
        fun epoch(s: String): Long? = try {
            LocalDateTime.parse(s.trim().replace(' ', 'T').removeSuffix("Z").substringBefore('.')).toEpochSecond(ZoneOffset.UTC)
        } catch (_: Throwable) { null }
        if (a.opt(0) is JSONArray) {
            val head = a.getJSONArray(0)
            var it = -1; var ik = -1; var io = -1
            for (i in 0 until head.length()) when (head.optString(i).lowercase(Locale.ROOT)) {
                "time_tag" -> it = i; "kp" -> ik = i; "observed" -> io = i
            }
            if (it < 0 || ik < 0) throw IllegalArgumentException("formato Kp desconocido")
            for (r in 1 until a.length()) {
                val row = a.optJSONArray(r) ?: continue
                val e = epoch(row.optString(it)) ?: continue
                val k = row.optString(ik).toDoubleOrNull() ?: continue
                out.add(Kp(e, k, io >= 0 && row.optString(io).lowercase(Locale.ROOT) == "observed"))
            }
        } else {
            for (r in 0 until a.length()) {
                val o = a.optJSONObject(r) ?: continue
                val e = epoch(o.optString("time_tag")) ?: continue
                val k = (o.opt("kp") ?: o.opt("Kp") ?: o.opt("kp_index") ?: o.opt("estimated_kp"))?.toString()?.toDoubleOrNull() ?: continue
                out.add(Kp(e, k, o.optString("observed").lowercase(Locale.ROOT) == "observed"))
            }
        }
        return out.sortedBy { it.startEpochS }
    }

    // ---------------------------------------------------------------- evaluación

    /**
     * Semáforo para una hora. [planMin] = duración del plan en calma (null si no hay plan).
     * 0 verde, 1 ámbar (vuela con cuidado / reduce el plan), 2 rojo (no volar).
     */
    fun evaluate(h: Hour, kp: Kp?, a: Airframe, altM: Double, planMin: Double?): Verdict {
        var level = 0
        val r = ArrayList<String>()
        fun add(l: Int, s: String) { level = max(level, l); r.add((if (l == 2) "⛔ " else if (l == 1) "⚠ " else "✔ ") + s) }

        val w = h.windAt(altM); val g = h.gustAt(altM)
        val wTxt = "Viento a ${altM.roundToInt()} m: ${f1(w)} m/s (${(w * 3.6).roundToInt()} km/h)" +
            (if (!g.isNaN()) ", ráfagas ${f1(g)} m/s" else "")
        when {
            w.isNaN() -> add(1, "Sin dato de viento")
            w > a.windRed -> add(2, "$wTxt: por encima del límite (${f1(a.windRed)} m/s)")
            !g.isNaN() && g > a.gustRed -> add(2, "$wTxt: ráfagas por encima de ${f1(a.gustRed)} m/s")
            w > a.windAmber -> add(1, "$wTxt: fuerte para la ${a.label.substringBefore(' ')}")
            !g.isNaN() && g > a.windRed -> add(1, "$wTxt: ráfagas fuertes")
            else -> add(0, wTxt)
        }
        val tf = if (w.isNaN()) 1.0 else a.windTimeFactor(w)
        if (a.fixedWing && !w.isNaN()) {
            val v = a.cruise!!
            val up = v - w
            if (up < 3.0) add(2, "Contra el viento avanzaría a ${f1(max(0.0, up))} m/s: puede no volver")
            else if (up < 5.0) add(1, "Contra el viento solo avanza a ${f1(up)} m/s (crucero ~${f1(v)} m/s)")
            if (!h.dir10.isNaN()) add(0, "Lanzar y aterrizar de cara al viento: mirando al ${rumbo(h.dir10)} (${h.dir10.roundToInt()}°)")
        }
        if (planMin != null && planMin > 0) {
            val usable = a.enduranceMin * 0.7 // hasta el ~30 % de batería
            val need = planMin * tf
            val ratio = need / usable
            val txt = if (need.isInfinite()) "Con este viento el plan no se puede completar"
                else "Plan ≈${f1(planMin)} min en calma → ≈${f1(need)} min con este viento (usable ≈${usable.roundToInt()} min hasta el 30 %)"
            when { ratio > 1.0 -> add(2, "$txt: no da una batería, divide la zona"); ratio > 0.85 -> add(1, "$txt: muy justo"); else -> add(0, txt) }
        }
        // Datos que faltan: nunca verde por omisión.
        val missing = ArrayList<String>()
        if (h.gust10.isNaN()) missing.add("ráfagas")
        if (h.precip.isNaN() && h.precipProb.isNaN()) missing.add("lluvia")
        if (h.code < 0) missing.add("tiempo (tormenta/niebla)")
        if (h.temp.isNaN()) missing.add("temperatura")
        if (h.visibility.isNaN()) missing.add("visibilidad")
        if (missing.isNotEmpty()) add(1, "Sin dato de " + missing.joinToString(", "))
        when {
            h.code in 95..99 -> add(2, "Tormenta prevista")
            h.code in setOf(45, 48) -> add(2, "Niebla")
            (h.code in 71..77 || h.code in 85..86) -> add(2, "Nieve")
        }
        when {
            !h.precip.isNaN() && h.precip >= 0.2 -> add(2, "Lluvia prevista (${f1(h.precip)} mm)")
            !h.precipProb.isNaN() && h.precipProb >= 60 -> add(2, "Probabilidad de lluvia ${h.precipProb.roundToInt()} %")
            !h.precipProb.isNaN() && h.precipProb >= 30 -> add(1, "Probabilidad de lluvia ${h.precipProb.roundToInt()} %")
        }
        if (!h.temp.isNaN()) when {
            h.temp < a.tempMin || h.temp > a.tempMax -> add(2, "Temperatura ${f1(h.temp)} °C fuera de ${a.tempMin.roundToInt()}–${a.tempMax.roundToInt()} °C")
            h.temp < 5 -> add(1, "Frío (${f1(h.temp)} °C): la LiPo rinde menos, calienta las baterías")
            h.temp > 35 -> add(1, "Calor (${f1(h.temp)} °C): vigila la temperatura de batería")
        }
        if (!h.visibility.isNaN()) when {
            h.visibility < 1000 -> add(2, "Visibilidad ${(h.visibility / 1000).let { f1(it) }} km")
            h.visibility < 3000 -> add(1, "Visibilidad reducida (${f1(h.visibility / 1000)} km)")
        }
        if (kp == null) add(1, "Kp (tormenta geomagnética) no disponible")
        else when {
            kp.kp >= 5 -> add(2, "Kp ${f1(kp.kp)}: tormenta geomagnética, el GPS puede fallar")
            kp.kp >= 4 -> add(1, "Kp ${f1(kp.kp)}: actividad geomagnética, GPS menos fiable")
            else -> add(0, "Kp ${f1(kp.kp)}: GPS sin problemas")
        }
        return Verdict(level, r, w, g, tf)
    }

    fun rumbo(deg: Double): String {
        val n = arrayOf("norte", "nordeste", "este", "sudeste", "sur", "sudoeste", "oeste", "noroeste")
        return n[(((deg % 360) + 360) % 360 / 45.0).roundToInt() % 8]
    }

    private fun f1(v: Double) = String.format(Locale("es", "ES"), "%.1f", v)
}
