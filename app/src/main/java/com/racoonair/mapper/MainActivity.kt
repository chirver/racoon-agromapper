package com.racoonair.mapper

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : Activity(), DroneLink.Listener {

    private lateinit var link: Link

    private lateinit var modeSpinner: Spinner
    /** Modos de conexión de ESTA app (Parrot o ANAFI). */
    private val choices = ConnectChoice.values().filter { Flavor.supports(it.platform) }
    private lateinit var connectBtn: Button
    private lateinit var connText: TextView
    private lateinit var telemText: TextView
    private lateinit var checksText: TextView
    private lateinit var planText: TextView
    private lateinit var uploadBtn: Button
    private lateinit var startBtn: Button
    private lateinit var pauseBtn: Button
    private lateinit var logText: TextView

    private var plan: FlightPlan?
        get() = Session.plan
        set(v) { Session.plan = v }
    private var planName: String
        get() = Session.planName
        set(v) { Session.planName = v }
    private var planUploaded: Boolean
        get() = Session.planUploaded
        set(v) { Session.planUploaded = v }
    private val logLines get() = Session.logLines

    companion object {
        private const val REQ_OPEN_PLAN = 42
        private const val C_BG = 0xFF101418.toInt()
        private const val C_CARD = 0xFF1B2128.toInt()
        private const val C_TEXT = 0xFFE6EDF3.toInt()
        private const val C_DIM = 0xFF8B98A5.toInt()
        private const val C_GREEN = 0xFF2E9E5B.toInt()
        private const val C_AMBER = 0xFFC98A1B.toInt()
        private const val C_BLUE = 0xFF2F6FB3.toInt()
        private const val C_RED = 0xFFC0392B.toInt()
        private const val C_GREY = 0xFF3A444F.toInt()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(android.R.style.Theme_Material_NoActionBar)
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.statusBarColor = C_BG
        Crumbs.init(applicationContext)
        Net.appContext = applicationContext
        UsbMux.onLog = { onLog(it) }
        val existing = Session.link
        link = existing ?: Flavor.newLink(Flavor.platforms.first(), applicationContext, this).also { Session.link = it }
        link.listener = this
        setContentView(buildUi())
        if (existing == null) {
            if (!DroneLink.loadLibs()) onLog("ERROR: no se pudieron cargar las librerías ARSDK.")
            else onLog("ARSDK 3.14 cargado. Conecta el móvil a la WiFi del dron y pulsa Conectar.")
        } else onLog("Pantalla recreada; el enlace sigue activo.")
        plan?.let { showPlan(it) }
        flightMap.track = Session.track.toList()
        Crumbs.takeLastCrash()?.let { crash ->
            Session.lastCrash = crash
            onLog("⚠ La app se cerró por un fallo la última vez. Pulsa Diagnóstico → Compartir y envíalo.")
        }
        setViewMode(Session.showVideo && link.supportsVideo)
        onTelemetry(link.telemetry)
        refreshButtons()
    }

    private var shownPlanVersion = -1
    private var logoView: View? = null
    private lateinit var headerView: View
    private lateinit var connCard: View
    private lateinit var telCard: View
    private lateinit var chkCard: View
    private lateinit var planCard: View
    private lateinit var mapCard: LinearLayout
    private lateinit var mapToolsCard: LinearLayout
    private lateinit var missionCard: View
    private lateinit var logCard: View
    private lateinit var scrollRoot: LinearLayout
    private lateinit var mainFrame: LinearLayout

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        arrange(newConfig.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE)
    }

    private fun detach(v: View) { (v.parent as? ViewGroup)?.removeView(v) }

    /**
     * Vertical: todo en una columna con scroll. Apaisado (móvil en el soporte del SkyController):
     * mapa grande a la izquierda y controles a la derecha, con la misión arriba del todo.
     */
    private fun arrange(landscape: Boolean) {
        val cards = listOfNotNull(logoView, headerView, alertBanner, connCard, telCard, chkCard, planCard, mapCard, mapToolsCard, missionCard, logCard)
        cards.forEach { detach(it) }
        detach(scrollRoot)
        mainFrame.removeAllViews()
        fun lp() = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply { bottomMargin = dp(10) }
        if (!landscape) {
            mainFrame.orientation = LinearLayout.VERTICAL
            logoView?.let { it.visibility = View.VISIBLE; scrollRoot.addView(it, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(120)).apply { bottomMargin = dp(10) }) }
            for (v in listOf(headerView, alertBanner, connCard, telCard, chkCard, planCard, mapCard, missionCard, mapToolsCard, logCard)) scrollRoot.addView(v, lp())
            // Vertical: el mapa/vídeo ocupa ~60 % del alto de pantalla (mínimo 330 dp).
            val h = maxOf(dp(330), (resources.displayMetrics.heightPixels * 0.6).toInt())
            mapCard.getChildAt(0).visibility = View.VISIBLE
            mapCard.setPadding(dp(14), dp(10), dp(14), dp(12))
            viewHolder.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h).apply { topMargin = dp(6) }
            mainFrame.addView(ScrollView(this).apply { addView(scrollRoot) }, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        } else {
            mainFrame.orientation = LinearLayout.HORIZONTAL
            val left = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(4), dp(4), dp(2), dp(4)) }
            // Apaisado: sin título y con márgenes mínimos; mapa/vídeo lo más grande posible.
            mapCard.getChildAt(0).visibility = View.GONE
            mapCard.setPadding(dp(4), dp(4), dp(4), dp(4))
            left.addView(alertBanner, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply { bottomMargin = dp(6) })
            left.addView(mapCard, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f))
            viewHolder.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f).apply { topMargin = dp(2) }
            mainFrame.addView(left, LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 2.0f))
            // Sin el logo grande en apaisado: el espacio vertical es oro.
            for (v in listOf(headerView, connCard, missionCard, telCard, mapToolsCard, chkCard, planCard, logCard)) scrollRoot.addView(v, lp())
            mainFrame.addView(ScrollView(this).apply { addView(scrollRoot) }, LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1f))
        }
    }
    private lateinit var alertBanner: TextView
    private var lastCalibResult = -1
    private var calibRequested = false
    private lateinit var rthDiscBtn: Button

    // ---------------------------------------------------------------- vuelta a casa automática por batería
    private lateinit var rthBatBtn: Button
    private val prefs by lazy { getSharedPreferences("ajustes", MODE_PRIVATE) }
    private fun rthBatteryLevel() = prefs.getInt("rth_bateria", 30)

    private fun updateRthBatBtn() {
        val l = rthBatteryLevel()
        rthBatBtn.text = if (l == 0) "Vuelta a casa por batería: DESACTIVADA" else "Vuelta a casa por batería: $l %"
        rthBatBtn.background = GradientDrawable().apply { setColor(if (l == 0) C_AMBER else C_GREY); cornerRadius = dp(8).toFloat() }
    }

    private fun pickRthBattery() {
        val levels = intArrayOf(0, 20, 25, 30, 35, 40)
        val names = levels.map { if (it == 0) "Desactivada" else "$it %" + if (it == 30) " (recomendado)" else "" }.toTypedArray()
        AlertDialog.Builder(this).setTitle("Vuelta a casa automática por batería")
            .setSingleChoiceItems(names, levels.indexOf(rthBatteryLevel()).coerceAtLeast(0)) { d, i ->
                prefs.edit().putInt("rth_bateria", levels[i]).apply()
                updateRthBatBtn(); onLog("Vuelta a casa por batería: ${names[i]}"); d.dismiss()
            }.setNegativeButton("Cerrar", null).show()
    }

    private fun canShowDialog() = !isFinishing && !isDestroyed
    private var rthDialog: AlertDialog? = null

    /**
     * Vuelta a casa automática por batería. Solo con el dron EN EL AIRE de verdad (estacionario o volando; nunca
     * despegando, aterrizando ni esperando lanzamiento), con telemetría actual del dron, y con la batería ≤ umbral
     * durante 10 s seguidos (filtra bajones sueltos al subir). Se da por hecha solo cuando el dron confirma
     * «vuelta a casa en curso»; si no la acepta, se reintenta cada 5 s con alarma.
     */
    private fun checkAutoRth(t: DroneLink.Telemetry) {
        val level = rthBatteryLevel()
        val now = System.currentTimeMillis()
        when (Session.autoRth) {
            2, 3 -> return
            1 -> {
                if (t.navigateHome == "en curso") {
                    Session.autoRth = 2
                    onLog("Vuelta a casa por batería confirmada por el dron ✓")
                    showAutoRthDialog(t, level)
                } else if (now - Session.autoRthSentAt >= 5000 && link.isConnected && t.droneLinked) {
                    Session.autoRthTries++
                    Session.autoRthSentAt = now
                    onLog("⛔ El dron no ha aceptado la vuelta a casa (${t.navigateHome}); reintento ${Session.autoRthTries}")
                    alarm(strong = true)
                    link.returnHome()
                    if (Session.autoRthTries == 3) onLog("⛔ VUELTA A CASA NO ACEPTADA: trae el dron a mano con el SkyController")
                }
                return
            }
        }
        val inAir = t.flyingStateRaw == 2 || t.flyingStateRaw == 3
        if (level == 0 || !inAir || !link.isConnected || !t.droneLinked || t.battery <= 0 || t.battery > level) {
            Session.belowSince = 0L; return
        }
        if (Session.belowSince == 0L) { Session.belowSince = now; return }
        if (now - Session.belowSince < 10_000) return
        if (t.navigateHome == "en curso") { Session.autoRth = 2; onLog("Batería ${t.battery} %: el dron ya vuelve a casa"); return }
        Session.autoRth = 1; Session.autoRthSentAt = now; Session.autoRthTries = 0
        onLog("⛔ Batería ${t.battery} % (umbral $level %): VUELTA A CASA AUTOMÁTICA")
        link.returnHome()
        alarm(strong = true)
    }

    private fun showAutoRthDialog(t: DroneLink.Telemetry, level: Int) {
        if (!canShowDialog()) return
        rthDialog?.dismiss()
        rthDialog = AlertDialog.Builder(this)
            .setTitle("Volviendo a casa · batería ${t.battery} %")
            .setMessage("La batería ha bajado del $level %, así que el dron ha abandonado el plan y vuelve a casa.\n\n" +
                "El progreso queda guardado: tras cambiar la batería podrás REANUDAR la misión donde se quedó.\n\n" +
                "Para pilotar a mano: pulsa «Cancelar vuelta» (queda en estacionario) y usa los sticks del SkyController.")
            .setNegativeButton("Cancelar vuelta") { _, _ ->
                Session.autoRth = 3
                link.cancelReturnHome(); onLog("Vuelta automática cancelada por el piloto (el dron queda en estacionario)")
            }
            .setPositiveButton("OK", null).show()
    }

    // ---------------------------------------------------------------- mapa | vídeo
    private lateinit var viewHolder: FrameLayout
    private lateinit var videoBox: FrameLayout
    private lateinit var videoView: VideoView
    private lateinit var videoWait: TextView
    private lateinit var osd: TextView
    private lateinit var mapTab: Button
    private lateinit var videoTab: Button
    private lateinit var tabsRow: View

    private fun setViewMode(video: Boolean) {
        Session.showVideo = video
        tabsRow.visibility = if (link.supportsVideo) View.VISIBLE else View.GONE
        fun tab(b: Button, on: Boolean) { b.background = GradientDrawable().apply { setColor(if (on) C_BLUE else C_GREY); cornerRadius = dp(8).toFloat() } }
        tab(mapTab, !video); tab(videoTab, video)
        flightMap.visibility = if (video) View.GONE else View.VISIBLE
        videoBox.visibility = if (video) View.VISIBLE else View.GONE
        followBtn.isEnabled = !video
        if (video) { link.videoSink = videoView; link.setVideoEnabled(true) }
        else { link.setVideoEnabled(false); if (link.videoSink === videoView) link.videoSink = null }
        updateOsd(link.telemetry)
    }

    /** Datos superpuestos al vídeo: lo imprescindible para volver a mano. */
    private fun updateOsd(t: DroneLink.Telemetry) {
        if (!::osd.isInitialized || videoBox.visibility != View.VISIBLE) return
        val d = dronePos(t)
        val home = plan?.homePoint?.let { LatLon(it.lat, it.lon) } ?: Session.track.firstOrNull()
        val dist = if (d != null && home != null) "%.0f m".format(GridPlanner.distance(d, home)) else "—"
        osd.text = buildString {
            append("ALT ${fmt(t.altitude, 0, "m")}  VEL ${fmt(t.speed, 1, "m/s")}  BAT ${if (t.battery < 0) "—" else "${t.battery}%"}")
            append("\nCASA $dist  ·  GPS ${if (t.gpsFix) "FIX ${t.satellites}" else "SIN FIX"}  ·  ${t.flyingState}")
        }
        val fresh = System.currentTimeMillis() - videoView.lastFrameAt < 2000
        videoWait.visibility = if (fresh) View.GONE else View.VISIBLE
        videoWait.text = if (!link.isConnected) "Conecta el dron para ver el vídeo" else "Esperando vídeo…"
    }
    private var lastAlertsKey = ""

    private fun toggleRthOnDisconnect() {
        val t = link.telemetry
        if (!link.isConnected) return
        if (t.rthOnDisconnectReadOnly) { onLog("El dron no permite cambiar este ajuste."); return }
        val turnOn = t.rthOnDisconnect != 1
        AlertDialog.Builder(this)
            .setTitle("Si se pierde el enlace durante el plan")
            .setMessage(if (turnOn) "El dron abandonará el plan y VOLVERÁ A CASA si pierde el enlace con el mando."
                        else "El dron SEGUIRÁ el plan aunque pierda el enlace (y al terminar aterrizará en la casa del plan).")
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Aplicar") { _, _ -> link.setReturnHomeOnDisconnect(turnOn) }
            .show()
    }

    private fun alarm(strong: Boolean) {
        try {
            val v = getSystemService(VIBRATOR_SERVICE) as android.os.Vibrator
            v.vibrate(android.os.VibrationEffect.createWaveform(
                if (strong) longArrayOf(0, 600, 250, 600, 250, 600) else longArrayOf(0, 350, 200, 350), -1))
        } catch (_: Throwable) {}
        try {
            val tg = android.media.ToneGenerator(android.media.AudioManager.STREAM_ALARM, if (strong) 100 else 70)
            tg.startTone(if (strong) android.media.ToneGenerator.TONE_CDMA_EMERGENCY_RINGBACK else android.media.ToneGenerator.TONE_PROP_BEEP2, if (strong) 2000 else 600)
            alertBanner.postDelayed({ tg.release() }, 2500)
        } catch (_: Throwable) {}
    }

    /** Batería y avisos: banner fijo arriba + alarmas al 30 % y al 20 % en vuelo. */
    private fun updateAlerts(t: DroneLink.Telemetry) {
        val airborne = t.flyingStateRaw in 1..4 || t.flyingStateRaw in 7..8
        val alerts = ArrayList(t.alerts())
        val rem = missionRemainingMin(t)
        val bat = FlightRecorder.minutesTo(20, t.battery)
        if (airborne && rem != null && bat != null && bat < rem + 2)
            alerts.add("⚠ La batería puede no llegar al final del plan (≈${"%.0f".format(bat)} min de batería, ≈${"%.0f".format(rem)} min de plan)")
        if (airborne && t.battery in 0..20) alerts.add(0, "⛔ Batería ${t.battery} %: vuelve a casa")
        else if (airborne && t.battery in 21..30) alerts.add("⚠ Batería ${t.battery} %")
        if (!link.isConnected && FlightRecorder.recording) alerts.add(0, "⛔ ENLACE PERDIDO en vuelo")
        if (Session.autoRth == 1) alerts.add(0, "⛔ VUELTA A CASA POR BATERÍA: el dron aún no la ha aceptado")
        else if (Session.autoRth == 2 && t.navigateHome == "en curso") alerts.add(0, "⛔ VOLVIENDO A CASA por batería")

        alertBanner.visibility = if (alerts.isEmpty()) View.GONE else View.VISIBLE
        if (alerts.isNotEmpty()) {
            alertBanner.text = alerts.joinToString("\n")
            val critical = alerts.any { it.startsWith("⛔") }
            alertBanner.background = GradientDrawable().apply { setColor(if (critical) C_RED else C_AMBER); cornerRadius = dp(8).toFloat() }
            val key = alerts.joinToString("|")
            if (key != lastAlertsKey && alerts.any { it !in lastAlertsKey.split("|") } && critical) alarm(strong = true)
            lastAlertsKey = key
        } else lastAlertsKey = ""

        if (!airborne) { if (t.flyingStateRaw == 0) { Session.batteryAlarmLevel = 0; Session.autoRth = 0; Session.belowSince = 0L }; return }
        checkAutoRth(t)
        if (t.battery in 0..20 && Session.batteryAlarmLevel < 2) {
            Session.batteryAlarmLevel = 2; alarm(strong = true)
            if (canShowDialog() && t.navigateHome != "en curso" && (rthBatteryLevel() == 0 || Session.autoRth == 3)) AlertDialog.Builder(this)
                .setTitle("Batería ${t.battery} %")
                .setMessage("Conviene volver ya. ¿Ordenar VOLVER A CASA?")
                .setNegativeButton("Seguir", null)
                .setPositiveButton("VOLVER A CASA") { _, _ -> link.returnHome() }.show()
        } else if (t.battery in 21..30 && Session.batteryAlarmLevel < 1) {
            Session.batteryAlarmLevel = 1; alarm(strong = false); onLog("Batería al ${t.battery} %")
        }
    }

    /** Minutos aproximados de plan pendiente (ruta restante + giros). Null sin plan en curso. */
    private fun missionRemainingMin(t: DroneLink.Telemetry): Double? {
        val p = plan ?: return null
        if (t.planStateRaw != 0 && t.planStateRaw != 2) return null
        val wps = p.waypoints
        val done = if (t.missionItem >= 0) wps.count { it.index <= t.missionItem } else 0
        if (done >= wps.size) return 0.0
        var len = 0.0
        var prev = dronePos(t) ?: LatLon(wps[done].lat, wps[done].lon)
        for (i in done until wps.size) { val w = LatLon(wps[i].lat, wps[i].lon); len += GridPlanner.distance(prev, w); prev = w }
        // Velocidad media realista ~7 m/s y ~6 s por waypoint (frenar, pausa, girar, acelerar). Se calibrará con vuelos reales.
        return (len / 7.0 + (wps.size - done) * 6.0) / 60.0
    }

    /** KML con el plan cargado y el rastro del último vuelo. */
    private fun exportFlightKml() {
        val d = KmlExport.Doc("Racoon AgroMapper · ${planName.ifEmpty { "vuelo" }}")
        plan?.let { KmlExport.addPlan(d, it, planName) }
        val f = FlightRecorder.lastFlight(this)
        var name = planName.ifEmpty { "plan" }
        if (f != null) { d.line("Vuelo ${f.name.removeSuffix(".csv")}", KmlExport.trackFromCsv(f), "vuelo"); name = "vuelo ${f.name.removeSuffix(".csv")}" }
        if (plan == null && f == null) { Toast.makeText(this, "No hay plan ni vuelos que exportar", Toast.LENGTH_SHORT).show(); return }
        KmlExport.saveAndShare(this, name, d.build())
    }

    private fun shareLastFlight() {
        val f = FlightRecorder.lastFlight(this)
        if (f == null) { Toast.makeText(this, "Aún no hay vuelos registrados", Toast.LENGTH_SHORT).show(); return }
        val text = try { "Vuelo ${f.name}\n" + FlightRecorder.summarize(f) + "\n\n== CSV ==\n" + f.readText() } catch (e: Exception) { "Error: ${e.message}" }
        startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, "Racoon AgroMapper vuelo ${f.name}")
            .putExtra(Intent.EXTRA_TEXT, if (text.length > 400_000) text.take(400_000) else text), "Enviar log de vuelo"))
    }
    private lateinit var flightMap: MapView
    private lateinit var flightInfo: TextView
    private lateinit var followBtn: Button
    private var follow = true

    private fun updateFollowBtn() {
        followBtn.text = if (follow) "Seguir dron: SÍ" else "Seguir dron: NO"
    }

    private fun dronePos(t: DroneLink.Telemetry): LatLon? =
        if (t.lat == 500.0 || t.lon == 500.0 || (t.lat == 0.0 && t.lon == 0.0)) null else LatLon(t.lat, t.lon)

    private fun centerOnDrone() {
        dronePos(link.telemetry)?.let { flightMap.setCenter(it, if (flightMap.zoom < 15) 17.0 else null) }
    }

    private fun fitFlightMap() {
        val pts = ArrayList<LatLon>()
        plan?.waypoints?.forEach { pts.add(LatLon(it.lat, it.lon)) }
        dronePos(link.telemetry)?.let { pts.add(it) }
        if (pts.size >= 2) flightMap.fit(pts) else pts.firstOrNull()?.let { flightMap.setCenter(it, 17.0) }
    }

    /** Pinta el plan en el mapa de vuelo. */
    private fun showPlanOnMap(p: FlightPlan) {
        flightMap.route = p.waypoints.map { LatLon(it.lat, it.lon) }
        flightMap.home = p.homePoint?.let { LatLon(it.lat, it.lon) }
        flightMap.routeDone = 0
        flightMap.post { fitFlightMap() }
    }

    /** Actualiza dron, rastro y progreso en el mapa de vuelo. */
    private fun updateFlightMap(t: DroneLink.Telemetry) {
        val d = dronePos(t)
        flightMap.drone = d
        flightMap.droneYaw = if (t.yawDeg.isNaN()) null else t.yawDeg
        val airborne = t.flyingStateRaw in 1..4 || t.flyingStateRaw == 7 || t.planStateRaw == 0
        if (d != null && airborne) {
            val last = Session.track.lastOrNull()
            if (last == null || GridPlanner.distance(last, d) > 1.5) {
                Session.track.add(d)
                if (Session.track.size > 6000) Session.track.subList(0, 1000).clear()
                flightMap.track = Session.track.toList()
            }
            if (follow) flightMap.setCenter(d)
        }
        val p = plan
        val wps = p?.waypoints ?: emptyList()
        val doneWp = if (t.missionItem >= 0) wps.count { it.index <= t.missionItem } else 0
        flightMap.routeDone = doneWp
        flightInfo.text = buildString {
            append(if (wps.isNotEmpty()) "WP $doneWp/${wps.size}" else "Sin plan")
            if (!t.altitude.isNaN()) append("  ·  ${"%.0f".format(t.altitude)} m")
            if (!t.speed.isNaN()) append("  ·  ${"%.1f".format(t.speed)} m/s")
            if (t.battery >= 0) append("  ·  bat ${t.battery} %")
            FlightRecorder.drainPerMin()?.let { r ->
                append("\nBatería: ≈${"%.0f".format(FlightRecorder.minutesTo(20, t.battery) ?: 0.0)} min hasta 20 % (gasta ${"%.1f".format(r)} %/min)")
            }
            missionRemainingMin(t)?.let { append("\nPlan restante ≈ ${"%.0f".format(it)} min") }
            if (FlightRecorder.recording) append("  ·  en el aire ${"%.1f".format(FlightRecorder.elapsedS / 60)} min")
            val home = p?.homePoint
            if (d != null && home != null) append("\nA casa: ${"%.0f".format(FlightPlan.haversine(d.lat, d.lon, home.lat, home.lon))} m")
            if (d == null) append("\nPosición del dron: esperando GPS…")
        }
    }

    override fun onResume() {
        super.onResume()
        if (Session.planVersion != shownPlanVersion) {
            shownPlanVersion = Session.planVersion
            plan?.let { showPlan(it); onLog("Plan listo: $planName (${it.items.size} ítems, estimado ${"%.1f".format(Session.plannerEstimateS / 60)} min). Pulsa «Subir al dron».") }
            refreshButtons()
        }
    }

    private fun pickSavedPlan() {
        val files = PlanStore.plans(this)
        if (files.isEmpty()) { Toast.makeText(this, "Aún no hay planes guardados: usa «Planificar en el mapa»", Toast.LENGTH_LONG).show(); return }
        AlertDialog.Builder(this).setTitle("Planes guardados")
            .setItems(files.map { it.name.removeSuffix(".mavlink") }.toTypedArray()) { _, i ->
                try {
                    val p = FlightPlan.parse(files[i].readText())
                    Session.setPlan(p, files[i].name)
                    shownPlanVersion = Session.planVersion
                    showPlan(p); onLog("Plan cargado: ${files[i].name}")
                    refreshButtons()
                } catch (e: Exception) { onLog("Plan no válido: ${e.message}") }
            }.show()
    }

    override fun onDestroy() {
        flightMap.release() // la vista es de esta pantalla; el rastro vive en Session
        rthDialog?.dismiss(); rthDialog = null
        // Esta pantalla muere: que el enlace no le mande más telemetría (ni abra diálogos sobre ella).
        // Si es una recreación, la nueva pantalla vuelve a ponerse como oyente en onCreate.
        if (link.listener === this) link.listener = object : DroneLink.Listener {
            override fun onTelemetry(t: DroneLink.Telemetry) {}
            override fun onLog(msg: String) { Session.logLines.addFirst(msg) }
        }
        if (link.videoSink === videoView) link.videoSink = null
        if (isFinishing) link.setVideoEnabled(false)
        // Solo al salir de verdad (atrás); un cambio de configuración no debe cortar el enlace en vuelo.
        if (isFinishing) {
            link.disconnect()
            Session.link = null
        }
        super.onDestroy()
    }

    // ------------------------------------------------------------------ UI

    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()

    private fun card(title: String): LinearLayout {
        val c = LinearLayout(this)
        c.orientation = LinearLayout.VERTICAL
        c.setPadding(dp(14), dp(10), dp(14), dp(12))
        c.background = GradientDrawable().apply { setColor(C_CARD); cornerRadius = dp(10).toFloat() }
        val lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.setMargins(0, 0, 0, dp(10))
        c.layoutParams = lp
        c.addView(TextView(this).apply {
            text = title.uppercase(Locale.ROOT); setTextColor(C_DIM); textSize = 11f
            letterSpacing = 0.08f; typeface = Typeface.DEFAULT_BOLD
            setPadding(0, 0, 0, dp(6))
        })
        return c
    }

    private fun label(size: Float = 14f, mono: Boolean = false) = TextView(this).apply {
        setTextColor(C_TEXT); textSize = size
        if (mono) typeface = Typeface.MONOSPACE
    }

    private fun button(text: String, color: Int, onClick: () -> Unit) = Button(this).apply {
        this.text = text; isAllCaps = false; setTextColor(Color.WHITE); textSize = 15f
        typeface = Typeface.DEFAULT_BOLD
        background = GradientDrawable().apply { setColor(color); cornerRadius = dp(8).toFloat() }
        minHeight = dp(52)
        setOnClickListener { onClick() }
    }

    private fun row(vararg views: View): LinearLayout {
        val r = LinearLayout(this)
        r.orientation = LinearLayout.HORIZONTAL
        for ((i, v) in views.withIndex()) {
            val lp = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
            lp.setMargins(if (i == 0) 0 else dp(4), dp(4), if (i == views.size - 1) 0 else dp(4), dp(4))
            r.addView(v, lp)
        }
        return r
    }

    private fun buildUi(): View {
        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(dp(12), dp(12), dp(12), dp(12))
        root.setBackgroundColor(C_BG)

        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL
            setPadding(0, 0, 0, dp(12))
        }
        val logoId = resources.getIdentifier("logo_racoonair", "drawable", packageName)
        if (logoId != 0) root.addView(android.widget.ImageView(this).apply {
            logoView = this
            setImageResource(logoId); scaleType = android.widget.ImageView.ScaleType.FIT_CENTER
            adjustViewBounds = true
            background = GradientDrawable().apply { setColor(Color.WHITE); cornerRadius = dp(10).toFloat() }
            setPadding(dp(8), dp(6), dp(8), dp(6))
        }, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(120)).apply { bottomMargin = dp(10) })
        val titles = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        titles.addView(TextView(this).apply {
            text = "Racoon AgroMapper"; setTextColor(C_TEXT); textSize = 22f; typeface = Typeface.DEFAULT_BOLD
        })
        titles.addView(TextView(this).apply {
            val ver = try { packageManager.getPackageInfo(packageName, 0).versionName } catch (_: Throwable) { "?" }
            text = "for Parrot " + Flavor.TITLE + " · v$ver · app no oficial"; setTextColor(C_DIM); textSize = 12f
        })
        titles.addView(TextView(this).apply {
            text = "Hecho a cuatro manos con Claude"; setTextColor(0xFFD97757.toInt()); textSize = 11f
            setTypeface(typeface, Typeface.ITALIC)
        })
        header.addView(titles)
        root.addView(header); headerView = header
        alertBanner = TextView(this).apply {
            setTextColor(Color.WHITE); textSize = 15f; typeface = Typeface.DEFAULT_BOLD
            setPadding(dp(12), dp(10), dp(12), dp(10)); visibility = View.GONE
        }
        root.addView(alertBanner, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply { bottomMargin = dp(10) })

        // Conexión
        val conn = card("Conexión")
        modeSpinner = Spinner(this)
        modeSpinner.adapter = object : ArrayAdapter<ConnectChoice>(this, android.R.layout.simple_spinner_item, choices) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
                (super.getView(position, convertView, parent) as TextView).apply { setTextColor(C_TEXT); textSize = 15f }
        }.apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        conn.addView(modeSpinner)
        choices.indexOfFirst { it.platform == link.platform }.takeIf { it >= 0 }?.let { modeSpinner.setSelection(it) }
        connText = label(14f)
        conn.addView(connText)
        connectBtn = button("Conectar", C_BLUE) { onConnectClicked() }
        conn.addView(row(connectBtn))
        root.addView(conn); connCard = conn

        // Telemetría
        val tel = card("Telemetría")
        telemText = label(14f, mono = true)
        tel.addView(telemText)
        root.addView(tel); telCard = tel

        // Pre-vuelo
        val chk = card("Comprobaciones del dron para el plan")
        checksText = label(14f, mono = true)
        chk.addView(checksText)
        root.addView(chk); chkCard = chk

        // Plan
        val pl = card("Plan de vuelo")
        planText = label(13f, mono = true)
        planText.text = "Sin plan cargado."
        pl.addView(planText)
        uploadBtn = button("Subir al dron", C_BLUE) { onUploadClicked() }
        pl.addView(row(button("🗺  PLANIFICAR EN EL MAPA", C_GREEN) {
            startActivity(Intent(this, PlannerActivity::class.java))
        }))
        pl.addView(row(button("Planes guardados", C_GREY) { pickSavedPlan() }, button("Cargar fichero", C_GREY) { pickPlan() }))
        pl.addView(row(uploadBtn))
        pl.addView(row(button("Calibración de cámara (Sequoia / térmica)", C_GREY) { onCalibrateClicked() }))
        pl.addView(row(button("📷  Enviar el plan a la Sequoia (por WiFi)", C_GREY) { onSequoiaClicked() }))
        pl.addView(row(button("🌤  Meteo y Kp: ¿se puede volar?", C_BLUE) { onWeatherClicked() }))
        root.addView(pl); planCard = pl

        // Mapa en vuelo
        val mc = card("Mapa en vuelo")
        // Datos de vuelo encima del mapa (como el OSD del vídeo): el hueco entero es para el mapa.
        flightInfo = label(12f, mono = true).apply {
            setTextColor(Color.WHITE); setShadowLayer(4f, 1f, 1f, Color.BLACK)
            setPadding(dp(8), dp(4), dp(8), dp(4)); setBackgroundColor(0x77000000)
        }
        // Selector Mapa | Vídeo: comparten el mismo hueco para no saturar la pantalla.
        mapTab = button("🗺 Mapa", C_BLUE) { setViewMode(video = false) }
        videoTab = button("🎥 Vídeo", C_GREY) { setViewMode(video = true) }
        tabsRow = row(mapTab, videoTab)
        mc.addView(tabsRow)
        viewHolder = FrameLayout(this)
        flightMap = MapView(this)
        viewHolder.addView(flightMap, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        viewHolder.addView(flightInfo, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP))
        videoBox = FrameLayout(this).apply { setBackgroundColor(Color.BLACK); visibility = View.GONE }
        videoView = VideoView(this)
        videoBox.addView(videoView, FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER))
        videoWait = TextView(this).apply { setTextColor(C_DIM); textSize = 14f; gravity = Gravity.CENTER; text = "Esperando vídeo…" }
        videoBox.addView(videoWait, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        osd = TextView(this).apply {
            setTextColor(Color.WHITE); textSize = 13f; typeface = Typeface.MONOSPACE
            setShadowLayer(4f, 1f, 1f, Color.BLACK); setPadding(dp(8), dp(6), dp(8), dp(6))
            setBackgroundColor(0x66000000)
        }
        videoBox.addView(osd, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP))
        viewHolder.addView(videoBox, FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        mc.addView(viewHolder, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(330)).apply { topMargin = dp(6) })
        root.addView(mc); mapCard = mc

        // Opciones del mapa: aparte, para no quitarle sitio (en apaisado van en la columna derecha, bajando).
        val mt = card("Mapa: opciones y registros")
        followBtn = button("Seguir dron: SÍ", C_BLUE) { follow = !follow; updateFollowBtn(); if (follow) centerOnDrone() }
        mt.addView(row(followBtn, button("Ver plan", C_GREY) { follow = false; updateFollowBtn(); fitFlightMap() }))
        mt.addView(row(button("Borrar rastro", C_GREY) { Session.track.clear(); flightMap.track = emptyList() },
            button("Compartir log", C_GREY) { shareLastFlight() }))
        mt.addView(row(button("Exportar KML", C_GREY) { exportFlightKml() }))
        root.addView(mt); mapToolsCard = mt

        // Misión
        val ms = card("Misión")
        startBtn = button("▶  INICIAR MISIÓN", C_GREEN) { onStartClicked() }
        ms.addView(row(startBtn))
        resumeBtn = button("↻ Reanudar misión", C_BLUE) { onResumeMissionClicked() }
        ms.addView(row(resumeBtn))
        pauseBtn = button("Pausar", C_AMBER) { onPauseClicked() }
        ms.addView(row(pauseBtn, button("Parar (estacionario)", C_AMBER) { if (!justStarted()) link.stopMission() }))
        ms.addView(row(
            button("Volver a casa", C_BLUE) { link.returnHome() },
            button("Aterrizar", C_BLUE) { link.land() }
        ))
        rthDiscBtn = button("Si se pierde el enlace: —", C_GREY) { toggleRthOnDisconnect() }
        ms.addView(row(rthDiscBtn))
        rthBatBtn = button("", C_GREY) { pickRthBattery() }
        updateRthBatBtn()
        ms.addView(row(rthBatBtn))
        val emergency = button("EMERGENCIA · mantener pulsado", C_RED) {
            Toast.makeText(this, "Mantén pulsado para CORTAR MOTORES (el dron cae)", Toast.LENGTH_SHORT).show()
        }
        emergency.setOnLongClickListener { confirmEmergency(); true }
        ms.addView(row(emergency))
        ms.addView(TextView(this).apply {
            text = "Con WiFi directa no hay mandos: el control manual de respaldo es el SkyController. " +
                "Ten FreeFlight Pro o el mando listos para tomar el control."
            setTextColor(C_DIM); textSize = 12f; setPadding(0, dp(6), 0, 0)
        })
        root.addView(ms); missionCard = ms

        // Registro
        val lg = card("Registro")
        lg.addView(row(button("Diagnóstico (compartir registro)", C_GREY) { showDiagnostics() }))
        logText = label(12f, mono = true)
        logText.movementMethod = ScrollingMovementMethod()
        lg.addView(logText)
        root.addView(lg); logCard = lg

        scrollRoot = root
        mainFrame = LinearLayout(this).apply { setBackgroundColor(C_BG) }
        arrange(resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE)
        return mainFrame
    }

    private fun setEnabled(b: Button, enabled: Boolean) {
        b.isEnabled = enabled
        b.alpha = if (enabled) 1f else 0.4f
    }

    private lateinit var resumeBtn: Button

    private fun refreshButtons() {
        val ri = if (::resumeBtn.isInitialized) resumeInfo() else null
        if (::resumeBtn.isInitialized) {
            resumeBtn.visibility = if (ri != null) View.VISIBLE else View.GONE
            ri?.let { resumeBtn.text = "↻ Reanudar misión desde la pasada ${it.first + 1}/${it.second}" }
            setEnabled(resumeBtn, ri != null && link.telemetry.planStateRaw != 0 && link.telemetry.planStateRaw != 2)
        }
        val t = link.telemetry
        val connected = link.isConnected
        val busy = connected || link.connecting
        connectBtn.text = if (busy) "Desconectar" else "Conectar"
        modeSpinner.isEnabled = !busy
        // Plan activo = ejecutando (0) o pausado (2). Mientras tanto no se sube ni se relanza nada.
        val planActive = t.planStateRaw == 0 || t.planStateRaw == 2
        val landed = t.flyingStateRaw == 0
        setEnabled(uploadBtn, connected && plan != null && !planActive && !link.uploading)
        // Disco: se lanza a mano y el plan se inicia ya en el aire (dando círculos). Multirrotor: en tierra.
        val disco = t.droneModel == 2
        val canStart = if (disco) t.flyingStateRaw == 2 || t.flyingStateRaw == 3 else landed
        setEnabled(startBtn, connected && planUploaded && !planActive && canStart)
        startBtn.text = when {
            disco && connected && !canStart -> "▶  INICIAR (lanza la Disco primero)"
            connected && !landed && t.flyingStateRaw >= 0 && !disco -> "▶  INICIAR (solo en tierra)"
            else -> "▶  INICIAR MISIÓN"
        }
        // Pausar/Reanudar se deduce SOLO de lo que informa el dron.
        when (t.planStateRaw) {
            0 -> { pauseBtn.text = "Pausar"; setEnabled(pauseBtn, connected) }
            2 -> { pauseBtn.text = "Reanudar"; setEnabled(pauseBtn, connected) }
            else -> { pauseBtn.text = "Pausar"; setEnabled(pauseBtn, false) }
        }
    }

    // ------------------------------------------------------------------ acciones

    private fun onConnectClicked() {
        if (link.isConnected || link.connecting) link.disconnect()
        else {
            planUploaded = false
            val choice = modeSpinner.selectedItem as ConnectChoice
            // Cambiar de SDK si hace falta: ARSDK3 (Bluegrass/Disco) o GroundSDK (ANAFI).
            if (choice.platform != link.platform) {
                // Cerrar del todo el enlace anterior (y silenciarlo) antes de cambiar de SDK.
                val old = link
                old.listener = object : DroneLink.Listener {
                    override fun onTelemetry(t: DroneLink.Telemetry) {}
                    override fun onLog(msg: String) {}
                }
                old.videoSink = null
                try { old.disconnect() } catch (_: Throwable) {}
                link = Flavor.newLink(choice.platform, applicationContext, this)
                Session.link = link
                onLog("Modo ${if (choice.platform == Platform.ANAFI) "ANAFI (GroundSDK)" else "Bluegrass/Disco (ARSDK)"}")
                setViewMode(Session.showVideo && link.supportsVideo)
            }
            if (choice.platform == Platform.BLUEGRASS_SEQUOIA && !DroneLink.loadLibs()) { onLog("ERROR: no se pudieron cargar las librerías ARSDK."); return }
            link.connect(choice)
        }
        refreshButtons()
    }

    private fun pickPlan() {
        val i = Intent(Intent.ACTION_OPEN_DOCUMENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.type = "*/*"
        startActivityForResult(i, REQ_OPEN_PLAN)
    }

    @Deprecated("API de framework; suficiente para v0.1")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != REQ_OPEN_PLAN || resultCode != RESULT_OK) return
        val uri = data?.data ?: return
        loadPlan(uri)
    }

    private fun displayName(uri: Uri): String {
        contentResolver.query(uri, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)?.use { c ->
            if (c.moveToFirst()) return c.getString(0) ?: "plan"
        }
        return uri.lastPathSegment ?: "plan"
    }

    private fun loadPlan(uri: Uri) {
        try {
            val raw = contentResolver.openInputStream(uri)?.use { it.readBytes().toString(Charsets.UTF_8) }
                ?: throw IllegalArgumentException("No se pudo leer el fichero")
            val p = FlightPlan.parse(raw)
            Session.setPlan(p, displayName(uri))
            shownPlanVersion = Session.planVersion
            showPlan(p)
            onLog("Plan cargado: $planName (${p.items.size} ítems)")
        } catch (e: Exception) {
            plan = null
            planText.text = "Plan no válido: ${e.message}"
            onLog("Plan no válido: ${e.message}")
        }
        refreshButtons()
    }

    private fun showPlan(p: FlightPlan) {
        showPlanOnMap(p)
        val w = p.warnings()
        planText.text = "$planName\n${p.summary()}" + if (w.isEmpty()) "" else "\n⚠ " + w.joinToString("\n⚠ ")
    }

    // ------------------------------------------------------------------ Disco (ala fija)

    /** La Disco no puede volar planes de multirrotor: bloquea los de este planificador y avisa con los demás. */
    private fun discoGate(p: FlightPlan, then: () -> Unit) {
        val model = link.telemetry.droneModel
        if (model == 1 && p.isFixedWing) {
            AlertDialog.Builder(this).setTitle("⛔ Plan de ala fija")
                .setMessage("Este plan es para la DISCO (sin despegue, con aproximación y aterrizaje en pista). " +
                    "La Bluegrass necesita un plan de multirrotor: en el planificador cambia a «✚ Bluegrass».")
                .setPositiveButton("Entendido", null).show()
            return
        }
        if (model == 0) {
            AlertDialog.Builder(this).setTitle("¿Qué dron es?")
                .setMessage("El dron aún no se ha identificado (Bluegrass o Disco). Espera unos segundos a que se enlace y vuelve a intentarlo.")
                .setPositiveButton("Entendido", null).show(); return
        }
        if (model != 2) { then(); return }
        if (p.looksMultirotor || !p.isFixedWing) {
            AlertDialog.Builder(this).setTitle("⛔ Plan no apto para la Disco")
                .setMessage("Este plan es de MULTIRROTOR: despega y aterriza en vertical y se para en cada pasada. " +
                    "La Disco es un ala fija: necesita giros amplios y una aproximación de ~200 m para aterrizar (manual Parrot).\n\n" +
                    "En el planificador pulsa «✈ Disco», fija el aterrizaje (🛬) y genera el plan de nuevo.")
                .setPositiveButton("Entendido", null).show()
            return
        }
        val msg = StringBuilder("Antes de subir y volar:\n\n")
        msg.append("• Lanzamiento a mano DE CARA AL VIENTO; espera a que la Disco esté dando círculos y luego pulsa INICIAR.\n")
        msg.append("• El pasillo de aproximación (cono azul del planificador) está libre de árboles, cables y gente.\n")
        if (p.items.none { it.command == FlightPlan.CMD_IMAGE_START })
            msg.append(if (System.currentTimeMillis() - Session.sequoiaArmedAt < 30 * 60_000) "• Sequoia preparada ✔\n"
                       else "• ⚠ La Sequoia NO está preparada: «Enviar el plan a la Sequoia».\n")
        val wind = finalWindNote(p)
        if (wind.startsWith("⛔")) {
            AlertDialog.Builder(this).setTitle("⛔ Viento de cola en la final")
                .setMessage(wind + "\n\nCon viento de cola la Disco aterrizaría demasiado rápida y larga. Rehaz el aterrizaje en el planificador.")
                .setPositiveButton("Entendido", null).show(); return
        }
        msg.append("• ").append(wind).append('\n')
        msg.append("\nSi algo va mal en la final: «Volver a casa» o toma el control con el SkyController.")
        AlertDialog.Builder(this).setTitle("✈ Plan para la Disco")
            .setMessage(msg.toString())
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Continuar") { _, _ -> then() }.show()
    }

    /** Viento de la previsión respecto a la final del plan (rumbo aproximación → toma). */
    private fun finalWindNote(p: FlightPlan): String {
        val land = p.items.lastOrNull { it.command == FlightPlan.CMD_LAND } ?: return "⚠ Plan sin aterrizaje."
        val li = p.items.indexOf(land)
        val ap = p.items.subList(0, li).lastOrNull { it.command == FlightPlan.CMD_WAYPOINT } ?: return "⚠ Plan sin aproximación."
        val heading = GridPlanner.bearing(LatLon(ap.lat, ap.lon), LatLon(land.lat, land.lon))
        val r = Session.weather
        val stale = r == null || System.currentTimeMillis() - r.fetchedAt > 3600_000L ||
            GridPlanner.distance(LatLon(land.lat, land.lon), LatLon(r.lat, r.lon)) > 5000
        if (r == null || stale) return "⚠ Viento ACTUAL sin consultar (botón «Meteo y Kp»): comprueba tú que la final (rumbo ${heading.toInt()}°) va de cara al viento."
        val now = System.currentTimeMillis() / 1000
        val h = r.hours.lastOrNull { it.epochS <= now }?.takeIf { now - it.epochS < 2 * 3600 }
            ?: return "⚠ Previsión sin la hora actual: comprueba el viento tú mismo."
        if (h.dir10.isNaN() || h.wind10.isNaN()) return "⚠ Previsión sin dato de viento: comprueba el viento tú mismo."
        val diff = GridPlanner.angleDiff(heading, h.dir10)
        val head = h.wind10 * Math.cos(Math.toRadians(diff)); val cross = h.wind10 * Math.sin(Math.toRadians(diff))
        return when {
            head < -1.0 -> "⛔ VIENTO DE COLA en la final (${"%.1f".format(-head)} m/s). Rehaz el aterrizaje con «🌬 Según viento»."
            cross > 4.0 -> "⚠ Viento cruzado de ${"%.1f".format(cross)} m/s en la final (rumbo ${heading.toInt()}°)."
            else -> "Viento en la final: ✔ de cara (${"%.1f".format(h.wind10)} m/s del ${Weather.rumbo(h.dir10)})."
        }
    }

    // ------------------------------------------------------------------ Sequoia por libre (Disco FPV)

    private var seq: SequoiaCam.Session? = null

    /** Parámetros de disparo sacados del plan: (metros entre fotos, segundos entre fotos, altura). */
    private fun sequoiaParamsFromPlan(): Triple<Double, Double, Double> {
        val p = plan
        val alt = p?.maxAltitude?.takeIf { it > 0 } ?: Session.params.altitude
        val dist = p?.sequoiaTriggerMeters ?: Session.plannerTriggerM.takeIf { it > 0 && p != null }
            ?: SequoiaCam.gpsDistance(alt, Session.params.frontOverlap)
        // Disco: vuela a su crucero (~12,5 m/s), no a la velocidad del plan de multirrotor.
        val speed = if (link.telemetry.droneModel == 2 || airframeNow() == Airframe.DISCO) Airframe.DISCO.cruise!!
                    else Session.params.speed.takeIf { it > 0 } ?: 8.0
        return Triple(Math.max(5.0, dist), Math.max(1.0, dist / speed), alt)
    }

    private fun onSequoiaClicked() {
        if (link.telemetry.droneModel == 1) {
            AlertDialog.Builder(this).setTitle("Bluegrass conectada")
                .setMessage("En la Bluegrass la Sequoia la gobierna el propio dron con el plan (y le apaga su WiFi). " +
                    "Esto es para la Disco FPV, donde la Sequoia va por libre.")
                .setPositiveButton("Entendido", null).show(); return
        }
        val (dist, secs, alt) = sequoiaParamsFromPlan()
        val items = arrayOf(
            "📍 Por distancia: cada ${"%.0f".format(dist)} m (recomendado)\n   Solo dispara al moverse: empieza sola al despegar",
            "⏱ Por tiempo: cada ${"%.1f".format(secs)} s\n   Dispara ya, también en tierra",
            "🔎 Ver lo que tiene programado",
            "⏹ Parar la captura (después de aterrizar)",
            "⚙ Nombre de la WiFi / contraseña de la Sequoia")
        AlertDialog.Builder(this).setTitle("Sequoia · plan a ${alt.toInt()} m" + (if (plan == null) " (sin plan: valores por defecto)" else ""))
            .setItems(items) { _, w ->
                when (w) {
                    0 -> runSequoia("Preparando la Sequoia (por distancia)…") { it.arm(true, dist) }
                    1 -> runSequoia("Preparando la Sequoia (por tiempo)…") { it.arm(false, secs) }
                    2 -> runSequoia("Leyendo la Sequoia…") { it.inspect() }
                    3 -> runSequoia("Parando la Sequoia…") { it.stop() }
                    4 -> sequoiaWifiSettings()
                }
            }.setNegativeButton("Cerrar", null).show()
    }

    private fun sequoiaWifiSettings() {
        val box = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(20), dp(8), dp(20), 0) }
        val ssid = android.widget.EditText(this).apply { hint = "Empieza por… (p. ej. Sequoia)"; setText(prefs.getString("sequoia_ssid", "Sequoia")); setTextColor(C_TEXT) }
        val pass = android.widget.EditText(this).apply { hint = "Contraseña (vacío si no tiene)"; setText(prefs.getString("sequoia_pass", "")); setTextColor(C_TEXT) }
        box.addView(label(13f).apply { text = "Nombre de la WiFi de la Sequoia (o su principio):" }); box.addView(ssid)
        box.addView(label(13f).apply { text = "Contraseña:" }); box.addView(pass)
        AlertDialog.Builder(this).setTitle("WiFi de la Sequoia").setView(box)
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Guardar") { _, _ ->
                prefs.edit().putString("sequoia_ssid", ssid.text.toString().trim().ifEmpty { "Sequoia" })
                    .putString("sequoia_pass", pass.text.toString()).apply()
            }.show()
    }

    private fun runSequoia(title: String, job: (SequoiaCam.Session) -> SequoiaCam.Session.Report) {
        if (seq != null) { Toast.makeText(this, "Ya hay una operación con la Sequoia en curso", Toast.LENGTH_SHORT).show(); return }
        val s = SequoiaCam.Session(applicationContext); seq = s
        val txt = label(14f).apply { text = "Buscando la WiFi de la Sequoia…\n(la primera vez Android te pedirá aprobarla)"; setPadding(dp(20), dp(12), dp(20), dp(8)) }
        val dlg = AlertDialog.Builder(this).setTitle(title).setView(txt).setCancelable(false)
            .setNegativeButton("Cancelar") { _, _ -> s.release(); seq = null }.show()
        fun finish(ok: Boolean, lines: List<String>) = runOnUiThread {
            s.release(); seq = null
            try { dlg.dismiss() } catch (_: Throwable) {}
            if (!canShowDialog()) return@runOnUiThread
            lines.forEach { onLog("Sequoia: $it") }
            AlertDialog.Builder(this).setTitle(if (ok) "📷 Sequoia" else "📷 Sequoia: problema")
                .setMessage(lines.joinToString("\n") + if (ok) "\n\nLa app ya ha soltado la WiFi de la Sequoia: el SkyController sigue conectado." else "")
                .setPositiveButton("OK", null).show()
        }
        s.connect(prefs.getString("sequoia_ssid", "Sequoia") ?: "Sequoia", prefs.getString("sequoia_pass", ""),
            ok = { _ ->
                runOnUiThread { txt.text = "Conectado a la Sequoia. Configurando…" }
                Thread {
                    val r = try { job(s) } catch (t: Throwable) { SequoiaCam.Session.Report(false, listOf("✘ ${t.message ?: t.javaClass.simpleName}")) }
                    if (r.ok && r.lines.any { it.contains("armada") || it.contains("EN MARCHA") }) Session.sequoiaArmedAt = System.currentTimeMillis()
                    if (r.ok && r.lines.any { it.contains("Captura parada") }) Session.sequoiaArmedAt = 0L
                    finish(r.ok, r.lines)
                }.start()
            },
            fail = { why -> finish(false, listOf("✘ $why", "Comprueba que la Sequoia está encendida y que su WiFi se llama «${prefs.getString("sequoia_ssid", "Sequoia")}…» (⚙ para cambiarlo).")) })
    }

    // ------------------------------------------------------------------ meteo + Kp

    private fun airframeNow(): Airframe = when (link.telemetry.droneModel) {
        1 -> Airframe.BLUEGRASS; 2 -> Airframe.DISCO
        else -> if (prefs.getString("airframe", "BLUEGRASS") == "DISCO") Airframe.DISCO else Airframe.BLUEGRASS
    }

    /** Dónde mirar el tiempo: dron, casa del plan, primer waypoint o última posición del móvil. */
    private fun weatherPlace(): LatLon? {
        dronePos(link.telemetry)?.let { return it }
        plan?.homePoint?.let { return LatLon(it.lat, it.lon) }
        plan?.waypoints?.firstOrNull()?.let { return LatLon(it.lat, it.lon) }
        try {
            val lm = getSystemService(LOCATION_SERVICE) as android.location.LocationManager
            for (prov in listOf(android.location.LocationManager.GPS_PROVIDER, android.location.LocationManager.NETWORK_PROVIDER))
                lm.getLastKnownLocation(prov)?.let { return LatLon(it.latitude, it.longitude) }
        } catch (_: SecurityException) {} catch (_: Throwable) {}
        return null
    }

    private fun onWeatherClicked() {
        val place = weatherPlace()
        if (place == null) {
            AlertDialog.Builder(this).setMessage("No sé dónde vas a volar: carga un plan, conecta el dron o activa la ubicación del móvil.")
                .setPositiveButton("Entendido", null).show(); return
        }
        val cached = Session.weather
        if (cached != null && System.currentTimeMillis() - cached.fetchedAt < 15 * 60_000 &&
            GridPlanner.distance(place, LatLon(cached.lat, cached.lon)) < 3000) { showWeather(cached); return }
        Toast.makeText(this, "Consultando Open-Meteo y NOAA…", Toast.LENGTH_SHORT).show()
        Thread {
            val r = try { Result.success(Weather.fetch(place.lat, place.lon)) } catch (t: Throwable) { Result.failure<Weather.Report>(t) }
            runOnUiThread {
                if (!canShowDialog()) return@runOnUiThread
                r.onSuccess { Session.weather = it; showWeather(it) }
                r.onFailure {
                    Crumbs.write("meteo: ${it.javaClass.simpleName}: ${it.message}")
                    AlertDialog.Builder(this).setTitle("Sin datos de meteo")
                        .setMessage("No se pudo consultar (${it.message ?: it.javaClass.simpleName}). Hace falta internet: " +
                            "datos móviles vale aunque estés en la WiFi del dron.")
                        .setPositiveButton("Entendido", null).show()
                }
            }
        }.start()
    }

    /** Veredicto de la hora actual con el plan y dron actuales (null si no hay informe válido). */
    private fun weatherVerdictNow(r: Weather.Report, a: Airframe): Pair<Weather.Hour, Weather.Verdict>? {
        val now = System.currentTimeMillis() / 1000
        val h = r.hours.lastOrNull { it.epochS <= now } ?: r.hours.firstOrNull() ?: return null
        if (now - h.epochS > 2 * 3600) return null
        val alt = plan?.maxAltitude?.takeIf { it > 0 } ?: 100.0
        return Pair(h, Weather.evaluate(h, r.kpAt(h.epochS), a, alt, plan?.estimatedMinutes()))
    }

    private fun showWeather(r: Weather.Report) {
        if (!canShowDialog()) return
        val a = airframeNow()
        Session.weatherAirframe = a
        val alt = plan?.maxAltitude?.takeIf { it > 0 } ?: 100.0
        val planMin = plan?.estimatedMinutes()
        val now = System.currentTimeMillis() / 1000
        val upcoming = r.hours.filter { it.epochS >= now - 3600 }.take(13)
        if (upcoming.isEmpty()) { onLog("Meteo: informe sin horas útiles"); return }
        val verdicts = upcoming.map { Pair(it, Weather.evaluate(it, r.kpAt(it.epochS), a, alt, planMin)) }
        val (h0, v0) = verdicts[0]
        val sb = StringBuilder()
        sb.append("${a.label} · altura ${alt.toInt()} m${if (planMin != null) " · plan ≈${"%.0f".format(planMin)} min" else ""}\n")
        sb.append("Ahora (${h0.label}): ${v0.icon} ${v0.word}\n\n")
        v0.reasons.forEach { sb.append(it).append('\n') }
        sb.append("\nPróximas horas:\n")
        for ((h, v) in verdicts.drop(1)) {
            sb.append("${v.icon} ${h.label}  ${"%.1f".format(v.windAlt)} m/s")
            if (!v.gustAlt.isNaN()) sb.append(" · ráf ${"%.1f".format(v.gustAlt)}")
            if (!h.precipProb.isNaN() && h.precipProb >= 20) sb.append(" · lluvia ${h.precipProb.toInt()} %")
            r.kpAt(h.epochS)?.let { if (it.kp >= 4) sb.append(" · Kp ${"%.1f".format(it.kp)}") }
            sb.append('\n')
        }
        // Mejor ventana: primera racha de ≥2 horas verdes
        val idx = verdicts.indices.firstOrNull { i -> verdicts[i].second.level == 0 && verdicts.getOrNull(i + 1)?.second?.level == 0 }
        sb.append(if (idx != null) "\nPrimera ventana buena: desde ${verdicts[idx].first.label}.\n" else "\nNo hay 2 horas seguidas en verde en las próximas 12 h.\n")
        if (r.kpError != null) sb.append("\n(Kp: no se pudo consultar a la NOAA: ${r.kpError})\n")
        sb.append("\nFuentes: Open-Meteo (previsión), NOAA SWPC (Kp). Viento máximo según manual Parrot " +
            "(Bluegrass 8 m/s, Disco 12 m/s); el resto son criterios prudentes de la app. La previsión no sustituye a mirar el cielo.")
        val b = AlertDialog.Builder(this).setTitle("${v0.icon} Meteo: ${v0.word}")
            .setMessage(sb.toString())
            .setPositiveButton("OK", null)
            .setNeutralButton("Actualizar") { _, _ -> Session.weather = null; onWeatherClicked() }
        if (link.telemetry.droneModel == 0) b.setNegativeButton(if (a == Airframe.DISCO) "Ver para Bluegrass" else "Ver para Disco") { _, _ ->
            prefs.edit().putString("airframe", if (a == Airframe.DISCO) "BLUEGRASS" else "DISCO").apply()
            showWeather(r)
        }
        b.show()
    }

    private fun onUploadClicked() {
        val p = plan ?: return
        discoGate(p) { doUpload(p) }
    }

    private fun doUpload(p: FlightPlan) {
        if (p.platform != link.platform) {
            AlertDialog.Builder(this).setTitle("Plan de otro dron")
                .setMessage("Este plan está hecho para ${if (p.platform == Platform.ANAFI) "ANAFI" else "Bluegrass/Disco (Sequoia)"}, " +
                    "pero estás conectado a ${if (link.platform == Platform.ANAFI) "una ANAFI" else "Bluegrass/Disco"}. " +
                    "Genera el plan en el planificador eligiendo la cámara correcta.")
                .setPositiveButton("Entendido", null).show()
            return
        }
        setEnabled(uploadBtn, false)
        planUploaded = false
        link.uploadPlan(p) { ok ->
            planUploaded = ok
            if (ok) Toast.makeText(this, "Plan subido", Toast.LENGTH_SHORT).show()
            refreshButtons()
        }
    }

    private fun onStartClicked() {
        val p = plan ?: return
        if ((link.telemetry.droneModel == 2 && !p.isFixedWing) || (link.telemetry.droneModel == 1 && p.isFixedWing)) { discoGate(p) {}; return }
        val t = link.telemetry
        val issues = ArrayList<String>()
        if (!t.gpsFix) issues.add("El dron NO tiene fix GPS.")
        val rthLvl = rthBatteryLevel()
        if (t.battery in 0..(if (rthLvl > 0) rthLvl + 5 else 20)) {
            AlertDialog.Builder(this).setTitle("Batería al ${t.battery} %")
                .setMessage(if (rthLvl > 0) "Con la vuelta a casa automática al $rthLvl %, el dron volvería nada más despegar. Cambia la batería."
                    else "Batería demasiado baja para empezar una misión. Cambia la batería.")
                .setPositiveButton("Entendido", null).show()
            return
        }
        if (t.battery in 0..39) issues.add("Batería del dron al ${t.battery} %.")
        if (t.magnetoCalibrationRequired == true) issues.add("El dron pide calibrar el magnetómetro.")
        if (t.planAvailable == false) issues.add("El dron dice que el plan NO está disponible.")
        t.components.filterValues { !it }.keys.forEach { issues.add("Comprobación fallida: $it") }
        issues.addAll(p.warnings())

        val known = t.lat != 500.0 && t.lon != 500.0 && !(t.lat == 0.0 && t.lon == 0.0)
        // La casa del plan (despegue/aterrizaje) debe ser donde está el dron AHORA.
        val isDisco = t.droneModel == 2
        if (isDisco && known) {
            p.landPoint?.let {
                val dl = FlightPlan.haversine(t.lat, t.lon, it.lat, it.lon)
                if (dl > FixedWing.MAX_LAND_FROM_LAUNCH) {
                    AlertDialog.Builder(this).setTitle("⛔ Aterrizaje lejos")
                        .setMessage("El punto de toma del plan está a ${dl.toInt()} m de la Disco. ¿Es el plan de otra parcela? Revísalo en el planificador.")
                        .setPositiveButton("Entendido", null).show()
                    return
                }
            }
            // Ala fija: el aterrizaje está a ~200 m del lanzamiento (cono). Nunca se mueve; solo se avisa del lanzamiento.
            p.takeoffPoint?.takeIf { !(it.lat == 0.0 && it.lon == 0.0) }?.let {
                val d = FlightPlan.haversine(t.lat, t.lon, it.lat, it.lon)
                if (d > 50.0) issues.add("Disco: el punto de lanzamiento del plan está a ${d.toInt()} m del dron.")
            }
        }
        val land = if (isDisco) null else p.homePoint
        if (land != null) {
            if (!known) {
                AlertDialog.Builder(this).setTitle("Sin posición del dron")
                    .setMessage("El dron aún no informa de su posición GPS, así que no se puede comprobar dónde aterrizará. Espera al fix GPS.")
                    .setPositiveButton("Entendido", null).show()
                return
            }
            val dHome = FlightPlan.haversine(t.lat, t.lon, land.lat, land.lon)
            if (dHome > 15.0) {
                AlertDialog.Builder(this).setTitle("La casa del plan no es esta")
                    .setMessage("El plan despegaría/aterrizaría a ${dHome.toInt()} m de donde está el dron.\n\n" +
                        "¿Mover la casa del plan a la posición actual del dron? Después tendrás que volver a subirlo.")
                    .setNegativeButton("Cancelar", null)
                    .setPositiveButton("Mover casa") { _, _ ->
                        try {
                            val np = p.withHome(t.lat, t.lon)
                            Session.setPlan(np, planName)
                            shownPlanVersion = Session.planVersion
                            showPlan(np); onLog("Casa del plan movida a la posición del dron. Vuelve a subir el plan.")
                            refreshButtons()
                        } catch (e: Exception) { onLog("No se pudo mover la casa: ${e.message}") }
                    }.show()
                return
            }
        }

        // ¿El plan es de este campo? Bloquea si el primer waypoint está a más de 1 km del dron.
        val first = p.waypoints.firstOrNull()
        if (first != null && known) {
            val dist = FlightPlan.haversine(t.lat, t.lon, first.lat, first.lon)
            if (dist > 1000.0) {
                AlertDialog.Builder(this)
                    .setTitle("Plan bloqueado")
                    .setMessage("El primer waypoint está a ${"%.1f".format(dist / 1000)} km del dron. " +
                        "Ese plan no es de este campo. Carga el plan correcto.")
                    .setPositiveButton("Entendido", null).show()
                return
            }
            if (dist > 300.0) issues.add("El primer waypoint está a ${"%.0f".format(dist)} m del dron.")
        } else if (first != null) {
            issues.add("Posición del dron desconocida: no se puede comprobar que el plan sea de este campo.")
        }

        if (t.droneModel == 2 && System.currentTimeMillis() - Session.sequoiaArmedAt > 30 * 60_000)
            issues.add("Sequoia sin preparar (botón «Enviar el plan a la Sequoia»): sin eso no habrá fotos.")
        // Meteo: si hay un informe reciente de esta zona, cuenta; si no, se recuerda consultarlo.
        val wr = Session.weather
        val place = dronePos(t) ?: p.homePoint?.let { LatLon(it.lat, it.lon) } ?: p.waypoints.firstOrNull()?.let { LatLon(it.lat, it.lon) }
        val wv = if (wr != null && place != null && System.currentTimeMillis() - wr.fetchedAt < 60 * 60_000 &&
            GridPlanner.distance(place, LatLon(wr.lat, wr.lon)) < 5000) weatherVerdictNow(wr, airframeNow()) else null
        when {
            wv == null -> issues.add("Meteo no comprobada (botón «Meteo y Kp»).")
            wv.second.level == 2 -> issues.add("METEO: NO VOLAR → " + wv.second.reasons.filter { it.startsWith("⛔") }.joinToString(" ") { it.removePrefix("⛔ ") })
            wv.second.level == 1 -> issues.add("Meteo: precaución → " + wv.second.reasons.filter { it.startsWith("⚠") }.joinToString(" ") { it.removePrefix("⚠ ") })
        }

        val msg = StringBuilder()
        msg.append("Plan: $planName\n${p.summary()}\n\n")
        if (issues.isEmpty()) msg.append("✔ Sin avisos.\n\n") else msg.append("AVISOS:\n• ${issues.joinToString("\n• ")}\n\n")
        msg.append(if (t.droneModel == 2) "La Disco ya está en el aire: al iniciar dejará los círculos e irá a la primera pasada. Marca todo para poder volar:"
                   else "Si el dron está en tierra, DESPEGARÁ solo. Marca todo para poder volar:")

        // Lista de comprobación obligatoria
        val calib = Session.calibratedAt
        val checks = listOf(
            "Hélices bien montadas, apretadas y sin daños",
            "Batería del dron cargada y bien encajada",
            if (link.platform == Platform.ANAFI) "Tarjeta SD de la ANAFI con espacio y gimbal libre (sin protector)"
            else "Sequoia encendida (luz) y con espacio en la tarjeta SD",
            if (calib != null) "Calibración de cámara hecha (${calib})" else "Calibración de cámara hecha (o asumo que no la necesito)",
            "Zona despejada, sin personas debajo, y vuelo permitido (zonas UAS / ENAIRE)",
            "Viento aceptable y SkyController en la mano para tomar el control"
        )
        val box = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(20), dp(8), dp(20), 0) }
        box.addView(TextView(this).apply { text = msg.toString(); textSize = 14f; setPadding(0, 0, 0, dp(8)) })
        val cbs = checks.map { c -> android.widget.CheckBox(this).apply { text = c; textSize = 15f } }
        cbs.forEach { box.addView(it) }
        val dialog = AlertDialog.Builder(this)
            .setTitle(if (issues.isEmpty()) "¿Iniciar misión?" else "¿Iniciar misión CON AVISOS?")
            .setView(ScrollView(this).apply { addView(box) })
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("VOLAR") { _, _ ->
                // Reverificar: el estado puede haber cambiado con el diálogo abierto.
                refreshButtons()
                if (startBtn.isEnabled) {
                    Session.track.clear(); flightMap.track = emptyList()
                    follow = true; updateFollowBtn()
                    Progress.start(this, p, planName)
                    Session.eventsAtStart = link.missionItemEvents
                    Session.missionStartedAt = System.currentTimeMillis(); Session.missionAirborne = false
                    link.startMission()
                }
                else onLog("Inicio cancelado: el estado del dron cambió (enlace, plan o vuelo).")
            }
            .create()
        dialog.setOnShowListener {
            val ok = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            ok.isEnabled = false
            cbs.forEach { cb -> cb.setOnCheckedChangeListener { _, _ -> ok.isEnabled = cbs.all { it.isChecked } } }
        }
        dialog.show()
    }

    // ------------------------------------------------------------------ reanudar tras cambio de batería

    /** (pasada a reanudar, total, plan activo) según el plan que realmente se lanzó con VOLAR. */
    private fun resumeInfo(): Triple<Int, Int, Progress.Active>? {
        val a = Progress.active(this) ?: return null
        if (a.last < 0) return null
        val p = a.plan
        if (p.isFixedWing) return null // Disco: reanudar aún no está preparado para ala fija
        val k = p.resumePass(a.last) ?: return null
        val firstCapture = p.items[p.passStarts.first()].index
        if (k == 0 && a.last < firstCapture) return null // no llegó a empezar a capturar
        return Triple(k, p.passStarts.size, a)
    }



    private fun onResumeMissionClicked() {
        val (k, n, active) = resumeInfo() ?: return
        val p = active.plan
        val t = link.telemetry
        AlertDialog.Builder(this)
            .setTitle("Reanudar misión")
            .setMessage("Se generará un plan que empieza en la pasada ${k + 1} de $n (la pasada interrumpida se repite entera para no dejar huecos)." +
                "\n\nLa casa será la posición actual del dron. Después: Subir al dron e INICIAR.")
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Generar") { _, _ ->
                try {
                    var r = p.resumeFromPass(k)
                    // Disco: no tocar despegue/aterrizaje (el cono de aterrizaje es del plan original).
                    if (t.droneModel != 2) dronePos(t)?.let { r = r.withHome(it.lat, it.lon) }
                    val base = active.name.removeSuffix(".mavlink").substringBefore(" · reanudar")
                    Session.setPlan(r, "$base · reanudar p${k + 1}")
                    shownPlanVersion = Session.planVersion
                    showPlan(r); refreshButtons()
                    onLog("Plan de reanudación listo (pasadas ${k + 1}-$n). Súbelo e inicia.")
                } catch (e: Exception) { onLog("No se pudo generar la reanudación: ${e.message}") }
            }.show()
    }

    // ------------------------------------------------------------------ calibración radiométrica Sequoia

    private fun onCalibrateClicked() {
        if (!link.isConnected) { onLog("Conecta primero con el dron."); return }
        if (link.platform == Platform.ANAFI) {
            AlertDialog.Builder(this)
                .setTitle("Calibración del sensor térmico (ANAFI Thermal)")
                .setMessage("Recalibra el sensor térmico (el obturador hace clic). Hazlo con el dron ya encendido unos minutos, " +
                    "justo antes de despegar, para que las temperaturas sean consistentes en todo el vuelo.")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("CALIBRAR") { _, _ -> lastCalibResult = -1; calibRequested = true; link.cameraCalibration() }
                .show()
            return
        }
        AlertDialog.Builder(this)
            .setTitle("Calibración radiométrica (Sequoia)")
            .setMessage("1. Coloca el panel de calibración en el suelo, a pleno sol y sin sombras.\n" +
                "2. Sujeta el dron (o la Sequoia) a ~1 m por encima, con la cámara apuntando al panel y sin tapar el sensor de luz superior.\n" +
                "3. Pulsa CALIBRAR y no te muevas hasta que salga el resultado.")
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("CALIBRAR") { _, _ -> lastCalibResult = -1; calibRequested = true; link.cameraCalibration() }
            .show()
    }

    /** Un doble toque en VOLAR caía en «Pausar» justo debajo (visto en vuelo real): se ignora 2 s tras iniciar. */
    private fun justStarted(): Boolean {
        val recent = System.currentTimeMillis() - Session.missionStartedAt < 2000
        if (recent) onLog("Toque ignorado (la misión acaba de arrancar)")
        return recent
    }

    private fun onPauseClicked() {
        if (justStarted()) return
        when (link.telemetry.planStateRaw) {
            0 -> link.pauseMission()
            2 -> link.resumeMission()
            else -> onLog("No hay plan en curso que pausar/reanudar.")
        }
    }

    private fun confirmEmergency() {
        AlertDialog.Builder(this)
            .setTitle("¿CORTAR MOTORES?")
            .setMessage("El dron CAERÁ. Úsalo solo si va a herir a alguien o está fuera de control.\n\n" +
                "Para casi todo lo demás: Parar, Volver a casa o Aterrizar.")
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("CORTAR MOTORES") { _, _ -> link.emergency() }
            .show()
    }

    /** Junta nuestro registro + el registro interno de ARSDK (logcat de este proceso) para enviarlo. */
    private fun showDiagnostics() {
        val sb = StringBuilder()
        val ver = try { packageManager.getPackageInfo(packageName, 0).versionName } catch (_: Throwable) { "?" }
        sb.append("Racoon AgroMapper v$ver · ${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL} · Android ${android.os.Build.VERSION.RELEASE}\n")
        sb.append("Modo: ${link.modeName}  Estado: ${link.telemetry.connection}  Dron: ${link.telemetry.extension}\n\n")
        sb.append("== Registro de la app ==\n").append(logLines.reversed().joinToString("\n")).append("\n\n")
        Session.lastCrash?.let { sb.append("== Último fallo Java ==\n").append(it).append("\n\n") }
        sb.append("== Rastro (sobrevive a cierres) ==\n").append(Crumbs.tail(120)).append("\n\n")
        fun logcat(vararg args: String, filter: (String) -> Boolean = { true }, max: Int) {
            try {
                val p = Runtime.getRuntime().exec(arrayOf("logcat", "-d", "-v", "time", *args))
                p.inputStream.bufferedReader().readLines().filter(filter).takeLast(max).forEach { sb.append(it).append('\n') }
            } catch (e: Exception) { sb.append("(logcat: ${e.message})\n") }
        }
        sb.append("== Fallos (buffer crash) ==\n")
        logcat("-b", "crash", max = 200)
        // Sin filtro de PID: Android solo nos deja ver nuestros propios registros, también los del proceso anterior.
        val noise = Regex("MiuiMultiWindow|ForceDark|OpenGLRenderer|GraphicsEnvironment|nativeloader|Perf |ViewScrollStub|MessageMonitor|AppScout|CompatibilityChange|NetworkSecurityConfig")
        sb.append("\n== Registro ARSDK / sistema ==\n")
        logcat("-b", "main,system", filter = { !noise.containsMatchIn(it) }, max = 600)
        val text = sb.toString()
        val tv = TextView(this).apply {
            this.text = text; textSize = 10f; typeface = Typeface.MONOSPACE; setTextIsSelectable(true)
            setPadding(dp(12), dp(8), dp(12), dp(8))
        }
        AlertDialog.Builder(this)
            .setTitle("Diagnóstico")
            .setView(ScrollView(this).apply { addView(tv) })
            .setNegativeButton("Cerrar", null)
            .setPositiveButton("Compartir") { _, _ ->
                val i = Intent(Intent.ACTION_SEND).setType("text/plain")
                    .putExtra(Intent.EXTRA_SUBJECT, "Racoon AgroMapper diagnóstico")
                    .putExtra(Intent.EXTRA_TEXT, if (text.length > 400_000) text.takeLast(400_000) else text)
                startActivity(Intent.createChooser(i, "Enviar diagnóstico"))
            }
            .show()
    }

    // ------------------------------------------------------------------ DroneLink.Listener

    private fun fmt(v: Double, digits: Int, unit: String) = if (v.isNaN()) "—" else "%.${digits}f %s".format(v, unit)

    override fun onTelemetry(t: DroneLink.Telemetry) {
        // Progreso: solo índices confirmados por el dron DESPUÉS de este VOLAR.
        if (Session.eventsAtStart >= 0 && link.missionItemEvents > Session.eventsAtStart && t.missionItem >= 0) Progress.record(this, t.missionItem)
        if (calibRequested && t.radioCalibResult >= 0 && t.radioCalibResult != lastCalibResult) {
            calibRequested = false
            lastCalibResult = t.radioCalibResult
            if (t.radioCalibResult == 0) {
                Session.calibratedAt = java.text.SimpleDateFormat("HH:mm", java.util.Locale.ROOT).format(java.util.Date())
                Toast.makeText(this, "Calibración radiométrica OK", Toast.LENGTH_LONG).show()
            } else Toast.makeText(this, "La calibración radiométrica FALLÓ: repítela", Toast.LENGTH_LONG).show()
        }
        FlightRecorder.update(applicationContext, t, planName)
        // Cronómetro real de la misión (despegue → aterrizaje) para calibrar la estimación.
        if (Session.missionStartedAt > 0) {
            if (t.flyingStateRaw in 1..4) Session.missionAirborne = true
            else if (t.flyingStateRaw == 0 && Session.missionAirborne) {
                val s = (System.currentTimeMillis() - Session.missionStartedAt) / 1000
                val est = plan?.estimatedMinutes()
                onLog("Misión: ${s / 60} min ${s % 60} s de INICIAR a en tierra" +
                    (if (est != null) " (estimado en vuelo ${"%.1f".format(est)} min" + (if (Session.plannerEstimateS > 0) ", planificador ${"%.1f".format(Session.plannerEstimateS / 60)} min" else "") + ")" else ""))
                Session.missionStartedAt = 0L; Session.missionAirborne = false
            }
        }
        updateFlightMap(t)
        updateOsd(t)
        updateAlerts(t)
        rthDiscBtn.text = "Si se pierde el enlace: " + when (link.telemetry.rthOnDisconnect) {
            1 -> "VUELVE A CASA"; 0 -> "SIGUE EL PLAN"; else -> "—"
        }
        connText.text = buildString {
            append("Estado: ${t.connection}")
            if (t.productName.isNotEmpty()) append("  ·  ${t.productName}")
            if (t.extension.isNotEmpty()) append("\nDron: ${t.extension}")
        }
        connText.setTextColor(if (t.connected) C_GREEN else C_TEXT)

        val pos = if (t.lat == 500.0 || t.lon == 500.0) "—" else "%.6f, %.6f".format(t.lat, t.lon)
        telemText.text = buildString {
            append("Batería     ${if (t.battery < 0) "—" else "${t.battery} %"}")
            if (t.controllerBattery >= 0) append("   (mando ${t.controllerBattery} %)")
            append("\nGPS         ${if (t.gpsFix) "FIX" else "sin fix"}  ·  ${if (t.satellites < 0) "—" else t.satellites} sat")
            append("\nVuelo       ${t.flyingState}")
            append("\nAltura      ${fmt(t.altitude, 1, "m")}   Vel ${fmt(t.speed, 1, "m/s")}")
            append("\nPosición    $pos")
            append("\nPlan        ${t.planState}${if (t.missionItem >= 0) "  ·  ítem ${t.missionItem}" else ""}")
            if (t.planError.isNotEmpty()) append("\n⚠ Error plan: ${t.planError}")
            append("\nCasa        ${t.navigateHome}")
        }

        checksText.text = if (!t.connected) "Conecta para ver las comprobaciones." else buildString {
            if (t.components.isEmpty()) append("(el dron aún no ha informado)\n")
            for ((k, v) in t.components) append("${if (v) "✔" else "✘"} $k\n")
            t.planAvailable?.let { append(if (it) "✔ Plan disponible para iniciar" else "✘ Plan no disponible todavía") }
            t.magnetoCalibrationRequired?.let { if (it) append("\n✘ Requiere calibrar magnetómetro") }
            if (link.platform == Platform.BLUEGRASS_SEQUOIA)
                append(if (t.sequoiaSeen) "\n✔ Sequoia reconocida por el dron" + (if (t.accessories.isNotEmpty()) " (${t.accessories})" else "")
                       else "\n✘ El dron no informa de ninguna Sequoia (¿cable? ¿firmware?)")
        }.trimEnd()

        if (!t.connected && !link.connecting) planUploaded = false
        refreshButtons()
    }

    private var lastLogMsg = ""
    private var lastLogAt = 0L

    override fun onLog(msg: String) {
        // Los estados repetidos (mismo texto en <3 s) solo ensucian el registro.
        val nowMs = System.currentTimeMillis()
        if (msg == lastLogMsg && nowMs - lastLogAt < 3000 && !msg.endsWith("✓")) return
        lastLogMsg = msg; lastLogAt = nowMs
        val ts = SimpleDateFormat("HH:mm:ss", Locale.ROOT).format(Date())
        logLines.addFirst("$ts  $msg")
        while (logLines.size > 200) logLines.removeLast()
        logText.text = logLines.joinToString("\n")
    }
}
