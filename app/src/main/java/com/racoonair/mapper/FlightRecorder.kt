package com.racoonair.mapper

import android.content.Context
import android.os.SystemClock
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Registro de cada vuelo (1 muestra/s) en planes/vuelos/AAAAMMDD-HHMMSS.csv,
 * y estimación de autonomía a partir de la descarga real de la batería.
 */
object FlightRecorder {
    private var file: File? = null
    private var t0 = 0L
    private var lastSample = 0L
    private var landedSince = 0L
    private var lastItem = -1
    private var lastPlanState = -1
    private var linkLostLogged = false

    // Descarga de batería en vuelo: (segundos, %)
    private var batStart: Pair<Double, Int>? = null
    private var batLast: Pair<Double, Int>? = null

    val recording get() = file != null
    val elapsedS get() = if (file == null) 0.0 else (SystemClock.elapsedRealtime() - t0) / 1000.0

    private fun dir(ctx: Context) = File(PlanStore.dir(ctx), "vuelos").also { it.mkdirs() }

    fun lastFlight(ctx: Context): File? = dir(ctx).listFiles { f -> f.name.endsWith(".csv") }?.maxByOrNull { it.lastModified() }

    /** Llamar con cada actualización de telemetría (hilo principal). */
    fun update(ctx: Context, t: DroneLink.Telemetry, planName: String) {
        val airborne = t.flyingStateRaw in 1..4 || t.flyingStateRaw in 7..8
        val now = SystemClock.elapsedRealtime()
        if (file == null) {
            if (!airborne) return
            val name = SimpleDateFormat("yyyyMMdd-HHmmss", Locale.ROOT).format(Date())
            file = File(dir(ctx), "$name.csv").also {
                it.writeText("# Racoon AgroMapper · vuelo $name · plan: $planName\n" +
                    "t_s,lat,lon,alt_m,vel_ms,bateria,rumbo,estado_vuelo,estado_plan,item,alerta,evento\n")
            }
            t0 = now; lastSample = 0; landedSince = 0; lastItem = -1; lastPlanState = -1
            batStart = null; batLast = null
            event(t, "despegue")
        }
        // Eventos: cambio de ítem de misión o de estado del plan, con su segundo exacto.
        if (t.missionItem != lastItem && t.missionItem >= 0) { lastItem = t.missionItem; event(t, "item ${t.missionItem}") }
        if (t.planStateRaw != lastPlanState) { lastPlanState = t.planStateRaw; event(t, "plan ${t.planState}") }
        if (now - lastSample >= 1000) { lastSample = now; write(t, "") }
        if (t.battery in 0..100 && airborne) {
            val s = elapsedS
            if (batStart == null) batStart = Pair(s, t.battery)
            batLast = Pair(s, t.battery)
        }
        // Solo se cierra con aterrizaje CONFIRMADO (estado 0). Sin enlace el estado es desconocido (-1):
        // el vuelo sigue abierto y la app mantiene la alarma de enlace perdido.
        if (t.flyingStateRaw == 0) {
            if (landedSince == 0L) landedSince = now
            if (now - landedSince > 5000) { event(t, "aterrizado"); file = null }
        } else landedSince = 0
        if (t.flyingStateRaw == -1 && !linkLostLogged) { linkLostLogged = true; event(t, "enlace perdido") }
        if (t.flyingStateRaw >= 0) linkLostLogged = false
    }

    private fun event(t: DroneLink.Telemetry, e: String) = write(t, e)

    private fun write(t: DroneLink.Telemetry, ev: String) {
        val f = file ?: return
        fun d(v: Double, n: Int) = if (v.isNaN() || v == 500.0) "" else String.format(Locale.ROOT, "%.${n}f", v)
        val line = String.format(Locale.ROOT, "%.1f,%s,%s,%s,%s,%s,%s,%d,%d,%d,%d,%s\n",
            elapsedS, d(t.lat, 7), d(t.lon, 7), d(t.altitude, 1), d(t.speed, 1),
            if (t.battery >= 0) t.battery.toString() else "", d(t.yawDeg, 0),
            t.flyingStateRaw, t.planStateRaw, t.missionItem, t.alert, ev)
        try { FileWriter(f, true).use { it.write(line) } } catch (_: Throwable) {}
    }

    /** %/min de descarga medido en este vuelo, o null si aún no hay datos suficientes (≥2 min y ≥3 %). */
    fun drainPerMin(): Double? {
        val a = batStart ?: return null; val b = batLast ?: return null
        val dt = b.first - a.first; val db = a.second - b.second
        if (dt < 120 || db < 3) return null
        return db / (dt / 60.0)
    }

    /** Minutos de vuelo hasta que la batería llegue a [reserve] %. */
    fun minutesTo(reserve: Int, battery: Int): Double? {
        val r = drainPerMin() ?: return null
        return ((battery - reserve).coerceAtLeast(0)) / r
    }

    /** Resumen legible de un CSV de vuelo (duración, distancia, batería, velocidad media). */
    fun summarize(f: File): String {
        val rows = f.readLines().filter { it.isNotBlank() && !it.startsWith("#") && !it.startsWith("t_s") }.map { it.split(',') }
        if (rows.isEmpty()) return "Vuelo vacío"
        var dist = 0.0; var prev: LatLon? = null
        for (r in rows) {
            val la = r.getOrNull(1)?.toDoubleOrNull(); val lo = r.getOrNull(2)?.toDoubleOrNull()
            if (la != null && lo != null) { val p = LatLon(la, lo); prev?.let { dist += GridPlanner.distance(it, p) }; prev = p }
        }
        val dur = rows.last()[0].toDoubleOrNull() ?: 0.0
        val bats = rows.mapNotNull { it.getOrNull(5)?.toIntOrNull() }
        val events = rows.filter { it.getOrNull(11)?.isNotBlank() == true }.joinToString("\n") { "  ${it[0]} s: ${it[11]}" }
        return String.format(Locale.ROOT, "Duración %.1f min · recorrido %.0f m · velocidad media %.1f m/s · batería %s→%s %%\nEventos:\n%s",
            dur / 60, dist, if (dur > 0) dist / dur else 0.0, bats.firstOrNull() ?: "?", bats.lastOrNull() ?: "?", events)
    }
}
