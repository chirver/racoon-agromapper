package com.racoonair.mapper

import android.graphics.Bitmap
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * NDVI de vista previa a partir de las imágenes «live stitch» de la Sequoia (las que Pix4Dcapture/ParrotFields
 * bajan durante el vuelo). Parrot no documenta su formato: aquí se lee cualquier TIFF sin comprimir (8/16/32 bits,
 * una o varias páginas, una o varias bandas por página) y se describe en el registro lo que trae, para confirmarlo
 * en la primera prueba real.
 */
object LiveNdvi {

    class Band(val w: Int, val h: Int, val v: FloatArray) {
        fun mean(): Double { var s = 0.0; for (x in v) s += x; return if (v.isEmpty()) 0.0 else s / v.size }
    }

    class Result(val info: String, val bands: List<Band>, val ndvi: FloatArray?, val w: Int, val h: Int, val png: File?) {
        fun ndviMean(): Double? = ndvi?.let { a -> var s = 0.0; var n = 0; for (x in a) if (!x.isNaN()) { s += x; n++ }; if (n == 0) null else s / n }
    }

    /** Lee el TIFF, calcula el NDVI y guarda un PNG coloreado al lado. Nunca lanza: devuelve la descripción del fallo. */
    fun process(tif: File, nirFirst: Boolean = true): Result = try {
        val (bands, info) = readTiff(tif)
        var ndvi: FloatArray? = null; var w = 0; var h = 0; var png: File? = null
        if (bands.size >= 2 && bands[0].w == bands[1].w && bands[0].h == bands[1].h) {
            // Orden de las bandas: el de los bits de la máscara (NIR = bit 0, rojo = bit 2) → NIR primero.
            val nir = if (nirFirst) bands[0] else bands[1]; val red = if (nirFirst) bands[1] else bands[0]
            w = nir.w; h = nir.h
            val out = FloatArray(w * h)
            for (i in out.indices) { val n = nir.v[i]; val r = red.v[i]; out[i] = if (n + r <= 0f) Float.NaN else (n - r) / (n + r) }
            ndvi = out
            png = File(tif.parentFile, tif.nameWithoutExtension + "_ndvi.png")
            png.outputStream().use { colorize(out, w, h).compress(Bitmap.CompressFormat.PNG, 100, it) }
        }
        Result(info, bands, ndvi, w, h, png)
    } catch (t: Throwable) { Result("${t.javaClass.simpleName}: ${t.message}", emptyList(), null, 0, 0, null) }

    /** Rojo (suelo, < 0,2) → amarillo → verde (vegetación vigorosa, > 0,8). */
    fun colorize(nd: FloatArray, w: Int, h: Int): Bitmap {
        val px = IntArray(w * h)
        for (i in nd.indices) {
            val x = nd[i]
            px[i] = if (x.isNaN()) 0 else {
                val t = ((x - 0.2f) / 0.6f).coerceIn(0f, 1f)
                val r = if (t < 0.5f) 215 else (215 * (1 - (t - 0.5f) * 2)).toInt()
                val g = if (t < 0.5f) (40 + 400 * t).toInt() else 200
                (0xFF shl 24) or (r.coerceIn(0, 255) shl 16) or (g.coerceIn(0, 255) shl 8) or 40
            }
        }
        return Bitmap.createBitmap(px, w, h, Bitmap.Config.ARGB_8888)
    }

    // ------------------------------------------------------------------ TIFF mínimo

    private fun readTiff(f: File): Pair<List<Band>, String> {
        val bytes = f.readBytes()
        val bb = ByteBuffer.wrap(bytes)
        bb.order(if (bytes[0] == 'I'.code.toByte()) ByteOrder.LITTLE_ENDIAN else ByteOrder.BIG_ENDIAN)
        require(bb.getShort(2).toInt() == 42) { "no es un TIFF (o es BigTIFF)" }
        var ifd = bb.getInt(4).toLong() and 0xFFFFFFFFL
        val bands = ArrayList<Band>()
        val info = StringBuilder("${bytes.size} bytes")
        var page = 0
        while (ifd in 8 until bytes.size && page < 16) {
            val n = bb.getShort(ifd.toInt()).toInt() and 0xFFFF
            val tags = HashMap<Int, LongArray>()
            for (i in 0 until n) {
                val e = ifd.toInt() + 2 + i * 12
                val tag = bb.getShort(e).toInt() and 0xFFFF
                val type = bb.getShort(e + 2).toInt() and 0xFFFF
                val cnt = bb.getInt(e + 4)
                val size = when (type) { 3 -> 2; 4 -> 4; 1, 2, 7 -> 1; else -> 4 }
                val off = if (size * cnt <= 4) e + 8 else bb.getInt(e + 8)
                if (cnt in 1..100_000 && type in setOf(1, 3, 4)) tags[tag] = LongArray(cnt) { k ->
                    when (type) { 3 -> (bb.getShort(off + k * 2).toLong() and 0xFFFF); 4 -> (bb.getInt(off + k * 4).toLong() and 0xFFFFFFFFL); else -> (bytes[off + k].toLong() and 0xFF) }
                }
            }
            val w = tags[256]?.get(0)?.toInt() ?: 0; val h = tags[257]?.get(0)?.toInt() ?: 0
            val bps = tags[258]?.get(0)?.toInt() ?: 1; val comp = tags[259]?.get(0)?.toInt() ?: 1
            val spp = tags[277]?.get(0)?.toInt() ?: 1; val planar = tags[284]?.get(0)?.toInt() ?: 1
            val fmt = tags[339]?.get(0)?.toInt() ?: 1
            info.append(" · pág $page: ${w}×$h, $bps bits, $spp banda(s), compresión $comp, planar $planar, formato $fmt")
            if (comp == 1 && w > 0 && h > 0 && tags[273] != null) {
                val offs = tags[273]!!; val rps = tags[278]?.get(0)?.toInt() ?: h
                val bpsB = bps / 8
                val planes = if (planar == 2) spp else 1
                val per = if (planar == 2) 1 else spp
                val vals = Array(spp) { FloatArray(w * h) }
                val stripsPerPlane = (h + rps - 1) / rps
                for (p in 0 until planes) for (s in 0 until stripsPerPlane) {
                    val idx = p * stripsPerPlane + s
                    if (idx >= offs.size) break
                    var o = offs[idx].toInt()
                    val y0 = s * rps
                    for (y in y0 until minOf(h, y0 + rps)) for (x in 0 until w) for (c in 0 until per) {
                        val v = when {
                            fmt == 3 && bpsB == 4 -> bb.getFloat(o)
                            bpsB == 2 -> (bb.getShort(o).toInt() and 0xFFFF).toFloat()
                            bpsB == 4 -> (bb.getInt(o).toLong() and 0xFFFFFFFFL).toFloat()
                            else -> (bytes[o].toInt() and 0xFF).toFloat()
                        }
                        vals[if (planar == 2) p else c][y * w + x] = v; o += bpsB
                    }
                }
                vals.forEach { bands.add(Band(w, h, it)) }
            }
            page++
            val next = ifd.toInt() + 2 + n * 12
            ifd = if (next + 4 <= bytes.size) bb.getInt(next).toLong() and 0xFFFFFFFFL else 0
        }
        info.append(" · bandas leídas ${bands.size}")
        bands.forEachIndexed { i, b -> info.append(", b${i + 1} media %.0f".format(b.mean())) }
        return bands to info.toString()
    }
}
