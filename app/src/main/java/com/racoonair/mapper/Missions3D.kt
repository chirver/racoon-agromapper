package com.racoonair.mapper

import java.util.Locale
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt
import kotlin.math.tan

/** Tipos de misión. Las 3D son para la ANAFI (gimbal inclinable y apuntado a un punto). */
enum class MissionType(val label: String) {
    GRID(tr("Cuadrícula (mapa 2D)")),
    OBLIQUE(tr("Doble cuadrícula oblicua (3D)")),
    ORBIT(tr("Órbita alrededor de un objeto (3D)")),
    FACADE(tr("Fachada (rejilla vertical)"));
    val is3D get() = this != GRID
}

/** Parámetros de las misiones 3D. Alturas relativas al despegue. */
data class Spec3D(
    val type: MissionType = MissionType.GRID,
    /** Altura del objeto (edificio, estatua, pared) en m. */
    val objectHeight: Double = 15.0,
    /** Distancia mínima al objeto (m). */
    val standoff: Double = 15.0,
    /** Órbita: número de anillos (1–3). */
    val rings: Int = 2,
    /** Doble cuadrícula: inclinación del gimbal (−90 = vertical abajo). */
    val obliqueTilt: Double = -60.0,
    /** Fachada: altura de la fila más baja. */
    val minAlt: Double = 3.0,
    /** Velocidad de captura (m/s). */
    val speed: Double = 2.5,
    /** Desnivel: cota de la base del objeto menos cota del despegue (m; negativo si el objeto está más abajo). */
    val baseElev: Double = 0.0
)

/** Lo que el planificador necesita de cualquier plan (cuadrícula 2D o misión 3D). */
interface PlanResult {
    val home: LatLon
    val params: GridParams
    /** Tramos a dibujar en el mapa. */
    val passes: List<Pair<LatLon, LatLon>>
    val estimatedSeconds: Double
    val triggerDistance: Double
    val photos: Int
    fun summary(): String
    fun blockers(): List<String>
    fun warnings(): List<String>
    fun toMavlink(): String
}

/** Un punto de la ruta 3D. [tilt]: gimbal (−90 abajo, 0 horizontal). */
data class Wp3D(val p: LatLon, val alt: Double, val yaw: Double, val tilt: Double,
                val startCapture: Boolean = false, val stopCapture: Boolean = false)

