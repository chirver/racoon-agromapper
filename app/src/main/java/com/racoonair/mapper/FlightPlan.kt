package com.racoonair.mapper

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Plan de vuelo en formato Parrot "QGC WPL 120" (fichero flightPlan.mavlink).
 * Solo lee y valida; la generación llega en la v0.2.
 */
class FlightPlan private constructor(val text: String, val items: List<Item>) {

    data class Item(
        val index: Int, val frame: Int, val command: Int,
        val p1: Double, val p2: Double, val p3: Double, val p4: Double,
        val lat: Double, val lon: Double, val alt: Double
    )

    val waypoints get() = items.filter { it.command == CMD_WAYPOINT }

    /** Punto de aterrizaje (último cmd 21), si existe. */
    val landPoint: Item? get() = items.lastOrNull { it.command == CMD_LAND }
    val takeoffPoint: Item? get() = items.firstOrNull { it.command == CMD_TAKEOFF }

    /** Casa con coordenadas reales (aterrizaje o despegue); null si el plan aterriza "donde esté" (0,0). */
    val homePoint: Item? get() = listOfNotNull(landPoint, takeoffPoint).firstOrNull { !(it.lat == 0.0 && it.lon == 0.0) }

    /** Índices (en items) de cada inicio de captura: una por pasada. */
    val passStarts: List<Int> get() = items.indices.filter { items[it].command == CMD_IMAGE_START }

    /**
     * Pasada por la que hay que reanudar dado el último ítem ejecutado por el dron (índice del fichero),
     * o null si el plan ya se completó. Si la pasada en curso no llegó a cerrar su captura, se repite entera.
     */
    fun resumePass(lastExecuted: Int): Int? {
        val starts = passStarts
        if (starts.isEmpty()) return null
        for ((k, si) in starts.withIndex()) {
            val stop = (si + 1 until items.size).firstOrNull { items[it].command == CMD_IMAGE_STOP } ?: items.size
            if (items[si].index > lastExecuted || items.getOrNull(stop)?.index ?: Int.MAX_VALUE > lastExecuted) return k
        }
        return null
    }

    /**
     * Plan para continuar desde la pasada [k] (0-based): cabecera original (sensores, modo de disparo,
     * subida, despegue, tránsito) + desde el waypoint de inicio de esa pasada hasta el final, con la
     * velocidad de captura restablecida. La casa se mueve después con withHome().
     */
    fun resumeFromPass(k: Int): FlightPlan {
        val starts = passStarts
        require(k in starts.indices) { tr("Pasada fuera de rango") }
        val firstWp = items.indexOfFirst { it.command == CMD_WAYPOINT }
        require(firstWp > 0) { tr("Plan sin waypoints") }
        val header = items.subList(0, firstWp)
        val capIdx = starts[k]
        val startWp = (capIdx - 1 downTo 0).first { items[it].command == CMD_WAYPOINT }
        // Velocidad de captura: el primer cambio de velocidad posterior al primer waypoint (como en Pix4D).
        val surveySpeed = items.drop(firstWp).firstOrNull { it.command == CMD_CHANGE_SPEED }
        val out = ArrayList<Item>(header)
        out.add(items[startWp])
        if (k > 0) {
            // Igual que la primera pasada de Pix4D: pausa de 1 s tras el tránsito y velocidad de captura.
            items.firstOrNull { it.command == CMD_DELAY }?.let { out.add(it) }
            if (surveySpeed != null) out.add(surveySpeed)
        }
        out.addAll(items.subList(startWp + 1, items.size))
        val sb = StringBuilder("QGC WPL 120\n")
        for ((i, it) in out.withIndex()) {
            sb.append(String.format(java.util.Locale.ROOT, "%d\t0\t%d\t%d\t%f\t%f\t%f\t%f\t%f\t%f\t%f\t1\n",
                i, it.frame, it.command, it.p1, it.p2, it.p3, it.p4, it.lat, it.lon, it.alt))
        }
        return parse(sb.toString())
    }

