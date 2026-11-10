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
    val ffcPerPass: Boolean = true,
    /** Aviso por diferencia con el entorno (°C sobre la mediana de la imagen; 0 = no). Placas solares, noche. */
    val alertDelta: Double = 0.0,
    /** En misión: al ver un punto caliente, parar, hacer foto con la cámara del dron y seguir. */
    val pauseOnHot: Boolean = false
)

/** Punto caliente detectado en vuelo. */
data class Hotspot(val lat: Double, val lon: Double, val alt: Double, val tempC: Double?, val time: Long, val file: String?,
                   /** true = posición del punto en el suelo (proyectada desde la cámara); false = posición del dron. */
                   val projected: Boolean = false)

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
    data class Result(val maxC: Double?, val centerC: Double?, val hot: Boolean, val hotPixels: Int, val hx: Float, val hy: Float,
                      val valid: Int = 0, val medianC: Double? = null,
                      /** Diagnóstico: colores más frecuentes del fotograma («#rrggbb ×n»), para ver qué paleta pinta el dron. */
                      val colors: String = "")

    /** Un punto caliente ocupa entre el 0,05 % y el 30 % de la imagen. Más = todo el suelo caliente, no aviso. */
    private const val HOT_MIN_FRAC = 0.0005
    private const val HOT_MAX_FRAC = 0.30

    private const val COLS = 96
    private const val ROWS = 72
    /** Distancia de color máxima para aceptar que un píxel es de la paleta (texto, bordes, artefactos fuera). */
    private const val MAX_DIST2 = 60 * 60 * 3

    fun analyze(bmp: Bitmap, p: ThermalPrefs): Result {
        val w = bmp.width; val h = bmp.height
        if (w < COLS || h < ROWS) return Result(null, null, false, 0, 0.5f, 0.5f)
        val px = IntArray(w)
        val n = COLS * ROWS
        val tv = DoubleArray(n); val xs = FloatArray(n); val ys = FloatArray(n)
        var k = 0; var valid = 0
        var minX = 1f; var maxX = 0f; var minY = 1f; var maxY = 0f
        var hotN = 0; var hotSumX = 0f; var hotSumY = 0f
        val span = p.max - p.min
        val hist = HashMap<Int, Int>()
        for (j in 0 until ROWS) {
            val y = ((j + 0.5) * h / ROWS).toInt()
            bmp.getPixels(px, 0, w, 0, y, w, 1)
            for (i in 0 until COLS) {
                val c = px[((i + 0.5) * w / COLS).toInt()]
                val r = (c shr 16) and 0xFF; val g = (c shr 8) and 0xFF; val b = c and 0xFF
                if (r + g + b < 12) continue // bandas negras alrededor del vídeo: no son imagen
                val fx = (i + 0.5f) / COLS; val fy = (j + 0.5f) / ROWS
                valid++
                val q = ((r shr 5) shl 6) or ((g shr 5) shl 3) or (b shr 5) // color cuantizado (8 niveles por canal)
                hist[q] = (hist[q] ?: 0) + 1
                if (fx < minX) minX = fx; if (fx > maxX) maxX = fx; if (fy < minY) minY = fy; if (fy > maxY) maxY = fy
                when (p.mode) {
                    ThermalPaletteMode.SEARCH ->
                        // Rojo/naranja/amarillo saturado = por encima del umbral; gris = por debajo.
                        if (r > 170 && r - b > 120 && g < r - 10 && b < 110) { hotN++; hotSumX += fx; hotSumY += fy }
                    ThermalPaletteMode.ABSOLUTE -> {
                        val idx = nearest(r, g, b) ?: continue
                        tv[k] = p.min + span * idx / 255.0; xs[k] = fx; ys[k] = fy; k++
                    }
                    ThermalPaletteMode.RELATIVE -> {}
                }
            }
        }
        // Posición relativa a la imagen real (sin bandas negras), para poder proyectarla al suelo.
        fun nx(x: Float) = if (maxX > minX) ((x - minX) / (maxX - minX)).coerceIn(0f, 1f) else 0.5f
        fun ny(y: Float) = if (maxY > minY) ((y - minY) / (maxY - minY)).coerceIn(0f, 1f) else 0.5f
        val top = hist.entries.sortedByDescending { it.value }.take(4).joinToString(" ") { (q, c) ->
            "#%02x%02x%02x×%d".format(((q shr 6) and 7) * 36, ((q shr 3) and 7) * 36, (q and 7) * 36, c)
        }
        return when (p.mode) {
            ThermalPaletteMode.ABSOLUTE -> {
                if (k < 50) return Result(null, null, false, 0, 0.5f, 0.5f, valid, colors = top)
                val sorted = tv.copyOf(k).also { it.sort() }
                // Percentil 99,7 en vez del máximo: un píxel suelto de compresión no dispara nada.
                val maxC = sorted[min(k - 1, (k * 0.997).toInt())]
                val median = sorted[k / 2]
                // Umbral: absoluto, o «X °C más que el entorno» (placas solares, búsqueda nocturna).
                val thr = if (p.alertDelta > 0) min(p.alertTemp, median + p.alertDelta) else p.alertTemp
                val center = ArrayList<Double>()
                var bx = 0.5f; var by = 0.5f; var best = -1e9
                for (q in 0 until k) {
                    val t = tv[q]
                    if (t > best) { best = t; bx = xs[q]; by = ys[q] }
                    if (xs[q] in 0.46f..0.54f && ys[q] in 0.45f..0.55f) center.add(t)
                    if (t >= thr) { hotN++; hotSumX += xs[q]; hotSumY += ys[q] }
                }
                val c = if (center.isEmpty()) null else center.sorted()[center.size / 2]
                val hot = p.alert && isSpot(hotN, k)
                Result(maxC, c, hot, hotN, nx(if (hotN > 0) hotSumX / hotN else bx), ny(if (hotN > 0) hotSumY / hotN else by), k, median, top)
            }
            ThermalPaletteMode.SEARCH -> {
                val hot = p.alert && isSpot(hotN, valid)
                Result(null, null, hot, hotN, nx(if (hotN > 0) hotSumX / hotN else 0.5f), ny(if (hotN > 0) hotSumY / hotN else 0.5f), valid, colors = top)
            }
            else -> Result(null, null, false, 0, 0.5f, 0.5f, valid)
        }
    }

    /**
     * Punto del suelo que se ve en (fx, fy) de la imagen térmica. Suelo plano a la altura del despegue.
     * [pitchDeg]: inclinación de la cámara (−90 = mirando abajo). FOV de la térmica de la ANAFI Thermal ≈ 50° × 38°.
     * null si ese punto de la imagen está por encima del horizonte o demasiado lejos.
     */
    fun projectToGround(lat: Double, lon: Double, altAgl: Double, yawDeg: Double, pitchDeg: Double,
                        fx: Float, fy: Float, hfovDeg: Double = 50.0, vfovDeg: Double = 38.0): LatLon? {
        if (altAgl.isNaN() || altAgl < 1 || yawDeg.isNaN() || pitchDeg.isNaN()) return null
        val p = Math.toRadians(pitchDeg)
        val x = (fx - 0.5) * 2 * Math.tan(Math.toRadians(hfovDeg / 2))
        val y = (0.5 - fy) * 2 * Math.tan(Math.toRadians(vfovDeg / 2))
        // Rayo en ejes (adelante, derecha, arriba) del dron
        val fwd = Math.cos(p) - y * Math.sin(p)
        val up = Math.sin(p) + y * Math.cos(p)
        if (up > -0.05) return null
        val t = altAgl / -up
        val f = t * fwd; val r = t * x
        val yaw = Math.toRadians(yawDeg)
        val north = f * Math.cos(yaw) - r * Math.sin(yaw)
        val east = f * Math.sin(yaw) + r * Math.cos(yaw)
        if (Math.hypot(north, east) > 500) return null
        return LatLon(lat + north / 110540.0, lon + east / (111320.0 * Math.cos(Math.toRadians(lat))))
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
