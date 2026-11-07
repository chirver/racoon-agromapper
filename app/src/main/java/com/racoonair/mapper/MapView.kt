package com.racoonair.mapper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.util.LruCache
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import java.io.File
import java.util.Collections
import java.util.concurrent.Executors
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.exp
import kotlin.math.floor
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sinh
import kotlin.math.tan

/** Capas de teselas. PNOA (IGN, CC BY 4.0) es la ortofoto oficial de España y se puede descargar para usar sin
 *  conexión; Esri queda de reserva (fuera de España o si el IGN no responde) y solo se cachea lo que se ve. */
enum class TileLayer(val dir: String, val ext: String, val attribution: String) {
    PNOA("tiles-pnoa", "jpg", tr("Ortofoto PNOA © IGN (CC BY 4.0)")),
    ESRI("tiles", "jpg", tr("Imágenes © Esri, Maxar, Earthstar Geographics")),
    SIGPAC("tiles-sigpac", "png", tr("Recintos SIGPAC © FEGA (CC BY 4.0)"));

    fun url(z: Int, x: Int, y: Int): String = when (this) {
        PNOA -> "https://www.ign.es/wmts/pnoa-ma?request=GetTile&service=WMTS&version=1.0.0&layer=OI.OrthoimageCoverage" +
            "&style=default&tilematrixset=GoogleMapsCompatible&tilematrix=$z&tilerow=$y&tilecol=$x&format=image/jpeg"
        ESRI -> "https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/$z/$y/$x"
        SIGPAC -> Sigpac.wmsTileUrl(z, x, y)
    }
}

/**
 * Mapa satélite mínimo (Web Mercator; ortofoto PNOA o Esri) con caché en disco,
 * arrastrar / pellizcar / doble toque, y capas para polígono, pasadas, casa y dron.
 * Las teselas vistas con conexión quedan guardadas para usarlas en el campo sin cobertura.
 */
class MapView(ctx: Context) : View(ctx) {

    interface Listener {
        fun onMapTap(p: LatLon)
        fun onMapLongPress(p: LatLon)
    }

    var listener: Listener? = null

    // Estado de cámara
    var centerLat = 39.2
        private set
    var centerLon = -3.6
        private set
    var zoom = 15.0
        private set

