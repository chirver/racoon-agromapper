package com.racoonair.mapper

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Puntos calientes: se guardan en disco al momento (sobreviven a cerrar la app), se exportan a CSV/KML y
 * sus fotos van a la galería con la posición del PUNTO escrita en el EXIF (Google Fotos o un SIG las sitúan solas).
 */
object HotspotStore {
    private const val FILE = "puntos_calientes.json"

    private fun file(ctx: Context) = File(ctx.filesDir, FILE)

    fun save(ctx: Context, list: List<Hotspot>) {
        try {
            val a = JSONArray()
            for (h in list) a.put(JSONObject().apply {
                put("lat", h.lat); put("lon", h.lon); put("alt", if (h.alt.isNaN()) JSONObject.NULL else h.alt)
                put("temp", h.tempC ?: JSONObject.NULL); put("time", h.time); put("file", h.file ?: JSONObject.NULL)
                put("projected", h.projected)
            })
            file(ctx).writeText(a.toString())
        } catch (_: Exception) {}
    }

    fun load(ctx: Context): List<Hotspot> = try {
        val a = JSONArray(file(ctx).readText())
        (0 until a.length()).map { i ->
            val o = a.getJSONObject(i)
            Hotspot(o.getDouble("lat"), o.getDouble("lon"), o.optDouble("alt", Double.NaN),
                if (o.isNull("temp")) null else o.getDouble("temp"), o.getLong("time"),
                if (o.isNull("file")) null else o.getString("file"), o.optBoolean("projected", false))
        }
    } catch (_: Exception) { emptyList() }

    fun label(i: Int, h: Hotspot): String {
        val hora = SimpleDateFormat("HH:mm:ss", Locale.ROOT).format(Date(h.time))
        val t = h.tempC?.let { "%.1f °C".format(it) } ?: "—"
        return trf("#%1\$s · %2\$s · %3\$s · %4\$s", i + 1, t, hora, if (h.projected) tr("en el suelo") else tr("posición del dron"))
    }

    fun coords(h: Hotspot) = "%.6f, %.6f".format(Locale.ROOT, h.lat, h.lon)

