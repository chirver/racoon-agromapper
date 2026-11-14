package com.racoonair.mapper

import java.util.Locale
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt

/** Punto geográfico. */
data class LatLon(val lat: Double, val lon: Double)

/** Cámara: geometría para calcular huella, GSD y solapes. */
data class Camera(
    val id: String,
    val name: String,
    val sensorWidthMm: Double, val sensorHeightMm: Double, val focalMm: Double,
    val imageWidthPx: Int, val imageHeightPx: Int,
    val minIntervalS: Double,
    val platform: Platform,
    /** Resolución de la térmica (ANAFI Thermal), para mostrar su GSD; 0 = sin térmica. */
    val thermalWidthPx: Int = 0
) {
    companion object {
        /** Parrot Sequoia, bandas multiespectrales (datos de cameras.json de Pix4Dcapture: 4,8 mm · 3,98 mm · 1280×960). */
        val SEQUOIA = Camera("SEQUOIA", tr("Bluegrass/Disco · Sequoia (multiespectral)"), 4.8, 3.6, 3.98, 1280, 960, 1.0, Platform.BLUEGRASS_SEQUOIA)
        /**
         * ANAFI Thermal: RGB + térmica a la vez. Geometría de cameras.json de Pix4Dcapture (5,92 mm · 4,04 mm ·
         * 5344×4016, intervalo mínimo 2,5 s); la térmica (160×120) se guarda alineada con el mismo encuadre.
         */
        val ANAFI_THERMAL = Camera("ANAFI_THERMAL", tr("ANAFI Thermal · RGB + térmica"), 5.92, 5.92 * 4016 / 5344, 4.04, 5344, 4016, 2.5, Platform.ANAFI, 160)
        val ANAFI_4K = Camera("ANAFI_4K", tr("ANAFI 4K / USA · RGB"), 5.92, 5.92 * 4016 / 5344, 4.04, 5344, 4016, 2.5, Platform.ANAFI)
        val ALL = listOf(SEQUOIA, ANAFI_THERMAL, ANAFI_4K)
        /** La cámara por defecto de esta app: Sequoia en la de Bluegrass/Disco, ANAFI Thermal en la de ANAFI. */
        val DEFAULT get() = ALL.firstOrNull { Flavor.supports(it.platform) } ?: SEQUOIA
        fun byId(id: String?) = ALL.firstOrNull { it.id == id && Flavor.supports(it.platform) } ?: DEFAULT
    }
    override fun toString() = name
    /** GSD térmico en cm/px (0 si no hay térmica). */
    fun thermalGsdCm(alt: Double) = if (thermalWidthPx > 0) alt * sensorWidthMm / focalMm / thermalWidthPx * 100.0 else 0.0
    /** Huella en el suelo a una altura dada: (ancho, alto) en metros. */
    fun footprint(alt: Double) = Pair(alt * sensorWidthMm / focalMm, alt * sensorHeightMm / focalMm)
    /** GSD en cm/px. */
    fun gsdCm(alt: Double) = alt * sensorWidthMm / focalMm / imageWidthPx * 100.0
}

/**
 * Ala fija (Parrot Disco). Formato del plan calcado del que generaba Pix4Dcapture para la Disco
 * (MavlinkGenerator del plugin Parrot): sin ítem de despegue (se lanza a mano y espera dando círculos),
 * waypoints simples y, al final, un waypoint de aproximación con radio 35 m seguido de LAND en el punto
 * de aterrizaje: la Disco baja en línea recta desde la aproximación hasta el punto.
 */
data class FixedWing(
    /** Punto de toma. Null = aún no fijado (el plan no se puede usar). */
    val landing: LatLon? = null,
    /** Rumbo del avión en la final (hacia el punto de toma), grados 0..360. Debe ser de cara al viento. */
    val finalHeading: Double = 0.0,
    /** Distancia del punto de aproximación al de toma (m). Manual Parrot: pista de aproximación de ~200 m. */
    val approachDist: Double = 200.0,
    /** Altura del punto de aproximación (m). */
    val approachAlt: Double = 30.0,
    /** true = la Disco gobierna la Sequoia (Disco-Pro AG con la función activada). false = Sequoia por libre. */
    val droneCamera: Boolean = false,
    /** El rumbo de la final lo ha fijado el piloto (o el viento) para ESTE punto de toma. */
    val headingSet: Boolean = false,
    /** Desnivel del punto de toma respecto al lanzamiento (m, + si la toma está más alta). */
    val elevDiff: Double = 0.0,
    /** El piloto ha confirmado el desnivel (obligatorio si la toma está lejos del lanzamiento). */
    val elevSet: Boolean = false
) {
    companion object {
        const val CRUISE = 12.5          // m/s (40–50 km/h)
        const val TURN_RADIUS = 40.0     // m: radio de giro prudente para la Disco
        const val RUN_IN = 60.0          // m: entrada recta antes y después de cada pasada
        const val ALIGN_EXTRA = 150.0    // m: punto de alineación por detrás de la aproximación
        const val APPROACH_RADIUS = 35.0 // m: el mismo que usaba Pix4D
        const val MIN_ALT = 50.0
        const val CONE_HALF_DEG = 15.0   // cono de 30° del manual
        const val USABLE_MIN = 31.0      // 45 min nominales, hasta el ~30 % de batería
        const val MAX_LAND_FROM_LAUNCH = 500.0
        const val ELEV_CHECK_DIST = 150.0
        const val MAX_GLIDE = 0.16       // pendiente máx. de la final (≈9°)
        const val PRE_ALIGN = 200.0      // tramo extra recto si hay que girar mucho para entrar en la final
    }
    /** Alturas relativas al lanzamiento (frame 3): se suma el desnivel de la toma. */
    val approachAltRel get() = approachAlt + elevDiff
    val approachPoint: LatLon? get() = landing?.let { GridPlanner.destination(it, finalHeading + 180.0, approachDist) }
    val alignPoint: LatLon? get() = landing?.let { GridPlanner.destination(it, finalHeading + 180.0, approachDist + ALIGN_EXTRA) }
    val preAlignPoint: LatLon? get() = landing?.let { GridPlanner.destination(it, finalHeading + 180.0, approachDist + ALIGN_EXTRA + PRE_ALIGN) }
    /** ¿Hace falta un tramo recto extra? Si para entrar en la final desde [from] hay que girar más de 60°. */
    fun needsPreAlign(from: LatLon): Boolean {
        val al = alignPoint ?: return false
        return GridPlanner.angleDiff(GridPlanner.bearing(from, al), finalHeading) > 60.0
    }
}