    /**
     * Devuelve una copia con la casa movida a (lat, lon): despegue (22), aterrizaje (21) y el waypoint
     * de vuelta justo antes del aterrizaje. Así un plan guardado aterriza donde está el dron HOY.
     */
    fun withHome(lat: Double, lon: Double): FlightPlan {
        val landIdx = items.indexOfLast { it.command == CMD_LAND }
        val returnIdx = if (landIdx > 0) (landIdx - 1 downTo 0).firstOrNull { items[it].command == CMD_WAYPOINT } ?: -1 else -1
        val oldLand = landPoint
        // Misiones 3D: el primer waypoint tras el despegue es la subida vertical sobre la casa; se mueve con ella.
        val toIdx = items.indexOfFirst { it.command == CMD_TAKEOFF }
        val climbIdx = if (toIdx >= 0) (toIdx + 1 until items.size).firstOrNull { items[it].command == CMD_WAYPOINT } ?: -1 else -1
        val moved = items.mapIndexed { i, it ->
            val isReturnWp = i == returnIdx && oldLand != null &&
                haversine(it.lat, it.lon, oldLand.lat, oldLand.lon) < 3.0
            val isClimbWp = i == climbIdx && haversine(it.lat, it.lon, items[toIdx].lat, items[toIdx].lon) < 3.0
            if (it.command == CMD_TAKEOFF || it.command == CMD_LAND || isReturnWp || isClimbWp) it.copy(lat = lat, lon = lon) else it
        }
        val sb = StringBuilder("QGC WPL 120\n")
        for ((i, it) in moved.withIndex()) {
            sb.append(String.format(java.util.Locale.ROOT, "%d\t0\t%d\t%d\t%f\t%f\t%f\t%f\t%f\t%f\t%f\t1\n",
                i, it.frame, it.command, it.p1, it.p2, it.p3, it.p4, it.lat, it.lon, it.alt))
        }
        return parse(sb.toString())
    }
    val hasTakeoff get() = items.any { it.command == CMD_TAKEOFF }

    /**
     * Plan de MULTIRROTOR (el que genera este planificador): esperas en el sitio (112) o despegue y aterrizaje
     * verticales en el mismo punto. No vale para un ala fija como la Disco.
     */
    /** Plan de ala fija (Disco): sin despegue ni esperas, con aterrizaje. */
    val isFixedWing: Boolean get() {
        if (hasTakeoff || items.any { it.command == CMD_DELAY }) return false
        val li = items.indexOfLast { it.command == CMD_LAND }
        if (li < 1) return false
        val land = items[li]
        if (land.lat == 0.0 && land.lon == 0.0) return false
        // Aproximación de Disco: el último waypoint antes del LAND con radio (p3), a 100–450 m y bajo.
        val ap = items.subList(0, li).lastOrNull { it.command == CMD_WAYPOINT } ?: return false
        val d = haversine(ap.lat, ap.lon, land.lat, land.lon)
        return ap.p3 > 0 && d in 100.0..450.0 && ap.alt - land.alt in 10.0..70.0
    }

    val looksMultirotor: Boolean get() {
        if (items.any { it.command == CMD_DELAY }) return true
        val t = takeoffPoint; val l = landPoint
        return t != null && l != null && !(t.lat == 0.0 && t.lon == 0.0) && !(l.lat == 0.0 && l.lon == 0.0) &&
            haversine(t.lat, t.lon, l.lat, l.lon) < 30.0
    }

    /** Duración aproximada del plan completo en calma (min): misma fórmula que la estimación en vuelo. */
    fun estimatedMinutes(): Double {
        val wps = waypoints
        if (wps.isEmpty()) return 0.0
        var len = 0.0
        var prev = homePoint?.let { doubleArrayOf(it.lat, it.lon) } ?: doubleArrayOf(wps[0].lat, wps[0].lon)
        for (w in wps) { len += haversine(prev[0], prev[1], w.lat, w.lon); prev = doubleArrayOf(w.lat, w.lon) }
        homePoint?.let { len += haversine(prev[0], prev[1], it.lat, it.lon) }
        return (len / 7.0 + wps.size * 6.0) / 60.0 + 1.0 // +1 min de despegue y aterrizaje
    }

