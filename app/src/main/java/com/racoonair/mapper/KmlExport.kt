package com.racoonair.mapper

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import java.io.File
import java.util.Locale

/** Exporta zonas, planes y vuelos a KML (Google Earth, QGIS) en Descargas/RacoonMapper. */
object KmlExport {
    private fun esc(s: String) = s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
    private fun coords(pts: List<LatLon>, alt: Double? = null) =
        pts.joinToString(" ") { String.format(Locale.ROOT, "%.7f,%.7f,%.1f", it.lon, it.lat, alt ?: 0.0) }

    class Doc(val name: String) {
        private val sb = StringBuilder()
        fun polygon(n: String, pts: List<LatLon>) = apply {
            if (pts.size < 3) return@apply
            sb.append("<Placemark><name>${esc(n)}</name><styleUrl>#zona</styleUrl><Polygon><outerBoundaryIs><LinearRing><coordinates>")
                .append(coords(pts + pts.first())).append("</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>\n")
        }
        fun line(n: String, pts: List<LatLon>, style: String, alt: Double? = null) = apply {
            if (pts.size < 2) return@apply
            sb.append("<Placemark><name>${esc(n)}</name><styleUrl>#$style</styleUrl><LineString>")
            if (alt != null) sb.append("<altitudeMode>relativeToGround</altitudeMode>")
            sb.append("<coordinates>").append(coords(pts, alt)).append("</coordinates></LineString></Placemark>\n")
        }
        fun point(n: String, p: LatLon, style: String) = apply {
            sb.append("<Placemark><name>${esc(n)}</name><styleUrl>#$style</styleUrl><Point><coordinates>")
                .append(coords(listOf(p))).append("</coordinates></Point></Placemark>\n")
        }
        fun build(): String = """<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2"><Document><name>${esc(name)}</name>
<Style id="zona"><LineStyle><color>ff84dc3d</color><width>3</width></LineStyle><PolyStyle><color>4032a852</color></PolyStyle></Style>
<Style id="plan"><LineStyle><color>ff07c1ff</color><width>3</width></LineStyle></Style>
<Style id="vuelo"><LineStyle><color>ff4417ff</color><width>4</width></LineStyle></Style>
<Style id="casa"><IconStyle><scale>1.2</scale><Icon><href>http://maps.google.com/mapfiles/kml/paddle/H.png</href></Icon></IconStyle></Style>
$sb</Document></kml>
"""
    }

    /** Ruta del plan (waypoints) y casa. */
    fun addPlan(d: Doc, p: FlightPlan, name: String) {
        val wps = p.waypoints
        d.line("Plan: $name", wps.map { LatLon(it.lat, it.lon) }, "plan", wps.maxOfOrNull { it.alt })
        p.homePoint?.let { d.point("Casa", LatLon(it.lat, it.lon), "casa") }
    }

    /** Rastro de un CSV de vuelo de FlightRecorder. */
    fun trackFromCsv(f: File): List<LatLon> = f.readLines()
        .filter { it.isNotBlank() && !it.startsWith("#") && !it.startsWith("t_s") }
        .mapNotNull { r -> val c = r.split(','); val la = c.getOrNull(1)?.toDoubleOrNull(); val lo = c.getOrNull(2)?.toDoubleOrNull()
            if (la != null && lo != null) LatLon(la, lo) else null }

    /** Guarda en Descargas/RacoonMapper y ofrece compartir. */
    fun saveAndShare(a: Activity, fileName: String, kml: String) {
        try {
            val name = PlanStore.safeName(fileName).removeSuffix(".kml") + ".kml"
            if (android.os.Build.VERSION.SDK_INT < 29) {
                val f = File(PlanStore.dir(a), name); f.writeText(kml)
                Toast.makeText(a, "Guardado en ${f.absolutePath}", Toast.LENGTH_LONG).show(); return
            }
            val values = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, name)
                put(MediaStore.MediaColumns.MIME_TYPE, "application/vnd.google-earth.kml+xml")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + "/RacoonMapper")
            }
            val uri: Uri = a.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values)
                ?: throw IllegalStateException("no se pudo crear el fichero")
            a.contentResolver.openOutputStream(uri)?.use { it.write(kml.toByteArray()) }
            Toast.makeText(a, "Guardado en Descargas/RacoonMapper/$name", Toast.LENGTH_LONG).show()
            a.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).setType("application/vnd.google-earth.kml+xml")
                .putExtra(Intent.EXTRA_STREAM, uri).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION), "Compartir KML"))
        } catch (e: Exception) {
            Toast.makeText(a, "No se pudo exportar: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