    // Capas
    var polygon: List<LatLon> = emptyList(); set(v) { field = v; invalidate() }
    var passes: List<Pair<LatLon, LatLon>> = emptyList(); set(v) { field = v; invalidate() }
    var home: LatLon? = null; set(v) { field = v; invalidate() }
    /** Ala fija: aterrizaje planificado (cono de aproximación, alineación y punto de toma). */
    var landing: FixedWing? = null; set(v) { field = v; invalidate() }
    private val conePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0x5539A7FF; style = Paint.Style.FILL }
    private val coneEdge = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF39A7FF.toInt(); style = Paint.Style.STROKE; strokeWidth = 3f }
    private val finalPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF39A7FF.toInt(); strokeWidth = 6f }
    private val landPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF2E9E57.toInt() }
    var drone: LatLon? = null; set(v) { field = v; invalidate() }
    var phone: LatLon? = null; set(v) { field = v; invalidate() }
    /** Ruta del plan cargado (waypoints en orden) y cuántos se han completado. */
    var route: List<LatLon> = emptyList(); set(v) { field = v; invalidate() }
    var routeDone: Int = 0; set(v) { field = v; invalidate() }
    /** Rastro real del dron en vuelo. */
    var track: List<LatLon> = emptyList(); set(v) { field = v; invalidate() }
    /** Rumbo del dron en grados (0 = norte), null si no se conoce. */
    var droneYaw: Double? = null; set(v) { field = v; invalidate() }

    private val routePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFFFFC107.toInt(); strokeWidth = 4f; style = Paint.Style.STROKE }
    private val routeDonePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0x88FFFFFF.toInt(); strokeWidth = 4f; style = Paint.Style.STROKE }
    private val trackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFFFF1744.toInt(); strokeWidth = 6f; style = Paint.Style.STROKE; strokeJoin = Paint.Join.ROUND; strokeCap = Paint.Cap.ROUND }
    private val arrowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.WHITE; style = Paint.Style.FILL }

    /** Capa base (ortofoto) y capa de recintos SIGPAC encima. */
    var base: TileLayer = TileLayer.PNOA; set(v) { field = v; failed.clear(); invalidate() }
    var showSigpac = false; set(v) { field = v; invalidate() }
    /** Recinto resaltado (p. ej. el SIGPAC recién consultado). */
    var highlight: List<LatLon> = emptyList(); set(v) { field = v; invalidate() }
    private val filesDir = ctx.filesDir
    private val hlPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF00E5FF.toInt(); style = Paint.Style.STROKE; strokeWidth = 6f }
    private val hlFill = Paint().apply { color = 0x3300E5FF; style = Paint.Style.FILL }
    private val mem = object : LruCache<String, Bitmap>(48 * 1024 * 1024) {
        override fun sizeOf(key: String, value: Bitmap) = value.byteCount
    }
    private val pending = Collections.synchronizedSet(HashSet<String>())
    private val failed = Collections.synchronizedSet(HashSet<String>())
    private val pool = Executors.newFixedThreadPool(4)
    private val main = Handler(Looper.getMainLooper())

    private val tilePaint = Paint(Paint.FILTER_BITMAP_FLAG)
    private val bgPaint = Paint().apply { color = 0xFF1B2128.toInt() }
    private val gridPaint = Paint().apply { color = 0xFF2A333D.toInt(); strokeWidth = 1f }
    private val polyFill = Paint().apply { color = 0x5532A852; style = Paint.Style.FILL }
    private val polyStroke = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF3DDC84.toInt(); style = Paint.Style.STROKE; strokeWidth = 5f }
    private val vertexPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.WHITE }
    private val vertexRing = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF3DDC84.toInt(); style = Paint.Style.STROKE; strokeWidth = 5f }
    private val passPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFFFFC107.toInt(); strokeWidth = 4f }
    private val linkPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0x99FFC107.toInt(); strokeWidth = 2f; pathEffect = android.graphics.DashPathEffect(floatArrayOf(12f, 10f), 0f) }
    private val homePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF2F6FB3.toInt() }
    private val dronePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFFE53935.toInt() }
    private val phonePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF29B6F6.toInt() }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.WHITE; textSize = 34f; isFakeBoldText = true; textAlign = Paint.Align.CENTER }
    private val attrPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.WHITE; textSize = 24f; setShadowLayer(3f, 0f, 0f, Color.BLACK) }

    companion object {
        private const val TILE = 256
        private const val MAX_Z = 19
        /** La capa SIGPAC solo se pide de cerca (hay muchísimos recintos). */
        const val SIGPAC_MIN_Z = 14
        const val SIGPAC_MAX_Z = 18

        fun tileFile(filesDir: File, l: TileLayer, z: Int, x: Int, y: Int) = File(filesDir, "${l.dir}/$z/$x/$y.${l.ext}")

        fun lon2tile(lon: Double, z: Int) = floor((lon + 180.0) / 360.0 * (1 shl z)).toInt()
        fun lat2tile(lat: Double, z: Int): Int {
            val s = Math.toRadians(lat.coerceIn(-85.0, 85.0))
            return floor((1 - ln(tan(s) + 1 / kotlin.math.cos(s)) / PI) / 2 * (1 shl z)).toInt()
        }

        /** Teselas necesarias para cubrir una zona entre zMin y zMax (para avisar antes de descargar). */
        fun countTiles(minLat: Double, maxLat: Double, minLon: Double, maxLon: Double, zMin: Int, zMax: Int): Long {
            var n = 0L
            for (z in zMin..zMax) n += (lon2tile(maxLon, z) - lon2tile(minLon, z) + 1).toLong() * (lat2tile(minLat, z) - lat2tile(maxLat, z) + 1).toLong()
            return n
        }

        /** Escritura atómica: un fichero a medias nunca se queda como tesela «buena» en la caché. */
        fun writeTile(f: File, bytes: ByteArray) {
            f.parentFile?.mkdirs()
            val tmp = File(f.parentFile, f.name + "." + Thread.currentThread().id + ".tmp")
            tmp.writeBytes(bytes)
            if (!tmp.renameTo(f)) tmp.delete()
        }

        /**
         * Descarga una zona para usarla sin conexión (bloqueante: llamar en un hilo). Salta lo que ya está.
         * [progress] (hechas, total, fallidas) → devuelve false para cancelar.
         */
        fun download(filesDir: File, layers: List<TileLayer>, minLat: Double, maxLat: Double, minLon: Double, maxLon: Double,
                     zMin: Int, zMax: Int, progress: (Int, Int, Int) -> Boolean): Pair<Int, Int> {
            if (countTiles(minLat, maxLat, minLon, maxLon, zMin, zMax) * layers.size > 20_000L) return Pair(0, 0)
            val jobs = ArrayList<IntArray>()
            for (l in layers) {
                val z0 = if (l == TileLayer.SIGPAC) max(zMin, SIGPAC_MIN_Z) else zMin
                val z1 = if (l == TileLayer.SIGPAC) min(zMax, SIGPAC_MAX_Z) else zMax
                for (z in z0..z1) for (x in lon2tile(minLon, z)..lon2tile(maxLon, z)) for (y in lat2tile(maxLat, z)..lat2tile(minLat, z))
                    jobs.add(intArrayOf(l.ordinal, z, x, y))
            }
            var done = 0; var fail = 0
            // 3 hilos: bastante rápido y sin abusar de los servidores del IGN y del FEGA.
            val ex = Executors.newFixedThreadPool(3)
            val lock = Object()
            val cancel = java.util.concurrent.atomic.AtomicBoolean(false)
            try {
                val futures = jobs.map { j -> ex.submit(Runnable {
                    if (cancel.get()) return@Runnable
                    val l = TileLayer.values()[j[0]]
                    val f = tileFile(filesDir, l, j[1], j[2], j[3])
                    var ok = f.exists() && f.length() > 0
                    if (!ok) ok = try {
                        val b = Net.get(l.url(j[1], j[2], j[3]), 8000, 12000)
                        if (BitmapFactory.decodeByteArray(b, 0, b.size) != null) { writeTile(f, b); true } else false
                    } catch (_: Throwable) { false }
                    synchronized(lock) { done++; if (!ok) fail++; if (!progress(done, jobs.size, fail)) cancel.set(true) }
                }) }
                futures.forEach { try { it.get() } catch (_: Throwable) {} }
            } finally { ex.shutdownNow() }
            return Pair(done - fail, fail)
        }

        /** Espacio usado por los mapas guardados (MB). */
        fun cacheSizeMb(filesDir: File): Double =
            TileLayer.values().sumOf { l -> File(filesDir, l.dir).walkTopDown().filter { it.isFile }.sumOf { it.length() } } / 1048576.0

        fun clearCache(filesDir: File) { TileLayer.values().forEach { File(filesDir, it.dir).deleteRecursively() } }
    }

    /** Límites de lo que se ve ahora mismo: (minLat, maxLat, minLon, maxLon). */
    fun visibleBounds(): DoubleArray {
        val a = fromScreen(0f, 0f); val b = fromScreen(width.toFloat(), height.toFloat())
        return doubleArrayOf(min(a.lat, b.lat), max(a.lat, b.lat), min(a.lon, b.lon), max(a.lon, b.lon))
    }

    // ---------------------------------------------------------------- proyección

    private fun worldSize(z: Double) = TILE * 2.0.pow(z)
    private fun lonToX(lon: Double, z: Double) = (lon + 180.0) / 360.0 * worldSize(z)
    private fun latToY(lat: Double, z: Double): Double {
        val s = Math.toRadians(lat.coerceIn(-85.0, 85.0))
        return (1 - ln(tan(s) + 1 / kotlin.math.cos(s)) / PI) / 2 * worldSize(z)
    }
    private fun xToLon(x: Double, z: Double) = x / worldSize(z) * 360.0 - 180.0
    private fun yToLat(y: Double, z: Double) = Math.toDegrees(atan(sinh(PI * (1 - 2 * y / worldSize(z)))))

    fun toScreen(p: LatLon): FloatArray {
        val cx = lonToX(centerLon, zoom); val cy = latToY(centerLat, zoom)
        return floatArrayOf((lonToX(p.lon, zoom) - cx + width / 2.0).toFloat(), (latToY(p.lat, zoom) - cy + height / 2.0).toFloat())
    }
    fun fromScreen(sx: Float, sy: Float): LatLon {
        val cx = lonToX(centerLon, zoom); val cy = latToY(centerLat, zoom)
        return LatLon(yToLat(cy + sy - height / 2.0, zoom), xToLon(cx + sx - width / 2.0, zoom))
    }

    fun setCenter(p: LatLon, z: Double? = null) {
        centerLat = p.lat; centerLon = p.lon
        if (z != null) zoom = z.coerceIn(3.0, MAX_Z + 1.0)
        invalidate()
    }

    /** Encuadra un conjunto de puntos. */
    fun fit(points: List<LatLon>) {
        if (points.isEmpty() || width == 0) return
        val minLat = points.minOf { it.lat }; val maxLat = points.maxOf { it.lat }
        val minLon = points.minOf { it.lon }; val maxLon = points.maxOf { it.lon }
        centerLat = (minLat + maxLat) / 2; centerLon = (minLon + maxLon) / 2
        var z = MAX_Z.toDouble()
        while (z > 3) {
            val w = lonToX(maxLon, z) - lonToX(minLon, z)
            val h = latToY(minLat, z) - latToY(maxLat, z)
            if (w < width * 0.8 && h < height * 0.8) break
            z -= 0.25
        }
        zoom = z
        invalidate()
    }

    // ---------------------------------------------------------------- gestos

    private val scaleDetector = ScaleGestureDetector(ctx, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(d: ScaleGestureDetector): Boolean {
            zoomAround(d.focusX, d.focusY, ln(d.scaleFactor.toDouble()) / ln(2.0))
            return true
        }
    })

    private val gestures = GestureDetector(ctx, object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent) = true
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent, dx: Float, dy: Float): Boolean {
            val cx = lonToX(centerLon, zoom) + dx; val cy = latToY(centerLat, zoom) + dy
            centerLon = xToLon(cx, zoom); centerLat = yToLat(cy, zoom)
            invalidate(); return true
        }
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            listener?.onMapTap(fromScreen(e.x, e.y)); return true
        }
        override fun onDoubleTap(e: MotionEvent): Boolean { zoomAround(e.x, e.y, 1.0); return true }
        override fun onLongPress(e: MotionEvent) { listener?.onMapLongPress(fromScreen(e.x, e.y)) }
    })

    private fun zoomAround(fx: Float, fy: Float, dz: Double) {
        val before = fromScreen(fx, fy)
        zoom = (zoom + dz).coerceIn(3.0, MAX_Z + 1.0)
        // Mantener el punto bajo los dedos en el mismo sitio.
        val after = toScreen(before)
        val cx = lonToX(centerLon, zoom) + (after[0] - fx); val cy = latToY(centerLat, zoom) + (after[1] - fy)
        centerLon = xToLon(cx, zoom); centerLat = yToLat(cy, zoom)
        invalidate()
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        parent?.requestDisallowInterceptTouchEvent(true)
        scaleDetector.onTouchEvent(e)
        if (!scaleDetector.isInProgress) gestures.onTouchEvent(e)
        return true
    }

    // ---------------------------------------------------------------- teselas

    /** Capa base con la que se pintó cada tesela (PNOA puede caer a Esri tesela a tesela). */
    private fun loadOrFetch(l: TileLayer, z: Int, x: Int, y: Int): Bitmap? {
        val f = tileFile(filesDir, l, z, x, y)
        if (f.exists()) BitmapFactory.decodeFile(f.absolutePath)?.let { return it }
        return try {
            val bytes = Net.get(l.url(z, x, y), 6000, 8000)
            val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            if (bmp != null) writeTile(f, bytes)
            bmp
        } catch (_: Throwable) { null }
    }

    private fun getTile(z: Int, x: Int, y: Int, layer: TileLayer = base): Bitmap? {
        val key = "${layer.name}/$z/$x/$y"
        mem.get(key)?.let { return it }
        if (key in pending || key in failed) return null
        if (pool.isShutdown || pending.size > 64) return null
        pending.add(key)
        try { pool.execute {
            try {
                var bmp = loadOrFetch(layer, z, x, y)
                // PNOA solo cubre España: fuera (o si el IGN falla) se usa Esri para esa tesela.
                if (bmp == null && layer == TileLayer.PNOA) bmp = loadOrFetch(TileLayer.ESRI, z, x, y)
                if (bmp != null) mem.put(key, bmp) else failed.add(key)
            } catch (_: Throwable) {
                failed.add(key) // sin red: se reintenta al reabrir la pantalla
            } finally {
                pending.remove(key)
                main.post { invalidate() }
            }
        } } catch (_: java.util.concurrent.RejectedExecutionException) { pending.remove(key) }
        return null
    }

    /** Busca un ancestro ya cargado para no dejar huecos mientras llega la tesela buena. */
    private fun drawFallback(canvas: Canvas, z: Int, x: Int, y: Int, dst: RectF): Boolean {
        var zz = z; var xx = x; var yy = y; var sub = 1
        var ox = 0; var oy = 0
        while (zz > 0 && sub < 32) {
            ox += (xx and 1) * sub; oy += (yy and 1) * sub
            zz--; xx = xx shr 1; yy = yy shr 1; sub = sub shl 1
            val b = mem.get("${base.name}/$zz/$xx/$yy") ?: continue
            val part = TILE / sub
            val src = android.graphics.Rect(ox * part, oy * part, (ox + 1) * part, (oy + 1) * part)
            canvas.drawBitmap(b, src, dst, tilePaint)
            return true
        }
        return false
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), bgPaint)
        val z = min(MAX_Z, max(0, floor(zoom).toInt()))
        val scale = 2.0.pow(zoom - z)
        val ts = TILE * scale
        val cx = lonToX(centerLon, z.toDouble()) * scale; val cy = latToY(centerLat, z.toDouble()) * scale
        val left = cx - width / 2.0; val top = cy - height / 2.0
        val n = 1 shl z
        val x0 = floor(left / ts).toInt(); val x1 = floor((left + width) / ts).toInt()
        val y0 = max(0, floor(top / ts).toInt()); val y1 = min(n - 1, floor((top + height) / ts).toInt())
        for (ty in y0..y1) for (tx in x0..x1) {
            val wx = ((tx % n) + n) % n
            val dst = RectF((tx * ts - left).toFloat(), (ty * ts - top).toFloat(), ((tx + 1) * ts - left).toFloat(), ((ty + 1) * ts - top).toFloat())
            val b = getTile(z, wx, ty)
            if (b != null) canvas.drawBitmap(b, null, dst, tilePaint)
            else if (!drawFallback(canvas, z, wx, ty, dst)) {
                canvas.drawLine(dst.left, dst.top, dst.right, dst.top, gridPaint)
                canvas.drawLine(dst.left, dst.top, dst.left, dst.bottom, gridPaint)
            }
        }

        // Recintos SIGPAC encima de la ortofoto (solo de cerca)
        if (showSigpac && zoom >= SIGPAC_MIN_Z) {
            val sz = min(SIGPAC_MAX_Z, floor(zoom).toInt())
            val sc = 2.0.pow(zoom - sz); val sts = TILE * sc
            val scx = lonToX(centerLon, sz.toDouble()) * sc; val scy = latToY(centerLat, sz.toDouble()) * sc
            val sl = scx - width / 2.0; val st = scy - height / 2.0
            val sn = 1 shl sz
            for (ty in max(0, floor(st / sts).toInt())..min(sn - 1, floor((st + height) / sts).toInt()))
                for (tx in floor(sl / sts).toInt()..floor((sl + width) / sts).toInt()) {
                    val wx = ((tx % sn) + sn) % sn
                    val b = getTile(sz, wx, ty, TileLayer.SIGPAC) ?: continue
                    canvas.drawBitmap(b, null, RectF((tx * sts - sl).toFloat(), (ty * sts - st).toFloat(),
                        ((tx + 1) * sts - sl).toFloat(), ((ty + 1) * sts - st).toFloat()), tilePaint)
                }
        }
        if (highlight.size >= 3) {
            val hp = Path()
            highlight.forEachIndexed { i, p -> val s = toScreen(p); if (i == 0) hp.moveTo(s[0], s[1]) else hp.lineTo(s[0], s[1]) }
            hp.close(); canvas.drawPath(hp, hlFill); canvas.drawPath(hp, hlPaint)
        }
        // Polígono
        if (polygon.isNotEmpty()) {
            val path = Path()
            polygon.forEachIndexed { i, p -> val s = toScreen(p); if (i == 0) path.moveTo(s[0], s[1]) else path.lineTo(s[0], s[1]) }
            if (polygon.size >= 3) { path.close(); canvas.drawPath(path, polyFill) }
            canvas.drawPath(path, polyStroke)
        }
        // Ruta del plan (pantalla de vuelo): hecho en blanco, pendiente en ámbar
        if (route.size >= 2) {
            val done = Path(); val todo = Path()
            route.forEachIndexed { i, p ->
                val s = toScreen(p)
                if (i < routeDone) { if (i == 0) done.moveTo(s[0], s[1]) else done.lineTo(s[0], s[1]) }
                if (i >= routeDone - 1 && i >= 0) { if (todo.isEmpty) todo.moveTo(s[0], s[1]) else todo.lineTo(s[0], s[1]) }
            }
            canvas.drawPath(done, routeDonePaint); canvas.drawPath(todo, routePaint)
        }
        // Rastro real
        if (track.size >= 2) {
            val tp = Path()
            track.forEachIndexed { i, p -> val s = toScreen(p); if (i == 0) tp.moveTo(s[0], s[1]) else tp.lineTo(s[0], s[1]) }
            canvas.drawPath(tp, trackPaint)
        }
        // Pasadas
        var prev: FloatArray? = home?.let { toScreen(it) }
        for (pr in passes) {
            val a = toScreen(pr.first); val b = toScreen(pr.second)
            prev?.let { canvas.drawLine(it[0], it[1], a[0], a[1], linkPaint) }
            canvas.drawLine(a[0], a[1], b[0], b[1], passPaint)
            prev = b
        }
        val fw = landing
        val ld = fw?.landing
        if (fw != null && ld != null) {
            // Cono de aproximación (30°) desde el punto de toma hasta el de aproximación.
            val back = fw.finalHeading + 180.0
            val l = toScreen(ld)
            val e1 = toScreen(GridPlanner.destination(ld, back - FixedWing.CONE_HALF_DEG, fw.approachDist))
            val e2 = toScreen(GridPlanner.destination(ld, back + FixedWing.CONE_HALF_DEG, fw.approachDist))
            val cone = Path().apply { moveTo(l[0], l[1]); lineTo(e1[0], e1[1]); lineTo(e2[0], e2[1]); close() }
            canvas.drawPath(cone, conePaint); canvas.drawPath(cone, coneEdge)
            val al = toScreen(fw.alignPoint!!); val ap = toScreen(fw.approachPoint!!)
            // Pasillo de alineación (también a baja altura): franja de ±2R alrededor del tramo previo a la final.
            val lastEnd = passes.lastOrNull()?.second
            val pre = if (lastEnd != null && fw.needsPreAlign(lastEnd)) fw.preAlignPoint!! else fw.alignPoint!!
            val back2 = fw.finalHeading + 180.0
            val c1 = toScreen(GridPlanner.destination(GridPlanner.destination(ld, back2, fw.approachDist), back2 + 90, FixedWing.TURN_RADIUS))
            val c2 = toScreen(GridPlanner.destination(GridPlanner.destination(ld, back2, fw.approachDist), back2 - 90, FixedWing.TURN_RADIUS))
            val c3 = toScreen(GridPlanner.destination(pre, back2 - 90, FixedWing.TURN_RADIUS))
            val c4 = toScreen(GridPlanner.destination(pre, back2 + 90, FixedWing.TURN_RADIUS))
            val corr = Path().apply { moveTo(c1[0], c1[1]); lineTo(c2[0], c2[1]); lineTo(c3[0], c3[1]); lineTo(c4[0], c4[1]); close() }
            canvas.drawPath(corr, conePaint)
            val pr = toScreen(pre)
            prev?.let { canvas.drawLine(it[0], it[1], pr[0], pr[1], linkPaint) }
            if (pre !== fw.alignPoint) canvas.drawLine(pr[0], pr[1], al[0], al[1], finalPaint)
            canvas.drawLine(al[0], al[1], ap[0], ap[1], finalPaint)
            canvas.drawLine(ap[0], ap[1], l[0], l[1], finalPaint)
            // Flecha de la final en el punto de toma.
            val h = Math.toRadians(fw.finalHeading)
            val dx = Math.sin(h).toFloat(); val dy = -Math.cos(h).toFloat()
            val tip = floatArrayOf(l[0] + dx * 40f, l[1] + dy * 40f)
            canvas.drawLine(l[0], l[1], tip[0], tip[1], finalPaint)
            canvas.drawLine(tip[0], tip[1], tip[0] - dx * 16f - dy * 12f, tip[1] - dy * 16f + dx * 12f, finalPaint)
            canvas.drawLine(tip[0], tip[1], tip[0] - dx * 16f + dy * 12f, tip[1] - dy * 16f - dx * 12f, finalPaint)
            canvas.drawCircle(l[0], l[1], 22f, landPaint)
            textPaint.color = Color.WHITE; textPaint.textSize = 30f
            canvas.drawText("L", l[0], l[1] + 11f, textPaint)
        } else if (passes.isNotEmpty()) home?.let { val h = toScreen(it); prev?.let { p -> canvas.drawLine(p[0], p[1], h[0], h[1], linkPaint) } }
        // Vértices
        polygon.forEachIndexed { i, p ->
            val s = toScreen(p)
            canvas.drawCircle(s[0], s[1], 16f, vertexPaint); canvas.drawCircle(s[0], s[1], 16f, vertexRing)
            textPaint.textSize = 22f; textPaint.color = Color.BLACK
            canvas.drawText("${i + 1}", s[0], s[1] + 8f, textPaint)
        }
        textPaint.color = Color.WHITE; textPaint.textSize = 34f
        phone?.let { val s = toScreen(it); canvas.drawCircle(s[0], s[1], 14f, phonePaint) }
        home?.let { val s = toScreen(it); canvas.drawCircle(s[0], s[1], 26f, homePaint); canvas.drawText("H", s[0], s[1] + 12f, textPaint) }
        drone?.let {
            val s = toScreen(it)
            canvas.drawCircle(s[0], s[1], 20f, dronePaint)
            val yaw = droneYaw
            if (yaw != null) {
                // Flecha de rumbo
                canvas.save(); canvas.rotate(yaw.toFloat(), s[0], s[1])
                val a = Path(); a.moveTo(s[0], s[1] - 36f); a.lineTo(s[0] - 13f, s[1] - 8f); a.lineTo(s[0] + 13f, s[1] - 8f); a.close()
                canvas.drawPath(a, arrowPaint); canvas.restore()
            }
            canvas.drawText("D", s[0], s[1] + 9f, textPaint.apply { textSize = 24f })
        }
        canvas.drawText(base.attribution + (if (base == TileLayer.PNOA) tr(" · Esri fuera de España") else "") +
            (if (showSigpac) tr(" · SIGPAC © FEGA") else ""), 12f, height - 12f, attrPaint)
    }

    fun release() { pool.shutdownNow() }

    /** Reintentar teselas fallidas (p. ej. al recuperar cobertura). */
    fun retryFailed() { failed.clear(); invalidate() }
}