data class GridParams(
    val altitude: Double = 50.0,
    val frontOverlap: Double = 0.80,
    val sideOverlap: Double = 0.70,
    /** Rumbo de las pasadas en grados (0 = norte). Null = automático (lado más largo del polígono). */
    val angleDeg: Double? = null,
    val speed: Double = 8.0,
    /** Prolongación de cada pasada fuera del polígono, en metros. */
    val overshoot: Double = 5.0,
    val camera: Camera = Camera.DEFAULT,
    /** No nulo = plan de ala fija (Disco). */
    val fixedWing: FixedWing? = null
)

class GridResult(
    override val passes: List<Pair<LatLon, LatLon>>,
    override val home: LatLon,
    override val params: GridParams,
    val angleDeg: Double,
    val lineSpacing: Double,
    override val triggerDistance: Double,
    val effectiveSpeed: Double,
    val speedLimited: Boolean,
    val surveyLength: Double,
    val transitLength: Double,
    override val photos: Int,
    val areaM2: Double,
    val concave: Boolean,
    /** Ala fija: pasadas voladas de forma alterna (cada [skip]) porque están más juntas que el giro. */
    val skip: Int = 1
) : PlanResult {
    val fw get() = params.fixedWing
    /** Longitud del tramo final: última pasada → alineación → aproximación → toma. */
    val landingLength: Double get() {
        val f = fw ?: return 0.0
        val al = f.alignPoint ?: return 0.0; val ap = f.approachPoint!!; val ld = f.landing!!
        val last = passes.lastOrNull()?.second ?: home
        val start = if (f.needsPreAlign(last)) f.preAlignPoint!! else al
        val pre = if (start !== al) GridPlanner.distance(last, start) + GridPlanner.distance(start, al) else GridPlanner.distance(last, al)
        return pre + GridPlanner.distance(al, ap) + GridPlanner.distance(ap, ld)
    }
    /** Número de ítems del fichero: 6 de cabecera + 6-7 por pasada + 3 finales. */
    val itemCount get() = 9 + passes.size * 6 + 1

    /** Motivos que IMPIDEN usar el plan (no solo avisos). */
    override fun blockers(): List<String> {
        val b = ArrayList<String>()
        fw?.let { f ->
            if (f.landing == null) b.add(tr("Falta el aterrizaje: pulsa «🛬 Aterrizaje», toca el punto de toma y luego de dónde llega el avión."))
            else {
                if (!f.headingSet) b.add(tr("Falta la dirección de la final: toca de dónde llega el avión o pulsa «🌬 Según viento»."))
                val dl = GridPlanner.distance(home, f.landing)
                if (dl > FixedWing.MAX_LAND_FROM_LAUNCH) b.add(trf("El punto de toma está a %1\$s m del lanzamiento (máx. %2\$s m): ¿es de otra parcela? Vuelve a fijarlo.", dl.toInt(), FixedWing.MAX_LAND_FROM_LAUNCH.toInt()))
                if (dl > FixedWing.ELEV_CHECK_DIST && !f.elevSet) b.add(trf("La toma está a %1\$s m del lanzamiento: indica el desnivel en «Aprox…» (0 si es llano).", dl.toInt()))
                if (f.approachAlt / f.approachDist > FixedWing.MAX_GLIDE) b.add(trf("Final demasiado empinada (%1\$s m en %2\$s m): baja la altura o alarga la aproximación.", "%.0f".format(f.approachAlt), "%.0f".format(f.approachDist)))
                if (f.approachAltRel > params.altitude) b.add(trf("La aproximación (%1\$s m) no puede ir más alta que el vuelo (%2\$s m).", "%.0f".format(f.approachAltRel), "%.0f".format(params.altitude)))
                if (f.approachAltRel < 15.0) b.add(trf("Con ese desnivel la aproximación queda a %1\$s m sobre el lanzamiento: súbela.", "%.0f".format(f.approachAltRel)))
            }
            val tight = (1 until passes.size).count { i -> GridPlanner.distance(passes[i - 1].second, passes[i].first) < 2 * FixedWing.TURN_RADIUS - 1 }
            if (tight > 0) b.add(trf("%1\$s giro(s) entre pasadas más cerrados de %2\$s m: la Disco podría quedarse dando vueltas. Cambia el rumbo de las pasadas, sube la altura o baja el solape lateral.", tight, (2 * FixedWing.TURN_RADIUS).toInt())+
                (if (passes.size < 4) tr(" (con tan pocas pasadas no hay orden posible: amplía la zona)") else ""))
            if (params.altitude < FixedWing.MIN_ALT) b.add(trf("La Disco necesita volar a %1\$s m o más.", FixedWing.MIN_ALT.toInt()))
            if (f.approachAlt !in 15.0..60.0) b.add(tr("Altura de aproximación entre 15 y 60 m."))
            if (f.approachDist !in 120.0..400.0) b.add(tr("Distancia de aproximación entre 120 y 400 m."))
            // La Disco no puede ir más despacio que su crucero: la Sequoia (1 foto/s como máximo) debe dar abasto.
            if (triggerDistance < FixedWing.CRUISE * params.camera.minIntervalS)
                b.add(trf("A %1\$s m/s la Sequoia necesita %2\$s m o más entre fotos (ahora %3\$s m): sube la altura o baja el solape frontal.", FixedWing.CRUISE, "%.1f".format(FixedWing.CRUISE * params.camera.minIntervalS), "%.1f".format(triggerDistance)))
            if (estimatedSeconds > FixedWing.USABLE_MIN * 60) b.add(trf("Vuelo estimado de %1\$s min: no cabe en una batería de Disco. Divide la zona.", "%.0f".format(estimatedSeconds / 60)))
            if (params.altitude > 120.0) b.add(tr("Altura máxima 120 m."))
            if (passes.size > GridPlanner.MAX_PASSES) b.add(trf("%1\$s pasadas (máx. %2\$s).", passes.size, GridPlanner.MAX_PASSES))
            return b
        }
        if (params.altitude > 120.0) b.add(tr("Altura máxima 120 m."))
        if (estimatedSeconds > 22 * 60) b.add(trf("Vuelo estimado de %1\$s min: no cabe en una batería. Divide la zona o sube la altura.", "%.0f".format(estimatedSeconds / 60)))
        if (passes.size > GridPlanner.MAX_PASSES) b.add(trf("%1\$s pasadas (máx. %2\$s). Baja el solape lateral o sube la altura.", passes.size, GridPlanner.MAX_PASSES))
        if (effectiveSpeed < 2.0) b.add(trf("Velocidad resultante %1\$s m/s: demasiado lenta. Baja el solape frontal o sube la altura.", "%.1f".format(effectiveSpeed)))
        return b
    }
    /** Tiempo estimado en segundos: subida, tránsitos a 10 m/s, pasadas, giros y aterrizaje. */
    override val estimatedSeconds: Double
        get() = if (fw != null)
            // Ala fija: todo a crucero; cada giro ≈ media vuelta de radio 40 m; final y toma ≈ 60 s.
            (transitLength + surveyLength + landingLength) / FixedWing.CRUISE +
                passes.size * Math.PI * FixedWing.TURN_RADIUS / FixedWing.CRUISE + 60.0
        else params.altitude / 2.5 + transitLength / GridPlanner.TRANSIT_SPEED +
            surveyLength / effectiveSpeed + passes.size * 2 * 4.0 + params.altitude / 1.5

    val gsdCm get() = params.camera.gsdCm(params.altitude)

    override fun summary(): String {
        val min = estimatedSeconds / 60.0
        fw?.let { f ->
            return String.format(Locale.ROOT,
                tr("DISCO (ala fija) · Sequoia %s\nPasadas %d · separación %.1f m · rumbo %.0f°%s\n") +
                    tr("Foto cada %.1f m · ~%d fotos · GSD %.1f cm/px · área %.2f ha\n") +
                    tr("Crucero %.1f m/s · ruta %.0f m · tiempo estimado %.1f min\n%s"),
                if (f.droneCamera) tr("gobernada por el dron") else tr("por libre (prepárala por WiFi)"),
                passes.size, lineSpacing, angleDeg,
                if (skip > 1) trf(" · alternas de %1\$s en %2\$s (giro de %3\$s m)", skip, skip, FixedWing.TURN_RADIUS.toInt()) else "",
                triggerDistance, photos, gsdCm, areaM2 / 10000.0,
                FixedWing.CRUISE, surveyLength + transitLength + landingLength, min,
                if (f.landing == null) tr("🛬 Aterrizaje SIN fijar")
                else String.format(Locale.ROOT, tr("🛬 Final rumbo %.0f° · aproximación a %.0f m y %.0f m de altura"),
                    f.finalHeading, f.approachDist, f.approachAlt))
        }
        val cam = params.camera
        val shot = if (cam.platform == Platform.ANAFI)
            String.format(Locale.ROOT, tr("Foto cada %.1f m (%.1f s)"), triggerDistance, intervalS)
        else String.format(Locale.ROOT, tr("Disparo Sequoia cada %.1f m"), triggerDistance)
        val gsd = if (cam.thermalWidthPx > 0)
            String.format(Locale.ROOT, tr("GSD RGB %.1f · térmica %.0f cm/px"), gsdCm, cam.thermalGsdCm(params.altitude))
        else String.format(Locale.ROOT, tr("GSD %.1f cm/px"), gsdCm)
        val limit = if (!speedLimited) "" else if (cam.platform == Platform.ANAFI) tr(" (limitada: 1 foto cada 2,5 s)") else tr(" (limitada por la Sequoia: 1 foto/s)")
        return String.format(Locale.ROOT,
            tr("%s\nPasadas %d · separación %.1f m · rumbo %.0f°\n%s · ~%d fotos · %s\n") +
                tr("Velocidad %.1f m/s%s · área %.2f ha\nRuta %.0f m · tiempo estimado %.1f min"),
            cam.name, passes.size, lineSpacing, angleDeg, shot, photos, gsd,
            effectiveSpeed, limit, areaM2 / 10000.0, surveyLength + transitLength, min)
    }

    override fun warnings(): List<String> {
        val w = ArrayList<String>()
        fw?.let { f ->
            if (concave) w.add(tr("Zona cóncava: algunas pasadas cruzan por FUERA del contorno dibujado."))
            if (estimatedSeconds > 0.85 * FixedWing.USABLE_MIN * 60) w.add(tr("Vuelo justo para una batería de Disco."))
            f.landing?.let { ld ->
                val d = GridPlanner.distance(home, ld)
                if (d > 500) w.add(trf("El punto de toma está a %1\$s m del lanzamiento: ¿lo verás bien?", d.toInt()))
            }
            w.add(trf("Los giros entre pasadas salen hasta ~%1\$s m fuera de la parcela: que no haya obstáculos ni zonas prohibidas alrededor.", (FixedWing.RUN_IN + 2 * FixedWing.TURN_RADIUS).toInt()))
            w.add(tr("Revisa en la ortofoto que el pasillo de aproximación (cono azul) está libre de árboles, cables y casas."))
            return w
        }
        if (params.camera.thermalWidthPx > 0 && (params.frontOverlap < 0.8 || params.sideOverlap < 0.75 || params.speed > 4.0))
            w.add(tr("Mosaico térmico: la térmica es de 160×120 y cubre poco. Usa solape ≥ 80 % frontal y ≥ 75 % lateral y velocidad ≤ 4 m/s, o el mosaico térmico tendrá huecos."))
        if (params.altitude > 120) w.add(tr("Altura por encima de 120 m."))
        if (params.altitude < 20) w.add(tr("Altura muy baja (<20 m): obstáculos y muchas fotos."))
        if (concave) w.add(tr("Zona cóncava: algunas pasadas cruzan por FUERA del contorno dibujado. Revisa obstáculos o divide la zona."))
        if (estimatedSeconds > 18 * 60) w.add(tr("Vuelo de más de 18 min: probablemente no da una batería de Bluegrass. Divide la zona."))
        val d0 = GridPlanner.distance(home, passes.firstOrNull()?.first ?: home)
        if (d0 > 500) w.add(trf("El primer punto está a %1\$s m de la casa.", d0.toInt()))
        return w
    }

    /** Intervalo de disparo en segundos (ANAFI dispara por tiempo). */
    val intervalS get() = triggerDistance / effectiveSpeed

    override fun toMavlink(): String = when {
        fw != null -> toMavlinkDisco()
        params.camera.platform == Platform.ANAFI -> toMavlinkAnafi()
        else -> toMavlinkSequoia()
    }

    /**
     * Disco: sin despegue (lanzamiento a mano), sin esperas ni cambios de velocidad (vuela a crucero).
     * Cámara solo si la Disco gobierna la Sequoia; si no, la Sequoia va por libre en modo GPS.
     * Final: alineación → aproximación (radio 35 m, como Pix4D) → LAND en el punto de toma.
     */
    private fun toMavlinkDisco(): String {
        val f = fw!!
        val ld = requireNotNull(f.landing) { tr("Falta el aterrizaje") }
        val sb = StringBuilder("QGC WPL 120\n")
        var i = 0
        fun item(cmd: Int, p1: Double = 0.0, p2: Double = 0.0, p3: Double = 0.0, p4: Double = 0.0,
                 lat: Double = 0.0, lon: Double = 0.0, alt: Double = 0.0) {
            sb.append(String.format(Locale.ROOT, "%d\t0\t3\t%d\t%f\t%f\t%f\t%f\t%f\t%f\t%f\t1\n",
                i++, cmd, p1, p2, p3, p4, lat, lon, alt))
        }
        val alt = params.altitude
        val trig = triggerDistance
        // Ítem 0: un punto de entrada 60 m antes de la primera pasada (algunos autopilotos toman el ítem 0 como
        // «casa» y lo saltan; si no lo saltan, el siguiente punto queda siempre por delante).
        passes.firstOrNull()?.let { (a0, b0) ->
            val h = GridPlanner.bearing(a0, b0)
            val e = GridPlanner.destination(a0, h + 180.0, FixedWing.RUN_IN)
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = h, lat = e.lat, lon = e.lon, alt = alt)
        }
        if (f.droneCamera) {
            item(FlightPlan.CMD_PARROT_PHOTO_SENSORS, p1 = 69905.0)
            item(FlightPlan.CMD_PARROT_PICTURE_MODE, p1 = 1.0, p2 = trig * 100.0)
        }
        for (pass in passes) {
            val (a, b) = pass
            val yaw = GridPlanner.bearing(a, b)
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = yaw, lat = a.lat, lon = a.lon, alt = alt)
            if (f.droneCamera) item(FlightPlan.CMD_IMAGE_START, p1 = trig, p3 = 13.0)
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = yaw, lat = b.lat, lon = b.lon, alt = alt)
            if (f.droneCamera) item(FlightPlan.CMD_IMAGE_STOP)
        }
        val al = f.alignPoint!!; val ap = f.approachPoint!!
        val apAlt = f.approachAltRel
        val alignAlt = max(apAlt, min(alt, apAlt + 20.0))
        val last = passes.lastOrNull()?.second ?: home
        if (f.needsPreAlign(last)) { val pa = f.preAlignPoint!!
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = f.finalHeading, lat = pa.lat, lon = pa.lon, alt = max(alignAlt, min(alt, apAlt + 30.0))) }
        item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = f.finalHeading, lat = al.lat, lon = al.lon, alt = alignAlt)
        item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p3 = FixedWing.APPROACH_RADIUS, lat = ap.lat, lon = ap.lon, alt = apAlt)
        item(FlightPlan.CMD_LAND, lat = ld.lat, lon = ld.lon, alt = f.elevDiff)
        return sb.toString()
    }

    /**
     * ANAFI (GroundSDK): mismos comandos que expone GroundSDK 1.8 — cámara a nadir (205), modo de vista,
     * captura por intervalo de tiempo (50001 modo 0 = TIMELAPSE, 2000 con formato 12 = RECTILINEAR).
     */
    private fun toMavlinkAnafi(): String {
        val sb = StringBuilder("QGC WPL 120\n")
        var i = 0
        fun item(cmd: Int, p1: Double = 0.0, p2: Double = 0.0, p3: Double = 0.0, p4: Double = 0.0,
                 lat: Double = 0.0, lon: Double = 0.0, alt: Double = 0.0) {
            sb.append(String.format(Locale.ROOT, "%d\t0\t3\t%d\t%f\t%f\t%f\t%f\t%f\t%f\t%f\t1\n",
                i++, cmd, p1, p2, p3, p4, lat, lon, alt))
        }
        val alt = params.altitude
        val iv = intervalS
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = 2.5)                              // subida
        item(FlightPlan.CMD_TAKEOFF, lat = home.lat, lon = home.lon, alt = alt)
        item(FlightPlan.CMD_MOUNT_CONTROL, p1 = -90.0, alt = 2.0)               // cámara mirando abajo (p7 = modo 2)
        item(FlightPlan.CMD_PARROT_VIEW_MODE, p1 = 1.0)                          // CONTINUE
        item(FlightPlan.CMD_PARROT_PICTURE_MODE, p1 = 0.0, p2 = iv)              // TIMELAPSE cada iv s
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = GridPlanner.TRANSIT_SPEED)
        for ((n, pass) in passes.withIndex()) {
            val (a, b) = pass
            val yaw = GridPlanner.bearing(a, b)
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = yaw, lat = a.lat, lon = a.lon, alt = alt)
            if (n == 0) {
                item(FlightPlan.CMD_DELAY, p1 = 1.0)
                item(FlightPlan.CMD_CHANGE_SPEED, p2 = effectiveSpeed)
                item(FlightPlan.CMD_IMAGE_START, p1 = iv, p3 = 12.0)
            } else {
                item(FlightPlan.CMD_IMAGE_START, p1 = iv, p3 = 12.0)
                item(FlightPlan.CMD_DELAY, p1 = 1.0)
            }
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = yaw, lat = b.lat, lon = b.lon, alt = alt)
            item(FlightPlan.CMD_IMAGE_STOP)
            item(FlightPlan.CMD_DELAY, p1 = 1.0)
        }
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = GridPlanner.TRANSIT_SPEED)
        item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, lat = home.lat, lon = home.lon, alt = alt)
        item(FlightPlan.CMD_LAND, lat = home.lat, lon = home.lon, alt = 0.0)
        return sb.toString()
    }

    /** Fichero Parrot "QGC WPL 120" con la misma estructura que generaba Pix4Dcapture para Sequoia. */
    private fun toMavlinkSequoia(): String {
        val sb = StringBuilder("QGC WPL 120\n")
        var i = 0
        fun item(cmd: Int, p1: Double = 0.0, p2: Double = 0.0, p3: Double = 0.0, p4: Double = 0.0,
                 lat: Double = 0.0, lon: Double = 0.0, alt: Double = 0.0) {
            sb.append(String.format(Locale.ROOT, "%d\t0\t3\t%d\t%f\t%f\t%f\t%f\t%f\t%f\t%f\t1\n",
                i++, cmd, p1, p2, p3, p4, lat, lon, alt))
        }
        val alt = params.altitude
        val trig = triggerDistance
        item(FlightPlan.CMD_PARROT_PHOTO_SENSORS, p1 = 69905.0)          // RGB+G+R+RE+NIR
        item(FlightPlan.CMD_PARROT_PICTURE_MODE, p1 = 1.0, p2 = trig * 100.0) // disparo por distancia (cm)
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = 2.5, p3 = -1.0)            // subida
        item(FlightPlan.CMD_TAKEOFF, lat = home.lat, lon = home.lon, alt = alt)
        item(FlightPlan.CMD_PARROT_VIEW_MODE, p1 = 1.0)
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = GridPlanner.TRANSIT_SPEED, p3 = -1.0)
        for ((n, pass) in passes.withIndex()) {
            val (a, b) = pass
            val yaw = GridPlanner.bearing(a, b)
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = yaw, lat = a.lat, lon = a.lon, alt = alt)
            if (n == 0) {
                item(FlightPlan.CMD_DELAY, p1 = 1.0)
                item(FlightPlan.CMD_CHANGE_SPEED, p2 = effectiveSpeed, p3 = -1.0)
                item(FlightPlan.CMD_IMAGE_START, p1 = trig, p3 = 13.0)
            } else {
                item(FlightPlan.CMD_IMAGE_START, p1 = trig, p3 = 13.0)
                item(FlightPlan.CMD_DELAY, p1 = 1.0)
            }
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = yaw, lat = b.lat, lon = b.lon, alt = alt)
            item(FlightPlan.CMD_IMAGE_STOP)
            item(FlightPlan.CMD_DELAY, p1 = 1.0)
        }
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = GridPlanner.TRANSIT_SPEED, p3 = -1.0)
        item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, lat = home.lat, lon = home.lon, alt = alt)
        item(FlightPlan.CMD_LAND, lat = home.lat, lon = home.lon, alt = 0.0)
        return sb.toString()
    }
}

