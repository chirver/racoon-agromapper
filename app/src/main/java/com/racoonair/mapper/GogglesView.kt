package com.racoonair.mapper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.SurfaceTexture
import android.graphics.Typeface
import android.opengl.GLES11Ext
import android.opengl.GLES30.*
import android.opengl.GLSurfaceView
import android.opengl.GLUtils
import android.os.Handler
import android.os.Looper
import android.view.Surface
import org.json.JSONObject
import java.io.DataInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * Modo gafas (Parrot Cockpitglasses y visores VR de móvil): la misma imagen para los dos ojos, deformada en barril
 * para compensar las lentes, con la telemetría y los avisos dentro de la imagen.
 *
 * Es una adaptación del modo HMD de GroundSDK (Parrot, BSD-3: hmd/Geometry, LensProjection y sus shaders),
 * reescrita para el vídeo H.264 de ARSDK3 (Bluegrass/Disco), que no pasa por GroundSDK. Las medidas de las lentes
 * vienen del paquete oficial de Parrot (res/raw/hmd_models, de la app de ejemplo de GroundSDK).
 *
 * El vídeo llega por [onSurface]: el decodificador escribe en esa Surface (una SurfaceTexture de este GL).
 */
class GogglesView(ctx: Context, private val onSurface: (Surface?) -> Unit) : GLSurfaceView(ctx) {

    /** Modelo de gafas del paquete (cockpitGlasses2 sirve también para las Cockpitglasses originales). */
    var model = "cockpitGlasses2"
    /** Desplazamiento vertical de las lentes en mm (si la imagen queda alta o baja dentro de las gafas). */
    @Volatile var verticalOffsetMm = 0f
    /** Ajuste de la distancia entre ojos en mm (0 = la del modelo). */
    @Volatile var ipdDeltaMm = 0f

    private val main = Handler(Looper.getMainLooper())
    private val r = R()

    init {
        setEGLContextClientVersion(3)
        setRenderer(r)
        renderMode = RENDERMODE_CONTINUOUSLY
    }

    /** Texto encima del vídeo (telemetría) y aviso (abajo, con su color). Se puede llamar desde el hilo principal. */
    fun setOverlay(text: String, alert: String?, alertColor: Int, homeRelDeg: Float = Float.NaN, homeLabel: String = "", linkLevel: Int = -1) {
        if (text == r.overlayText && alert == r.alertText && alertColor == r.alertColor && homeLabel == r.homeLabel && linkLevel == r.linkLevel &&
            (homeRelDeg.isNaN() == r.homeRel.isNaN()) && (homeRelDeg.isNaN() || Math.abs(homeRelDeg - r.homeRel) < 2f)) return
        r.overlayText = text; r.alertText = alert; r.alertColor = alertColor
        r.homeRel = homeRelDeg; r.homeLabel = homeLabel; r.linkLevel = linkLevel; r.overlayDirty = true
    }

    override fun onDetachedFromWindow() {
        main.post { onSurface(null) }
        super.onDetachedFromWindow()
    }

