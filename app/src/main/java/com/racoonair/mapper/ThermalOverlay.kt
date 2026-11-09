package com.racoonair.mapper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.graphics.Typeface
import android.view.View

/** Capa sobre el vídeo térmico: escala de colores con °C, cruz central, lectura y marca del punto caliente. */
class ThermalOverlay(ctx: Context) : View(ctx) {
    var prefs: ThermalPrefs = ThermalPrefs(); set(v) { field = v; invalidate() }
    var result: ThermalScan.Result? = null; set(v) { field = v; invalidate() }
    /** Texto de estado (p. ej. «Paleta fija de misión»). */
    var note: String = ""; set(v) { field = v; invalidate() }

    private val d = resources.displayMetrics.density
    private val text = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE; textSize = 14 * d; typeface = Typeface.DEFAULT_BOLD; setShadowLayer(4f, 1f, 1f, Color.BLACK)
    }
    private val small = Paint(text).apply { textSize = 11 * d }
    private val line = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.WHITE; strokeWidth = 2 * d; style = Paint.Style.STROKE; setShadowLayer(3f, 0f, 0f, Color.BLACK) }
    private val hotRing = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF00E5FF.toInt(); strokeWidth = 3 * d; style = Paint.Style.STROKE }
    private val bar = Paint()
    private val back = Paint().apply { color = 0x88000000.toInt() }

    override fun onDraw(c: Canvas) {
        val w = width.toFloat(); val h = height.toFloat()
        val p = prefs
        // Escala de colores a la derecha
        if (p.mode != ThermalPaletteMode.RELATIVE) {
            val bw = 12 * d; val top = 54 * d; val bottom = h - 44 * d
            if (bottom - top > 40 * d) {
                val x = w - bw - 8 * d
                val cols = ThermalScan.legendColors(p)
                bar.shader = LinearGradient(0f, bottom, 0f, top, cols, null, Shader.TileMode.CLAMP)
                c.drawRect(x, top, x + bw, bottom, bar)
                small.textAlign = Paint.Align.RIGHT
                if (p.mode == ThermalPaletteMode.ABSOLUTE) {
                    c.drawText("%.0f°".format(p.max), x - 4 * d, top + 10 * d, small)
                    c.drawText("%.0f°".format((p.min + p.max) / 2), x - 4 * d, (top + bottom) / 2 + 4 * d, small)
                    c.drawText("%.0f°".format(p.min), x - 4 * d, bottom, small)
                } else c.drawText(">%.0f°".format(p.threshold), x - 4 * d, top + (bottom - top) * 0.5f, small)
            }
        }
        val r = result
        // Cruz central (lectura del centro)
        if (p.mode == ThermalPaletteMode.ABSOLUTE) {
            val cx = w / 2; val cy = h / 2; val s = 12 * d
            c.drawLine(cx - s, cy, cx - 4 * d, cy, line); c.drawLine(cx + 4 * d, cy, cx + s, cy, line)
            c.drawLine(cx, cy - s, cx, cy - 4 * d, line); c.drawLine(cx, cy + 4 * d, cx, cy + s, line)
        }
        // Punto caliente
        if (r != null && r.hotPixels > 0) {
            val hx = r.hx * w; val hy = r.hy * h
            c.drawCircle(hx, hy, 22 * d, hotRing)
        }
        // Lectura abajo a la izquierda
        val msg = buildString {
            if (r != null) when (p.mode) {
                ThermalPaletteMode.ABSOLUTE -> {
                    append(if (r.maxC != null) tr("Máx") + " %.1f °C".format(r.maxC) else tr("Máx") + " —")
                    append("   " + tr("Centro") + " " + (r.centerC?.let { "%.1f °C".format(it) } ?: "—"))
                }
                ThermalPaletteMode.SEARCH -> append(if (r.hotPixels > 0) trf("Puntos > %1\$s °C en imagen", "%.0f".format(p.threshold)) else trf("Nada por encima de %1\$s °C", "%.0f".format(p.threshold)))
                else -> {}
            }
            if (note.isNotEmpty()) { if (isNotEmpty()) append("\n"); append(note) }
        }
        if (msg.isNotEmpty()) {
            val lines = msg.split("\n")
            val lh = 18 * d
            val y0 = h - 10 * d - lh * (lines.size - 1)
            c.drawRect(0f, y0 - lh, w * 0.75f, h, back)
            text.textAlign = Paint.Align.LEFT
            lines.forEachIndexed { i, s -> c.drawText(s, 10 * d, y0 + i * lh, text) }
        }
    }
}