object GridPlanner {
    const val TRANSIT_SPEED = 10.0
    const val MAX_PASSES = 120
    private const val R = 6371000.0

    fun distance(a: LatLon, b: LatLon) = FlightPlan.haversine(a.lat, a.lon, b.lat, b.lon)

    /** Punto a [dist] metros de [p] con rumbo [bearingDeg]. */
    fun destination(p: LatLon, bearingDeg: Double, dist: Double): LatLon {
        val d = dist / R; val br = Math.toRadians(bearingDeg)
        val la1 = Math.toRadians(p.lat); val lo1 = Math.toRadians(p.lon)
        val la2 = Math.asin(sin(la1) * cos(d) + cos(la1) * sin(d) * cos(br))
        val lo2 = lo1 + atan2(sin(br) * sin(d) * cos(la1), cos(d) - sin(la1) * sin(la2))
        return LatLon(Math.toDegrees(la2), Math.toDegrees(lo2))
    }

    /** Diferencia angular mínima entre dos rumbos (0..180). */
    fun angleDiff(a: Double, b: Double): Double { val d = abs(((a - b) % 360 + 540) % 360 - 180); return d }

    /**
     * Orden de vuelo para ala fija: dos pasadas seguidas deben estar separadas al menos el diámetro de giro
     * (2R), si no la Disco no puede girar de una a otra. Con need = ceil(2R / separación):
     *  - need ≤ 1 → ida y vuelta normal.
     *  - si no, bloques de 2k pasadas (k = need + 1) recorridos «en peine»: lo, lo+k, lo+1, lo+k+1…
     *    (saltos de k y k−1 ≥ need), alternando el orden de cada bloque para enlazar con saltos de k+1.
     *  - el resto (bloque incompleto) se resuelve con una búsqueda exhaustiva; si no hay solución, el mejor intento.
     * Devuelve (índices espaciales, need) — need > 1 significa «pasadas alternas».
     */
    fun fixedWingOrder(n: Int, spacing: Double): Pair<List<Int>, Int> {
        val need = max(1, ceil(2 * FixedWing.TURN_RADIUS / spacing - 1e-9).toInt())
        if (need <= 1 || n <= 1) return Pair((0 until n).toList(), 1)
        // 1) Búsqueda completa con heurística de Warnsdorff (primero la pasada con menos salidas, y el salto más corto):
        //    encuentra el orden casi siempre al instante; empieza por la pasada 0 (la del lado del lanzamiento).
        orderSearch(n, need)?.let { return Pair(it, need) }
        // 2) Si no (pocas pasadas: puede no existir), el peine por bloques + el mejor intento para el resto.
        val k = need + 1
        val out = ArrayList<Int>()
        val full = n / (2 * k)
        for (b in 0 until full) {
            val lo = b * 2 * k
            for (i in 0 until k) {
                if (b % 2 == 0) { out.add(lo + i); out.add(lo + k + i) } else { out.add(lo + k + i); out.add(lo + i) }
            }
        }
        val rest = (full * 2 * k until n).toList()
        if (rest.isNotEmpty()) {
            val last = out.lastOrNull()
            val best = tailSearch(rest, last, need) ?: tailGreedy(rest, last, need)
            out.addAll(best)
        }
        return Pair(out, need)
    }

