package com.racoonair.mapper

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.parrot.drone.groundsdk.GroundSdk
import com.parrot.drone.groundsdk.Ref
import com.parrot.drone.groundsdk.device.DeviceState
import com.parrot.drone.groundsdk.device.Drone
import com.parrot.drone.groundsdk.device.RemoteControl
import com.parrot.drone.groundsdk.device.instrument.Alarms
import com.parrot.drone.groundsdk.device.instrument.Altimeter
import com.parrot.drone.groundsdk.device.instrument.BatteryInfo
import com.parrot.drone.groundsdk.device.instrument.Compass
import com.parrot.drone.groundsdk.device.instrument.FlyingIndicators
import com.parrot.drone.groundsdk.device.instrument.Gps
import com.parrot.drone.groundsdk.device.instrument.Speedometer
import com.parrot.drone.groundsdk.device.peripheral.MainCamera
import com.parrot.drone.groundsdk.device.peripheral.ThermalCamera
import com.parrot.drone.groundsdk.device.peripheral.ThermalControl
import com.parrot.drone.groundsdk.device.peripheral.camera.CameraExposure
import com.parrot.drone.groundsdk.device.peripheral.camera.CameraPhoto
import com.parrot.drone.groundsdk.device.peripheral.camera.CameraWhiteBalance
import com.parrot.drone.groundsdk.device.pilotingitf.Activable
import com.parrot.drone.groundsdk.device.pilotingitf.FlightPlanPilotingItf
import com.parrot.drone.groundsdk.device.pilotingitf.ManualCopterPilotingItf
import com.parrot.drone.groundsdk.device.pilotingitf.ReturnHomePilotingItf
import com.parrot.drone.groundsdk.facility.AutoConnection
import java.io.File

/**
 * Enlace con la familia ANAFI (4K, Thermal, USA) mediante GroundSDK 1.8, el mismo SDK que
 * usaba Pix4Dcapture. La conexión la gestiona AutoConnection: SkyController 3 por USB o WiFi directa.
 * Todos los callbacks de GroundSDK llegan en el hilo principal.
 */
class AnafiLink(private val ctx: Context, override var listener: DroneLink.Listener) : Link {

    override val telemetry = DroneLink.Telemetry()
    override val platform = Platform.ANAFI
    override val modeName = "ANAFI"
    /** "En curso" mientras haya sesión abierta sin dron conectado (p. ej. tras cambiar de batería). */
    override val connecting get() = gsdk != null && !telemetry.connected
    override var uploading = false
        private set
    override var missionItemEvents = 0
        private set
    override val isConnected get() = telemetry.connected

    private val main = Handler(Looper.getMainLooper())
    private var gsdk: GroundSdk? = null
    private var autoConnRef: Ref<AutoConnection>? = null
    private var drone: Drone? = null
    private var rc: RemoteControl? = null
    private val droneRefs = ArrayList<Ref<*>>()
    private val rcRefs = ArrayList<Ref<*>>()

    private var fp: FlightPlanPilotingItf? = null
    private var rth: ReturnHomePilotingItf? = null
    private var manual: ManualCopterPilotingItf? = null
    private var thermal: ThermalControl? = null
    private var mainCam: MainCamera? = null
    private var thermalCam: ThermalCamera? = null
    private var model: Drone.Model? = null
    private var lastMissionItem = -1
    private var uploadDone: ((Boolean) -> Unit)? = null
    private var alarmsSeen = HashSet<String>()

    companion object { const val PLAN_FILE = "flightPlan.mavlink" }

    // ---------------------------------------------------------------- conexión