    /** Abre el punto en Google Maps (o en la app de mapas que haya) para ir hasta él. */
    fun openInMaps(a: Activity, i: Int, h: Hotspot) {
        val q = "%.6f,%.6f".format(Locale.ROOT, h.lat, h.lon)
        val uri = Uri.parse("geo:$q?q=$q(" + Uri.encode(trf("Punto caliente %1\$s", i + 1)) + ")")
        try { a.startActivity(Intent(Intent.ACTION_VIEW, uri)) }
        catch (_: Exception) { a.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=$q"))) }
    }

    fun shareOne(a: Activity, i: Int, h: Hotspot) {
        val q = "%.6f,%.6f".format(Locale.ROOT, h.lat, h.lon)
        val txt = label(i, h) + "\n" + coords(h) + "\nhttps://www.google.com/maps/search/?api=1&query=$q"
        a.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, txt), tr("Compartir punto")))
    }

    /** CSV para Excel o un SIG: n, fecha, hora, lat, lon, temperatura, tipo de posición, altura del dron, foto. */
    fun csv(list: List<Hotspot>): String {
        val sb = StringBuilder("n;fecha;hora;lat;lon;temp_c;posicion;alt_dron_m;foto\n")
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT); val hf = SimpleDateFormat("HH:mm:ss", Locale.ROOT)
        list.forEachIndexed { i, h ->
            sb.append("${i + 1};${df.format(Date(h.time))};${hf.format(Date(h.time))};")
            sb.append("%.7f;%.7f;".format(Locale.ROOT, h.lat, h.lon))
            sb.append(h.tempC?.let { "%.1f".format(Locale.ROOT, it) } ?: "").append(';')
            sb.append(if (h.projected) "suelo" else "dron").append(';')
            sb.append(if (h.alt.isNaN()) "" else "%.1f".format(Locale.ROOT, h.alt)).append(';')
            sb.append(h.file?.substringAfterLast('/') ?: "").append('\n')
        }
        return sb.toString()
    }

    /** Guarda el CSV en Descargas/RacoonMapper y abre el menú de compartir. */
    fun exportCsv(a: Activity, list: List<Hotspot>) {
        if (list.isEmpty()) { Toast.makeText(a, tr("No hay puntos calientes"), Toast.LENGTH_SHORT).show(); return }
        val name = "puntos_calientes_" + SimpleDateFormat("yyyyMMdd_HHmm", Locale.ROOT).format(Date()) + ".csv"
        val data = csv(list).toByteArray()
        try {
            if (android.os.Build.VERSION.SDK_INT < 29) {
                val f = File(PlanStore.dir(a), name); f.writeBytes(data)
                Toast.makeText(a, trf("Guardado en %1\$s", f.absolutePath), Toast.LENGTH_LONG).show(); return
            }
            val values = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, name)
                put(MediaStore.MediaColumns.MIME_TYPE, "text/csv")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + "/RacoonMapper")
            }
            val uri = a.contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values) ?: throw IllegalStateException("MediaStore")
            a.contentResolver.openOutputStream(uri)?.use { it.write(data) }
            Toast.makeText(a, trf("Guardado en %1\$s", "Descargas/RacoonMapper/$name"), Toast.LENGTH_LONG).show()
            a.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).setType("text/csv")
                .putExtra(Intent.EXTRA_STREAM, uri).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION), tr("Compartir puntos")))
        } catch (e: Exception) { Toast.makeText(a, trf("No se pudo exportar: %1\$s", e.message), Toast.LENGTH_LONG).show() }
    }

    /**
     * Escribe en la foto la posición del punto (EXIF GPS), la temperatura y la hora, y la copia a la galería
     * (Imágenes/RacoonAgroMapper). Devuelve la ruta o URI de la copia visible, o null. Llamar fuera del hilo principal.
     */
    fun geotagAndPublish(ctx: Context, path: String, h: Hotspot, n: Int): String? = try {
        val ex = android.media.ExifInterface(path)
        fun dms(v: Double): String {
            val a = Math.abs(v); val d = a.toInt(); val mf = (a - d) * 60; val m = mf.toInt(); val sec = (mf - m) * 60
            return "$d/1,$m/1,${Math.round(sec * 10000)}/10000"
        }
        ex.setAttribute(android.media.ExifInterface.TAG_GPS_LATITUDE, dms(h.lat))
        ex.setAttribute(android.media.ExifInterface.TAG_GPS_LATITUDE_REF, if (h.lat >= 0) "N" else "S")
        ex.setAttribute(android.media.ExifInterface.TAG_GPS_LONGITUDE, dms(h.lon))
        ex.setAttribute(android.media.ExifInterface.TAG_GPS_LONGITUDE_REF, if (h.lon >= 0) "E" else "W")
        if (!h.alt.isNaN()) {
            ex.setAttribute(android.media.ExifInterface.TAG_GPS_ALTITUDE, "${Math.round(Math.abs(h.alt) * 100)}/100")
            ex.setAttribute(android.media.ExifInterface.TAG_GPS_ALTITUDE_REF, if (h.alt >= 0) "0" else "1")
        }
        ex.setAttribute(android.media.ExifInterface.TAG_DATETIME, SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.ROOT).format(Date(h.time)))
        ex.setAttribute(android.media.ExifInterface.TAG_IMAGE_DESCRIPTION,
            "Racoon AgroMapper - punto caliente $n" + (h.tempC?.let { " - %.1f C".format(Locale.ROOT, it) } ?: "") +
                if (h.projected) " - posicion en el suelo" else " - posicion del dron")
        ex.saveAttributes()
        if (android.os.Build.VERSION.SDK_INT >= 29) {
            val values = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, File(path).name)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/RacoonAgroMapper")
            }
            val uri = ctx.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            if (uri != null) { ctx.contentResolver.openOutputStream(uri)?.use { o -> File(path).inputStream().use { it.copyTo(o) } }; uri.toString() } else path
        } else path
    } catch (_: Exception) { null }

    /** Ver la foto de un punto (la copia de la galería si existe; si no, la del almacén de la app). */
    fun openPhoto(a: Activity, h: Hotspot) {
        val f = h.file ?: run { Toast.makeText(a, tr("Este punto no tiene foto"), Toast.LENGTH_SHORT).show(); return }
        try {
            val uri = if (f.startsWith("content://")) Uri.parse(f) else {
                Toast.makeText(a, trf("Foto en %1\$s", f), Toast.LENGTH_LONG).show(); return
            }
            a.startActivity(Intent(Intent.ACTION_VIEW).setDataAndType(uri, "image/jpeg").addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION))
        } catch (e: Exception) { Toast.makeText(a, trf("No se pudo abrir: %1\$s", e.message), Toast.LENGTH_SHORT).show() }
    }
}