    private fun orderSearch(n: Int, need: Int): List<Int>? {
        if (n > 400 || n < 2 * need) return null // con menos de 2·need pasadas no existe orden válido
        val used = BooleanArray(n); val path = ArrayList<Int>(n)
        var steps = 0
        fun options(c: Int) = (0 until n).count { !used[it] && abs(it - c) >= need }
        fun dfs(cur: Int): Boolean {
            if (path.size == n) return true
            if (++steps > 300_000) return false
            val raw = (0 until n).filter { !used[it] && abs(it - cur) >= need }
            val deg = HashMap<Int, Int>(raw.size * 2)
            for (j in raw) { used[j] = true; deg[j] = options(j); used[j] = false }
            val cands = raw.sortedWith(compareBy<Int>({ deg[it]!! }, { abs(it - cur) }))
            for (j in cands) {
                used[j] = true; path.add(j)
                if (dfs(j)) return true
                used[j] = false; path.removeAt(path.size - 1)
            }
            return false
        }
        used[0] = true; path.add(0)
        return if (dfs(0)) path else null
    }

    private fun tailSearch(rest: List<Int>, start: Int?, need: Int): List<Int>? {
        val used = BooleanArray(rest.size); val path = ArrayList<Int>()
        var steps = 0
        fun dfs(cur: Int?): Boolean {
            if (path.size == rest.size) return true
            if (++steps > 200_000) return false
            for (t in rest.indices) {
                if (used[t]) continue
                val j = rest[t]
                if (cur != null && abs(j - cur) < need) continue
                used[t] = true; path.add(j)
                if (dfs(j)) return true
                used[t] = false; path.removeAt(path.size - 1)
            }
            return false
        }
        return if (dfs(start)) path else null
    }