    override fun connect(choice: ConnectChoice) {
        if (gsdk != null) { log("Ya hay una conexión abierta o en curso."); return }
        telemetry.connection = "buscando ANAFI / SkyController 3…"
        listener.onTelemetry(telemetry)
        try {
            Crumbs.write("anafi: GroundSdk.newSession")
            val g = GroundSdk.newSession(ctx, null)
            g.resume() // una sesión nueva nace SUSPENDIDA: sin esto no llega ningún callback (Pix4D hace lo mismo)
            gsdk = g
            autoConnRef = g.getFacility(AutoConnection::class.java) { ac ->
                if (ac == null) return@getFacility
                if (ac.status != AutoConnection.Status.STARTED) ac.start()
                onRc(ac.remoteControl)
                onDrone(ac.drone)
            }
            log("GroundSDK iniciado. Enciende el SkyController 3 y la ANAFI; si Android pregunta qué app abrir con el mando, elige Racoon Mapper.")
        } catch (t: Throwable) {
            Crumbs.write("anafi EXC ${t.javaClass.name}: ${t.message}")
            log("ERROR iniciando GroundSDK: ${t.javaClass.simpleName}: ${t.message}")
            try { gsdk?.close() } catch (_: Throwable) {}
            gsdk = null
            telemetry.connection = "desconectado"
            listener.onTelemetry(telemetry)
        }
    }

    override fun disconnect() {
        log("Desconectando…")
        try { autoConnRef?.get()?.stop() } catch (_: Throwable) {}
        closeDrone(); closeRc()
        try { autoConnRef?.close() } catch (_: Throwable) {}
        autoConnRef = null
        try { gsdk?.close() } catch (_: Throwable) {}
        gsdk = null
        resetTelemetry()
        telemetry.connection = "desconectado"
        listener.onTelemetry(telemetry)
    }

    private fun closeDrone() {
        droneRefs.forEach { try { it.close() } catch (_: Throwable) {} }
        droneRefs.clear(); drone = null; fp = null; rth = null; manual = null; thermal = null; model = null
        mainCam = null; thermalCam = null
    }

    private fun closeRc() {
        rcRefs.forEach { try { it.close() } catch (_: Throwable) {} }
        rcRefs.clear(); rc = null
    }

    private fun onRc(r: RemoteControl?) {
        if (r?.uid == rc?.uid) return
        closeRc()
        rc = r ?: return
        log("Mando: ${r.name} (${r.model})")
        rcRefs += r.getInstrument(BatteryInfo::class.java) { b -> telemetry.controllerBattery = b?.batteryLevel ?: -1; changed() }
    }

    private fun onDrone(d: Drone?) {
        if (d?.uid == drone?.uid) return
        closeDrone()
        if (d == null) { telemetry.extension = ""; changed(); return }
        drone = d
        model = d.model
        log("Dron: ${d.name} (${d.model})")
        droneRefs += d.getState { st -> onState(st) }
        droneRefs += d.getInstrument(BatteryInfo::class.java) { b -> telemetry.battery = b?.batteryLevel ?: -1; changed() }
        droneRefs += d.getInstrument(Gps::class.java) { g ->
            telemetry.gpsFix = g?.isFixed == true
            telemetry.satellites = g?.satelliteCount ?: -1
            val loc = g?.lastKnownLocation()
            if (loc != null && g.isFixed) { telemetry.lat = loc.latitude; telemetry.lon = loc.longitude }
            changed()
        }
        droneRefs += d.getInstrument(Altimeter::class.java) { a -> telemetry.altitude = a?.takeOffRelativeAltitude ?: Double.NaN; changed() }
        droneRefs += d.getInstrument(Speedometer::class.java) { s -> telemetry.speed = s?.groundSpeed ?: Double.NaN; changed() }
        droneRefs += d.getInstrument(Compass::class.java) { c ->
            val h = c?.heading ?: return@getInstrument
            if (!telemetry.yawDeg.isNaN() && Math.abs(((h - telemetry.yawDeg + 540.0) % 360.0) - 180.0) < 3.0) return@getInstrument
            telemetry.yawDeg = (h + 360.0) % 360.0; changed()
        }
        droneRefs += d.getInstrument(FlyingIndicators::class.java) { f -> onFlying(f) }
        droneRefs += d.getInstrument(Alarms::class.java) { a -> onAlarms(a) }
        droneRefs += d.getPilotingItf(FlightPlanPilotingItf::class.java) { p -> fp = p; onFlightPlan(p) }
        droneRefs += d.getPilotingItf(ReturnHomePilotingItf::class.java) { r -> rth = r; onRth(r) }
        droneRefs += d.getPilotingItf(ManualCopterPilotingItf::class.java) { m -> manual = m }
        droneRefs += d.getPeripheral(ThermalControl::class.java) { t -> thermal = t }
        droneRefs += d.getPeripheral(MainCamera::class.java) { c -> mainCam = c }
        droneRefs += d.getPeripheral(ThermalCamera::class.java) { c -> thermalCam = c }
    }

