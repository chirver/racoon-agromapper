package com.racoonair.mapper

import android.graphics.Bitmap
import kotlin.math.max
import kotlin.math.min

/**
 * Térmica de la ANAFI Thermal (FLIR Lepton 3.5, radiométrica, 160×120).
 *
 * El dron colorea la térmica dentro del vídeo (modo EMBEDDED) con la paleta que le mandamos. Como la paleta
 * la elegimos nosotros, podemos deshacer el coloreado: cada color del vídeo corresponde a una temperatura
 * cuando la paleta es ABSOLUTA (rango fijo en °C). Es una lectura aproximada (compresión del vídeo, ±2–3 °C),
 * suficiente para encontrar a una persona, un animal o un rescoldo, no para medir con certificado.
 */
enum class ThermalPaletteMode { RELATIVE, ABSOLUTE, SEARCH }

data class ThermalPrefs(
    /** Cómo se colorea el vídeo térmico. */
    val mode: ThermalPaletteMode = ThermalPaletteMode.ABSOLUTE,
    /** Rango de la paleta absoluta en el vídeo (°C). */
    val min: Double = 0.0,
    val max: Double = 40.0,
    /** Búsqueda: solo se colorea lo que pase de este umbral (°C). Persona al aire libre: 28–32 °C de piel. */
    val threshold: Double = 28.0,
    /** Aviso sonoro y marca en el mapa cuando algo supera [alertTemp] (absoluta) o el umbral (búsqueda). */
    val alert: Boolean = true,
    val alertTemp: Double = 30.0,
    /** Mosaico térmico: paleta fija durante la misión, para que todas las fotos tengan los mismos colores. */
    val missionLock: Boolean = true,
    val missionMin: Double = 0.0,
    val missionMax: Double = 40.0,
    /** Mosaico térmico: calibrar el sensor (obturador) en cada giro, nunca en mitad de una pasada. */
    val ffcPerPass: Boolean = true
)

/** Punto caliente detectado en vuelo. */
data class Hotspot(val lat: Double, val lon: Double, val alt: Double, val tempC: Double?, val time: Long, val file: String?)

object ThermalPalettes {
    class Stop(val pos: Double, val r: Double, val g: Double, val b: Double)

    /** «Hierro»: negro → azul/violeta → rojo → naranja → amarillo → blanco. Monótona en brillo para poder deshacerla. */
    val IRON = listOf(
        Stop(0.00, 0.00, 0.00, 0.00), Stop(0.15, 0.15, 0.00, 0.45), Stop(0.35, 0.55, 0.00, 0.55),
        Stop(0.55, 0.85, 0.15, 0.15), Stop(0.75, 1.00, 0.50, 0.00), Stop(0.90, 1.00, 0.85, 0.15),
        Stop(1.00, 1.00, 1.00, 1.00))

    /** Búsqueda: lo que pasa del umbral sale en rojo → amarillo, muy saturado (el resto queda gris). */
    val SPOT = listOf(Stop(0.0, 1.0, 0.0, 0.0), Stop(0.5, 1.0, 0.35, 0.0), Stop(1.0, 1.0, 0.95, 0.0))

    /** Tabla de 256 colores de la paleta hierro, para deshacer el coloreado. */
    val LUT: IntArray by lazy { IntArray(256) { i -> colorAt(IRON, i / 255.0) } }

    fun colorAt(stops: List<Stop>, x: Double): Int {
        val t = x.coerceIn(0.0, 1.0)
        val j = stops.indexOfFirst { it.pos >= t }.let { if (it <= 0) 1 else it }
        val a = stops[j - 1]; val b = stops[j]
        val f = if (b.pos == a.pos) 0.0 else (t - a.pos) / (b.pos - a.pos)
        fun ch(u: Double, v: Double) = ((u + (v - u) * f) * 255).toInt().coerceIn(0, 255)
        return (0xFF shl 24) or (ch(a.r, b.r) shl 16) or (ch(a.g, b.g) shl 8) or ch(a.b, b.b)
    }
}

object ThermalScan {
    /**
     * Resultado de analizar un fotograma.
     * [maxC]/[centerC]: temperaturas estimadas (solo paleta absoluta). [hot]: hay un punto que dispara el aviso.
     * [hx]/[hy]: posición relativa (0..1) del punto más caliente en el fotograma.
     */
    data class Result(val maxC: Double?, val centerC: Double?, val hot: Boolean, val hotPixels: Int, val hx: Float, val hy: Float)

    /** Un punto caliente es algo pequeño: entre el 0,05 % y el 5 % de la imagen. Más = suelo caliente, no aviso. */
    private const val HOT_MIN_FRAC = 0.0005
    private const val HOT_MAX_FRAC = 0.05

    private const val COLS = 96
    private const val ROWS = 72
    /** Distancia de color máxima para aceptar que un píxel es de la paleta (texto, bordes, artefactos fuera). */
    private const val MAX_DIST2 = 60 * 60 * 3