    private fun tailGreedy(rest: List<Int>, start: Int?, need: Int): List<Int> {
        val left = rest.toMutableList(); val out = ArrayList<Int>(); var cur = start
        while (left.isNotEmpty()) {
            val c = cur
            val ok = left.filter { c == null || abs(it - c) >= need }
            val pick = if (ok.isNotEmpty()) ok.minByOrNull { if (c == null) it else abs(it - c) }!! else left.maxByOrNull { if (c == null) it else abs(it - c) }!!
            out.add(pick); left.remove(pick); cur = pick
        }
        return out
    }

    /** Rumbo inicial a→b en grados 0..360 (0 = norte). */
    fun bearing(a: LatLon, b: LatLon): Double {
        val p1 = Math.toRadians(a.lat); val p2 = Math.toRadians(b.lat)
        val dl = Math.toRadians(b.lon - a.lon)
        val y = sin(dl) * cos(p2)
        val x = cos(p1) * sin(p2) - sin(p1) * cos(p2) * cos(dl)
        return (Math.toDegrees(atan2(y, x)) + 360.0) % 360.0
    }

    // Proyección local equirectangular (metros) alrededor de un origen: suficiente para parcelas de pocos km.
    private class Local(val o: LatLon) {
        val kx = R * cos(Math.toRadians(o.lat)) * PI / 180.0
        val ky = R * PI / 180.0
        fun toXY(p: LatLon) = doubleArrayOf((p.lon - o.lon) * kx, (p.lat - o.lat) * ky)
        fun toLL(x: Double, y: Double) = LatLon(o.lat + y / ky, o.lon + x / kx)
    }

