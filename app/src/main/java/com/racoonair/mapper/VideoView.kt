package com.racoonair.mapper

import android.content.Context
import android.media.MediaCodec
import android.media.MediaFormat
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.nio.ByteBuffer
import java.util.concurrent.locks.ReentrantLock

/** Receptor de vídeo H.264 del dron (lo implementa VideoView; lo alimenta el enlace). */
interface VideoSink {
    /** SPS/PPS del flujo (sin prefijo o con él, tal cual llegan del SDK). */
    fun configure(sps: ByteArray, pps: ByteArray)
    /** Un fotograma H.264. Se llama desde un hilo nativo; los datos se copian dentro. */
    fun frame(data: ByteArray, size: Int, isIFrame: Boolean)
}

/**
 * Vídeo en directo de la cámara del dron (Bluegrass/Disco: 856×480 H.264), decodificado por hardware
 * con MediaCodec sobre una SurfaceView. Basado en el ejemplo oficial de Parrot (H264VideoView, BSD).
 */
class VideoView(ctx: Context) : SurfaceView(ctx), SurfaceHolder.Callback, VideoSink {

    companion object {
        private const val MIME = "video/avc"
        private const val W = 856
        private const val H = 480
        /** Corto: si el decodificador va atrasado se descarta el fotograma en vez de bloquear el hilo del SDK. */
        private const val DEQUEUE_TIMEOUT_US = 5_000L
    }

    private val lock = ReentrantLock()
    private var codec: MediaCodec? = null
    private var configured = false
    private var sps: ByteArray? = null
    private var pps: ByteArray? = null
    /** Tras (re)configurar hay que esperar a un fotograma completo (I) para no mostrar basura. */
    private var waitIFrame = true
    @Volatile var framesShown = 0L
        private set
    @Volatile var lastFrameAt = 0L
        private set

    init { holder.addCallback(this) }

    /** Salida alternativa (modo gafas): el vídeo se pinta en esta Surface en vez de en la vista. null = volver. */
    private var external: android.view.Surface? = null
    fun setExternalSurface(s: android.view.Surface?) {
        lock.lock()
        try {
            external = s
            val c = codec ?: return
            val target = s ?: holder.surface
            if (configured) {
                try { c.setOutputSurface(target) } catch (t: Throwable) { configureCodec() }
            } else if (sps != null) configureCodec()
        } finally { lock.unlock() }
    }

    /** Mantiene la proporción 16:9 del vídeo dentro del hueco disponible. */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = MeasureSpec.getSize(widthMeasureSpec); val h = MeasureSpec.getSize(heightMeasureSpec)
        if (w == 0 || h == 0) { super.onMeasure(widthMeasureSpec, heightMeasureSpec); return }
        val ratio = W.toFloat() / H
        val (mw, mh) = if (w / ratio <= h) Pair(w, (w / ratio).toInt()) else Pair((h * ratio).toInt(), h)
        setMeasuredDimension(mw, mh)
    }

    override fun configure(sps: ByteArray, pps: ByteArray) {
        lock.lock()
        try {
            this.sps = sps; this.pps = pps
            if (codec != null) configureCodec()
        } finally { lock.unlock() }
    }

    override fun frame(data: ByteArray, size: Int, isIFrame: Boolean) {
        lock.lock()
        try {
            val c = codec ?: return
            if (!configured) return
            if (waitIFrame && !isIFrame) return
            waitIFrame = false
            try {
                val idx = c.dequeueInputBuffer(DEQUEUE_TIMEOUT_US)
                if (idx >= 0) {
                    val b = c.getInputBuffer(idx)
                    if (b != null && size <= b.capacity()) {
                        b.clear(); b.put(data, 0, size)
                        c.queueInputBuffer(idx, 0, size, 0, 0)
                    } else c.queueInputBuffer(idx, 0, 0, 0, 0)
                }
                val info = MediaCodec.BufferInfo()
                while (true) {
                    val out = c.dequeueOutputBuffer(info, 0)
                    if (out >= 0) { c.releaseOutputBuffer(out, true); framesShown++; lastFrameAt = System.currentTimeMillis() }
                    else if (out == MediaCodec.INFO_TRY_AGAIN_LATER) break
                    // INFO_OUTPUT_FORMAT_CHANGED / BUFFERS_CHANGED: seguir vaciando
                }
            } catch (e: IllegalStateException) {
                // Códec roto (o cerrándose): reiniciarlo con el SPS/PPS guardado y esperar a un fotograma I.
                Crumbs.write("video: códec ${e.javaClass.simpleName}: ${e.message} → reinicio")
                try { c.reset() } catch (_: Throwable) {}
                configured = false
                configureCodec()
            }
        } finally { lock.unlock() }
    }

    private fun configureCodec() {
        val c = codec ?: return
        val s = sps ?: return; val p = pps ?: return
        try {
            if (configured) c.stop()
            configured = false
            val f = MediaFormat.createVideoFormat(MIME, W, H)
            f.setByteBuffer("csd-0", ByteBuffer.wrap(s))
            f.setByteBuffer("csd-1", ByteBuffer.wrap(p))
            c.configure(f, external ?: holder.surface, null, 0)
            c.start()
            configured = true
            waitIFrame = true
        } catch (t: Throwable) {
            configured = false
            Crumbs.write("video: configure EXC ${t.javaClass.simpleName}: ${t.message}")
        }
    }

    override fun surfaceCreated(h: SurfaceHolder) {
        lock.lock()
        try {
            codec = try { MediaCodec.createDecoderByType(MIME) } catch (t: Throwable) { Crumbs.write("video: sin decodificador H.264"); null }
            if (sps != null) configureCodec()
        } finally { lock.unlock() }
    }

    override fun surfaceChanged(h: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(h: SurfaceHolder) {
        lock.lock()
        try {
            try { if (configured) codec?.stop() } catch (_: Throwable) {}
            try { codec?.release() } catch (_: Throwable) {}
            codec = null; configured = false
        } finally { lock.unlock() }
    }
}
