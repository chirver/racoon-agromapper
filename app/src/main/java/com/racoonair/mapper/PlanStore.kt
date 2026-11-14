package com.racoonair.mapper

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/**
 * Guarda zonas (polígono + parámetros, para reeditar) y planes .mavlink generados.
 * Carpeta: Android/data/com.racoonair.mapper/files/planes (accesible por USB desde el PC).
 */
object PlanStore {
    fun dir(ctx: Context): File =
        (ctx.getExternalFilesDir("planes") ?: File(ctx.filesDir, "planes")).also { it.mkdirs() }

    fun safeName(name: String) = name.trim().replace(Regex("[^A-Za-z0-9áéíóúÁÉÍÓÚñÑ _.-]"), "_").ifEmpty { "plan" }

    fun save(ctx: Context, name: String, zone: List<LatLon>, params: GridParams, home: LatLon?, mavlink: String, spec3D: Spec3D? = null): File {
        val base = safeName(name)
        val d = dir(ctx)
        val j = JSONObject()
        j.put("name", name)
        j.put("zone", JSONArray().apply { zone.forEach { put(JSONArray().put(it.lat).put(it.lon)) } })
        home?.let { j.put("home", JSONArray().put(it.lat).put(it.lon)) }
        j.put("altitude", params.altitude); j.put("front", params.frontOverlap); j.put("side", params.sideOverlap)
        j.put("speed", params.speed); j.put("overshoot", params.overshoot); j.put("camera", params.camera.id)
        params.angleDeg?.let { j.put("angle", it) }
        params.fixedWing?.let { f -> j.put("fixedWing", org.json.JSONObject().apply {
            f.landing?.let { put("landing", JSONArray().put(it.lat).put(it.lon)) }
            put("finalHeading", f.finalHeading); put("headingSet", f.headingSet)
            put("approachDist", f.approachDist); put("approachAlt", f.approachAlt)
            put("elevDiff", f.elevDiff); put("elevSet", f.elevSet); put("droneCamera", f.droneCamera)
        }) }
        spec3D?.takeIf { it.type.is3D }?.let { s -> j.put("mission3D", JSONObject().apply {
            put("type", s.type.name); put("objectHeight", s.objectHeight); put("standoff", s.standoff)
            put("rings", s.rings); put("obliqueTilt", s.obliqueTilt); put("minAlt", s.minAlt)
            put("speed", s.speed); put("baseElev", s.baseElev)
        }) }
        File(d, "$base.zona.json").writeText(j.toString(2))
        val f = File(d, "$base.mavlink")
        f.writeText(mavlink)
        return f
    }

    data class Zone(val name: String, val zone: List<LatLon>, val params: GridParams, val home: LatLon?, val fixedWing: FixedWing? = null,
                    val spec3D: Spec3D = Spec3D())

    fun loadZone(f: File): Zone {
        val j = JSONObject(f.readText())
        val z = j.getJSONArray("zone")
        val pts = (0 until z.length()).map { val a = z.getJSONArray(it); LatLon(a.getDouble(0), a.getDouble(1)) }
        val home = j.optJSONArray("home")?.let { LatLon(it.getDouble(0), it.getDouble(1)) }
        val p = GridParams(
            altitude = j.optDouble("altitude", 50.0), frontOverlap = j.optDouble("front", 0.8),
            sideOverlap = j.optDouble("side", 0.7), speed = j.optDouble("speed", 8.0),
            overshoot = j.optDouble("overshoot", 5.0), angleDeg = if (j.has("angle")) j.getDouble("angle") else null,
            camera = Camera.byId(j.optString("camera", Camera.DEFAULT.id)))
        val fw = j.optJSONObject("fixedWing")?.let { o ->
            FixedWing(landing = o.optJSONArray("landing")?.let { LatLon(it.getDouble(0), it.getDouble(1)) },
                finalHeading = o.optDouble("finalHeading", 0.0), headingSet = o.optBoolean("headingSet", false),
                approachDist = o.optDouble("approachDist", 200.0), approachAlt = o.optDouble("approachAlt", 30.0),
                elevDiff = o.optDouble("elevDiff", 0.0), elevSet = o.optBoolean("elevSet", false),
                droneCamera = o.optBoolean("droneCamera", false))
        }
        val s3 = j.optJSONObject("mission3D")?.let { o ->
            val d = Spec3D()
            Spec3D(type = runCatching { MissionType.valueOf(o.optString("type")) }.getOrDefault(MissionType.GRID),
                objectHeight = o.optDouble("objectHeight", d.objectHeight), standoff = o.optDouble("standoff", d.standoff),
                rings = o.optInt("rings", d.rings), obliqueTilt = o.optDouble("obliqueTilt", d.obliqueTilt),
                minAlt = o.optDouble("minAlt", d.minAlt), speed = o.optDouble("speed", d.speed), baseElev = o.optDouble("baseElev", 0.0))
        } ?: Spec3D()
        return Zone(j.optString("name", f.name.removeSuffix(".zona.json")), pts, p, home, fw, s3)
    }

    fun zones(ctx: Context) = dir(ctx).listFiles { f -> f.name.endsWith(".zona.json") }?.sortedByDescending { it.lastModified() } ?: emptyList()
    fun plans(ctx: Context) = dir(ctx).listFiles { f -> f.name.endsWith(".mavlink") }?.sortedByDescending { it.lastModified() } ?: emptyList()
}