    fun areaM2(poly: List<LatLon>): Double {
        if (poly.size < 3) return 0.0
        val l = Local(poly[0])
        val pts = poly.map { l.toXY(it) }
        var s = 0.0
        for (i in pts.indices) { val a = pts[i]; val b = pts[(i + 1) % pts.size]; s += a[0] * b[1] - b[0] * a[1] }
        return abs(s) / 2.0
    }

    /** Rumbo del lado más largo: así salen menos pasadas y más largas. */
    fun autoAngle(poly: List<LatLon>): Double {
        var best = 0.0; var bestLen = -1.0
        for (i in poly.indices) {
            val a = poly[i]; val b = poly[(i + 1) % poly.size]
            val d = distance(a, b)
            if (d > bestLen) { bestLen = d; best = bearing(a, b) }
        }
        return best % 180.0
    }

    fun plan(poly: List<LatLon>, home: LatLon, p: GridParams): GridResult {
        require(poly.size >= 3) { tr("Dibuja al menos 3 puntos") }
        require(p.altitude.isFinite() && p.altitude in 10.0..120.0) { tr("Altura entre 10 y 120 m") }
        require(p.speed.isFinite() && p.overshoot.isFinite()) { tr("Parámetros no válidos") }
        require(p.frontOverlap in 0.3..0.95 && p.sideOverlap in 0.3..0.95) { tr("Solapes entre 30 y 95 %") }
        val angle = p.angleDeg ?: autoAngle(poly)
        val (fw, fh) = p.camera.footprint(p.altitude)
        // Sequoia: lado largo de la imagen transversal a la pasada (como el montaje en la Bluegrass).
        val lineSpacing = fw * (1 - p.sideOverlap)
        val trigger = fh * (1 - p.frontOverlap)
        val maxSpeed = trigger / (p.camera.minIntervalS * 1.1)
        val speed = min(p.speed, maxSpeed)

        val centroid = LatLon(poly.map { it.lat }.average(), poly.map { it.lon }.average())
        val l = Local(centroid)
        // Rotamos para que las pasadas queden horizontales (eje x local = rumbo de pasada).
        val th = Math.toRadians(90.0 - angle) // rumbo (desde el norte, horario) → ángulo matemático
        val c = cos(th); val s = sin(th)
        fun rot(x: Double, y: Double) = doubleArrayOf(x * c + y * s, -x * s + y * c)
        fun unrot(u: Double, v: Double) = doubleArrayOf(u * c - v * s, u * s + v * c)
        val pts = poly.map { val xy = l.toXY(it); rot(xy[0], xy[1]) }
        val minV = pts.minOf { it[1] }; val maxV = pts.maxOf { it[1] }

        val passes = ArrayList<Pair<LatLon, LatLon>>()
        var concave = false
        require((maxV - minV) / lineSpacing < 2000) { tr("Demasiadas pasadas") }
        val n = max(1, ceil((maxV - minV) / lineSpacing).toInt())
        val start = minV + ((maxV - minV) - (n - 1) * lineSpacing) / 2.0
        for (k in 0 until n) {
            val v = start + k * lineSpacing
            val xs = ArrayList<Double>()
            for (i in pts.indices) {
                val a = pts[i]; val b = pts[(i + 1) % pts.size]
                if ((a[1] <= v && b[1] > v) || (b[1] <= v && a[1] > v)) {
                    xs.add(a[0] + (v - a[1]) / (b[1] - a[1]) * (b[0] - a[0]))
                }
            }
            if (xs.size < 2) continue
            if (xs.size > 2) concave = true
            // Polígonos cóncavos: se cubre de extremo a extremo (puede sobrevolar fuera de la zona).
            val over = if (p.fixedWing != null) max(p.overshoot, FixedWing.RUN_IN) else p.overshoot
            var u0 = xs.minOrNull()!! - over
            var u1 = xs.maxOrNull()!! + over
            if (passes.size % 2 == 1) { val t = u0; u0 = u1; u1 = t }
            val a = unrot(u0, v); val b = unrot(u1, v)
            passes.add(Pair(l.toLL(a[0], a[1]), l.toLL(b[0], b[1])))
        }
        require(passes.isNotEmpty()) { tr("No salen pasadas: zona demasiado pequeña") }
        if (p.fixedWing != null) return planFixedWing(poly, home, p, angle, lineSpacing, trigger, passes, concave)
        // Empezar por el extremo más cercano a la casa.
        val first = passes.first(); val last = passes.last()
        val dFirst = min(distance(home, first.first), distance(home, first.second))
        val dLast = min(distance(home, last.first), distance(home, last.second))
        var ordered: List<Pair<LatLon, LatLon>> = if (dLast < dFirst) passes.reversed() else passes
        if (distance(home, ordered[0].second) < distance(home, ordered[0].first)) {
            ordered = ordered.map { Pair(it.second, it.first) }
        }
        var survey = 0.0; var transit = distance(home, ordered[0].first); var photos = 0
        for ((i, pr) in ordered.withIndex()) {
            val len = distance(pr.first, pr.second)
            survey += len
            photos += floor(len / trigger).toInt() + 1
            if (i > 0) transit += distance(ordered[i - 1].second, pr.first)
        }
        transit += distance(ordered.last().second, home)
        return GridResult(ordered, home, p, angle, lineSpacing, trigger, speed, speed < p.speed - 1e-6,
            survey, transit, photos, areaM2(poly), concave)
    }

