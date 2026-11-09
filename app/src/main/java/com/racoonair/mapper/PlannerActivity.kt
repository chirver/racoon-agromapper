package com.racoonair.mapper

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** Planificador tipo Pix4Dcapture: dibuja la zona, ajusta parámetros y genera el plan Parrot. */
class PlannerActivity : Activity(), MapView.Listener {

    private lateinit var map: MapView
    private lateinit var stats: TextView
    private lateinit var homeText: TextView
    private lateinit var homeBtn: Button
    private lateinit var fAlt: EditText
    private lateinit var cameraSpinner: android.widget.Spinner
    /** Cámaras de los drones que maneja ESTA app. */
    private val cameras = Camera.ALL.filter { Flavor.supports(it.platform) }
    private lateinit var fFront: EditText
    private lateinit var fSide: EditText
    private lateinit var fSpeed: EditText
    private lateinit var fAngle: EditText
    private lateinit var fOver: EditText

    private val zone get() = Session.zone
    private lateinit var topBar: View
    private lateinit var toolsBar: View
    private lateinit var panelScroll: ScrollView
    private lateinit var frame: LinearLayout

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        arrange(newConfig.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE)
    }

    /** Vertical: mapa arriba y panel abajo. Apaisado: mapa a la izquierda y panel a la derecha. */
    private fun arrange(landscape: Boolean) {
        for (v in listOf(topBar, map, toolsBar, panelScroll)) (v.parent as? ViewGroup)?.removeView(v)
        frame.removeAllViews()
        val mw = ViewGroup.LayoutParams.MATCH_PARENT; val wc = ViewGroup.LayoutParams.WRAP_CONTENT
        if (!landscape) {
            frame.orientation = LinearLayout.VERTICAL
            frame.addView(topBar, LinearLayout.LayoutParams(mw, wc))
            frame.addView(map, LinearLayout.LayoutParams(mw, 0, 1.15f))
            frame.addView(toolsBar, LinearLayout.LayoutParams(mw, wc))
            frame.addView(panelScroll, LinearLayout.LayoutParams(mw, 0, 1f))
        } else {
            frame.orientation = LinearLayout.HORIZONTAL
            frame.addView(map, LinearLayout.LayoutParams(0, mw, 1.3f))
            val right = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
            right.addView(topBar, LinearLayout.LayoutParams(mw, wc))
            right.addView(toolsBar, LinearLayout.LayoutParams(mw, wc))
            right.addView(panelScroll, LinearLayout.LayoutParams(mw, 0, 1f))
            frame.addView(right, LinearLayout.LayoutParams(0, mw, 1f))
        }
    }
    private var result: PlanResult? = null
    private var blockersShown: List<String> = emptyList()
    private var settingHome = false
    private var phonePos: LatLon? = null
    private val main = Handler(Looper.getMainLooper())
    private var locMgr: LocationManager? = null

    companion object {
        private const val C_BG = 0xFF101418.toInt()
        private const val C_CARD = 0xFF1B2128.toInt()
        private const val C_TEXT = 0xFFE6EDF3.toInt()
        private const val C_DIM = 0xFF8B98A5.toInt()
        private const val C_GREEN = 0xFF2E9E5B.toInt()
        private const val C_BLUE = 0xFF2F6FB3.toInt()
        private const val C_GREY = 0xFF3A444F.toInt()
        private const val C_AMBER = 0xFFC98A1B.toInt()
        private const val REQ_LOC = 7
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        I18n.init(applicationContext)
        setTheme(android.R.style.Theme_Material_NoActionBar)
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.statusBarColor = C_BG
        Crumbs.write("planner: abrir")
        Net.appContext = applicationContext
        setContentView(buildUi())
        startLocation()
        map.post { initialView(); recompute() }
        main.post(tick)
    }

    override fun onDestroy() {
        main.removeCallbacksAndMessages(null)
        downloadCancel?.set(true)
        try { locMgr?.removeUpdates(locListener) } catch (_: Throwable) {}
        map.release()
        super.onDestroy()
    }

    // ---------------------------------------------------------------- UI

    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()

    private fun button(text: String, color: Int, onClick: () -> Unit) = Button(this).apply {
        this.text = text; isAllCaps = false; setTextColor(Color.WHITE); textSize = 14f
        typeface = Typeface.DEFAULT_BOLD
        background = GradientDrawable().apply { setColor(color); cornerRadius = dp(8).toFloat() }
        minHeight = dp(46); minimumHeight = dp(46)
        setPadding(dp(6), 0, dp(6), 0)
        setOnClickListener { onClick() }
    }

    private fun row(vararg views: View) = LinearLayout(this).apply {
        orientation = LinearLayout.HORIZONTAL
        for ((i, v) in views.withIndex()) {
            val lp = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            lp.setMargins(if (i == 0) 0 else dp(3), dp(3), if (i == views.size - 1) 0 else dp(3), dp(3))
            addView(v, lp)
        }
    }

    private fun field(label: String, value: String, hint: String = ""): Pair<View, EditText> {
        val box = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        box.addView(TextView(this).apply { text = label; setTextColor(C_DIM); textSize = 11f })
        val e = EditText(this).apply {
            setText(value); this.hint = hint; setHintTextColor(0xFF5A6773.toInt())
            setTextColor(C_TEXT); textSize = 16f; setSingleLine()
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            background = GradientDrawable().apply { setColor(0xFF242C35.toInt()); cornerRadius = dp(6).toFloat() }
            setPadding(dp(8), dp(6), dp(8), dp(6))
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, a: Int, b: Int, c: Int) {}
                override fun onTextChanged(s: CharSequence?, a: Int, b: Int, c: Int) {}
                override fun afterTextChanged(s: Editable?) { scheduleRecompute() }
            })
        }
        box.addView(e)
        return Pair(box, e)
    }

    private fun fmt(v: Double) = if (v == Math.floor(v)) v.toInt().toString() else "%.1f".format(Locale.ROOT, v)

    private fun buildUi(): View {
        val root = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setBackgroundColor(C_BG) }

        val top = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(12), dp(8), dp(8), dp(4))
        }
        top.addView(TextView(this).apply {
            text = tr("Planificar zona"); setTextColor(C_TEXT); textSize = 19f; typeface = Typeface.DEFAULT_BOLD
        }, LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f))
        top.addView(button(tr("Cerrar"), C_GREY) { finish() })
        root.addView(top); topBar = top

        map = MapView(this)
        map.listener = this
        map.polygon = zone.toList()
        map.base = if (prefs.getString("capa_base", "PNOA") == "ESRI") TileLayer.ESRI else TileLayer.PNOA
        map.showSigpac = prefs.getBoolean("capa_sigpac", false)
        root.addView(map, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.15f))

        val tools = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(8), dp(4), dp(8), 0) }
        homeBtn = button(tr("Casa…"), C_GREY) { toggleHomeMode() }
        sigpacBtn = button(tr("🌾 Parcela SIGPAC"), C_GREY) { toggleSigpacMode() }
        layerBtn = button("", C_GREY) { toggleSigpacLayer() }
        updateLayerBtn()
        airBtn = button("", C_GREY) { toggleAirframe() }
        tools.addView(row(airBtn, sigpacBtn, layerBtn, button(tr("📥 Offline"), C_GREY) { offlineDialog() }))
        landBtn = button(tr("🛬 Aterrizaje"), C_GREY) { toggleLandingMode() }
        val discoRow = row(landBtn,
            button(tr("🌬 Según viento"), C_GREY) { landingFromWind() },
            button(tr("Aprox…"), C_GREY) { approachDialog() },
            button("", C_GREY) { toggleDroneCamera() }.also { camBtn = it })
        tools.addView(discoRow); this.discoRow = discoRow
        tools.addView(row(
            button(tr("↶ Deshacer"), C_GREY) { if (zone.isNotEmpty()) { zone.removeAt(zone.size - 1); zoneChanged() } },
            button(tr("Borrar"), C_GREY) { confirmClear() },
            button(tr("Encuadrar"), C_GREY) { recenter() },
            homeBtn
        ))
        root.addView(tools); toolsBar = tools

        val panel = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(10), dp(4), dp(10), dp(10)) }
        // La cámara por defecto es siempre una de los drones de ESTA app.
        if (!Flavor.supports(Session.params.camera.platform))
            Session.params = Session.params.copy(camera = cameras.first())
        val p = Session.params
        cameraSpinner = android.widget.Spinner(this).apply {
            adapter = object : android.widget.ArrayAdapter<Camera>(this@PlannerActivity, android.R.layout.simple_spinner_item, cameras) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
                    (super.getView(position, convertView, parent) as TextView).apply { setTextColor(C_TEXT); textSize = 15f }
            }.apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
            setSelection(cameras.indexOf(p.camera).coerceAtLeast(0))
            onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
                override fun onItemSelected(pv: android.widget.AdapterView<*>?, v: View?, pos: Int, id: Long) { scheduleRecompute() }
                override fun onNothingSelected(pv: android.widget.AdapterView<*>?) {}
            }
        }
        panel.addView(TextView(this).apply { text = tr("Dron y cámara"); setTextColor(C_DIM); textSize = 11f })
        panel.addView(cameraSpinner)
        // Misiones 3D (solo ANAFI: gimbal inclinable y apuntado a un punto).
        missionBtn = button("", C_BLUE) { pickMissionType() }
        spec3DBtn = button(tr("Ajustes 3D…"), C_GREY) { spec3DDialog() }
        missionRow = row(missionBtn, spec3DBtn)
        panel.addView(missionRow)
        updateMissionUi()
        val (a, ea) = field(tr("Altura (m)"), fmt(p.altitude)); fAlt = ea
        val (b, eb) = field(tr("Solape frontal %"), fmt(p.frontOverlap * 100)); fFront = eb
        val (c, ec) = field(tr("Solape lateral %"), fmt(p.sideOverlap * 100)); fSide = ec
        val (d, ed) = field(tr("Velocidad (m/s)"), fmt(p.speed)); fSpeed = ed
        val (e, ee) = field(tr("Rumbo pasadas (°)"), p.angleDeg?.let { fmt(it) } ?: "", "auto"); fAngle = ee
        val (f, ef) = field(tr("Margen extra (m)"), fmt(p.overshoot)); fOver = ef
        panel.addView(row(a, b, c))
        panel.addView(row(d, e, f))
        homeText = TextView(this).apply { setTextColor(C_DIM); textSize = 12f; setPadding(0, dp(4), 0, 0) }
        panel.addView(homeText)
        stats = TextView(this).apply {
            setTextColor(C_TEXT); textSize = 13f; typeface = Typeface.MONOSPACE
            setPadding(dp(10), dp(8), dp(10), dp(8))
            background = GradientDrawable().apply { setColor(C_CARD); cornerRadius = dp(8).toFloat() }
        }
        panel.addView(stats, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply { topMargin = dp(6) })
        panel.addView(row(
            button(tr("Abrir zona"), C_GREY) { openZone() },
            button(tr("Exportar KML"), C_GREY) { exportKml() },
            button(tr("Guardar"), C_BLUE) { saveDialog(useAfter = false) }
        ))
        panel.addView(row(button(tr("✔  USAR ESTE PLAN"), C_GREEN) { saveDialog(useAfter = true) }))
        panelScroll = ScrollView(this).apply { addView(panel) }
        frame = LinearLayout(this).apply { setBackgroundColor(C_BG) }
        root.removeAllViews()
        arrange(resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE)
        // El modo sale del dron conectado si se sabe; si no, el último elegido.
        when (Session.link?.telemetry?.droneModel) {
            1 -> prefs.edit().putString("airframe", "BLUEGRASS").apply()
            2 -> prefs.edit().putString("airframe", "DISCO").apply()
        }
        updateAirframeUi()
        return frame
    }

    // ---------------------------------------------------------------- mapa

    private fun dronePos(): LatLon? {
        val t = Session.link?.telemetry ?: return null
        if (t.lat == 500.0 || t.lon == 500.0 || (t.lat == 0.0 && t.lon == 0.0)) return null
        return LatLon(t.lat, t.lon)
    }

    /** Casa: manual > posición del dron > posición del móvil. */
    private fun homeAndSource(): Pair<LatLon?, String> {
        Session.manualHome?.let { return Pair(it, tr("casa fijada a mano en el mapa")) }
        dronePos()?.let { return Pair(it, tr("casa = posición GPS del dron")) }
        phonePos?.let { return Pair(it, tr("casa = posición del MÓVIL (aprox.): ponte junto al dron o fíjala con «Casa…»")) }
        return Pair(null, tr("sin casa: conecta el dron con GPS, activa la ubicación del móvil o fíjala con «Casa…»"))
    }

    private fun initialView() {
        when {
            zone.size >= 2 -> map.fit(zone + listOfNotNull(homeAndSource().first))
            dronePos() != null -> map.setCenter(dronePos()!!, 17.0)
            phonePos != null -> map.setCenter(phonePos!!, 17.0)
            else -> map.setCenter(LatLon(38.9848, -3.9274), 13.0) // Ciudad Real hasta tener posición
        }
    }

    private fun recenter() {
        val pts = zone + listOfNotNull(homeAndSource().first, dronePos())
        if (pts.size >= 2) map.fit(pts) else (pts.firstOrNull() ?: phonePos)?.let { map.setCenter(it, 17.0) }
    }

    override fun onMapTap(p: LatLon) {
        if (sigpacMode) { querySigpac(p); return }
        if (landStep == 1) {
            Session.fixedWing = Session.fixedWing.copy(landing = p, headingSet = false, elevSet = false, elevDiff = 0.0)
            landStep = 2; landBtn.text = tr("Toca DE DÓNDE llega…")
            Toast.makeText(this, tr("Ahora toca de dónde llega el avión (lado de la aproximación, ~200 m)"), Toast.LENGTH_LONG).show()
            map.landing = null; recompute(); return
        }
        if (landStep == 2) {
            val ld = Session.fixedWing.landing ?: return
            if (GridPlanner.distance(p, ld) < 50.0) {
                Toast.makeText(this, tr("Toca más lejos del punto de toma (a 100–200 m, por donde llegará el avión)"), Toast.LENGTH_LONG).show(); return
            }
            Session.fixedWing = Session.fixedWing.copy(finalHeading = GridPlanner.bearing(p, ld), headingSet = true)
            landStep = 0; landBtn.text = tr("🛬 Aterrizaje"); setBtnColor(landBtn, C_GREY)
            Toast.makeText(this, trf("Final rumbo %1\$s°. Revisa el cono azul en la ortofoto.", GridPlanner.bearing(p, ld).toInt()), Toast.LENGTH_LONG).show()
            recompute(); return
        }
        if (settingHome) {
            Session.manualHome = p; settingHome = false; homeBtn.text = tr("Casa…")
            Toast.makeText(this, tr("Casa fijada aquí"), Toast.LENGTH_SHORT).show()
            recompute(); return
        }
        zone.add(p); zoneChanged()
    }

    override fun onMapLongPress(p: LatLon) {
        // Pulsación larga cerca de un vértice: lo borra.
        val s = map.toScreen(p)
        val idx = zone.indexOfFirst { val q = map.toScreen(it); Math.hypot((q[0] - s[0]).toDouble(), (q[1] - s[1]).toDouble()) < dp(24) }
        if (idx >= 0) { zone.removeAt(idx); zoneChanged(); Toast.makeText(this, trf("Punto %1\$s borrado", idx + 1), Toast.LENGTH_SHORT).show() }
    }

    // ---------------------------------------------------------------- SIGPAC

    private val prefs by lazy { getSharedPreferences("ajustes", MODE_PRIVATE) }
    private lateinit var sigpacBtn: Button
    private lateinit var layerBtn: Button
    private var sigpacMode = false
    private var sigpacBusy = false

    private fun setBtnColor(b: Button, c: Int) { b.background = GradientDrawable().apply { setColor(c); cornerRadius = dp(8).toFloat() } }

    private fun toggleSigpacMode() {
        sigpacMode = !sigpacMode
        if (sigpacMode) { settingHome = false; homeBtn.text = tr("Casa…") }
        sigpacBtn.text = if (sigpacMode) tr("Toca la parcela…") else tr("🌾 Parcela SIGPAC")
        setBtnColor(sigpacBtn, if (sigpacMode) C_AMBER else C_GREY)
        if (sigpacMode) {
            if (!map.showSigpac && map.zoom >= MapView.SIGPAC_MIN_Z) { map.showSigpac = true; updateLayerBtn() }
            Toast.makeText(this, tr("Toca dentro de la parcela que quieres volar"), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateLayerBtn() {
        layerBtn.text = if (map.showSigpac) tr("Recintos: SÍ") else tr("Recintos: NO")
        setBtnColor(layerBtn, if (map.showSigpac) C_BLUE else C_GREY)
    }

    private fun toggleSigpacLayer() {
        map.showSigpac = !map.showSigpac
        prefs.edit().putBoolean("capa_sigpac", map.showSigpac).apply()
        updateLayerBtn()
        if (map.showSigpac && map.zoom < MapView.SIGPAC_MIN_Z) Toast.makeText(this, tr("Acércate más para ver los recintos"), Toast.LENGTH_SHORT).show()
    }

    private fun querySigpac(p: LatLon) {
        if (sigpacBusy) return
        sigpacBusy = true
        sigpacBtn.text = tr("Consultando SIGPAC…")
        Thread {
            val res = try { Result.success(Sigpac.query(p)) } catch (t: Throwable) { Result.failure<List<Sigpac.Recinto>>(t) }
            main.post {
                sigpacBusy = false
                if (isFinishing || isDestroyed) return@post
                sigpacBtn.text = if (sigpacMode) tr("Toca la parcela…") else tr("🌾 Parcela SIGPAC")
                res.onFailure {
                    Crumbs.write("sigpac: ${it.javaClass.simpleName}: ${it.message}")
                    AlertDialog.Builder(this).setTitle(tr("SIGPAC no responde"))
                        .setMessage(trf("No se pudo consultar el recinto (%1\$s).\n\n", it.message ?: it.javaClass.simpleName) +
                            tr("Hace falta internet (datos móviles vale aunque estés conectado a la WiFi del dron). ") +
                            tr("Si estás en el campo sin cobertura, dibuja la zona a mano."))
                        .setPositiveButton(tr("Entendido"), null).show()
                }
                res.onSuccess { list ->
                    when {
                        list.isEmpty() -> Toast.makeText(this, tr("No hay recinto SIGPAC en ese punto"), Toast.LENGTH_LONG).show()
                        list.size == 1 -> offerRecinto(list[0])
                        else -> AlertDialog.Builder(this).setTitle(tr("Varios recintos en ese punto"))
                            .setItems(list.map { it.nombre + " · " + (Sigpac.USOS[it.uso] ?: it.uso) }.toTypedArray()) { _, i -> offerRecinto(list[i]) }
                            .setNegativeButton(tr("Cancelar"), null).show()
                    }
                }
            }
        }.start()
    }

    private fun offerRecinto(r: Sigpac.Recinto) {
        map.highlight = r.contorno
        val sim = Sigpac.simplify(r.contorno)
        val simple = sim.points
        val notes = buildString {
            append(r.descripcion())
            append(trf("\n\nContorno: %1\$s vértices → %2\$s para el plan", r.contorno.size, simple.size) +
                (if (sim.tolM > 0) trf(" (se aparta del lindero como mucho %1\$s m).", "%.1f".format(sim.tolM)) else tr(" (sin simplificar).")))
            if (r.huecos > 0) append(trf("\n⚠ El recinto tiene %1\$s hueco(s) interior(es): se volarán igualmente.", r.huecos))
            if ((r.pendiente ?: 0.0) > 10) append(trf("\n⚠ Pendiente media %1\$s %%: la altura sobre el suelo variará a lo largo de las pasadas.", "%.0f".format(r.pendiente)))
            if (zone.isNotEmpty()) append(tr("\n\nSustituirá la zona dibujada ahora."))
        }
        AlertDialog.Builder(this).setTitle(tr("Recinto SIGPAC"))
            .setMessage(notes)
            .setNegativeButton(tr("Cancelar")) { _, _ -> map.highlight = emptyList() }
            .setPositiveButton(tr("Usar como zona")) { _, _ ->
                zone.clear(); zone.addAll(simple); clearLanding()
                Session.zoneName = trf("SIGPAC %1\$s-%2\$s-%3\$s-%4\$s-%5\$s", r.provincia, r.municipio, r.poligono, r.parcela, r.recinto)
                if (sigpacMode) toggleSigpacMode()
                zoneChanged(); recenter()
                Crumbs.write("sigpac: recinto ${r.codigo} (${simple.size} vértices)")
            }.show()
    }

    // ---------------------------------------------------------------- mapas sin conexión

    private fun offlineDialog() {
        val mb = MapView.cacheSizeMb(filesDir)
        val items = arrayOf(
            tr("📥 Descargar la zona que se ve ahora"),
            trf("Ortofoto: %1\$s", if (map.base == TileLayer.PNOA) "PNOA (IGN) ✓ · cambiar a Esri" else "Esri ✓ · cambiar a PNOA (IGN)"),
            trf("🗑 Borrar mapas guardados (%1\$s MB)", "%.0f".format(mb))
        )
        AlertDialog.Builder(this).setTitle(tr("Mapas sin conexión")).setItems(items) { _, w ->
            when (w) {
                0 -> askDownload()
                1 -> {
                    map.base = if (map.base == TileLayer.PNOA) TileLayer.ESRI else TileLayer.PNOA
                    prefs.edit().putString("capa_base", map.base.name).apply()
                }
                2 -> AlertDialog.Builder(this).setMessage(tr("¿Borrar todos los mapas guardados en el móvil?"))
                    .setNegativeButton(tr("No"), null).setPositiveButton(tr("Borrar")) { _, _ ->
                        MapView.clearCache(filesDir); map.retryFailed(); Toast.makeText(this, tr("Mapas borrados"), Toast.LENGTH_SHORT).show()
                    }.show()
            }
        }.setNegativeButton(tr("Cerrar"), null).show()
    }

    private fun askDownload() {
        if (map.base != TileLayer.PNOA) {
            AlertDialog.Builder(this).setMessage(tr("La descarga para usar sin conexión se hace con la ortofoto PNOA del IGN ") +
                tr("(licencia libre). Cambia la ortofoto a PNOA en este mismo menú.")).setPositiveButton(tr("Entendido"), null).show()
            return
        }
        if (map.zoom < 13) {
            AlertDialog.Builder(this).setMessage(tr("Acércate a la parcela (la zona visible debe ser de unos pocos km) y vuelve a intentarlo."))
                .setPositiveButton(tr("Entendido"), null).show(); return
        }
        val b = map.visibleBounds()
        val zMin = 12
        var zMax = 19
        val maxTiles = 6000L
        while (zMax > 15 && MapView.countTiles(b[0], b[1], b[2], b[3], zMin, zMax) > maxTiles) zMax--
        val withSigpac = android.widget.CheckBox(this).apply { text = trf("Incluir recintos SIGPAC (≈%1\$s teselas más)", MapView.countTiles(b[0], b[1], b[2], b[3], MapView.SIGPAC_MIN_Z, minOf(zMax, MapView.SIGPAC_MAX_Z))); isChecked = true; setTextColor(C_TEXT) }
        val layers = { if (withSigpac.isChecked) listOf(TileLayer.PNOA, TileLayer.SIGPAC) else listOf(TileLayer.PNOA) }
        val n = MapView.countTiles(b[0], b[1], b[2], b[3], zMin, zMax)
        if (n > maxTiles) {
            AlertDialog.Builder(this).setMessage(tr("La zona visible es demasiado grande. Acércate a la parcela y vuelve a intentarlo."))
                .setPositiveButton(tr("Entendido"), null).show(); return
        }
        val w = GridPlanner.distance(LatLon(b[0], b[2]), LatLon(b[0], b[3])); val h = GridPlanner.distance(LatLon(b[0], b[2]), LatLon(b[1], b[2]))
        val box = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(20), dp(8), dp(20), 0) }
        box.addView(TextView(this).apply {
            setTextColor(C_TEXT); textSize = 14f
            text = trf("Zona %1\$s km, zoom %2\$s–%3\$s%4\$s.\n", "%.1f × %.1f".format(w / 1000, h / 1000), zMin, zMax, if (zMax < 19) " (máximo detalle recortado: acércate más para el detalle completo)" else "") +
                trf("≈%1\$s teselas de ortofoto, ≈%2\$s MB.\n\nHace falta internet ahora; después se verá sin cobertura.", n, "%.0f".format(n * 0.03))
        })
        box.addView(withSigpac)
        AlertDialog.Builder(this).setTitle(tr("Descargar mapa")).setView(box)
            .setNegativeButton(tr("Cancelar"), null)
            .setPositiveButton(tr("Descargar")) { _, _ -> runDownload(b, zMin, zMax, layers()) }.show()
    }

    private var downloadCancel: java.util.concurrent.atomic.AtomicBoolean? = null

    private fun runDownload(b: DoubleArray, zMin: Int, zMax: Int, layers: List<TileLayer>) {
        val txt = TextView(this).apply { setTextColor(C_TEXT); textSize = 15f; setPadding(dp(20), dp(16), dp(20), dp(8)); text = tr("Preparando…") }
        val cancel = java.util.concurrent.atomic.AtomicBoolean(false)
        downloadCancel = cancel
        val dlg = AlertDialog.Builder(this).setTitle(tr("Descargando mapa")).setView(txt).setCancelable(false)
            .setNegativeButton(tr("Cancelar")) { _, _ -> cancel.set(true) }.show()
        Thread {
            var last = 0L
            val (ok, fail) = MapView.download(filesDir, layers, b[0], b[1], b[2], b[3], zMin, zMax) { d, t, f ->
                val now = System.currentTimeMillis()
                if (now - last > 300 || d == t) { last = now; main.post { txt.text = trf("%1\$s / %2\$s teselas%3\$s", d, t, if (f > 0) " · $f fallidas" else "") } }
                !cancel.get()
            }
            main.post {
                try { dlg.dismiss() } catch (_: Throwable) {}
                if (isFinishing || isDestroyed) return@post
                map.retryFailed()
                Crumbs.write("offline: ok=$ok fallidas=$fail z$zMin-$zMax")
                AlertDialog.Builder(this).setTitle(if (cancel.get()) tr("Descarga cancelada") else tr("Mapa guardado"))
                    .setMessage(trf("%1\$s teselas guardadas", ok) + (if (fail > 0) trf(", %1\$s fallidas (sin conexión o fuera de cobertura PNOA). Puedes repetir la descarga: solo pide lo que falta.", fail) else ".") +
                        tr("\n\nEn el campo, sin cobertura, el mapa de esta zona se verá igual."))
                    .setPositiveButton("OK", null).show()
            }
        }.start()
    }

    // ---------------------------------------------------------------- misiones 3D (ANAFI)

    private lateinit var missionBtn: Button
    private lateinit var spec3DBtn: Button
    private lateinit var missionRow: View
    /** Las misiones 3D necesitan gimbal inclinable: solo en la app de ANAFI. */
    private val supports3D get() = Flavor.supports(Platform.ANAFI)

    private fun updateMissionUi() {
        missionRow.visibility = if (supports3D) View.VISIBLE else View.GONE
        val t = Session.spec3D.type
        missionBtn.text = "🎯 " + t.label
        spec3DBtn.visibility = if (t.is3D) View.VISIBLE else View.GONE
    }

    private fun pickMissionType() {
        val types = MissionType.values()
        AlertDialog.Builder(this).setTitle(tr("Tipo de misión"))
            .setSingleChoiceItems(types.map { it.label }.toTypedArray(), types.indexOf(Session.spec3D.type)) { d, i ->
                d.dismiss()
                val t = types[i]
                if (t != Session.spec3D.type && zone.isNotEmpty())
                    Toast.makeText(this, tr("Cada tipo usa la zona de forma distinta: revisa los puntos dibujados."), Toast.LENGTH_LONG).show()
                Session.spec3D = Session.spec3D.copy(type = t)
                if (t == MissionType.OBLIQUE && (num(fAlt) ?: 0.0) < Session.spec3D.objectHeight + 15) fAlt.setText(fmt(Session.spec3D.objectHeight + 25))
                updateMissionUi(); recompute()
                if (t.is3D) spec3DDialog()
            }.setNegativeButton(tr("Cerrar"), null).show()
    }

    private fun spec3DDialog() {
        val s = Session.spec3D
        val box = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(20), dp(8), dp(20), 0) }
        val (v1, eH) = field(tr("Altura del objeto (m)"), fmt(s.objectHeight))
        val (v2, eD) = field(tr("Distancia al objeto (m)"), fmt(s.standoff))
        val (v3, eR) = field(tr("Anillos (1–3)"), s.rings.toString())
        val (v4, eT) = field(tr("Inclinación cámara (°)"), fmt(s.obliqueTilt), "-60")
        val (v5, eM) = field(tr("Fila más baja (m)"), fmt(s.minAlt))
        val (v6, eS) = field(tr("Velocidad de captura (m/s)"), fmt(s.speed))
        val (v7, eE) = field(tr("Desnivel base del objeto − despegue (m)"), fmt(s.baseElev), "0")
        // Inclinación y desnivel son negativos a menudo: teclado con signo.
        for (e in listOf(eT, eE)) e.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
        box.addView(v1)
        when (s.type) {
            MissionType.ORBIT -> { box.addView(v2); box.addView(v3); box.addView(v5); box.addView(v6); box.addView(v7) }
            MissionType.FACADE -> { box.addView(v2); box.addView(v5); box.addView(v6); box.addView(v7) }
            MissionType.OBLIQUE -> { box.addView(v4); box.addView(v7) }
            else -> {}
        }
        box.addView(TextView(this).apply {
            setTextColor(C_DIM); textSize = 12f
            text = when (s.type) {
                MissionType.ORBIT -> tr("Anillos alrededor del objeto con la cámara apuntando a su centro. Más anillos = mejor modelo, más tiempo. Para estatuas, 2–3 anillos a 8–12 m.")
                MissionType.FACADE -> tr("Filas horizontales de abajo arriba frente a la pared, cámara horizontal. Distancia típica 8–15 m.")
                MissionType.OBLIQUE -> tr("Dos cuadrículas cruzadas con la cámara inclinada. −60° es un buen punto de partida; −45° ve más fachada, −75° más tejado. La altura de vuelo es la del campo «Altura».")
                else -> ""
            } + "\n\n" + tr("Desnivel: si despegas más abajo o más arriba que la base del objeto, indica la diferencia (positivo = el objeto está más alto). Las alturas se calculan desde el despegue.")
        })
        AlertDialog.Builder(this).setTitle(Session.spec3D.type.label).setView(box)
            .setNegativeButton(tr("Cancelar"), null)
            .setPositiveButton(tr("Aplicar")) { _, _ ->
                val c = Session.spec3D
                Session.spec3D = c.copy(
                    objectHeight = (num(eH) ?: c.objectHeight).coerceIn(1.0, 100.0),
                    standoff = (num(eD) ?: c.standoff).coerceIn(1.0, 100.0),
                    rings = (num(eR)?.toInt() ?: c.rings).coerceIn(1, 3),
                    obliqueTilt = (num(eT) ?: c.obliqueTilt).coerceIn(-85.0, -30.0),
                    minAlt = (num(eM) ?: c.minAlt).coerceIn(1.0, 50.0),
                    speed = (num(eS) ?: c.speed).coerceIn(0.5, 8.0),
                    baseElev = (num(eE) ?: c.baseElev).coerceIn(-50.0, 50.0))
                recompute()
            }.show()
    }

    // ---------------------------------------------------------------- Disco (ala fija)

    /** Viento previsto respecto a la final: aviso de viento cruzado o de cola. */
    private fun windWarning(f: FixedWing): String? {
        val r = Session.weather ?: return tr("Sin previsión de viento: pulsa «🌬 Según viento» para orientar la final.")
        val ld = f.landing ?: return null
        if (System.currentTimeMillis() - r.fetchedAt > 3 * 3600_000L || GridPlanner.distance(ld, LatLon(r.lat, r.lon)) > 5000)
            return tr("Previsión de viento antigua o de otro sitio: vuelve a consultarla («🌬 Según viento») el día del vuelo.")
        val now = System.currentTimeMillis() / 1000
        val h = r.hours.lastOrNull { it.epochS <= now }?.takeIf { now - it.epochS < 2 * 3600 } ?: return tr("Previsión sin la hora actual: vuelve a consultarla.")
        if (h.dir10.isNaN() || h.wind10.isNaN()) return null
        val diff = GridPlanner.angleDiff(f.finalHeading, h.dir10)
        val head = h.wind10 * Math.cos(Math.toRadians(diff)); val cross = h.wind10 * Math.sin(Math.toRadians(diff))
        return when {
            head < -1.0 -> trf("⛔ VIENTO DE COLA en la final (%1\$s m/s): aterrizaría demasiado rápida. Usa «🌬 Según viento».", "%.1f".format(-head))
            cross > 4.0 -> trf("Viento cruzado de %1\$s m/s en la final: orienta la toma más de cara al viento.", "%.1f".format(cross))
            else -> null
        }
    }

    private lateinit var airBtn: Button
    private lateinit var landBtn: Button
    private lateinit var camBtn: Button
    private lateinit var discoRow: View
    /** 0 nada · 1 esperando el punto de toma · 2 esperando de dónde llega el avión. */
    private var landStep = 0

    private val isDisco get() = prefs.getString("airframe", "BLUEGRASS") == "DISCO"

    private fun updateAirframeUi() {
        airBtn.text = if (isDisco) tr("✈ Disco") else tr("✚ Bluegrass")
        setBtnColor(airBtn, if (isDisco) C_AMBER else C_BLUE)
        discoRow.visibility = if (isDisco) View.VISIBLE else View.GONE
        camBtn.text = if (Session.fixedWing.droneCamera) tr("📷 Dron") else tr("📷 Libre")
        homeBtn.text = if (isDisco) tr("Lanzam.…") else tr("Casa…")
        fSpeed.isEnabled = !isDisco
    }

    private fun toggleAirframe() {
        val next = if (isDisco) "BLUEGRASS" else "DISCO"
        prefs.edit().putString("airframe", next).apply()
        landStep = 0
        if (next == "DISCO") {
            fOver.setText(fmt(maxOf(num(fOver) ?: 0.0, FixedWing.RUN_IN)))
            if ((num(fAlt) ?: 0.0) < FixedWing.MIN_ALT) fAlt.setText(fmt(100.0))
            Toast.makeText(this, tr("Modo Disco: pasadas de ala fija y aterrizaje planificado"), Toast.LENGTH_SHORT).show()
        }
        updateAirframeUi(); recompute()
    }

    private fun clearLanding() {
        Session.fixedWing = FixedWing(droneCamera = Session.fixedWing.droneCamera, approachDist = Session.fixedWing.approachDist, approachAlt = Session.fixedWing.approachAlt)
        landStep = 0; if (::landBtn.isInitialized) { landBtn.text = tr("🛬 Aterrizaje"); setBtnColor(landBtn, C_GREY) }
    }

    private fun toggleLandingMode() {
        if (landStep != 0) { landStep = 0; landBtn.text = tr("🛬 Aterrizaje"); setBtnColor(landBtn, C_GREY); return }
        sigpacMode = false; settingHome = false
        landStep = 1; landBtn.text = tr("Toca el punto de TOMA…"); setBtnColor(landBtn, C_AMBER)
        Toast.makeText(this, tr("Toca dónde quieres que toque tierra la Disco (zona despejada)"), Toast.LENGTH_LONG).show()
    }

    /** Final de cara al viento: el avión vuela HACIA donde viene el viento. */
    private fun landingFromWind() {
        val ld = Session.fixedWing.landing ?: zone.takeIf { it.isNotEmpty() }?.let { z -> LatLon(z.map { it.lat }.average(), z.map { it.lon }.average()) }
        if (ld == null) { Toast.makeText(this, tr("Fija antes el punto de toma (🛬)"), Toast.LENGTH_SHORT).show(); return }
        val cached = Session.weather
        fun apply(r: Weather.Report) {
            val now = System.currentTimeMillis() / 1000
            val h = r.hours.lastOrNull { it.epochS <= now } ?: r.hours.firstOrNull()
            if (h == null || h.dir10.isNaN()) { Toast.makeText(this, tr("La previsión no trae dirección del viento"), Toast.LENGTH_LONG).show(); return }
            if (Session.fixedWing.landing == null) { Toast.makeText(this, tr("Fija antes el punto de toma (🛬)"), Toast.LENGTH_SHORT).show(); return }
            Session.fixedWing = Session.fixedWing.copy(finalHeading = h.dir10, headingSet = true)
            Toast.makeText(this, trf("Final de cara al viento: rumbo %1\$s° (viento del %2\$s, %3\$s m/s)", h.dir10.toInt(), Weather.rumbo(h.dir10), "%.1f".format(h.wind10)), Toast.LENGTH_LONG).show()
            recompute()
        }
        if (cached != null && System.currentTimeMillis() - cached.fetchedAt < 60 * 60_000 &&
            GridPlanner.distance(ld, LatLon(cached.lat, cached.lon)) < 5000) { apply(cached); return }
        Toast.makeText(this, tr("Consultando el viento…"), Toast.LENGTH_SHORT).show()
        Thread {
            val r = try { Weather.fetch(ld.lat, ld.lon) } catch (t: Throwable) { null }
            main.post {
                if (isFinishing || isDestroyed) return@post
                if (r == null) Toast.makeText(this, tr("Sin internet: no se pudo consultar el viento"), Toast.LENGTH_LONG).show()
                else { Session.weather = r; apply(r) }
            }
        }.start()
    }

    private fun approachDialog() {
        val f = Session.fixedWing
        val box = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(20), dp(8), dp(20), 0) }
        val (v1, e1) = field(tr("Distancia de aproximación (m)"), fmt(f.approachDist))
        val (v2, e2) = field(tr("Altura de aproximación (m)"), fmt(f.approachAlt))
        val (v3, e3) = field(tr("Desnivel toma − lanzamiento (m)"), fmt(f.elevDiff), tr("0 si es llano"))
        box.addView(v1); box.addView(v2); box.addView(v3)
        box.addView(TextView(this).apply {
            setTextColor(C_DIM); textSize = 12f
            text = tr("La Disco llega al punto de aproximación a esa altura y baja en línea recta hasta el punto de toma. ") +
                tr("Manual Parrot: unos 200 m de pasillo libre en un cono de 30°. ") +
                tr("Desnivel: cuánto más alta (+) o más baja (−) está la toma que el sitio de lanzamiento; las alturas del plan son relativas al lanzamiento.")
        })
        AlertDialog.Builder(this).setTitle(tr("Aproximación")).setView(box)
            .setNegativeButton(tr("Cancelar"), null)
            .setPositiveButton(tr("Aplicar")) { _, _ ->
                val d = num(e1); val a = num(e2); val el = num(e3) ?: 0.0
                if (d != null && a != null && d.isFinite() && a.isFinite() && el.isFinite())
                    Session.fixedWing = Session.fixedWing.copy(approachDist = d.coerceIn(120.0, 400.0), approachAlt = a.coerceIn(15.0, 60.0),
                        elevDiff = el.coerceIn(-50.0, 50.0), elevSet = true)
                recompute()
            }.show()
    }

    private fun toggleDroneCamera() {
        val on = !Session.fixedWing.droneCamera
        if (on) AlertDialog.Builder(this).setTitle(tr("¿La Disco gobierna la Sequoia?"))
            .setMessage(tr("Solo en una Disco-Pro AG con la función Sequoia activa. En una Disco FPV la Sequoia va por libre: ") +
                tr("déjalo en «Libre» y prepárala por WiFi antes de lanzar."))
            .setNegativeButton(tr("No, por libre"), null)
            .setPositiveButton(tr("Sí, Pro AG")) { _, _ ->
                Session.fixedWing = Session.fixedWing.copy(droneCamera = true); updateAirframeUi(); recompute()
            }.show()
        else { Session.fixedWing = Session.fixedWing.copy(droneCamera = false); updateAirframeUi(); recompute() }
    }

    private fun toggleHomeMode() {
        val items = arrayOf(tr("Tocar en el mapa para fijar la casa"), tr("Usar posición del dron / móvil (automático)"))
        AlertDialog.Builder(this).setTitle(tr("Punto de despegue y aterrizaje")).setItems(items) { _, w ->
            if (w == 0) { settingHome = true; homeBtn.text = tr("Toca el mapa…") }
            else { Session.manualHome = null; settingHome = false; homeBtn.text = tr("Casa…"); recompute() }
        }.show()
    }

    private fun confirmClear() {
        if (zone.isEmpty()) return
        AlertDialog.Builder(this).setMessage(tr("¿Borrar todos los puntos de la zona?"))
            .setNegativeButton(tr("No"), null).setPositiveButton(tr("Borrar")) { _, _ -> zone.clear(); clearLanding(); zoneChanged() }.show()
    }

    private fun zoneChanged() {
        map.polygon = zone.toList()
        if (zone.size < 3) map.highlight = emptyList()
        recompute()
    }

    // ---------------------------------------------------------------- cálculo

    private val recomputeRunnable = Runnable { recompute() }
    private fun scheduleRecompute() { main.removeCallbacks(recomputeRunnable); main.postDelayed(recomputeRunnable, 350) }

    private fun num(e: EditText): Double? = e.text.toString().replace(',', '.').trim().toDoubleOrNull()

    private fun readParams(): GridParams? {
        val alt = num(fAlt) ?: return null
        val fr = num(fFront) ?: return null
        val sd = num(fSide) ?: return null
        val sp = num(fSpeed) ?: return null
        val ov = num(fOver) ?: 0.0
        val ang = if (fAngle.text.isBlank()) null else (num(fAngle) ?: return null)
        if (listOf(alt, fr, sd, sp, ov).any { !it.isFinite() } || (ang != null && !ang.isFinite())) return null
        val cam = (cameraSpinner.selectedItem as? Camera) ?: Camera.SEQUOIA
        val fw = if (isDisco && !Session.spec3D.type.is3D) Session.fixedWing else null
        return GridParams(alt, fr / 100.0, sd / 100.0, ang?.let { ((it % 360) + 360) % 360 }, sp.coerceIn(1.0, 12.0),
            ov.coerceIn(0.0, if (fw != null) 150.0 else 50.0), cam, fw)
    }

    private fun recompute() {
        val (home, src) = homeAndSource()
        homeText.text = src
        map.home = home
        map.landing = if (isDisco && Session.fixedWing.landing != null && landStep != 1) Session.fixedWing else null
        result = null
        val p = readParams()
        if (p == null) { stats.text = tr("Revisa los parámetros (números)."); map.passes = emptyList(); return }
        Session.params = p
        if (zone.size < 3 && Session.spec3D.type.is3D && supports3D) {
            stats.text = when (Session.spec3D.type) {
                MissionType.ORBIT -> tr("Órbita: toca alrededor del objeto (estatua, torre, edificio) para marcar su planta (mínimo 3 puntos).")
                MissionType.FACADE -> tr("Fachada: toca un extremo de la pared, luego el otro, y por último un punto en el lado desde el que volará el dron.")
                else -> tr("Doble cuadrícula: toca las esquinas de la zona o manzana (mínimo 3).")
            }
            map.passes = emptyList(); return
        }
        if (zone.size < 3) {
            stats.text = tr("Toca el mapa para marcar las esquinas de la parcela (mínimo 3).\n") +
                tr("Pulsación larga sobre un punto = borrarlo. Doble toque = acercar.\n\n") +
                trf("Huella Sequoia a %1\$s m: %2\$s m · GSD %3\$s cm/px", fmt(p.altitude), "%.0f × %.0f".format(p.camera.footprint(p.altitude).first, p.camera.footprint(p.altitude).second), "%.1f".format(p.camera.gsdCm(p.altitude)))
            map.passes = emptyList(); return
        }
        if (home == null) { stats.text = if (isDisco) tr("Falta el punto de lanzamiento (botón «Lanzam.»).") else tr("Falta la casa (punto de despegue/aterrizaje)."); map.passes = emptyList(); return }
        try {
            val s3 = Session.spec3D
            val r: PlanResult = if (s3.type.is3D && supports3D) Planner3D.plan(zone, home, p, s3) else GridPlanner.plan(zone, home, p)
            result = r
            map.passes = r.passes
            val w = ArrayList(r.warnings()); val bl = ArrayList(r.blockers())
            (r as? GridResult)?.fw?.takeIf { it.landing != null && it.headingSet }?.let { f -> windWarning(f)?.let { if (it.startsWith("⛔")) bl.add(0, it.removePrefix("⛔ ")) else w.add(0, it) } }
            blockersShown = bl
            stats.text = r.summary() + (if (bl.isEmpty()) "" else "\n⛔ " + bl.joinToString("\n⛔ ")) +
                (if (w.isEmpty()) "" else "\n⚠ " + w.joinToString("\n⚠ "))
        } catch (e: Exception) {
            stats.text = trf("No se puede generar: %1\$s", e.message)
            map.passes = emptyList()
        }
    }

    // ---------------------------------------------------------------- guardar / usar

    private fun saveDialog(useAfter: Boolean) {
        val r = result
        if (r == null) { Toast.makeText(this, tr("Primero dibuja una zona válida"), Toast.LENGTH_SHORT).show(); return }
        if (landStep != 0) { Toast.makeText(this, tr("Termina de fijar el aterrizaje (🛬) antes de usar el plan"), Toast.LENGTH_LONG).show(); return }
        (blockersShown.firstOrNull() ?: r.blockers().firstOrNull())?.let { Toast.makeText(this, trf("No se puede usar: %1\$s", it), Toast.LENGTH_LONG).show(); return }
        val suggested = Session.zoneName.ifEmpty { tr("Zona ") + SimpleDateFormat(tr("dd-MM HH.mm"), Locale.ROOT).format(Date()) }
        val input = EditText(this).apply { setText(suggested); setSelectAllOnFocus(true) }
        AlertDialog.Builder(this)
            .setTitle(if (useAfter) tr("Nombre del plan") else tr("Guardar zona y plan"))
            .setView(input)
            .setNegativeButton(tr("Cancelar"), null)
            .setPositiveButton(if (useAfter) tr("Usar") else tr("Guardar")) { _, _ ->
                val name = input.text.toString().ifBlank { suggested }
                try {
                    val txt = r.toMavlink()
                    val plan = FlightPlan.parse(txt) // validación con el mismo lector que el resto de la app
                    val f = PlanStore.save(this, name, zone, r.params, Session.manualHome, txt, if (supports3D) Session.spec3D else null)
                    Session.zoneName = name
                    Crumbs.write("planner: guardado ${f.name} (${plan.items.size} ítems)")
                    if (useAfter) {
                        Session.setPlan(plan, f.name)
                        Session.plannerEstimateS = r.estimatedSeconds
                        Session.plannerTriggerM = r.triggerDistance
                        Toast.makeText(this, tr("Plan listo: súbelo al dron"), Toast.LENGTH_LONG).show()
                        finish()
                    } else Toast.makeText(this, trf("Guardado en %1\$s/%2\$s", f.parentFile?.name, f.name), Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, trf("Error al guardar: %1\$s", e.message), Toast.LENGTH_LONG).show()
                }
            }.show()
    }

    private fun exportKml() {
        if (zone.size < 3) { Toast.makeText(this, tr("Dibuja primero la zona"), Toast.LENGTH_SHORT).show(); return }
        val name = Session.zoneName.ifEmpty { "zona" }
        val d = KmlExport.Doc(trf("Racoon AgroMapper · %1\$s", name)).polygon(trf("Zona %1\$s", name), zone)
        result?.let { r ->
            val route = ArrayList<LatLon>(); route.add(r.home)
            r.passes.forEach { route.add(it.first); route.add(it.second) }
            route.add(r.home)
            val kmlAlt = (r as? Plan3D)?.let { p3 -> p3.wps.map { it.alt }.average() } ?: r.params.altitude
            d.line(trf("Pasadas (%1\$s)", r.passes.size), route, "plan", kmlAlt)
            d.point(tr("Casa"), r.home, "casa")
        }
        KmlExport.saveAndShare(this, name, d.build())
    }

    private fun openZone() {
        val files = PlanStore.zones(this)
        if (files.isEmpty()) { Toast.makeText(this, tr("No hay zonas guardadas"), Toast.LENGTH_SHORT).show(); return }
        AlertDialog.Builder(this).setTitle(tr("Zonas guardadas"))
            .setItems(files.map { it.name.removeSuffix(".zona.json") }.toTypedArray()) { _, i ->
                try {
                    val z = PlanStore.loadZone(files[i])
                    zone.clear(); zone.addAll(z.zone); map.highlight = emptyList()
                    Session.fixedWing = z.fixedWing ?: FixedWing(droneCamera = Session.fixedWing.droneCamera)
                    Session.manualHome = z.home; Session.zoneName = z.name; Session.params = z.params
                    if (supports3D) { Session.spec3D = z.spec3D; updateMissionUi() }
                    cameraSpinner.setSelection(cameras.indexOf(z.params.camera).coerceAtLeast(0))
                    fAlt.setText(fmt(z.params.altitude)); fFront.setText(fmt(z.params.frontOverlap * 100))
                    fSide.setText(fmt(z.params.sideOverlap * 100)); fSpeed.setText(fmt(z.params.speed))
                    fAngle.setText(z.params.angleDeg?.let { fmt(it) } ?: ""); fOver.setText(fmt(z.params.overshoot))
                    zoneChanged(); recenter()
                } catch (e: Exception) { Toast.makeText(this, trf("No se pudo abrir: %1\$s", e.message), Toast.LENGTH_LONG).show() }
            }.show()
    }

    // ---------------------------------------------------------------- posición

    private var ticks = 0
    private val tick = object : Runnable {
        override fun run() {
            val d = dronePos()
            val old = map.drone
            // Replanificar solo si el dron se ha movido de verdad (evita bailar con el ruido del GPS).
            val moved = (d == null) != (old == null) || (d != null && old != null && GridPlanner.distance(d, old) > 3.0)
            // Disco en el aire (dando círculos): el lanzamiento no debe seguir al avión.
            val airborneDisco = isDisco && (Session.link?.telemetry?.flyingStateRaw ?: 0) in 1..5
            if (moved) { map.drone = d; if (Session.manualHome == null && !airborneDisco) recompute() }
            if (++ticks % 30 == 0) map.retryFailed()
            main.postDelayed(this, 1000)
        }
    }

    private val locListener = object : LocationListener {
        override fun onLocationChanged(l: Location) {
            // Ignorar posiciones viejas o imprecisas (getLastKnownLocation puede tener horas).
            val ageMs = (android.os.SystemClock.elapsedRealtimeNanos() - l.elapsedRealtimeNanos) / 1_000_000
            if (ageMs > 60_000 || (l.hasAccuracy() && l.accuracy > 25f)) return
            val first = phonePos == null
            phonePos = LatLon(l.latitude, l.longitude)
            map.phone = phonePos
            if (first) { if (zone.isEmpty() && dronePos() == null) map.setCenter(phonePos!!, 17.0); recompute() }
        }
        @Deprecated("API antigua") override fun onStatusChanged(p: String?, s: Int, b: Bundle?) {}
        override fun onProviderEnabled(p: String) {}
        override fun onProviderDisabled(p: String) {}
    }

    private fun startLocation() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), REQ_LOC)
            return
        }
        try {
            val lm = getSystemService(LOCATION_SERVICE) as LocationManager
            locMgr = lm
            for (prov in listOf(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER)) {
                if (!lm.isProviderEnabled(prov)) continue
                lm.getLastKnownLocation(prov)?.let { if (phonePos == null) locListener.onLocationChanged(it) }
                lm.requestLocationUpdates(prov, 2000L, 2f, locListener, Looper.getMainLooper())
            }
        } catch (e: SecurityException) { /* sin permiso: seguimos sin posición del móvil */ }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQ_LOC && grantResults.any { it == PackageManager.PERMISSION_GRANTED }) startLocation()
    }
}