class Plan3D(
    override val home: LatLon,
    override val params: GridParams,
    val spec: Spec3D,
    val wps: List<Wp3D>,
    /** Punto al que mira la cámara (órbita) y su altura. */
    val roi: LatLon?, val roiAlt: Double,
    /** Altura segura de ida y vuelta (por encima del objeto). */
    val safeAlt: Double,
    override val triggerDistance: Double,
    val captureSpeed: Double,
    override val photos: Int,
    private val info: String,
    private val extraBlockers: List<String>,
    private val extraWarnings: List<String>
) : PlanResult {
    companion object {
        const val ANAFI_USABLE_MIN = 18.0   // 25 min nominales, hasta el ~30 %
        const val TRANSIT = 8.0
        const val CLIMB = 2.5
    }

    override val passes: List<Pair<LatLon, LatLon>> get() {
        val out = ArrayList<Pair<LatLon, LatLon>>()
        var prev = home
        for (w in wps) { if (GridPlanner.distance(prev, w.p) > 0.3) out.add(Pair(prev, w.p)); prev = w.p }
        out.add(Pair(prev, home))
        return out
    }

    val pathLength: Double get() {
        var len = 0.0; var prevP = home; var prevA = safeAlt
        for (w in wps) {
            val h = GridPlanner.distance(prevP, w.p); val v = abs(w.alt - prevA)
            len += sqrt(h * h + v * v); prevP = w.p; prevA = w.alt
        }
        return len + GridPlanner.distance(prevP, home)
    }

    override val estimatedSeconds: Double get() {
        var capture = 0.0; var other = 0.0
        var capturing = false; var prevP = home; var prevA = safeAlt
        for (w in wps) {
            val h = GridPlanner.distance(prevP, w.p); val v = abs(w.alt - prevA)
            val t = h / (if (capturing) captureSpeed else TRANSIT) + v / CLIMB
            if (capturing) capture += t else other += t
            if (w.startCapture) capturing = true
            if (w.stopCapture) capturing = false
            prevP = w.p; prevA = w.alt
        }
        val last = wps.lastOrNull()
        other += GridPlanner.distance(prevP, home) / TRANSIT + safeAlt / CLIMB * 2 + wps.size * 0.5 +
            (if (last != null) abs(safeAlt - last.alt) / CLIMB else 0.0) + wps.count { it.startCapture } * 2.0
        return capture + other
    }

    override fun summary(): String = info + String.format(Locale.ROOT,
        tr("\nFoto cada %.1f m · ~%d fotos · vel. %.1f m/s · ruta %.0f m · tiempo estimado %.1f min"),
        triggerDistance, photos, captureSpeed, pathLength, estimatedSeconds / 60)

    override fun blockers(): List<String> {
        val b = ArrayList(extraBlockers)
        if (wps.any { it.alt > 120.0 } || safeAlt > 120.0) b.add(tr("Altura máxima 120 m."))
        val ground = if (roi == null && spec.type == MissionType.OBLIQUE) 0.0 else spec.baseElev
        if (wps.any { it.alt - ground < 2.0 }) b.add(tr("Algún punto queda a menos de 2 m del suelo."))
        if (wps.any { it.alt < 1.0 }) b.add(tr("Parte de la misión queda por debajo del punto de despegue: despega a la altura de la base del objeto o más abajo."))
        if (estimatedSeconds > ANAFI_USABLE_MIN * 60) b.add(trf("Vuelo estimado de %1\$s min: no cabe en una batería de ANAFI. Reduce anillos o zona.", "%.0f".format(estimatedSeconds / 60)))
        if (captureSpeed * params.camera.minIntervalS * 1.05 > triggerDistance) b.add(tr("La cámara no da abasto a esa velocidad: baja la velocidad o el solape."))
        return b
    }

    override fun warnings(): List<String> {
        val w = ArrayList(extraWarnings)
        w.add(tr("La ANAFI no esquiva obstáculos: revisa árboles, cables y farolas alrededor del objeto."))
        if (estimatedSeconds > 0.85 * ANAFI_USABLE_MIN * 60) w.add(tr("Vuelo justo para una batería."))
        return w
    }

    /** Plan ANAFI (GroundSDK): mismas órdenes que generaba Pix4Dcapture para la ANAFI. */
    override fun toMavlink(): String {
        val sb = StringBuilder("QGC WPL 120\n")
        var i = 0
        fun item(cmd: Int, p1: Double = 0.0, p2: Double = 0.0, p3: Double = 0.0, p4: Double = 0.0,
                 lat: Double = 0.0, lon: Double = 0.0, alt: Double = 0.0) {
            sb.append(String.format(Locale.ROOT, "%d\t0\t3\t%d\t%f\t%f\t%f\t%f\t%f\t%f\t%f\t1\n",
                i++, cmd, p1, p2, p3, p4, lat, lon, alt))
        }
        val iv = max(params.camera.minIntervalS, triggerDistance / captureSpeed)
        val first = wps.first()
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = CLIMB)
        item(FlightPlan.CMD_TAKEOFF, lat = home.lat, lon = home.lon, alt = safeAlt)
        item(FlightPlan.CMD_MOUNT_CONTROL, p1 = first.tilt, alt = 2.0)
        if (roi != null) {
            item(FlightPlan.CMD_SET_ROI, p1 = 3.0, lat = roi.lat, lon = roi.lon, alt = roiAlt)
            item(FlightPlan.CMD_PARROT_VIEW_MODE, p1 = 2.0, p2 = 0.0)          // ROI: mirar al objeto
        } else item(FlightPlan.CMD_PARROT_VIEW_MODE, p1 = 0.0)                 // ABSOLUTE: rumbo de cada punto
        item(FlightPlan.CMD_PARROT_PICTURE_MODE, p1 = 0.0, p2 = iv)            // TIMELAPSE cada iv s
        // Subida vertical sobre el despegue antes de ir a ningún sitio (TAKEOFF solo deja el dron a ~1 m).
        item(FlightPlan.CMD_WAYPOINT, p2 = 2.0, lat = home.lat, lon = home.lon, alt = safeAlt)
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = TRANSIT)
        // Llegar por arriba (altura segura) a la vertical del primer punto y bajar.
        item(FlightPlan.CMD_WAYPOINT, p2 = 2.0, p4 = first.yaw, lat = first.p.lat, lon = first.p.lon, alt = safeAlt)
        var tilt = first.tilt
        var capturing = false
        for (w in wps) {
            if (abs(w.tilt - tilt) > 0.5) { item(FlightPlan.CMD_MOUNT_CONTROL, p1 = w.tilt, alt = 2.0); tilt = w.tilt }
            item(FlightPlan.CMD_WAYPOINT, p2 = 2.0, p4 = w.yaw, lat = w.p.lat, lon = w.p.lon, alt = w.alt)
            if (w.startCapture && !capturing) {
                item(FlightPlan.CMD_DELAY, p1 = 1.0)
                item(FlightPlan.CMD_CHANGE_SPEED, p2 = captureSpeed)
                item(FlightPlan.CMD_IMAGE_START, p1 = iv, p3 = 12.0)
                capturing = true
            }
            if (w.stopCapture && capturing) {
                item(FlightPlan.CMD_IMAGE_STOP)
                item(FlightPlan.CMD_DELAY, p1 = 1.0)
                item(FlightPlan.CMD_CHANGE_SPEED, p2 = TRANSIT)
                capturing = false
            }
        }
        if (capturing) item(FlightPlan.CMD_IMAGE_STOP)
        // Vuelta: subir a la altura segura en el sitio, volver y aterrizar.
        val last = wps.last()
        item(FlightPlan.CMD_PARROT_VIEW_MODE, p1 = 1.0)                         // CONTINUE
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = TRANSIT)
        item(FlightPlan.CMD_WAYPOINT, p2 = 2.0, lat = last.p.lat, lon = last.p.lon, alt = safeAlt)
        item(FlightPlan.CMD_MOUNT_CONTROL, p1 = -90.0, alt = 2.0)
        item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, lat = home.lat, lon = home.lon, alt = safeAlt)
        item(FlightPlan.CMD_LAND, lat = home.lat, lon = home.lon, alt = 0.0)
        return sb.toString()
    }
}