    private fun planFixedWing(poly: List<LatLon>, home: LatLon, p: GridParams, angle: Double, spacing: Double,
                              trigger: Double, spatial: List<Pair<LatLon, LatLon>>, concave: Boolean): GridResult {
        // Todas en el mismo sentido (las impares venían giradas) y luego el orden de vuelo con giros amplios.
        val norm = spatial.mapIndexed { i, pr -> if (i % 2 == 1) Pair(pr.second, pr.first) else pr }
        val (order, k) = fixedWingOrder(norm.size, spacing)
        // Empezar por el extremo de la parcela más cercano al lanzamiento.
        val idx = if (min(distance(home, norm.first().first), distance(home, norm.first().second)) <=
                      min(distance(home, norm.last().first), distance(home, norm.last().second))) order
                  else order.map { norm.size - 1 - it }
        val flown = ArrayList<Pair<LatLon, LatLon>>()
        var prevEnd = home
        for (j in idx) {
            val pr = norm[j]
            // Cada pasada se recorre en el sentido que deja su inicio más cerca de donde está el avión.
            flown.add(if (distance(prevEnd, pr.first) <= distance(prevEnd, pr.second)) pr else Pair(pr.second, pr.first))
            prevEnd = flown.last().second
        }
        var survey = 0.0; var transit = distance(home, flown[0].first); var photos = 0
        for ((i, pr) in flown.withIndex()) {
            val len = distance(pr.first, pr.second)
            survey += len
            photos += floor(len / trigger).toInt() + 1
            if (i > 0) transit += distance(flown[i - 1].second, pr.first)
        }
        return GridResult(flown, home, p, angle, spacing, trigger, FixedWing.CRUISE, false,
            survey, transit, photos, areaM2(poly), concave, k)
    }
}