    private fun onState(st: DeviceState?) {
        val cs = st?.connectionState
        telemetry.connected = cs == DeviceState.ConnectionState.CONNECTED
        telemetry.connection = when (cs) {
            DeviceState.ConnectionState.CONNECTED -> "CONECTADO"
            DeviceState.ConnectionState.CONNECTING -> "conectando…"
            DeviceState.ConnectionState.DISCONNECTING -> "desconectando…"
            else -> "desconectado"
        }
        val d = drone
        telemetry.productName = d?.name ?: ""
        telemetry.extension = if (d != null) "${d.model} · ${rc?.let { "vía ${it.model}" } ?: "WiFi"}" else ""
        if (cs == DeviceState.ConnectionState.DISCONNECTED) {
            val cause = st.connectionStateCause
            if (cause != DeviceState.ConnectionStateCause.NONE) log("Dron desconectado ($cause)")
            telemetry.missionItem = -1
        } else log("Estado: ${telemetry.connection}")
        changed()
    }

    private fun onFlying(f: FlyingIndicators?) {
        val raw = when (f?.state) {
            FlyingIndicators.State.LANDED -> if (f.landedState == FlyingIndicators.LandedState.MOTOR_RAMPING) 7 else 0
            FlyingIndicators.State.FLYING -> when (f.flyingState) {
                FlyingIndicators.FlyingState.TAKING_OFF -> 1
                FlyingIndicators.FlyingState.LANDING -> 4
                FlyingIndicators.FlyingState.WAITING -> 2
                else -> 3
            }
            FlyingIndicators.State.EMERGENCY_LANDING -> 8
            null -> -1
            else -> 5 // EMERGENCY
        }
        if (raw != telemetry.flyingStateRaw) {
            telemetry.flyingStateRaw = raw
            telemetry.flyingState = arrayOf("en tierra", "despegando", "en estacionario", "volando", "aterrizando",
                "EMERGENCIA", "esperando usuario", "motores arrancando", "aterrizaje de emergencia").getOrElse(raw) { "—" }
            log("Vuelo: ${telemetry.flyingState}")
        }
        changed()
    }

    private fun onAlarms(a: Alarms?) {
        val t = telemetry
        t.extraAlerts.clear()
        t.alert = 0; t.wind = 0; t.motorError = 0
        if (a == null) { changed(); return }
        fun lvl(k: Alarms.Alarm.Kind) = try { a.getAlarm(k).level } catch (_: Throwable) { Alarms.Alarm.Level.NOT_SUPPORTED }
        when (lvl(Alarms.Alarm.Kind.POWER)) {
            Alarms.Alarm.Level.CRITICAL -> t.alert = 3
            Alarms.Alarm.Level.WARNING -> t.alert = 4
            else -> {}
        }
        if (lvl(Alarms.Alarm.Kind.USER_EMERGENCY) == Alarms.Alarm.Level.CRITICAL) t.alert = 1
        if (lvl(Alarms.Alarm.Kind.MOTOR_CUT_OUT) == Alarms.Alarm.Level.CRITICAL) t.alert = 2
        t.wind = when (lvl(Alarms.Alarm.Kind.STRONG_WIND)) {
            Alarms.Alarm.Level.CRITICAL -> 2; Alarms.Alarm.Level.WARNING -> 1; else -> 0
        }
        val named = listOf(
            Alarms.Alarm.Kind.MOTOR_ERROR to "Error de motor",
            Alarms.Alarm.Kind.BATTERY_TOO_HOT to "Batería demasiado caliente",
            Alarms.Alarm.Kind.BATTERY_TOO_COLD to "Batería demasiado fría",
            Alarms.Alarm.Kind.HOVERING_DIFFICULTIES_NO_GPS_TOO_DARK to "Sin GPS y poca luz: estacionario difícil",
            Alarms.Alarm.Kind.HOVERING_DIFFICULTIES_NO_GPS_TOO_HIGH to "Sin GPS y demasiado alto: estacionario difícil",
            Alarms.Alarm.Kind.AUTOMATIC_LANDING_BATTERY_ISSUE to "ATERRIZAJE AUTOMÁTICO por batería",
            Alarms.Alarm.Kind.STRONG_VIBRATIONS to "Vibraciones fuertes",
            Alarms.Alarm.Kind.MAGNETOMETER_PERTURBATION to "Perturbación del magnetómetro",
            Alarms.Alarm.Kind.MAGNETOMETER_LOW_EARTH_FIELD to "Campo magnético débil"
        )
        for ((k, txt) in named) when (lvl(k)) {
            Alarms.Alarm.Level.CRITICAL -> t.extraAlerts.add("⛔ $txt")
            Alarms.Alarm.Level.WARNING -> t.extraAlerts.add("⚠ $txt")
            else -> {}
        }
        if (a.automaticLandingDelay() > 0) t.extraAlerts.add("⛔ Aterrizará solo en ${a.automaticLandingDelay()} s")
        val key = t.extraAlerts.joinToString("|") + "|${t.alert}|${t.wind}"
        if (key !in alarmsSeen) { alarmsSeen.clear(); alarmsSeen.add(key); if (t.extraAlerts.isNotEmpty()) log("Alarmas: " + t.extraAlerts.joinToString("; ")) }
        changed()
    }