object Planner3D {

    fun centroid(z: List<LatLon>) = LatLon(z.map { it.lat }.average(), z.map { it.lon }.average())

    private fun speedFor(trigger: Double, cam: Camera, wanted: Double) = min(wanted, trigger / (cam.minIntervalS * 1.1)).coerceAtLeast(0.5)

    fun plan(zone: List<LatLon>, home: LatLon, p: GridParams, s: Spec3D): Plan3D {
        require(p.frontOverlap in 0.3..0.95 && p.sideOverlap in 0.3..0.95) { tr("Los solapes deben estar entre 30 y 95 %.") }
        return when (s.type) {
            MissionType.ORBIT -> orbit(zone, home, p, s)
            MissionType.OBLIQUE -> oblique(zone, home, p, s)
            MissionType.FACADE -> facade(zone, home, p, s)
            MissionType.GRID -> throw IllegalArgumentException("GRID")
        }
    }

    /** Altura segura: nunca por debajo de ningún punto de la misión. */
    private fun safeOf(base: Double, wps: List<Wp3D>) = max(base, wps.maxOfOrNull { it.alt } ?: base)
    private fun norm360(x: Double) = ((x % 360.0) + 360.0) % 360.0

    /**
     * Órbita: la zona dibujada es la planta del objeto. Anillos alrededor, a [Spec3D.standoff] del borde,
     * con la cámara apuntando al centro del objeto (a media altura).
     */
    fun orbit(zone: List<LatLon>, home: LatLon, p: GridParams, s: Spec3D): Plan3D {
        require(zone.size >= 3) { tr("Dibuja la planta del objeto (mínimo 3 puntos)") }
        val c = centroid(zone)
        val objR = zone.maxOf { GridPlanner.distance(c, it) }
        val r = objR + s.standoff
        val h = s.objectHeight
        val n = s.rings.coerceIn(1, 3)
        val top = h + r * tan(Math.toRadians(25.0))
        val bottom = min(max(s.minAlt, h * 0.35), top)
        val e = s.baseElev                                   // las alturas del objeto se miden desde su base
        val alts = if (n == 1) listOf(top) else (0 until n).map { k -> top - (top - bottom) * k / (n - 1) }
        val altsT = alts.map { it + e }                      // relativas al despegue
        val cam = p.camera
        val wps = ArrayList<Wp3D>()
        var trigger = Double.MAX_VALUE
        var photos = 0
        // Empezar por el lado del objeto que mira al despegue.
        val startBearing = GridPlanner.bearing(c, home)
        for ((k, alt) in alts.withIndex()) {
            val dz = alt - h / 2
            val slant = sqrt(r * r + dz * dz)
            val tilt = (-Math.toDegrees(atan2(dz, r))).coerceIn(-90.0, 30.0)
            // Solape medido en la cara cercana del objeto (a la distancia de seguridad), no en su centro.
            val near = sqrt(s.standoff * s.standoff + dz * dz).coerceAtMost(slant)
            val (fw, _) = cam.footprint(near)
            val arcStep = fw * (1 - p.frontOverlap) * r / s.standoff.coerceAtLeast(1.0)
            val nShots = ceil(2 * PI * r / arcStep).toInt().coerceIn(24, 90)
            trigger = min(trigger, 2 * PI * r / nShots)
            photos += nShots
            val steps = 36
            val dir = if (k % 2 == 0) 1 else -1            // anillos alternos en sentido contrario (sin enrollar)
            for (j in 0..steps) {
                val brg = startBearing + dir * 360.0 * j / steps
                val pt = GridPlanner.destination(c, brg, r)
                wps.add(Wp3D(pt, alt + e, norm360(brg + 180.0), tilt, startCapture = j == 0, stopCapture = j == steps))
            }
        }
        val speed = speedFor(trigger, cam, s.speed)
        val bl = ArrayList<String>()
        if (s.standoff < 5.0) bl.add(tr("Deja al menos 5 m entre el dron y el objeto."))
        if (r > 250) bl.add(tr("Órbita de más de 250 m de radio: ¿has dibujado bien la planta del objeto?"))
        val wn = ArrayList<String>()
        if (GridPlanner.distance(home, c) < r) wn.add(tr("El despegue queda dentro de la órbita: el dron subirá en vertical a la altura segura antes de moverse."))
        val safe = safeOf(max(top, h + 10.0) + e, wps)
        val info = String.format(Locale.ROOT, tr("ÓRBITA · %d anillo(s) · radio %.0f m (objeto %.0f m + %.0f m)\nAlturas %s m · cámara %s°"),
            n, r, objR, s.standoff, altsT.joinToString(" / ") { "%.0f".format(it) },
            alts.joinToString(" / ") { "%.0f".format(-Math.toDegrees(atan2(it - h / 2, r))) })
        return Plan3D(home, p, s, wps, c, h / 2 + e, safe, trigger, speed, photos, info, bl, wn)
    }