    val maxAltitude: Double get() = waypoints.maxOfOrNull { it.alt } ?: 0.0
    val hasLand get() = items.any { it.command == CMD_LAND }
    val captureStarts get() = items.count { it.command == CMD_IMAGE_START }
    val captureStops get() = items.count { it.command == CMD_IMAGE_STOP }
    val maxAlt get() = items.filter { it.command in POSITIONAL }.maxOfOrNull { it.alt } ?: 0.0
    val minWaypointAlt get() = waypoints.minOfOrNull { it.alt } ?: 0.0

    /** Distancia de disparo de la Sequoia (cmd 50001, p2 en cm), si existe. */
    val sequoiaTriggerMeters: Double?
        get() = if (items.none { it.command == CMD_PARROT_PHOTO_SENSORS }) null // solo planes Sequoia (cm)
                else items.firstOrNull { it.command == CMD_PARROT_PICTURE_MODE && it.p1 == 1.0 }?.p2?.div(100.0)

    /** Intervalo de disparo por tiempo (planes ANAFI: 50001 modo 0), si existe. */
    val timelapseSeconds: Double?
        get() = items.firstOrNull { it.command == CMD_PARROT_PICTURE_MODE && it.p1 == 0.0 }?.p2

    /** Plan para ANAFI (cámara con gimbal: lleva orden 205) o para Bluegrass/Disco. */
    val platform: Platform get() = when {
        items.any { it.command == CMD_MOUNT_CONTROL } || timelapseSeconds != null -> Platform.ANAFI
        items.any { it.command == CMD_PARROT_PHOTO_SENSORS } -> Platform.BLUEGRASS_SEQUOIA // 50002 = sensores Sequoia
        items.any { it.command == CMD_PARROT_PICTURE_MODE } -> Platform.ANAFI           // GPS-lapse sin Sequoia (Pix4D/ANAFI)
        else -> Platform.BLUEGRASS_SEQUOIA
    }

    /** Longitud de la ruta entre waypoints, en metros. */
    val pathLengthMeters: Double
        get() {
            val wp = waypoints
            var d = 0.0
            for (i in 1 until wp.size) d += haversine(wp[i - 1].lat, wp[i - 1].lon, wp[i].lat, wp[i].lon)
            return d
        }

    fun summary(): String {
        val sb = StringBuilder()
        sb.append(trf("Ítems: %1\$s  ·  Waypoints: %2\$s\n", items.size, waypoints.size))
        sb.append(trf("Altura máx: %1\$s m  ·  mín WP: %2\$s m\n", "%.0f".format(maxAlt), "%.0f".format(minWaypointAlt)))
        sb.append(trf("Ruta: %1\$s m\n", "%.0f".format(pathLengthMeters)))
        sb.append(trf("Despegue: %1\$s  ·  Aterrizaje: %2\$s\n", if (hasTakeoff) "sí" else "NO", if (hasLand) "sí" else "NO"))
        sb.append(trf("Pasadas con fotos: %1\$s inicio / %2\$s fin", captureStarts, captureStops))
        sequoiaTriggerMeters?.let { sb.append(trf("  ·  disparo cada %1\$s m", "%.1f".format(it))) }
        timelapseSeconds?.let { sb.append(trf("  ·  foto cada %1\$s s", "%.1f".format(it))) }
        waypoints.firstOrNull()?.let { sb.append(trf("\nPrimer WP: %1\$s, %2\$s", "%.6f".format(it.lat), "%.6f".format(it.lon))) }
        return sb.toString()
    }