    private fun onFlightPlan(p: FlightPlanPilotingItf?) {
        val t = telemetry
        if (p == null) {
            // Dron desconectado: la interfaz desaparece; no dejar un estado "ejecutando" colgado.
            t.planAvailable = null; t.planStateRaw = -1; t.planPlaying = false; t.planState = "—"
            t.components.clear(); t.planError = ""
            changed(); return
        }
        val st = p.state
        t.planStateRaw = when {
            st == Activable.State.ACTIVE -> 0
            // Pausado pero en tierra (Parar/RTH/Aterrizar pausan el plan en GroundSDK): a efectos de la app,
            // el plan ya no está en curso; INICIAR con activate(true) lo para y lo relanza desde el principio.
            p.isPaused && telemetry.flyingStateRaw == 0 -> 1
            p.isPaused -> 2
            p.isFlightPlanFileKnown -> 3
            else -> 1
        }
        t.planPlaying = t.planStateRaw == 0
        t.planState = arrayOf("EJECUTANDO", "PARADO", "PAUSADO", "CARGADO").getOrElse(t.planStateRaw) { "—" }
        t.planAvailable = st != Activable.State.UNAVAILABLE
        val reasons = p.unavailabilityReasons
        t.components.clear()
        t.components["GPS"] = FlightPlanPilotingItf.UnavailabilityReason.DRONE_GPS_INFO_INACCURATE !in reasons
        t.components["Calibración"] = FlightPlanPilotingItf.UnavailabilityReason.DRONE_NOT_CALIBRATED !in reasons
        t.components["Fichero de plan"] = FlightPlanPilotingItf.UnavailabilityReason.MISSING_FLIGHT_PLAN_FILE !in reasons
        t.components["Despegue"] = FlightPlanPilotingItf.UnavailabilityReason.CANNOT_TAKE_OFF !in reasons
        t.components["Cámara"] = FlightPlanPilotingItf.UnavailabilityReason.CAMERA_UNAVAILABLE !in reasons
        t.planError = when (p.latestActivationError) {
            FlightPlanPilotingItf.ActivationError.INCORRECT_FLIGHT_PLAN_FILE -> "fichero de plan incorrecto"
            FlightPlanPilotingItf.ActivationError.WAYPOINT_BEYOND_GEOFENCE -> "waypoint fuera de la geocerca"
            else -> ""
        }
        val item = p.latestMissionItemExecuted
        if (item >= 0 && item != lastMissionItem) { lastMissionItem = item; t.missionItem = item; missionItemEvents++ }
        p.returnHomeOnDisconnect?.let { s -> t.rthOnDisconnect = if (s.isEnabled) 1 else 0; t.rthOnDisconnectReadOnly = !s.isMutable }
        // Subida en curso
        val cb = uploadDone
        if (cb != null) when (p.latestUploadState) {
            FlightPlanPilotingItf.UploadState.UPLOADED -> { uploadDone = null; uploading = false; log("Plan subido."); prepareCamera(); cb(true) }
            FlightPlanPilotingItf.UploadState.FAILED -> { uploadDone = null; uploading = false; log("ERROR: la subida del plan falló."); cb(false) }
            else -> {}
        }
        changed()
    }