    /**
     * Doble cuadrícula oblicua: dos cuadrículas cruzadas con la cámara inclinada hacia delante (en cada pasada
     * mira en el sentido de avance, así las pasadas de ida y vuelta ven las dos caras). La zona se amplía para
     * que las fachadas del borde también salgan.
     */
    fun oblique(zone: List<LatLon>, home: LatLon, p: GridParams, s: Spec3D): Plan3D {
        require(zone.size >= 3) { tr("Dibuja la zona (mínimo 3 puntos)") }
        val tilt = s.obliqueTilt.coerceIn(-85.0, -30.0)
        val offNadir = 90.0 + tilt
        val buffer = p.altitude * tan(Math.toRadians(offNadir))
        val c = centroid(zone)
        val grown = zone.map { v ->
            val d = GridPlanner.distance(c, v)
            if (d < 0.1) v else GridPlanner.destination(c, GridPlanner.bearing(c, v), d + buffer)
        }
        val a0 = p.angleDeg ?: GridPlanner.plan(grown, home, p).angleDeg
        val g1 = GridPlanner.plan(grown, home, p.copy(angleDeg = a0, overshoot = max(p.overshoot, 5.0)))
        val g2 = GridPlanner.plan(grown, g1.passes.last().second, p.copy(angleDeg = (a0 + 90) % 360, overshoot = max(p.overshoot, 5.0)))
        val wps = ArrayList<Wp3D>()
        for ((a, b) in g1.passes + g2.passes) {
            val yaw = GridPlanner.bearing(a, b)
            wps.add(Wp3D(a, p.altitude, yaw, tilt, startCapture = true))
            wps.add(Wp3D(b, p.altitude, yaw, tilt, stopCapture = true))
        }
        val trig = min(g1.triggerDistance, g2.triggerDistance)
        val speed = speedFor(trig, p.camera, p.speed)
        val bl = ArrayList<String>()
        if (p.altitude < s.objectHeight + s.baseElev + 10) bl.add(trf("Vuela al menos 10 m por encima del edificio más alto (%1\$s m).", "%.0f".format(s.objectHeight + s.baseElev)))
        val info = String.format(Locale.ROOT, tr("DOBLE CUADRÍCULA OBLICUA · cámara %.0f° · %d + %d pasadas · zona ampliada %.0f m"),
            tilt, g1.passes.size, g2.passes.size, buffer)
        return Plan3D(home, p, s, wps, null, 0.0, p.altitude, trig, speed, g1.photos + g2.photos, info, bl, emptyList())
    }