    private inner class R : Renderer {
        @Volatile var overlayText = ""
        @Volatile var alertText: String? = null
        @Volatile var alertColor = Color.RED
        @Volatile var overlayDirty = true
        @Volatile var homeRel = Float.NaN
        @Volatile var homeLabel = ""
        @Volatile var linkLevel = -1

        private var st: SurfaceTexture? = null
        private var surface: Surface? = null
        private var oesTex = 0
        private val stMatrix = FloatArray(16)
        private var videoProg = 0; private var ovlProg = 0; private var lensProg = 0
        private var contentFbo = 0; private var contentTex = 0
        private var lensFbo = 0; private var lensRbo = 0
        private var ovlTex = 0
        private var ovlBitmap: Bitmap? = null
        private var lensVao = 0; private var elementCount = 0
        private var meshSizeMm = 62.78f; private var defaultIpd = 62f
        private var maxRenderW = 54f; private var maxRenderH = 41f
        private var chromaR = 1f; private var chromaG = 1f; private var chromaB = 1f
        private var surfW = 0; private var surfH = 0
        // Geometría (como hmd/Geometry de GroundSDK)
        private var lensW = 0; private var lensH = 0; private var lensTop = 0
        private var leftL = 0; private var rightL = 0
        private var meshScaleX = 1f; private var meshScaleY = 1f; private var texScaleX = 1f; private var texScaleY = 1f

        override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
            val t = IntArray(1)
            glGenTextures(1, t, 0); oesTex = t[0]
            glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, oesTex)
            glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
            glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
            glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE)
            glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE)
            val s = SurfaceTexture(oesTex).apply { setDefaultBufferSize(856, 480) }
            st = s
            val sf = Surface(s); surface = sf
            main.post { onSurface(sf) }

            videoProg = program(VS_QUAD, FS_OES)
            ovlProg = program(VS_QUAD, FS_TEX)
            lensProg = program(VS_LENS, FS_LENS)
            glGenTextures(1, t, 0); ovlTex = t[0]
            loadModel()
            overlayDirty = true
        }

        override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
            surfW = width; surfH = height
            computeGeometry()
            makeTargets()
        }

        override fun onDrawFrame(gl: GL10?) {
            try { st?.updateTexImage(); st?.getTransformMatrix(stMatrix) } catch (_: Throwable) {}
            if (lensW <= 0 || lensH <= 0) { glClearColor(0f, 0f, 0f, 1f); glClear(GL_COLOR_BUFFER_BIT); return }
            computeGeometry()
            if (overlayDirty) uploadOverlay()

            // 1) Contenido de cada ojo: vídeo ajustado (16:9) + telemetría.
            glBindFramebuffer(GL_FRAMEBUFFER, contentFbo)
            glViewport(0, 0, lensW, lensH)
            glClearColor(0f, 0f, 0f, 1f); glClear(GL_COLOR_BUFFER_BIT)
            val va = 856f / 480f; val ca = lensW.toFloat() / lensH
            val (sx, sy) = if (ca > va) Pair(va / ca, 1f) else Pair(1f, ca / va)
            glUseProgram(videoProg)
            glUniform2f(glGetUniformLocation(videoProg, "scale"), sx, sy)
            glUniformMatrix4fv(glGetUniformLocation(videoProg, "stMatrix"), 1, false, stMatrix, 0)
            glActiveTexture(GL_TEXTURE0); glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, oesTex)
            glUniform1i(glGetUniformLocation(videoProg, "tex"), 0)
            drawQuad(videoProg)
            glEnable(GL_BLEND); glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
            glUseProgram(ovlProg)
            glUniform2f(glGetUniformLocation(ovlProg, "scale"), 1f, 1f)
            glBindTexture(GL_TEXTURE_2D, ovlTex)
            glUniform1i(glGetUniformLocation(ovlProg, "tex"), 0)
            drawQuad(ovlProg)
            glDisable(GL_BLEND)

            // 2) Deformación de la lente (malla de Parrot) a un búfer del tamaño de un ojo.
            glBindFramebuffer(GL_FRAMEBUFFER, lensFbo)
            glViewport(0, 0, lensW, lensH)
            glClearColor(0f, 0f, 0f, 1f); glClear(GL_COLOR_BUFFER_BIT)
            glUseProgram(lensProg)
            glUniform2f(glGetUniformLocation(lensProg, "meshScale"), meshScaleX, meshScaleY)
            glUniform2f(glGetUniformLocation(lensProg, "texScale"), texScaleX, texScaleY)
            glUniform3f(glGetUniformLocation(lensProg, "chromaCorrection"), chromaR, chromaG, chromaB)
            glBindTexture(GL_TEXTURE_2D, contentTex)
            glUniform1i(glGetUniformLocation(lensProg, "sampler"), 0)
            glBindVertexArray(lensVao)
            glDrawElements(GL_TRIANGLES, elementCount, GL_UNSIGNED_INT, 0)
            glBindVertexArray(0)

            // 3) La misma imagen a los dos ojos.
            glBindFramebuffer(GL_READ_FRAMEBUFFER, lensFbo)
            glBindFramebuffer(GL_DRAW_FRAMEBUFFER, 0)
            glViewport(0, 0, surfW, surfH)
            glClearColor(0f, 0f, 0f, 1f); glClear(GL_COLOR_BUFFER_BIT)
            // En GL el origen está abajo: convertir «top» de pantalla a coordenadas GL.
            val y0 = surfH - lensTop - lensH
            glBlitFramebuffer(0, 0, lensW, lensH, leftL, y0, leftL + lensW, y0 + lensH, GL_COLOR_BUFFER_BIT, GL_LINEAR)
            glBlitFramebuffer(0, 0, lensW, lensH, rightL, y0, rightL + lensW, y0 + lensH, GL_COLOR_BUFFER_BIT, GL_LINEAR)
            glBindFramebuffer(GL_FRAMEBUFFER, 0)
        }

        // ------------------------------------------------------------------ geometría (hmd/Geometry de GroundSDK)

        private var lastKey = ""
        private fun computeGeometry() {
            val key = "$surfW/$surfH/$verticalOffsetMm/$ipdDeltaMm"
            if (key == lastKey) return
            val dm = resources.displayMetrics
            fun mmX(mm: Float) = Math.round(mm * dm.xdpi / 25.4f)
            fun mmY(mm: Float) = Math.round(mm * dm.ydpi / 25.4f)
            val off = mmX((defaultIpd + ipdDeltaMm) / 2)
            val meshWpx = mmX(meshSizeMm); val meshHpx = mmY(meshSizeMm)
            val maxW = minOf(mmX(maxRenderW), meshWpx); val maxH = minOf(mmY(maxRenderH), meshHpx)
            val half = surfW / 2
            val newW = maxOf(0, minOf(maxW, half - Math.abs(2 * off - half)))
            val newH = maxOf(0, minOf(maxH, surfH))
            val changed = newW != lensW || newH != lensH
            lensW = newW; lensH = newH
            leftL = half - lensW / 2 - off
            rightL = half - lensW / 2 + off
            lensTop = surfH / 2 - lensH / 2 + mmY(verticalOffsetMm)
            if (lensW > 0 && lensH > 0) {
                val wMm = lensW * 25.4f / dm.xdpi; val hMm = lensH * 25.4f / dm.ydpi
                meshScaleX = 2 / wMm; meshScaleY = 2 / hMm
                texScaleX = meshWpx.toFloat() / lensW; texScaleY = meshHpx.toFloat() / lensH
                // Corrección empírica de Parrot (LensProjection.resize)
                texScaleX += 0.5f - 0.5f / texScaleX; texScaleY += 0.5f - 0.5f / texScaleY
            }
            lastKey = key
            if (changed && contentFbo != 0) makeTargets()
            overlayDirty = true
        }

        private fun makeTargets() {
            if (lensW <= 0 || lensH <= 0) return
            val ids = IntArray(1)
            if (contentFbo == 0) { glGenFramebuffers(1, ids, 0); contentFbo = ids[0]; glGenTextures(1, ids, 0); contentTex = ids[0]
                glGenFramebuffers(1, ids, 0); lensFbo = ids[0]; glGenRenderbuffers(1, ids, 0); lensRbo = ids[0] }
            glBindTexture(GL_TEXTURE_2D, contentTex)
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, lensW, lensH, 0, GL_RGBA, GL_UNSIGNED_BYTE, null)
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR); glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE); glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE)
            glBindFramebuffer(GL_FRAMEBUFFER, contentFbo)
            glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, contentTex, 0)
            glBindRenderbuffer(GL_RENDERBUFFER, lensRbo)
            glRenderbufferStorage(GL_RENDERBUFFER, GL_RGBA8, lensW, lensH)
            glBindFramebuffer(GL_FRAMEBUFFER, lensFbo)
            glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_RENDERBUFFER, lensRbo)
            glBindFramebuffer(GL_FRAMEBUFFER, 0); glBindRenderbuffer(GL_RENDERBUFFER, 0)
        }

        // ------------------------------------------------------------------ telemetría dentro de la imagen

        private fun uploadOverlay() {
            overlayDirty = false
            if (lensW <= 0 || lensH <= 0) return
            val b = ovlBitmap?.takeIf { it.width == lensW && it.height == lensH }
                ?: Bitmap.createBitmap(lensW, lensH, Bitmap.Config.ARGB_8888).also { ovlBitmap?.recycle(); ovlBitmap = it }
            b.eraseColor(Color.TRANSPARENT)
            val c = Canvas(b)
            // HUD compacto: dos líneas pequeñas arriba, flecha a casa abajo y el centro libre para pilotar.
            // Se queda dentro del 70 % central de la lente: los bordes se ven deformados.
            val p = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.WHITE; typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
                textSize = lensH / 26f; setShadowLayer(3f, 0f, 0f, Color.BLACK); textAlign = Paint.Align.CENTER
            }
            val bg = Paint().apply { color = 0x77000000 }
            val lines = overlayText.split("\n").filter { it.isNotBlank() }
            // Encoger si alguna línea no cabe en el ancho útil.
            val maxW = lensW * 0.70f
            val widest = lines.maxOfOrNull { p.measureText(it) } ?: 0f
            if (widest > maxW) p.textSize *= maxW / widest
            val lh = p.textSize * 1.2f
            var y = lensH * 0.17f + p.textSize
            if (lines.isNotEmpty()) {
                val w = minOf(maxW, widest) / 2 + p.textSize * 0.5f
                c.drawRoundRect(lensW / 2f - w, y - p.textSize * 1.05f, lensW / 2f + w, y + lh * (lines.size - 1) + p.textSize * 0.35f, p.textSize * 0.3f, p.textSize * 0.3f, bg)
            }
            for (l in lines) { c.drawText(l, lensW / 2f, y, p); y += lh }
            // Indicador de calidad del enlace: barras de color a la izquierda de la primera línea.
            if (linkLevel in 1..5 && lines.isNotEmpty()) {
                val col = when (linkLevel) { 1 -> 0xFFE74C3C.toInt(); 2 -> 0xFFF39C12.toInt(); 3 -> 0xFFF1C40F.toInt(); else -> 0xFF2ECC71.toInt() }
                val bw = p.textSize * 0.28f; val bx = lensW / 2f - minOf(maxW, widest) / 2 - p.textSize * 2.6f
                val base = lensH * 0.17f + p.textSize + lh * 0.5f
                for (i in 0 until 5) {
                    val bh = p.textSize * (0.35f + 0.25f * i)
                    val bp = Paint().apply { color = if (i < linkLevel) col else 0x66FFFFFF }
                    c.drawRect(bx + i * bw * 1.5f, base - bh, bx + i * bw * 1.5f + bw, base, bp)
                }
            }
            // Flecha a casa: hacia dónde queda la casa respecto al morro del dron (arriba = de frente).
            val cx = lensW / 2f; val cy = lensH * 0.74f; val rr = lensH * 0.045f
            if (!homeRel.isNaN() || homeLabel.isNotEmpty()) {
                c.drawCircle(cx, cy, rr * 1.45f, bg)
                if (!homeRel.isNaN()) {
                    val path = android.graphics.Path().apply {
                        moveTo(0f, -rr); lineTo(rr * 0.62f, rr * 0.75f); lineTo(0f, rr * 0.38f); lineTo(-rr * 0.62f, rr * 0.75f); close()
                    }
                    c.save(); c.translate(cx, cy); c.rotate(homeRel)
                    c.drawPath(path, Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xFF00E5FF.toInt(); setShadowLayer(3f, 0f, 0f, Color.BLACK) })
                    c.restore()
                } else c.drawText("⌂", cx, cy + p.textSize * 0.35f, p)
                c.drawText(homeLabel, cx, cy + rr * 1.45f + p.textSize * 1.15f, p)
            }
            alertText?.takeIf { it.isNotBlank() }?.let { a ->
                val ap = Paint(p).apply { textSize = lensH / 22f }
                val al = a.split("\n").take(2)
                val aw = (al.maxOfOrNull { ap.measureText(it) } ?: 0f)
                if (aw > maxW) ap.textSize *= maxW / aw
                var ay = lensH * 0.58f
                val hw = minOf(maxW, aw) / 2 + ap.textSize * 0.5f
                c.drawRoundRect(cx - hw, ay - ap.textSize * 1.05f, cx + hw, ay + ap.textSize * 1.2f * (al.size - 1) + ap.textSize * 0.4f, ap.textSize * 0.3f, ap.textSize * 0.3f,
                    Paint().apply { color = (alertColor and 0x00FFFFFF) or (0xDD shl 24) })
                for (l in al) { c.drawText(l, cx, ay, ap); ay += ap.textSize * 1.2f }
            }
            glBindTexture(GL_TEXTURE_2D, ovlTex)
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR); glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
            GLUtils.texImage2D(GL_TEXTURE_2D, 0, b, 0)
        }

        // ------------------------------------------------------------------ paquete de lentes de Parrot

        private fun loadModel() {
            try {
                val raw = resources.openRawResource(resources.getIdentifier("hmd_models", "raw", context.packageName))
                DataInputStream(raw).use { src ->
                    val magic = ByteArray(20); src.readFully(magic)
                    if (String(magic) != "Parrot HMD Data Pack") throw IllegalStateException("bad magic")
                    src.readByte()
                    val json = ByteArray(src.readInt()); src.readFully(json)
                    val all = src.readBytes()
                    val m = JSONObject(String(json)).getJSONObject(model)
                    meshSizeMm = m.getDouble("meshSize").toFloat()
                    defaultIpd = m.getJSONObject("ipd").getDouble("default").toFloat()
                    m.optJSONObject("maxRenderSize")?.let { maxRenderW = it.optDouble("width", 1e6).toFloat(); maxRenderH = it.optDouble("height", 1e6).toFloat() }
                    m.optJSONObject("chromaCorrection")?.let { chromaR = it.optDouble("r", 1.0).toFloat(); chromaG = it.optDouble("g", 1.0).toFloat(); chromaB = it.optDouble("b", 1.0).toFloat() }
                    fun buf(key: String): ByteBuffer? = m.optJSONObject(key)?.let { o ->
                        val off = o.getInt("offset"); val size = o.getInt("size")
                        ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder()).apply {
                            put(ByteBuffer.wrap(all, off, size).order(ByteOrder.LITTLE_ENDIAN).let { le ->
                                // Los datos vienen en little-endian; en ARM (little-endian) es copia directa.
                                le.slice()
                            }); position(0)
                        }
                    }
                    val ids = IntArray(1)
                    glGenVertexArrays(1, ids, 0); lensVao = ids[0]
                    glBindVertexArray(lensVao)
                    fun attr(loc: Int, b: ByteBuffer?, comps: Int) {
                        if (b == null) { glDisableVertexAttribArray(loc); if (loc == 2) glVertexAttrib4f(2, 1f, 1f, 1f, 1f); return }
                        val v = IntArray(1); glGenBuffers(1, v, 0)
                        glBindBuffer(GL_ARRAY_BUFFER, v[0]); glBufferData(GL_ARRAY_BUFFER, b.capacity(), b, GL_STATIC_DRAW)
                        glVertexAttribPointer(loc, comps, GL_FLOAT, false, 0, 0); glEnableVertexAttribArray(loc)
                    }
                    attr(0, buf("meshPositions"), 2)
                    attr(1, buf("texCoords"), 2)
                    attr(2, buf("colorFilter"), 4)
                    val idx = buf("indices")!!
                    val v = IntArray(1); glGenBuffers(1, v, 0)
                    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, v[0]); glBufferData(GL_ELEMENT_ARRAY_BUFFER, idx.capacity(), idx, GL_STATIC_DRAW)
                    elementCount = idx.capacity() / 4
                    glBindVertexArray(0)
                    glBindBuffer(GL_ARRAY_BUFFER, 0)
                }
            } catch (t: Throwable) { Crumbs.write("gafas: modelo ${t.javaClass.simpleName}: ${t.message}") }
        }

        // ------------------------------------------------------------------ utilidades GL

        private val quad: ByteBuffer = ByteBuffer.allocateDirect(4 * 4 * 4).order(ByteOrder.nativeOrder()).apply {
            asFloatBuffer().put(floatArrayOf(-1f, -1f, 0f, 0f, 1f, -1f, 1f, 0f, -1f, 1f, 0f, 1f, 1f, 1f, 1f, 1f)); position(0)
        }

        private fun drawQuad(prog: Int) {
            val pos = glGetAttribLocation(prog, "pos"); val uv = glGetAttribLocation(prog, "uv")
            quad.position(0); glVertexAttribPointer(pos, 2, GL_FLOAT, false, 16, quad); glEnableVertexAttribArray(pos)
            quad.position(8); glVertexAttribPointer(uv, 2, GL_FLOAT, false, 16, quad); glEnableVertexAttribArray(uv)
            glDrawArrays(GL_TRIANGLE_STRIP, 0, 4)
            glDisableVertexAttribArray(pos); glDisableVertexAttribArray(uv)
        }

        private fun shader(type: Int, src: String): Int {
            val s = glCreateShader(type); glShaderSource(s, src); glCompileShader(s)
            val ok = IntArray(1); glGetShaderiv(s, GL_COMPILE_STATUS, ok, 0)
            if (ok[0] == 0) Crumbs.write("gafas: shader ${glGetShaderInfoLog(s)}")
            return s
        }

        private fun program(vs: String, fs: String): Int {
            val p = glCreateProgram()
            glAttachShader(p, shader(GL_VERTEX_SHADER, vs)); glAttachShader(p, shader(GL_FRAGMENT_SHADER, fs))
            if (vs === VS_LENS) { glBindAttribLocation(p, 0, "position"); glBindAttribLocation(p, 1, "texCoords"); glBindAttribLocation(p, 2, "colorFilter") }
            glLinkProgram(p)
            return p
        }
    }

    companion object {
        private const val VS_QUAD = """#version 300 es
in vec2 pos; in vec2 uv; uniform vec2 scale; uniform mat4 stMatrix; out vec2 vUv;
void main() { vUv = uv; gl_Position = vec4(pos * scale, 0.0, 1.0); }"""
        // El vídeo externo: aplicar la matriz de la SurfaceTexture.
        private const val FS_OES = """#version 300 es
#extension GL_OES_EGL_image_external_essl3 : require
precision mediump float; uniform samplerExternalOES tex; uniform mat4 stMatrix; in vec2 vUv; out vec4 c;
void main() { c = texture(tex, (stMatrix * vec4(vUv, 0.0, 1.0)).xy); }"""
        // La telemetría está en un Bitmap (origen arriba): invertir Y.
        private const val FS_TEX = """#version 300 es
precision mediump float; uniform sampler2D tex; in vec2 vUv; out vec4 c;
void main() { c = texture(tex, vec2(vUv.x, 1.0 - vUv.y)); }"""
        // Shaders de lente de GroundSDK (gsdk_internal_hmd_lens_*.glsl, BSD-3) con uniformes por nombre (GLES 3.0).
        private const val VS_LENS = """#version 300 es
precision highp float;
uniform vec2 meshScale; uniform vec2 texScale; uniform vec3 chromaCorrection;
in vec2 position; in vec2 texCoords; in vec4 colorFilter;
out vec2 fragTexCoordsR; out vec2 fragTexCoordsG; out vec2 fragTexCoordsB; out vec4 fragColorFilter;
const vec2 center = vec2(0.5);
void main() {
    vec2 scaledCoords = (vec2(texCoords.x, 1.0 - texCoords.y) - center) * texScale;
    fragTexCoordsR = scaledCoords * chromaCorrection.r + center;
    fragTexCoordsG = scaledCoords * chromaCorrection.g + center;
    fragTexCoordsB = scaledCoords * chromaCorrection.b + center;
    fragColorFilter = colorFilter;
    gl_Position = vec4(position * meshScale, 0.0, 1.0);
}"""
        private const val FS_LENS = """#version 300 es
precision highp float;
uniform sampler2D sampler;
in vec2 fragTexCoordsR; in vec2 fragTexCoordsG; in vec2 fragTexCoordsB; in vec4 fragColorFilter;
out vec4 fragColor;
bool inside(vec2 t) { return t.x >= 0.0 && t.y >= 0.0 && t.x <= 1.0 && t.y <= 1.0; }
void main() {
    fragColor = vec4(vec3(0.0), 1.0);
    if (inside(fragTexCoordsR)) fragColor.r = texture(sampler, fragTexCoordsR).r;
    if (inside(fragTexCoordsG)) fragColor.g = texture(sampler, fragTexCoordsG).g;
    if (inside(fragTexCoordsB)) fragColor.b = texture(sampler, fragTexCoordsB).b;
    fragColor *= fragColorFilter;
}"""
    }
}