    private fun onRth(r: ReturnHomePilotingItf?) {
        val t = telemetry
        if (r == null) { t.navigateHome = "—"; t.navHomeReason = -1; t.rthBattery = -1; changed(); return }
        val was = t.navigateHome
        t.navigateHome = when (r.state) {
            Activable.State.ACTIVE -> "en curso"
            Activable.State.IDLE -> "disponible"
            else -> "no disponible"
        }
        t.navHomeReason = when (r.reason) {
            ReturnHomePilotingItf.Reason.USER_REQUESTED -> 0
            ReturnHomePilotingItf.Reason.CONNECTION_LOST -> 1
            ReturnHomePilotingItf.Reason.POWER_LOW -> 2
            else -> -1
        }
        t.rthBattery = when (r.homeReachability) {
            ReturnHomePilotingItf.Reachability.WARNING -> 1
            ReturnHomePilotingItf.Reachability.CRITICAL -> 2
            else -> 0
        }
        if (was != t.navigateHome) log("Vuelta a casa: ${t.navigateHome}" + when (t.navHomeReason) {
            0 -> " (pedida por ti)"; 1 -> " (ENLACE PERDIDO)"; 2 -> " (BATERÍA BAJA)"; else -> "" })
        changed()
    }

    // ---------------------------------------------------------------- plan y órdenes

    override fun uploadPlan(plan: FlightPlan, done: (Boolean) -> Unit) {
        val p = fp
        if (p == null) { log("La interfaz de plan de vuelo no está disponible (¿dron conectado?)."); done(false); return }
        if (uploading) { log("Ya hay una subida en curso."); return }
        val f = File(ctx.cacheDir, PLAN_FILE)
        f.writeText(plan.text)
        uploading = true
        uploadDone = done
        log("Subiendo plan (${f.length()} bytes)…")
        try { p.uploadFlightPlan(f) } catch (t: Throwable) {
            uploading = false; uploadDone = null; log("ERROR subida: ${t.message}"); done(false); return
        }
        main.postDelayed({
            if (uploadDone === done) { uploadDone = null; uploading = false; log("ERROR: la subida no terminó en 60 s."); done(false) }
        }, 60_000)
    }

    override fun startMission() {
        val p = fp ?: run { log("No conectado."); return }
        telemetry.missionItem = -1
        lastMissionItem = -1
        // La cámara debe estar en foto JPEG, sencilla, rectilínea, exposición y balance automáticos (como Pix4D).
        val cam = activeCamera()
        if (cam == null || !cam.canStartPhotoCapture()) {
            prepareCamera()
            log("La cámara aún no está lista para fotos; la estoy configurando. Pulsa INICIAR otra vez en unos segundos.")
            return
        }
        val ok = p.activate(true)
        log(if (ok) "INICIAR plan ✓" else "INICIAR plan rechazado por el dron" + p.unavailabilityReasons.let { if (it.isEmpty()) "" else " ($it)" })
    }

    override fun resumeMission() { val ok = fp?.activate(false); log(if (ok == true) "Reanudar plan ✓" else "Reanudar plan rechazado") }
    override fun pauseMission() { val ok = fp?.deactivate(); log(if (ok == true) "Pausar plan ✓" else "Pausar plan rechazado") }
    override fun stopMission() {
        fp?.deactivate()
        val ok = manual?.activate()
        log(if (ok == true) "Plan parado: el dron queda en estacionario ✓" else "Parar plan: no se pudo pasar a manual")
    }
    override fun cancelReturnHome() { val ok = rth?.deactivate(); log(if (ok == true) "Vuelta a casa cancelada ✓" else "No se pudo cancelar la vuelta a casa") }

    // Vídeo en directo de ANAFI: pendiente (GroundSDK lo trae, se activará en la app ANAFI cuando toque).
    override val supportsVideo = false
    override var videoSink: VideoSink? = null
    override fun setVideoEnabled(on: Boolean) {}