    /** Avisos de seguridad (no bloquean, pero se muestran antes de volar). */
    fun warnings(): List<String> {
        val w = ArrayList<String>()
        if (waypoints.isEmpty()) w.add(tr("El plan no tiene waypoints."))
        if (!hasLand) w.add(tr("El plan no termina en aterrizaje (cmd 21)."))
        if (maxAlt > 120.0) w.add(trf("Altura máxima %1\$s m: supera 120 m.", "%.0f".format(maxAlt)))
        if (waypoints.isNotEmpty() && minWaypointAlt < 10.0) w.add(tr("Hay waypoints por debajo de 10 m."))
        if (captureStarts != captureStops) w.add(tr("Inicios/fin de captura desparejados."))
        if (items.any { it.frame != 3 && it.command in POSITIONAL }) w.add(tr("Hay ítems con frame distinto de 3 (altura relativa)."))
        return w
    }

    companion object {
        const val CMD_WAYPOINT = 16
        const val CMD_LAND = 21
        const val CMD_TAKEOFF = 22
        const val CMD_DELAY = 112
        const val CMD_CHANGE_SPEED = 178
        const val CMD_IMAGE_START = 2000
        const val CMD_IMAGE_STOP = 2001
        const val CMD_MOUNT_CONTROL = 205
        const val CMD_SET_ROI = 201
        const val CMD_PARROT_VIEW_MODE = 50000
        const val CMD_PARROT_PICTURE_MODE = 50001
        const val CMD_PARROT_PHOTO_SENSORS = 50002
        private val POSITIONAL = setOf(CMD_WAYPOINT, CMD_TAKEOFF, CMD_LAND)

        /** Lanza IllegalArgumentException con el motivo si el fichero no es válido. */
        fun parse(raw: String): FlightPlan {
            val text = raw.replace("\r\n", "\n").replace('\r', '\n')
            val lines = text.split('\n').map { it.trim() }.filter { it.isNotEmpty() }
            require(lines.isNotEmpty()) { tr("Fichero vacío") }
            require(lines[0].startsWith("QGC WPL")) { trf("No es un plan QGC WPL (primera línea: '%1\$s')", lines[0].take(30)) }
            val items = ArrayList<Item>()
            for ((n, line) in lines.drop(1).withIndex()) {
                val f = line.split('\t', ' ').filter { it.isNotEmpty() }
                require(f.size >= 12) { trf("Línea %1\$s: %2\$s columnas, se esperaban 12", n + 2, f.size) }
                fun d(i: Int): Double {
                    val v = f[i].toDoubleOrNull()
                    if (v == null || !v.isFinite()) throw IllegalArgumentException(trf("Línea %1\$s: valor no válido '%2\$s'", n + 2, f[i]))
                    return v
                }
                val it = Item(d(0).toInt(), d(2).toInt(), d(3).toInt(), d(4), d(5), d(6), d(7), d(8), d(9), d(10))
                if (it.command in POSITIONAL) {
                    require(it.lat in -90.0..90.0 && it.lon in -180.0..180.0) { trf("Línea %1\$s: coordenadas fuera de rango", n + 2) }
                    require(it.alt in 0.0..150.0) { trf("Línea %1\$s: altura %2\$s m fuera de rango", n + 2, it.alt) }
                    // Solo los waypoints necesitan coordenadas: GroundSDK escribe despegue/aterrizaje con ceros.
                    if (it.command == CMD_WAYPOINT) require(!(it.lat == 0.0 && it.lon == 0.0)) { trf("Línea %1\$s: waypoint en 0,0", n + 2) }
                }
                items.add(it)
            }
            require(items.isNotEmpty()) { tr("El plan no tiene ítems") }
            return FlightPlan(text.trimEnd() + "\n", items)
        }

        fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
            val r = 6371000.0
            val dLat = Math.toRadians(lat2 - lat1)
            val dLon = Math.toRadians(lon2 - lon1)
            val a = sin(dLat / 2) * sin(dLat / 2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2) * sin(dLon / 2)
            return 2 * r * atan2(sqrt(a), sqrt(1 - a))
        }
    }
}