    fun analyze(bmp: Bitmap, p: ThermalPrefs): Result {
        val w = bmp.width; val h = bmp.height
        if (w < COLS || h < ROWS) return Result(null, null, false, 0, 0.5f, 0.5f)
        val px = IntArray(w)
        val temps = ArrayList<Double>(COLS * ROWS)
        var best = -1e9; var bx = 0.5f; var by = 0.5f
        var hotN = 0; var hotSumX = 0f; var hotSumY = 0f; var valid = 0
        val center = ArrayList<Double>()
        val span = p.max - p.min
        for (j in 0 until ROWS) {
            val y = ((j + 0.5) * h / ROWS).toInt()
            bmp.getPixels(px, 0, w, 0, y, w, 1)
            for (i in 0 until COLS) {
                val x = ((i + 0.5) * w / COLS).toInt()
                val c = px[x]
                val r = (c shr 16) and 0xFF; val g = (c shr 8) and 0xFF; val b = c and 0xFF
                if (r + g + b < 12) continue // bandas negras alrededor del vídeo: no son imagen
                valid++
                val fx = (i + 0.5f) / COLS; val fy = (j + 0.5f) / ROWS
                when (p.mode) {
                    ThermalPaletteMode.SEARCH -> {
                        // Rojo/naranja/amarillo saturado = por encima del umbral; gris = por debajo.
                        if (r > 170 && r - b > 120 && g < r - 10 && b < 110) { hotN++; hotSumX += fx; hotSumY += fy }
                    }
                    ThermalPaletteMode.ABSOLUTE -> {
                        val k = nearest(r, g, b) ?: continue
                        val t = p.min + span * k / 255.0
                        temps.add(t)
                        if (t > best) { best = t; bx = fx; by = fy }
                        if (fx in 0.46f..0.54f && fy in 0.45f..0.55f) center.add(t)
                        if (t >= p.alertTemp) { hotN++; hotSumX += fx; hotSumY += fy }
                    }
                    ThermalPaletteMode.RELATIVE -> {}
                }
            }
        }
        return when (p.mode) {
            ThermalPaletteMode.ABSOLUTE -> {
                if (temps.size < 50) return Result(null, null, false, 0, 0.5f, 0.5f)
                temps.sort()
                // Percentil 99,7 en vez del máximo: un píxel suelto de compresión no dispara nada.
                val maxC = temps[min(temps.size - 1, (temps.size * 0.997).toInt())]
                val c = if (center.isEmpty()) null else center.sorted()[center.size / 2]
                val hot = p.alert && isSpot(hotN, valid)
                Result(maxC, c, hot, hotN, if (hotN > 0) hotSumX / hotN else bx, if (hotN > 0) hotSumY / hotN else by)
            }
            ThermalPaletteMode.SEARCH -> {
                val hot = p.alert && isSpot(hotN, valid)
                Result(null, null, hot, hotN, if (hotN > 0) hotSumX / hotN else 0.5f, if (hotN > 0) hotSumY / hotN else 0.5f)
            }
            else -> Result(null, null, false, 0, 0.5f, 0.5f)
        }
    }

    private fun isSpot(n: Int, valid: Int) = valid > 200 && n >= 3 && n.toDouble() / valid in HOT_MIN_FRAC..HOT_MAX_FRAC

    /** Índice 0..255 de la paleta hierro más parecido al color, o null si no se parece a ninguno. -2 = sin calcular. */
    private val cache = IntArray(1 shl 18) { -2 }
    private fun nearest(r: Int, g: Int, b: Int): Int? {
        val key = ((r shr 2) shl 12) or ((g shr 2) shl 6) or (b shr 2)
        cache[key].let { if (it != -2) return if (it < 0) null else it }
        var bi = -1; var bd = Int.MAX_VALUE
        val lut = ThermalPalettes.LUT
        for (i in 0 until 256) {
            val c = lut[i]
            val dr = r - ((c shr 16) and 0xFF); val dg = g - ((c shr 8) and 0xFF); val db = b - (c and 0xFF)
            val d = dr * dr + dg * dg + db * db
            if (d < bd) { bd = d; bi = i }
        }
        val res = if (bd <= MAX_DIST2) bi else -1
        cache[key] = res
        return if (res < 0) null else res
    }

    /** Escala de colores para la leyenda (de abajo, frío, a arriba, caliente). */
    fun legendColors(p: ThermalPrefs): IntArray = when (p.mode) {
        ThermalPaletteMode.SEARCH -> intArrayOf(0xFF808080.toInt(), 0xFF808080.toInt(), ThermalPalettes.colorAt(ThermalPalettes.SPOT, 0.0), ThermalPalettes.colorAt(ThermalPalettes.SPOT, 1.0))
        else -> IntArray(16) { ThermalPalettes.colorAt(ThermalPalettes.IRON, it / 15.0) }
    }

    @Suppress("unused") private fun clamp(v: Double) = max(0.0, min(1.0, v))
}