    /**
     * Fachada: los dos primeros puntos de la zona son los extremos de la pared y el tercero indica el lado
     * desde el que se vuela. Filas horizontales de abajo arriba, en zigzag, con la cámara horizontal mirando a la pared.
     */
    fun facade(zone: List<LatLon>, home: LatLon, p: GridParams, s: Spec3D): Plan3D {
        require(zone.size >= 3) { tr("Toca los dos extremos de la pared y luego un punto en el lado desde el que volar") }
        val a = zone[0]; val b = zone[1]; val side = zone[2]
        val wallBrg = GridPlanner.bearing(a, b)
        val mid = LatLon((a.lat + b.lat) / 2, (a.lon + b.lon) / 2)
        val toSide = GridPlanner.bearing(mid, side)
        val n1 = (wallBrg + 90) % 360; val n2 = (wallBrg + 270) % 360
        val normal = if (GridPlanner.angleDiff(n1, toSide) < GridPlanner.angleDiff(n2, toSide)) n1 else n2
        val d = s.standoff
        val cam = p.camera
        val (fw, fh) = cam.footprint(d)                   // ancho y alto cubiertos sobre la pared
        val vStep = fh * (1 - p.sideOverlap)
        val top = max(s.objectHeight + fh * 0.3, s.minAlt)
        val rows = max(1, ceil((top - s.minAlt) / vStep).toInt() + 1)
        val ext = 5.0 + fw * 0.5
        val pa = GridPlanner.destination(GridPlanner.destination(a, normal, d), wallBrg + 180, ext)
        val pb = GridPlanner.destination(GridPlanner.destination(b, normal, d), wallBrg, ext)
        val yaw = norm360(normal + 180)
        val wps = ArrayList<Wp3D>()
        var photos = 0
        val trig = fw * (1 - p.frontOverlap)
        val rowLen = GridPlanner.distance(pa, pb)
        for (k in 0 until rows) {
            val alt = s.baseElev + if (rows == 1) s.minAlt else s.minAlt + (top - s.minAlt) * k / (rows - 1)
            val (s0, s1) = if (k % 2 == 0) Pair(pa, pb) else Pair(pb, pa)
            wps.add(Wp3D(s0, alt, yaw, 0.0, startCapture = true))
            wps.add(Wp3D(s1, alt, yaw, 0.0, stopCapture = true))
            photos += floor(rowLen / trig).toInt() + 1
        }
        val speed = speedFor(trig, cam, s.speed)
        val bl = ArrayList<String>()
        if (d < 5.0) bl.add(tr("Deja al menos 5 m entre el dron y la pared."))
        if (s.minAlt < 2.0) bl.add(tr("La fila más baja debe ir a 2 m o más."))
        if (GridPlanner.distance(a, b) < 3) bl.add(tr("Los dos extremos de la pared están demasiado juntos."))
        val wn = ArrayList<String>()
        wn.add(tr("Fachada a poca distancia: vuela con el dron a la vista y en un sitio sin GPS reflejado (calles estrechas = GPS malo)."))
        val info = String.format(Locale.ROOT, tr("FACHADA · pared %.0f m × %.0f m · a %.0f m · %d filas cada %.1f m"),
            GridPlanner.distance(a, b), s.objectHeight, d, rows, vStep)
        return Plan3D(home, p, s, wps, null, 0.0, safeOf(top + 10.0 + s.baseElev, wps), trig, speed, photos, info, bl, wn)
    }

    private fun floor(v: Double) = kotlin.math.floor(v)
}