    override fun returnHome() { val ok = rth?.activate(); log(if (ok == true) "Volver a casa ✓" else "Volver a casa rechazado") }
    override fun land() {
        val m = manual ?: run { log("No conectado."); return }
        if (m.state != Activable.State.ACTIVE) m.activate()
        m.land(); log("Aterrizar ✓")
    }
    override fun emergency() {
        val m = manual ?: run { log("No conectado."); return }
        if (m.state != Activable.State.ACTIVE) m.activate()
        m.emergencyCutOut(); log("EMERGENCIA (corte de motores) enviada")
    }
    override fun setReturnHomeOnDisconnect(on: Boolean) {
        val s = fp?.returnHomeOnDisconnect ?: run { log("No disponible."); return }
        s.isEnabled = on
        log(if (on) "Enlace perdido → volver a casa ✓" else "Enlace perdido → seguir el plan ✓")
    }
    override fun cameraCalibration() {
        val th = thermal ?: run { log("Calibración: este dron no tiene cámara térmica (la radiométrica es solo de la Sequoia)."); return }
        try {
            val ok = th.calibration()?.calibrate() ?: false
            log(if (ok) "Calibración del sensor térmico lanzada (el obturador hace clic) ✓" else "El dron no aceptó la calibración térmica")
        } catch (t: Throwable) { log("Calibración térmica no disponible: ${t.message}") }
    }

    /** Cámara activa: la térmica cuando el modo térmico está encendido, si no la principal. */
    private fun activeCamera(): com.parrot.drone.groundsdk.device.peripheral.camera.Camera? =
        thermalCam?.takeIf { it.isActive } ?: mainCam?.takeIf { it.isActive }

    /** Térmica en STANDARD (fotos RGB + térmica) y cámara en foto JPEG sencilla, igual que Pix4Dcapture. */
    private fun prepareCamera() {
        thermal?.let { th ->
            try { if (th.mode().value != ThermalControl.Mode.STANDARD) { th.mode().value = ThermalControl.Mode.STANDARD; log("Cámara térmica activada (modo estándar).") } }
            catch (t: Throwable) { log("No se pudo activar la térmica: ${t.message}") }
        }
        for (cam in listOfNotNull(mainCam, thermalCam)) try {
            val ph = cam.photo()
            val fmt = if (ph.supportedFormats().contains(CameraPhoto.Format.RECTILINEAR)) CameraPhoto.Format.RECTILINEAR else CameraPhoto.Format.FULL_FRAME
            ph.setSingleMode(fmt, CameraPhoto.FileFormat.JPEG)
            cam.mode().value = com.parrot.drone.groundsdk.device.peripheral.camera.Camera.Mode.PHOTO
            cam.exposure().setMode(CameraExposure.Mode.AUTOMATIC)
            cam.whiteBalance().setMode(CameraWhiteBalance.Mode.AUTOMATIC)
        } catch (t: Throwable) { log("Configurar cámara: ${t.javaClass.simpleName}: ${t.message}") }
    }

    // ---------------------------------------------------------------- utilidades

    private fun resetTelemetry() {
        val t = telemetry
        t.connected = false; t.extension = ""; t.productName = ""; t.battery = -1; t.controllerBattery = -1
        t.gpsFix = false; t.satellites = -1; t.flyingState = "—"; t.flyingStateRaw = -1; t.altitude = Double.NaN
        t.lat = 500.0; t.lon = 500.0; t.speed = Double.NaN; t.yawDeg = Double.NaN; t.planState = "—"; t.planStateRaw = -1
        t.planPlaying = false; t.planError = ""; t.missionItem = -1; t.planAvailable = null; t.components.clear()
        t.navigateHome = "—"; t.alert = -1; t.wind = -1; t.navHomeReason = -1; t.rthBattery = -1; t.motorError = 0
        t.rthOnDisconnect = -1; t.extraAlerts.clear(); lastMissionItem = -1
    }

    private fun changed() = listener.onTelemetry(telemetry)

    private fun log(msg: String) {
        Crumbs.write("anafi: $msg")
        if (Looper.myLooper() == Looper.getMainLooper()) listener.onLog(msg) else main.post { listener.onLog(msg) }
    }
}
