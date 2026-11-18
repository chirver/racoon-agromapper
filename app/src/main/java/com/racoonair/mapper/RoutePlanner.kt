package com.racoonair.mapper

import java.util.Locale
import kotlin.math.max
import kotlin.math.min

/**
 * Ruta por waypoints: el dron recorre los puntos en el orden en que se tocaron en el mapa, a la altura del plan,
 * sin fotos programadas.
 * - Multirrotor (Bluegrass, ANAFI): despega, sube en vertical, recorre los puntos, vuelve a casa y aterriza.
 * - Disco (ala fija): se lanza a mano, recorre los puntos a crucero y termina con la misma final que los mapas
 *   (alineación → aproximación → toma).
 */
class RoutePlan(
    override val home: LatLon,
    override val params: GridParams,
    val points: List<LatLon>
) : PlanResult {
    val fw get() = params.fixedWing
    override val triggerDistance: Double = 0.0
    override val photos: Int = 0

    /** Tramos a dibujar: casa → puntos → (casa o final de la Disco). */
    override val passes: List<Pair<LatLon, LatLon>> get() {
        val out = ArrayList<Pair<LatLon, LatLon>>()
        var prev = home
        for (p in points) { out.add(Pair(prev, p)); prev = p }
        val f = fw
        if (f?.landing != null && f.headingSet) {
            val seq = landingSeq(prev)
            for (p in seq) { out.add(Pair(prev, p)); prev = p }
        } else if (f == null) out.add(Pair(prev, home))
        return out
    }

    /** Puntos de la final de la Disco desde [from]: (prealineación) → alineación → aproximación → toma. */
    private fun landingSeq(from: LatLon): List<LatLon> {
        val f = fw ?: return emptyList()
        val ld = f.landing ?: return emptyList()
        val s = ArrayList<LatLon>()
        if (f.needsPreAlign(from)) s.add(f.preAlignPoint!!)
        s.add(f.alignPoint!!); s.add(f.approachPoint!!); s.add(ld)
        return s
    }

    val routeLength: Double get() {
        var len = 0.0; var prev = home
        for (p in points) { len += GridPlanner.distance(prev, p); prev = p }
        return len
    }

    val totalLength: Double get() = passes.sumOf { GridPlanner.distance(it.first, it.second) }

    /** Tramo más corto entre puntos (la Disco necesita sitio para girar). */
    private val shortestLeg: Double get() =
        (1 until points.size).minOfOrNull { GridPlanner.distance(points[it - 1], points[it]) } ?: Double.MAX_VALUE

    private val farthest: Double get() = points.maxOfOrNull { GridPlanner.distance(home, it) } ?: 0.0

    override val estimatedSeconds: Double get() = if (fw != null)
        totalLength / FixedWing.CRUISE + points.size * 6.0 + 60.0
    else params.altitude / 2.5 * 2 + totalLength / params.speed + points.size * 3.0 + 10.0

    override fun summary(): String {
        val f = fw
        return if (f != null) String.format(Locale.ROOT,
            tr("DISCO · Ruta por waypoints\n%d puntos · altura %.0f m · crucero %.1f m/s\nRecorrido %.0f m (con la final) · tiempo estimado %.1f min\n%s"),
            points.size, params.altitude, FixedWing.CRUISE, totalLength, estimatedSeconds / 60,
            if (f.landing == null) tr("🛬 Aterrizaje SIN fijar")
            else String.format(Locale.ROOT, tr("🛬 Final rumbo %.0f° · aproximación a %.0f m y %.0f m de altura"), f.finalHeading, f.approachDist, f.approachAlt))
        else String.format(Locale.ROOT,
            tr("Ruta por waypoints · sin fotos programadas\n%d puntos · altura %.0f m · velocidad %.1f m/s\nRecorrido %.0f m (ida y vuelta) · tiempo estimado %.1f min"),
            points.size, params.altitude, params.speed, totalLength, estimatedSeconds / 60)
    }

    override fun blockers(): List<String> {
        val b = ArrayList<String>()
        if (points.size < 2) b.add(tr("Marca al menos 2 puntos de ruta."))
        if (params.altitude > 120.0) b.add(tr("Altura máxima 120 m."))
        if (points.size > 200) b.add(tr("Demasiados puntos (máx. 200)."))
        val f = fw
        if (f != null) {
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
            if (params.altitude < FixedWing.MIN_ALT) b.add(trf("La Disco necesita volar a %1\$s m o más.", FixedWing.MIN_ALT.toInt()))
            if (f.approachAlt !in 15.0..60.0) b.add(tr("Altura de aproximación entre 15 y 60 m."))
            if (f.approachDist !in 120.0..400.0) b.add(tr("Distancia de aproximación entre 120 y 400 m."))
            if (estimatedSeconds > FixedWing.USABLE_MIN * 60) b.add(trf("Vuelo estimado de %1\$s min: no cabe en una batería de Disco. Acorta la ruta.", "%.0f".format(estimatedSeconds / 60)))
        } else {
            val maxMin = if (params.camera.platform == Platform.ANAFI) Plan3D.ANAFI_USABLE_MIN else 18.0
            if (estimatedSeconds > maxMin * 60) b.add(trf("Vuelo estimado de %1\$s min: no cabe en una batería. Acorta la ruta.", "%.0f".format(estimatedSeconds / 60)))
        }
        return b
    }

    override fun warnings(): List<String> {
        val w = ArrayList<String>()
        if (fw != null && shortestLeg < 2 * FixedWing.TURN_RADIUS)
            w.add(trf("Hay puntos a menos de %1\$s m entre sí: la Disco los pasará de largo y girará en círculo para alcanzarlos.", (2 * FixedWing.TURN_RADIUS).toInt()))
        if (fw != null) w.add(trf("En los giros la Disco se abre hasta ~%1\$s m fuera de la ruta: que no haya obstáculos alrededor.", (2 * FixedWing.TURN_RADIUS).toInt()))
        if (farthest > 500) w.add(trf("El punto más lejano está a %1\$s m: mantén el dron a la vista (VLOS).", farthest.toInt()))
        if (params.altitude < 20 && fw == null) w.add(tr("Altura muy baja (<20 m): cuidado con árboles y cables en toda la ruta."))
        w.add(tr("La ruta no esquiva obstáculos: revísala en la ortofoto."))
        return w
    }

    override fun toMavlink(): String {
        val sb = StringBuilder("QGC WPL 120\n")
        var i = 0
        fun item(cmd: Int, p1: Double = 0.0, p2: Double = 0.0, p3: Double = 0.0, p4: Double = 0.0,
                 lat: Double = 0.0, lon: Double = 0.0, alt: Double = 0.0) {
            sb.append(String.format(Locale.ROOT, "%d\t0\t3\t%d\t%f\t%f\t%f\t%f\t%f\t%f\t%f\t1\n",
                i++, cmd, p1, p2, p3, p4, lat, lon, alt))
        }
        val alt = params.altitude
        val f = fw
        if (f != null) {
            // Disco: igual que el mapa de ala fija, pero sin cámara ni pasadas.
            val ld = requireNotNull(f.landing) { tr("Falta el aterrizaje") }
            // Ítem 0: entrada 60 m antes del primer punto (algunos autopilotos toman el ítem 0 como «casa» y lo saltan).
            points.firstOrNull()?.let { p0 ->
                val h = GridPlanner.bearing(p0, points.getOrElse(1) { p0 })
                val e = GridPlanner.destination(p0, h + 180.0, FixedWing.RUN_IN)
                item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = h, lat = e.lat, lon = e.lon, alt = alt)
            }
            for ((k, p) in points.withIndex()) {
                val next = points.getOrNull(k + 1)
                val yaw = if (next != null) GridPlanner.bearing(p, next) else GridPlanner.bearing(points.getOrElse(k - 1) { home }, p)
                item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = yaw, lat = p.lat, lon = p.lon, alt = alt)
            }
            val al = f.alignPoint!!; val ap = f.approachPoint!!
            val apAlt = f.approachAltRel
            val alignAlt = max(apAlt, min(alt, apAlt + 20.0))
            val last = points.lastOrNull() ?: home
            if (f.needsPreAlign(last)) { val pa = f.preAlignPoint!!
                item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = f.finalHeading, lat = pa.lat, lon = pa.lon, alt = max(alignAlt, min(alt, apAlt + 30.0))) }
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = f.finalHeading, lat = al.lat, lon = al.lon, alt = alignAlt)
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p3 = FixedWing.APPROACH_RADIUS, lat = ap.lat, lon = ap.lon, alt = apAlt)
            item(FlightPlan.CMD_LAND, lat = ld.lat, lon = ld.lon, alt = f.elevDiff)
            return sb.toString()
        }
        // Multirrotor: despegue, subida vertical en la casa, ruta, vuelta y aterrizaje.
        val anafi = params.camera.platform == Platform.ANAFI
        val p3 = if (anafi) 0.0 else -1.0
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = 2.5, p3 = p3)
        item(FlightPlan.CMD_TAKEOFF, lat = home.lat, lon = home.lon, alt = alt)
        if (anafi) item(FlightPlan.CMD_PARROT_VIEW_MODE, p1 = 0.0) else item(FlightPlan.CMD_PARROT_VIEW_MODE, p1 = 1.0)
        item(FlightPlan.CMD_WAYPOINT, p2 = 2.0, lat = home.lat, lon = home.lon, alt = alt)
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = params.speed, p3 = p3)
        var prev = home
        for (p in points) {
            item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = GridPlanner.bearing(prev, p), lat = p.lat, lon = p.lon, alt = alt)
            prev = p
        }
        item(FlightPlan.CMD_CHANGE_SPEED, p2 = GridPlanner.TRANSIT_SPEED, p3 = p3)
        item(FlightPlan.CMD_WAYPOINT, p2 = 5.0, p4 = GridPlanner.bearing(prev, home), lat = home.lat, lon = home.lon, alt = alt)
        item(FlightPlan.CMD_LAND, lat = home.lat, lon = home.lon, alt = 0.0)
        return sb.toString()
    }
}
