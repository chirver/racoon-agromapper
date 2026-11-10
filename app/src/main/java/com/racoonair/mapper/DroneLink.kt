package com.racoonair.mapper

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Handler
import android.os.Looper
import com.parrot.arsdk.ARSDK
import com.parrot.arsdk.arsal.ARSALPrint
import com.parrot.arsdk.arsal.ARSAL_PRINT_LEVEL_ENUM
import com.parrot.arsdk.arcommands.ARCOMMANDS_ARDRONE3_GPSSETTINGS_HOMETYPE_TYPE_ENUM
import com.parrot.arsdk.arcommands.ARCOMMANDS_COMMON_MAVLINK_START_TYPE_ENUM
import com.parrot.arsdk.arcontroller.ARCONTROLLER_DEVICE_STATE_ENUM
import com.parrot.arsdk.arcontroller.ARCONTROLLER_DICTIONARY_KEY_ENUM
import com.parrot.arsdk.arcontroller.ARCONTROLLER_ERROR_ENUM
import com.parrot.arsdk.arcontroller.ARControllerArgumentDictionary
import com.parrot.arsdk.arcontroller.ARControllerDictionary
import com.parrot.arsdk.arcontroller.ARDeviceController
import com.parrot.arsdk.arcontroller.ARDeviceControllerListener
import com.parrot.arsdk.arcontroller.ARDeviceControllerStreamListener
import com.parrot.arsdk.arcontroller.ARControllerCodec
import com.parrot.arsdk.arcontroller.ARFrame
import com.parrot.arsdk.arcontroller.ARCONTROLLER_STREAM_CODEC_TYPE_ENUM
import com.parrot.arsdk.arcontroller.ARFeatureARDrone3
import com.parrot.arsdk.arcontroller.ARFeatureCommon
import com.parrot.arsdk.arcontroller.ARFeatureSkyController
import com.parrot.arsdk.arcontroller.ARFeatureSequoiaCam
import com.parrot.arsdk.ardatatransfer.ARDATATRANSFER_ERROR_ENUM
import com.parrot.arsdk.ardatatransfer.ARDATATRANSFER_UPLOADER_RESUME_ENUM
import com.parrot.arsdk.ardatatransfer.ARDataTransferManager
import com.parrot.arsdk.ardatatransfer.ARDataTransferUploaderCompletionListener
import com.parrot.arsdk.ardatatransfer.ARDataTransferUploaderProgressListener
import com.parrot.arsdk.ardiscovery.ARDISCOVERY_ERROR_ENUM
import com.parrot.arsdk.ardiscovery.ARDISCOVERY_PRODUCT_ENUM
import com.parrot.arsdk.ardiscovery.ARDiscoveryDevice
import com.parrot.arsdk.arutils.ARUTILS_ERROR_ENUM
import com.parrot.arsdk.arutils.ARUtilsManager
import java.io.File

/**
 * Enlace con el dron vía ARSDK3 (mismo SDK y misma secuencia que usaba Pix4Dcapture).
 * Todas las notificaciones al listener llegan en el hilo principal.
 */
class DroneLink(private val ctx: Context, @Volatile override var listener: Listener) : Link {

    enum class Mode(val label: String, val product: ARDISCOVERY_PRODUCT_ENUM, val ip: String, val ftpPort: Int, val usb: Boolean = false) {
        SKYCONTROLLER2_USB(tr("SkyController 2 · cable USB (como Pix4D)"), ARDISCOVERY_PRODUCT_ENUM.ARDISCOVERY_PRODUCT_SKYCONTROLLER_2, "", ARUtilsManager.FTP_FLIGHTPLAN, usb = true),
        BLUEGRASS_WIFI(tr("Bluegrass · WiFi directa"), ARDISCOVERY_PRODUCT_ENUM.ARDISCOVERY_PRODUCT_CHIMERA, "192.168.42.1", ARUtilsManager.FTP_FLIGHTPLAN),
        DISCO_WIFI(tr("Disco · WiFi directa"), ARDISCOVERY_PRODUCT_ENUM.ARDISCOVERY_PRODUCT_EVINRUDE, "192.168.42.1", ARUtilsManager.FTP_FLIGHTPLAN),
        SKYCONTROLLER2_WIFI(tr("SkyController 2 · WiFi (experimental)"), ARDISCOVERY_PRODUCT_ENUM.ARDISCOVERY_PRODUCT_SKYCONTROLLER_2, "192.168.53.1", ARUtilsManager.FTP_FLIGHTPLAN_SKY);
        override fun toString() = label
    }

    /** Estado de telemetría. Lat/lon = 500 significa "desconocido" en Parrot. */
    data class Telemetry(
        var connection: String = "desconectado",
        var connected: Boolean = false,
        var extension: String = "",
        var productName: String = "",
        var battery: Int = -1,
        var controllerBattery: Int = -1,
        /** El DRON (no solo el mando) está enlazado y su telemetría es actual. */
        var droneLinked: Boolean = false,
        /** 0 desconocido, 1 Bluegrass, 2 Disco (para límites de meteo y para no subir planes de multirrotor a la Disco). */
        var droneModel: Int = 0,
        /** Accesorios que el DRON dice tener conectados (p. ej. «Sequoia · fw 1.7.1»). Vacío = no ha informado de ninguno. */
        var accessories: String = "",
        /** El dron ha mandado algún estado de la Sequoia (calibración, sesión…): su firmware la maneja. */
        var sequoiaSeen: Boolean = false,
        var gpsFix: Boolean = false,
        var satellites: Int = -1,
        var flyingState: String = "—",
        var flyingStateRaw: Int = -1,
        var altitude: Double = Double.NaN,
        var lat: Double = 500.0,
        var lon: Double = 500.0,
        var speed: Double = Double.NaN,
        var yawDeg: Double = Double.NaN,
        /** Calidad del enlace 1 (a punto de perderse) … 5 (muy buena); -1 sin dato. */
        var linkQuality: Int = -1,
        /** RSSI del enlace dron-mando en dBm; 0 sin dato. */
        var rssi: Int = 0,
        /** Casa que el propio dron tiene guardada (500 = sin dato). */
        var homeLat: Double = 500.0,
        var homeLon: Double = 500.0,
        var planState: String = "—",
        /** 0 ejecutando, 1 parado, 2 pausado, 3 cargado, -1 desconocido. */
        var planStateRaw: Int = -1,
        var planPlaying: Boolean = false,
        var planError: String = "",
        var missionItem: Int = -1,
        var magnetoCalibrationRequired: Boolean? = null,
        var planAvailable: Boolean? = null,
        val components: LinkedHashMap<String, Boolean> = LinkedHashMap(),
        var navigateHome: String = "—",
        /** Alertas del dron (enums ARSDK); -1 = sin dato. */
        var alert: Int = -1,
        var wind: Int = -1,
        var navHomeReason: Int = -1,
        var rthBattery: Int = -1,
        var motorError: Int = 0,
        var rthOnDisconnect: Int = -1,
        var rthOnDisconnectReadOnly: Boolean = false,
        val sensors: LinkedHashMap<String, Boolean> = LinkedHashMap(),
        /** Sequoia: calibración radiométrica. Estado 0 disponible, 1 no disponible, 2 en curso. Resultado 0 OK, 1 fallo. */
        var radioCalibStatus: Int = -1,
        var radioCalibResult: Int = -1,
        /** Avisos ya redactados (p. ej. de GroundSDK) que se añaden tal cual. */
        val extraAlerts: ArrayList<String> = ArrayList()
    ) {
        /** Avisos activos, del más grave al menos grave. */
        fun alerts(): List<String> {
            val a = ArrayList<String>()
            when (alert) {
                1 -> a.add(tr("⛔ Emergencia activada"))
                2 -> a.add(tr("⛔ Corte de motores (cut-out)"))
                3 -> a.add(tr("⛔ BATERÍA CRÍTICA"))
                4 -> a.add(tr("⚠ Batería baja (aviso del dron)"))
                5 -> a.add(tr("⚠ Inclinación excesiva"))
            }
            if (motorError != 0) a.add(trf("⛔ Error de motor: %1\$s", MOTOR_ERRORS.getOrElse(motorError) { "código $motorError" }))
            when (wind) { 1 -> a.add(tr("⚠ Viento fuerte")); 2 -> a.add(tr("⛔ Viento demasiado fuerte")) }
            when (rthBattery) { 1 -> a.add(tr("⚠ Batería justa para volver a casa")); 2 -> a.add(tr("⛔ Batería insuficiente para volver a casa")) }
            if (navigateHome == "en curso" && navHomeReason in 0..2)
                a.add(tr("↩ Volviendo a casa: ") + arrayOf(tr("pedido por ti"), tr("enlace perdido"), tr("BATERÍA BAJA"))[navHomeReason])
            sensors.filterValues { !it }.keys.forEach { a.add(trf("⚠ Sensor con fallo: %1\$s", it)) }
            if (planError.isNotEmpty()) a.add(trf("⚠ Plan: %1\$s", planError))
            a.addAll(extraAlerts)
            return a.sortedBy { if (it.startsWith("⛔")) 0 else 1 }
        }
    }

    interface Listener {
        fun onTelemetry(t: Telemetry)
        fun onLog(msg: String)
    }

    override val telemetry = Telemetry()
    override val platform = Platform.BLUEGRASS_SEQUOIA
    override val modeName get() = mode.name
    var mode: Mode = Mode.SKYCONTROLLER2_USB
        private set

    private val main = Handler(Looper.getMainLooper())
    @Volatile private var controller: ARDeviceController? = null
    @Volatile override var connecting = false
        private set
    @Volatile private var extensionProduct: ARDISCOVERY_PRODUCT_ENUM? = null
    @Volatile override var uploading = false
        private set

    companion object {
        private var libsLoaded = false
        fun loadLibs(): Boolean {
            if (!libsLoaded) {
                libsLoaded = ARSDK.loadSDKLibs()
                // Registro detallado de ARSDK (visible con el botón Diagnóstico).
                if (libsLoaded) try { ARSALPrint.setMinimumLogLevel(ARSAL_PRINT_LEVEL_ENUM.ARSAL_PRINT_DEBUG) } catch (_: Throwable) {}
            }
            return libsLoaded
        }

        /**
         * Las librerías nativas (más nuevas) numeran los errores distinto que la parte Java empaquetada:
         * p. ej. -1983 es CANCELED en nativo pero Java lo llama COMMAND_GENERATING. Traducimos por valor.
         */
        private val NATIVE_ERRORS = mapOf(
            0 to "OK", -1000 to "ERROR", -999 to "NOT_IMPLEMENTED", -998 to "ALLOC", -997 to "BAD_PARAMETER",
            -996 to "MUTEX", -995 to "INIT_MUTEX", -994 to "STATE", -993 to "BUFFER_SIZE",
            -2000 to "INIT", -1999 to "INIT_THREAD", -1998 to tr("INIT_ARNETWORKAL_MANAGER (no se alcanza el dispositivo)"),
            -1997 to "INIT_ARNETWORK_MANAGER", -1996 to "INIT_NETWORK_CONFIG", -1995 to "INIT_DEVICE_COPY",
            -1994 to "INIT_DEVICE_GET_NETWORK_CONFIG", -1993 to "INIT_DEVICE_JSON_CALLBACK", -1992 to "INIT_GET_DATE",
            -1991 to "INIT_GET_TIME", -1990 to "INIT_STREAM", -1989 to "INIT_SEMAPHORE", -1988 to "NOT_SENT",
            -1987 to "NO_VIDEO", -1986 to "NO_AUDIO", -1985 to "NO_ELEMENT", -1984 to "NO_ARGUMENTS",
            -1983 to tr("CANCELED (conexión cortada durante el arranque)"), -1982 to "COMMAND_GENERATING",
            -3000 to "COMMAND_CALLBACK", -2999 to "COMMAND_CALLBACK_ALREADY_REGISTERED", -2998 to "COMMAND_CALLBACK_NOT_REGISTERED",
            -6000 to "JNI", -5999 to "JNI_ENV", -5998 to "JNI_INIT", -7000 to "EXTENSION", -6999 to "EXTENSION_PRODUCT_NOT_VALID"
        )
        fun errName(e: ARCONTROLLER_ERROR_ENUM?): String =
            if (e == null) tr("desconocido") else (NATIVE_ERRORS[e.value] ?: trf("código %1\$s", e.value))
        const val REMOTE_PLAN_NAME = "flightPlan.mavlink"

        private val FLYING_STATES = arrayOf(
            tr("en tierra"), tr("despegando"), tr("en estacionario"), tr("volando"),
            tr("aterrizando"), tr("EMERGENCIA"), tr("esperando usuario"), tr("motores arrancando"),
            tr("aterrizaje de emergencia")
        )
        // Órdenes verificadas contra los enums de ARSDK 3.14.
        private val MAV_STATES = arrayOf(tr("EJECUTANDO"), tr("PARADO"), tr("PAUSADO"), tr("CARGADO (arranca al despegar)"))
        private val MAV_ERRORS = arrayOf(tr("ninguno"), tr("no está en modo exterior"), tr("sin fix GPS"), tr("magnetómetro sin calibrar"))
        private val COMPONENTS = arrayOf("GPS", tr("Calibración"), tr("Fichero de plan"), tr("Despegue"), tr("Dentro de geocerca"), tr("Cámara"))
        // «en curso» NO se traduce aquí: la app lo compara; se traduce al mostrarlo.
        private val NAV_HOME = arrayOf(tr("disponible"), "en curso", tr("no disponible"), tr("pendiente"))
        private val LIST_KEYS = setOf(
            "ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBRESULT",
            "ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_SESSIONSTATECHANGED",
            "ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_LIVESTITCHMASKCHANGED",
            "ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_LIVESTITCHLASTPICTUREIDCHANGED",
            "ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES",
            "ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_MASSSTORAGECONTENT")
        private val NAV_HOME_REASON = arrayOf(tr("pedido por el usuario"), tr("enlace perdido"), tr("BATERÍA BAJA"), tr("terminado"), tr("detenido"), tr("desactivado por el dron"), tr("activado por el dron"))
        private val ALERTS = arrayOf(tr("sin alerta"), tr("emergencia"), tr("corte de motores"), tr("BATERÍA CRÍTICA"), tr("batería baja"), tr("inclinación excesiva"))
        private val SENSORS = arrayOf("IMU", tr("Barómetro"), tr("Ultrasonidos"), "GPS", tr("Magnetómetro"), tr("Cámara vertical"))
        val MOTOR_ERRORS = arrayOf(tr("sin error"), "EEPROM", tr("motor bloqueado"), tr("seguridad de hélice"), tr("comunicación con motor"),
            tr("parada de emergencia RC"), tr("tiempo real del controlador"), tr("ajustes de motor"), tr("temperatura"), tr("voltaje de batería"),
            tr("número de celdas"), tr("MOSFET / fases del motor"), "HAL", tr("aserción BLDC"))
    }

    // ---------------------------------------------------------------- conexión

    /** Fuerza que el tráfico (también el nativo) salga por la WiFi del dron y no por datos móviles. */
    private fun bindToWifi(): Boolean {
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifi: Network? = null
        for (n in cm.allNetworks) {
            val caps = cm.getNetworkCapabilities(n) ?: continue
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) { wifi = n; break }
        }
        if (wifi == null) { log(tr("No hay red WiFi activa. Conecta el móvil a la WiFi del dron/mando.")); return false }
        val ok = cm.bindProcessToNetwork(wifi)
        log(if (ok) tr("Tráfico ligado a la WiFi (los datos móviles no interfieren).") else tr("No se pudo ligar el tráfico a la WiFi."))
        return ok
    }

    override fun connect(choice: ConnectChoice) { connect(choice.arsdkMode ?: return) }

    fun connect(m: Mode) {
        if (controller != null || connecting) { log(tr("Ya hay una conexión abierta o en curso.")); return }
        connecting = true
        telemetry.connection = tr("conectando…")
        listener.onTelemetry(telemetry)
        mode = m
        if (!loadLibs()) { log(tr("ERROR: no se cargaron las librerías ARSDK.")); connectFailed(); return }
        val mux = if (m.usb) UsbMux.ensure(ctx) else null
        if (m.usb && mux == null) { connectFailed(); return }
        if (m.usb) {
            (ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).bindProcessToNetwork(null)
            log(tr("Conectando por USB al SkyController 2 …"))
        } else {
            bindToWifi()
            log(trf("Conectando a %1\$s en %2\$s:44444 …", m.label, m.ip))
        }
        Thread {
            try {
                Crumbs.write("connect: ARDiscoveryDevice ${m.name}")
                val device = ARDiscoveryDevice()
                Crumbs.write("connect: init ${if (mux != null) "USB" else "WiFi"}")
                val err = if (mux != null) device.initUSB(m.product, mux)
                          else device.initWifi(m.product, "racoon-mapper", m.ip, 44444)
                Crumbs.write("connect: init → $err")
                if (err != ARDISCOVERY_ERROR_ENUM.ARDISCOVERY_OK) {
                    log(trf("ERROR discovery: %1\$s", err)); device.dispose(); connectFailed(); return@Thread
                }
                Crumbs.write("connect: new ARDeviceController")
                val c = ARDeviceController(device)
                device.dispose()
                c.addListener(controllerListener)
                c.addStreamListener(streamListener)
                controller = c
                Crumbs.write("connect: start()")
                val e = c.start()
                Crumbs.write("connect: start → ${errName(e)}")
                if (e != ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK) {
                    log(trf("ERROR al arrancar el controlador: %1\$s", errName(e)))
                    controller = null
                    c.dispose()
                    connectFailed()
                }
            } catch (t: Throwable) {
                log(trf("ERROR conectando: %1\$s: %2\$s", t.javaClass.simpleName, t.message))
                controller = null
                connectFailed()
            }
        }.start()
    }

    private fun connectFailed() {
        main.post {
            connecting = false
            telemetry.connection = "desconectado"
            telemetry.connected = false
            listener.onTelemetry(telemetry)
        }
    }

    override fun disconnect() {
        val c = controller
        if (c == null) {
            if (connecting) log(tr("Conexión en curso; espera unos segundos y vuelve a pulsar."))
            return
        }
        log(tr("Desconectando…"))
        Thread { try { c.stop() } catch (t: Throwable) { log(trf("stop(): %1\$s", t.message)) } }.start()
    }

    private fun releaseController() {
        val c = controller ?: return
        controller = null
        Thread { try { c.dispose() } catch (_: Throwable) {} }.start()
    }

    override val isConnected get() = telemetry.connected && controller != null

    // ---------------------------------------------------------------- plan

    /** Sube el plan por FTP (puerto 61 o 161 vía SkyController) como "flightPlan.mavlink". */
    override fun uploadPlan(plan: FlightPlan, done: (Boolean) -> Unit) {
        if (uploading) { log(tr("Ya hay una subida en curso.")); return }
        if (!loadLibs()) { log(tr("ERROR: ARSDK no cargado.")); done(false); return }
        uploading = true
        val local = File(ctx.cacheDir, REMOTE_PLAN_NAME)
        local.writeText(plan.text)
        val m = mode
        log(if (m.usb) tr("Subiendo plan por USB (vía SkyController) …") else trf("Subiendo plan a %1\$s:%2\$s …", m.ip, m.ftpPort))
        Thread {
            var ok = false
            val ftp = ARUtilsManager()
            val dt = ARDataTransferManager()
            try {
                val e = if (m.usb) {
                    val mux = UsbMux.mux ?: throw IllegalStateException(tr("canal USB cerrado"))
                    val ref = mux.newMuxRef()
                    try { ftp.initWifiFtp(ref, "drone", m.ftpPort, ARUtilsManager.FTP_ANONYMOUS, "") } finally { ref.release() }
                } else ftp.initWifiFtp(m.ip, m.ftpPort, ARUtilsManager.FTP_ANONYMOUS, "")
                if (e != ARUTILS_ERROR_ENUM.ARUTILS_OK) {
                    log(trf("ERROR FTP init: %1\$s", e))
                } else {
                    val progress = java.util.concurrent.atomic.AtomicReference(0f)
                    val result = java.util.concurrent.atomic.AtomicReference<ARDATATRANSFER_ERROR_ENUM?>(null)
                    val prog = ARDataTransferUploaderProgressListener { _, p -> progress.set(p) }
                    val comp = ARDataTransferUploaderCompletionListener { _, r -> result.set(r) }
                    val up = dt.arDataTransferUploader
                    up.createUploader(ftp, REMOTE_PLAN_NAME, local.absolutePath, prog, null, comp, null,
                        ARDATATRANSFER_UPLOADER_RESUME_ENUM.ARDATATRANSFER_UPLOADER_RESUME_FALSE)
                    val th = Thread(up.uploaderRunnable)
                    th.start(); th.join(30_000)
                    var timedOut = false
                    if (th.isAlive) {
                        timedOut = true
                        up.cancelThread()
                        log(tr("ERROR: subida sin respuesta en 30 s, cancelando…"))
                        th.join() // nunca liberar objetos nativos con el hilo aún dentro (como Pix4D)
                    }
                    up.dispose()
                    // Mismo criterio que Pix4D: vale si OK o si llegó al 100 %.
                    ok = !timedOut && (result.get() == ARDATATRANSFER_ERROR_ENUM.ARDATATRANSFER_OK || progress.get() >= 100f)
                    log(if (ok) trf("Plan subido (%1\$s bytes).", local.length()) else trf("ERROR subida: %1\$s (progreso %2\$s %%)", result.get(), progress.get().toInt()))
                    ftp.closeWifiFtp()
                }
            } catch (t: Throwable) {
                log(trf("ERROR subida: %1\$s: %2\$s", t.javaClass.simpleName, t.message))
            } finally {
                try { dt.dispose() } catch (_: Throwable) {}
                try { ftp.dispose() } catch (_: Throwable) {}
                uploading = false
                main.post { done(ok) }
            }
        }.start()
    }

    // ---------------------------------------------------------------- órdenes

    private fun d3(): ARFeatureARDrone3? = controller?.featureARDrone3
    private fun common(): ARFeatureCommon? = controller?.featureCommon

    private fun check(what: String, e: ARCONTROLLER_ERROR_ENUM?) {
        if (e == null) log(trf("%1\$s: sin controlador", what))
        else if (e != ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK) log("$what → ${errName(e)}") else log("$what ✓")
    }

    /** Secuencia de lanzamiento (la de Pix4Dcapture, sin tocar la cámara frontal). */
    /** Se incrementa con cada MissionItemExecuted recibido: así se sabe que el índice es de ESTE vuelo. */
    @Volatile override var missionItemEvents = 0
        private set

    override fun startMission() {
        val d = d3(); val c = common()
        if (d == null || c == null) { log(tr("No conectado.")); return }
        telemetry.missionItem = -1 // no arrastrar el índice de un vuelo anterior
        check(tr("Reset home"), d.sendGPSSettingsResetHome())
        check(tr("Home = punto de despegue"), d.sendGPSSettingsHomeType(ARCOMMANDS_ARDRONE3_GPSSETTINGS_HOMETYPE_TYPE_ENUM.ARCOMMANDS_ARDRONE3_GPSSETTINGS_HOMETYPE_TYPE_TAKEOFF))
        // Como Pix4D: estos límites solo para la Bluegrass (CHIMERA), también detrás del SkyController.
        val drone = if (mode.product == ARDISCOVERY_PRODUCT_ENUM.ARDISCOVERY_PRODUCT_SKYCONTROLLER_2) extensionProduct else mode.product
        if (drone == ARDISCOVERY_PRODUCT_ENUM.ARDISCOVERY_PRODUCT_CHIMERA) {
            check(tr("Vel. vertical máx 2,5 m/s"), d.sendSpeedSettingsMaxVerticalSpeed(2.5f))
            check(tr("Vel. vertical autónoma 2,5 m/s"), d.sendPilotingSettingsSetAutonomousFlightMaxVerticalSpeed(2.5f))
        }
        check(tr("App pilotando"), c.sendControllerIsPiloting(1.toByte()))
        check(tr("INICIAR plan"), c.sendMavlinkStart(REMOTE_PLAN_NAME, ARCOMMANDS_COMMON_MAVLINK_START_TYPE_ENUM.ARCOMMANDS_COMMON_MAVLINK_START_TYPE_FLIGHTPLAN))
    }

    /** Tras una pausa, Start reanuda el plan. */
    override fun resumeMission() = check(tr("Reanudar plan"), common()?.sendMavlinkStart(REMOTE_PLAN_NAME, ARCOMMANDS_COMMON_MAVLINK_START_TYPE_ENUM.ARCOMMANDS_COMMON_MAVLINK_START_TYPE_FLIGHTPLAN))
    override fun pauseMission() = check(tr("Pausar plan"), common()?.sendMavlinkPause())
    override fun stopMission() = check(tr("Parar plan"), common()?.sendMavlinkStop())
    override fun returnHome() = check(tr("Volver a casa"), d3()?.sendPilotingNavigateHome(1.toByte()))
    override fun cancelReturnHome() = check(tr("Cancelar vuelta a casa"), d3()?.sendPilotingNavigateHome(0.toByte()))
    override fun setReturnHomeOnDisconnect(on: Boolean) =
        check(if (on) tr("Enlace perdido → volver a casa") else tr("Enlace perdido → seguir el plan"),
            common()?.sendFlightPlanSettingsReturnHomeOnDisconnect((if (on) 1 else 0).toByte()))
    /** Calibración radiométrica de la Sequoia (cam_id 0), igual que Pix4Dcapture. */
    override fun cameraCalibration() = radiometricCalibration()

    fun radiometricCalibration() {
        val sc = controller?.featureSequoiaCam
        if (sc == null) { log(tr("La Sequoia no está disponible en este dron/conexión.")); return }
        telemetry.radioCalibResult = -1
        check(tr("Calibración radiométrica"), sc.sendRadiometricCalibStart(0.toByte()))
    }
    override fun land() = check(tr("Aterrizar"), d3()?.sendPilotingLanding())
    override fun emergency() = check(tr("EMERGENCIA (corte de motores)"), d3()?.sendPilotingEmergency())

    // ---------------------------------------------------------------- vídeo en directo

    override val supportsVideo = true
    @Volatile override var videoSink: VideoSink? = null
    @Volatile private var videoWanted = false
    @Volatile private var videoRunning = false

    /** Un único hilo pone el flujo en el estado pedido: así encender/apagar rápido nunca se descuadra.
     *  Sin hilos fijos: el hilo muere a los 10 s sin trabajo (no se acumulan al recrear el enlace). */
    private val videoExec = java.util.concurrent.ThreadPoolExecutor(0, 1, 10, java.util.concurrent.TimeUnit.SECONDS,
        java.util.concurrent.LinkedBlockingQueue())

    private fun modelOf(p: ARDISCOVERY_PRODUCT_ENUM?) = when (p) {
        ARDISCOVERY_PRODUCT_ENUM.ARDISCOVERY_PRODUCT_CHIMERA -> 1
        ARDISCOVERY_PRODUCT_ENUM.ARDISCOVERY_PRODUCT_EVINRUDE -> 2
        else -> 0
    }

    /** Con el SkyController 2, «conectado» es el mando; el dron va aparte (extensión). */
    private val viaController get() = mode.usb || mode.product == ARDISCOVERY_PRODUCT_ENUM.ARDISCOVERY_PRODUCT_SKYCONTROLLER_2

    override fun setVideoEnabled(on: Boolean) {
        videoWanted = on
        syncVideo()
    }

    /** [force]: tras conectar/enlazar no se sabe en qué estado está el flujo, así que se manda la orden igualmente. */
    private fun syncVideo(force: Boolean = false) {
        videoExec.execute {
            try {
                val c = controller
                if (c == null || !telemetry.connected) { videoRunning = false; return@execute }
                if (videoWanted && (force || !videoRunning)) {
                    if (!c.hasOutputVideoStream()) Crumbs.write("video: hasOutputVideoStream=false (se intenta igual)")
                    val e = c.startVideoStream()
                    val ok = e == ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK
                    if (ok || !videoRunning) log(if (ok) tr("Vídeo en directo activado") else trf("No se pudo iniciar el vídeo: %1\$s", errName(e)))
                    videoRunning = ok || videoRunning
                } else if (!videoWanted && (force || videoRunning)) {
                    c.stopVideoStream()
                    if (videoRunning) log(tr("Vídeo en directo desactivado (ahorra enlace)"))
                    videoRunning = false
                }
            } catch (t: Throwable) { log(trf("Vídeo: %1\$s: %2\$s", t.javaClass.simpleName, t.message)) }
        }
    }

    /** Fotogramas H.264 del SDK → VideoView. Hilo nativo: nunca dejar escapar excepciones. */
    private val streamListener = object : ARDeviceControllerStreamListener {
        override fun configureDecoder(dc: ARDeviceController?, codec: ARControllerCodec?): ARCONTROLLER_ERROR_ENUM {
            try {
                if (codec?.type == ARCONTROLLER_STREAM_CODEC_TYPE_ENUM.ARCONTROLLER_STREAM_CODEC_TYPE_H264) {
                    val h = codec.asH264
                    val sps = h.sps?.byteData; val pps = h.pps?.byteData
                    if (sps != null && pps != null) videoSink?.configure(sps, pps)
                    Crumbs.write("video: H264 configurado (sps ${sps?.size} B, pps ${pps?.size} B)")
                } else Crumbs.write("video: códec no soportado ${codec?.type}")
            } catch (t: Throwable) { Crumbs.write("video configure EXC ${t.javaClass.simpleName}: ${t.message}") }
            return ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK
        }

        override fun onFrameReceived(dc: ARDeviceController?, frame: ARFrame?): ARCONTROLLER_ERROR_ENUM {
            try {
                val sink = videoSink
                if (frame != null && sink != null) sink.frame(frame.byteData, frame.dataSize, frame.isIFrame)
            } catch (t: Throwable) { /* un fotograma perdido no importa */ }
            return ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK
        }

        override fun onFrameTimeout(dc: ARDeviceController?) {}
    }

    // ---------------------------------------------------------------- telemetría

    private val controllerListener = object : ARDeviceControllerListener {
        override fun onStateChanged(dc: ARDeviceController?, state: ARCONTROLLER_DEVICE_STATE_ENUM?, error: ARCONTROLLER_ERROR_ENUM?) {
            Crumbs.write("state ${state?.name} err=${errName(error)}")
            main.post {
                telemetry.connection = when (state) {
                    ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_RUNNING -> "CONECTADO"
                    ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_STARTING -> tr("conectando…")
                    ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_STOPPING -> tr("desconectando…")
                    ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_STOPPED -> "desconectado"
                    else -> state.toString()
                }
                telemetry.connected = state == ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_RUNNING
                // El SDK puede arrancar el vídeo él solo al conectar: lo damos por encendido y lo ajustamos a lo pedido
                // (si está el mapa a la vista, se apaga para no cargar el enlace).
                if (state == ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_RUNNING) {
                    if (!viaController) { telemetry.droneLinked = true; telemetry.droneModel = modelOf(mode.product) }
                    syncVideo(force = true)
                }
                if (state == ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_RUNNING ||
                    state == ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_STOPPED) connecting = false
                if (error != null && error != ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK) {
                    listener.onLog(trf("Estado %1\$s (error %2\$s)", telemetry.connection, errName(error)))
                    if (error.value == -1983 && !mode.usb && mode.product != ARDISCOVERY_PRODUCT_ENUM.ARDISCOVERY_PRODUCT_SKYCONTROLLER_2)
                        listener.onLog(tr("PISTA: el dron aceptó y luego cortó. Suele pasar si el SkyController está encendido y enlazado: ") +
                            tr("el dron solo atiende a un mando. Apaga el SkyController o usa el modo «cable USB»."))
                }
                else listener.onLog(trf("Estado: %1\$s", telemetry.connection))
                if (state == ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_STOPPED) {
                    releaseController()
                    resetTelemetry()
                    syncVideo() // el hilo de vídeo ve que no hay controlador y marca el flujo como parado
                }
                listener.onTelemetry(telemetry)
            }
        }

        override fun onExtensionStateChanged(dc: ARDeviceController?, state: ARCONTROLLER_DEVICE_STATE_ENUM?, product: ARDISCOVERY_PRODUCT_ENUM?, name: String?, error: ARCONTROLLER_ERROR_ENUM?) {
            extensionProduct = product
            main.post {
                // Dron desenlazado del mando (p. ej. cambio de batería): su índice de misión ya no vale.
                val linked = state == ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_RUNNING
                if (!linked) {
                    telemetry.missionItem = -1
                    // Lo último que se supo del dron ya no vale (batería incluida): nada debe decidirse con ello.
                    telemetry.droneLinked = false; telemetry.battery = -1
                } else {
                    telemetry.droneLinked = true
                    telemetry.droneModel = modelOf(product)
                    syncVideo(force = true) // el dron recién enlazado arranca con su vídeo por defecto
                }
                telemetry.extension = "${name ?: product?.name} · ${if (state == ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_RUNNING) "enlazado" else state?.name}" +
                    if (error != null && error != ARCONTROLLER_ERROR_ENUM.ARCONTROLLER_OK) " (${errName(error)})" else ""
                listener.onLog(trf("Dron vía mando: %1\$s", telemetry.extension))
                listener.onTelemetry(telemetry)
            }
        }

        override fun onCommandReceived(dc: ARDeviceController?, key: ARCONTROLLER_DICTIONARY_KEY_ENUM?, dict: ARControllerDictionary?) {
            if (key == null || dict == null) return
            try {
                val argKeys = argKeys[key] ?: return
                // Ojo: en ARSDK keySet()/entrySet() devuelven null y get(SINGLE_KEY) nunca es null.
                // Se leen solo los argumentos conocidos, con get(clave), aquí en el hilo nativo
                // (el diccionario no sobrevive al callback). Mismo criterio de listas que Pix4D.
                // Claves que llegan como lista aunque no se llamen *LISTCHANGED (mismo criterio que Pix4D).
                val isList = key.name.contains("LISTCHANGED") || key.name in LIST_KEYS
                val sources: Collection<ARControllerArgumentDictionary<Any>?> =
                    if (isList) dict.values ?: emptyList()
                    else listOf(dict.get(ARControllerDictionary.ARCONTROLLER_DICTIONARY_SINGLE_KEY))
                val rows = ArrayList<Map<String, Any?>>()
                for (a in sources) {
                    if (a == null) continue
                    val m = HashMap<String, Any?>()
                    for (k in argKeys) m[k] = a.get(k)
                    if (m.values.any { it != null }) rows.add(m)
                }
                if (rows.isEmpty()) return
                main.post { handle(key, if (isList) null else rows[0], rows) }
            } catch (t: Throwable) {
                // Nunca dejar escapar una excepción hacia JNI.
                log(trf("telemetría (%1\$s): %2\$s", key.name, t.javaClass.simpleName))
            }
        }
    }

    /** Argumentos que leemos de cada comando. Perezoso: las claves se inicializan tras cargar ARSDK. */
    private val argKeys: Map<ARCONTROLLER_DICTIONARY_KEY_ENUM, List<String>> by lazy {
        mapOf(
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_BATTERYSTATECHANGED to listOf(ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_BATTERYSTATECHANGED_PERCENT),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SKYCONTROLLERSTATE_BATTERYCHANGED to listOf(ARFeatureSkyController.ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SKYCONTROLLERSTATE_BATTERYCHANGED_PERCENT),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_PRODUCTNAMECHANGED to listOf(ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_PRODUCTNAMECHANGED_NAME),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_GPSFIXSTATECHANGED to listOf(ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_GPSFIXSTATECHANGED_FIXED),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSTATE_NUMBEROFSATELLITECHANGED to listOf(ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSTATE_NUMBEROFSATELLITECHANGED_NUMBEROFSATELLITE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_FLYINGSTATECHANGED to listOf(ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_FLYINGSTATECHANGED_STATE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ALTITUDECHANGED to listOf(ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ALTITUDECHANGED_ALTITUDE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_POSITIONCHANGED to listOf(
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_POSITIONCHANGED_LATITUDE,
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_POSITIONCHANGED_LONGITUDE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ATTITUDECHANGED to listOf(
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ATTITUDECHANGED_YAW),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_SPEEDCHANGED to listOf(
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_SPEEDCHANGED_SPEEDX,
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_SPEEDCHANGED_SPEEDY),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_NAVIGATEHOMESTATECHANGED to listOf(
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_NAVIGATEHOMESTATECHANGED_STATE,
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_NAVIGATEHOMESTATECHANGED_REASON),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES to listOf(
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES_ID,
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES_ACCESSORY_TYPE,
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES_UID,
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES_SWVERSION),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBSTATUS to listOf(
                ARFeatureSequoiaCam.ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBSTATUS_VALUE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBRESULT to listOf(
                ARFeatureSequoiaCam.ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBRESULT_VALUE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ALERTSTATECHANGED to listOf(ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ALERTSTATECHANGED_STATE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_WINDSTATECHANGED to listOf(ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_WINDSTATECHANGED_STATE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_RETURNHOMEBATTERYCAPACITY to listOf(ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_RETURNHOMEBATTERYCAPACITY_STATUS),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_MOTORERRORSTATECHANGED to listOf(ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_MOTORERRORSTATECHANGED_MOTORERROR),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSETTINGSSTATE_RETURNHOMEONDISCONNECTCHANGED to listOf(
                ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSETTINGSSTATE_RETURNHOMEONDISCONNECTCHANGED_STATE,
                ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSETTINGSSTATE_RETURNHOMEONDISCONNECTCHANGED_ISREADONLY),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_SENSORSSTATESLISTCHANGED to listOf(
                ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_SENSORSSTATESLISTCHANGED_SENSORNAME,
                ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_SENSORSSTATESLISTCHANGED_SENSORSTATE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKFILEPLAYINGSTATECHANGED to listOf(
                ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKFILEPLAYINGSTATECHANGED_STATE,
                ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKFILEPLAYINGSTATECHANGED_FILEPATH),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKPLAYERRORSTATECHANGED to listOf(ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKPLAYERRORSTATECHANGED_ERROR),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MISSIONITEMEXECUTED to listOf(ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MISSIONITEMEXECUTED_IDX),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_AVAILABILITYSTATECHANGED to listOf(ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_AVAILABILITYSTATECHANGED_AVAILABILITYSTATE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_COMPONENTSTATELISTCHANGED to listOf(
                ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_COMPONENTSTATELISTCHANGED_COMPONENT,
                ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_COMPONENTSTATELISTCHANGED_STATE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_CALIBRATIONSTATE_MAGNETOCALIBRATIONREQUIREDSTATE to listOf(ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_CALIBRATIONSTATE_MAGNETOCALIBRATIONREQUIREDSTATE_REQUIRED),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_LINKSIGNALQUALITY to listOf(ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_LINKSIGNALQUALITY_VALUE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_WIFISIGNALCHANGED to listOf(ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_WIFISIGNALCHANGED_RSSI),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_WIFISIGNALCHANGED to listOf(ARFeatureSkyController.ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_WIFISIGNALCHANGED_LEVEL),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_HOMECHANGED to listOf(
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_HOMECHANGED_LATITUDE,
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_HOMECHANGED_LONGITUDE),
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_RESETHOMECHANGED to listOf(
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_RESETHOMECHANGED_LATITUDE,
                ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_RESETHOMECHANGED_LONGITUDE)
        )
    }

    private fun num(m: Map<String, Any?>?, k: String): Number? = m?.get(k) as? Number

    private fun handle(key: ARCONTROLLER_DICTIONARY_KEY_ENUM, s: Map<String, Any?>?, all: List<Map<String, Any?>>) {
        val t = telemetry
        when (key) {
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_LINKSIGNALQUALITY ->
                num(s, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_LINKSIGNALQUALITY_VALUE)?.let { v ->
                    val q = v.toInt() and 0x0F; if (q in 1..5) t.linkQuality = q }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_WIFISIGNALCHANGED ->
                num(s, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_WIFISIGNALCHANGED_RSSI)?.let { v ->
                    val r = v.toInt(); if (r in -120..-1) t.rssi = r }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_WIFISIGNALCHANGED ->
                num(s, ARFeatureSkyController.ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_WIFISTATE_WIFISIGNALCHANGED_LEVEL)?.let { v ->
                    val q = v.toInt(); if (q in 1..5) t.linkQuality = q }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_HOMECHANGED,
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_RESETHOMECHANGED -> {
                val home = key == ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_HOMECHANGED
                val la = num(s, if (home) ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_HOMECHANGED_LATITUDE
                                else ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_RESETHOMECHANGED_LATITUDE)?.toDouble()
                val lo = num(s, if (home) ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_HOMECHANGED_LONGITUDE
                                else ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_RESETHOMECHANGED_LONGITUDE)?.toDouble()
                // 500 = el dron aún no tiene casa; 0,0 tampoco vale.
                if (la != null && lo != null && Math.abs(la) <= 90 && Math.abs(lo) <= 180 && !(la == 0.0 && lo == 0.0)) { t.homeLat = la; t.homeLon = lo }
            }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_BATTERYSTATECHANGED ->
                num(s, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_BATTERYSTATECHANGED_PERCENT)?.let { t.battery = it.toInt(); t.droneLinked = true }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SKYCONTROLLERSTATE_BATTERYCHANGED ->
                num(s, ARFeatureSkyController.ARCONTROLLER_DICTIONARY_KEY_SKYCONTROLLER_SKYCONTROLLERSTATE_BATTERYCHANGED_PERCENT)?.let { t.controllerBattery = it.toInt() }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_PRODUCTNAMECHANGED ->
                (s?.get(ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_SETTINGSSTATE_PRODUCTNAMECHANGED_NAME) as? String)?.let {
                    t.productName = it
                    // WiFi directa: el modelo lo decía el menú; si el nombre del dron dice otra cosa, manda el nombre.
                    val byName = when { it.contains("disco", true) -> 2; it.contains("bluegrass", true) -> 1; else -> 0 }
                    if (byName != 0 && byName != t.droneModel) {
                        if (t.droneModel != 0) log(trf("⚠ El dron se llama «%1\$s»: se trata como %2\$s aunque el modo elegido decía otra cosa", it, if (byName == 2) "Disco" else "Bluegrass"))
                        t.droneModel = byName
                    }
                }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_GPSFIXSTATECHANGED ->
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSETTINGSSTATE_GPSFIXSTATECHANGED_FIXED)?.let { t.gpsFix = it.toInt() == 1 }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSTATE_NUMBEROFSATELLITECHANGED ->
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_GPSSTATE_NUMBEROFSATELLITECHANGED_NUMBEROFSATELLITE)?.let { t.satellites = it.toInt() }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_FLYINGSTATECHANGED ->
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_FLYINGSTATECHANGED_STATE)?.toInt()?.let {
                    t.flyingStateRaw = it
                    t.flyingState = FLYING_STATES.getOrElse(it) { "estado $it" }
                    log(trf("Vuelo: %1\$s", t.flyingState))
                }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ALTITUDECHANGED ->
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ALTITUDECHANGED_ALTITUDE)?.let { t.altitude = it.toDouble() }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_POSITIONCHANGED -> {
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_POSITIONCHANGED_LATITUDE)?.let { t.lat = it.toDouble() }
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_POSITIONCHANGED_LONGITUDE)?.let { t.lon = it.toDouble() }
            }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ATTITUDECHANGED -> {
                // Llega a ~5 Hz: solo redibujar si el rumbo cambia de forma apreciable.
                val y = num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ATTITUDECHANGED_YAW)?.toDouble() ?: return
                val deg = (Math.toDegrees(y) + 360.0) % 360.0
                if (!t.yawDeg.isNaN() && Math.abs(((deg - t.yawDeg + 540.0) % 360.0) - 180.0) < 3.0) return
                t.yawDeg = deg
            }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_SPEEDCHANGED -> {
                val x = num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_SPEEDCHANGED_SPEEDX)?.toDouble() ?: 0.0
                val y = num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_SPEEDCHANGED_SPEEDY)?.toDouble() ?: 0.0
                t.speed = Math.sqrt(x * x + y * y)
            }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_NAVIGATEHOMESTATECHANGED ->
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_NAVIGATEHOMESTATECHANGED_STATE)?.toInt()?.let {
                    t.navigateHome = NAV_HOME.getOrElse(it) { "estado $it" }
                    num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_NAVIGATEHOMESTATECHANGED_REASON)?.toInt()?.let { r -> t.navHomeReason = r }
                    log(trf("Vuelta a casa: %1\$s", tr(t.navigateHome)) + if (t.navHomeReason >= 0) " (${NAV_HOME_REASON.getOrElse(t.navHomeReason) { "motivo ${t.navHomeReason}" }})" else "")
                }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES -> {
                // Lista de accesorios que ve el dron. Tipo: 0 = Sequoia, 1 = FLIR (enum ARSDK).
                val txt = all.joinToString(", ") { e ->
                    val type = e[ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES_ACCESSORY_TYPE]
                    val tn = when (type?.toString()?.toIntOrNull() ?: (type as? Enum<*>)?.ordinal) { 0 -> tr("Sequoia"); 1 -> "FLIR"; else -> "accesorio ${type}" }
                    val fw = e[ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_ACCESSORYSTATE_CONNECTEDACCESSORIES_SWVERSION]
                    tn + (if (fw != null && fw.toString().isNotBlank()) trf(" · fw %1\$s", fw) else "")
                }
                if (txt != t.accessories) log(trf("Accesorios que ve el dron: %1\$s", txt.ifEmpty { "ninguno" }))
                t.accessories = txt
                if (txt.contains("Sequoia")) t.sequoiaSeen = true
            }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBSTATUS ->
                num(s, ARFeatureSequoiaCam.ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBSTATUS_VALUE)?.toInt()?.let {
                    if (!t.sequoiaSeen) log(tr("El dron maneja la Sequoia (responde a su estado de calibración)"))
                    t.sequoiaSeen = true
                    if (it != t.radioCalibStatus) log(tr("Calibración Sequoia: ") + arrayOf("disponible", tr("no disponible"), tr("EN CURSO…")).getOrElse(it) { "estado $it" })
                    t.radioCalibStatus = it
                }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBRESULT -> {
                for (e in all) num(e, ARFeatureSequoiaCam.ARCONTROLLER_DICTIONARY_KEY_SEQUOIA_CAM_RADIOMETRICCALIBRESULT_VALUE)?.toInt()?.let {
                    t.radioCalibResult = it
                    log(if (it == 0) tr("✔ Calibración radiométrica correcta") else tr("✘ Calibración radiométrica FALLIDA"))
                }
            }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ALERTSTATECHANGED ->
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_ALERTSTATECHANGED_STATE)?.toInt()?.let {
                    if (it != t.alert) log(trf("Alerta del dron: %1\$s", ALERTS.getOrElse(it) { "código $it" }))
                    t.alert = it
                }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_WINDSTATECHANGED ->
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_WINDSTATECHANGED_STATE)?.toInt()?.let {
                    if (it != t.wind && it > 0) log(if (it == 1) tr("Viento fuerte") else tr("Viento DEMASIADO fuerte"))
                    t.wind = it
                }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_RETURNHOMEBATTERYCAPACITY ->
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_PILOTINGSTATE_RETURNHOMEBATTERYCAPACITY_STATUS)?.toInt()?.let { t.rthBattery = it }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_MOTORERRORSTATECHANGED ->
                num(s, ARFeatureARDrone3.ARCONTROLLER_DICTIONARY_KEY_ARDRONE3_SETTINGSSTATE_MOTORERRORSTATECHANGED_MOTORERROR)?.toInt()?.let {
                    if (it != t.motorError && it != 0) log(trf("ERROR DE MOTOR: %1\$s", MOTOR_ERRORS.getOrElse(it) { "código $it" }))
                    t.motorError = it
                }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSETTINGSSTATE_RETURNHOMEONDISCONNECTCHANGED -> {
                num(s, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSETTINGSSTATE_RETURNHOMEONDISCONNECTCHANGED_STATE)?.toInt()?.let { t.rthOnDisconnect = it }
                num(s, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSETTINGSSTATE_RETURNHOMEONDISCONNECTCHANGED_ISREADONLY)?.toInt()?.let { t.rthOnDisconnectReadOnly = it == 1 }
            }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_SENSORSSTATESLISTCHANGED -> {
                for (e in all) {
                    val n = num(e, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_SENSORSSTATESLISTCHANGED_SENSORNAME)?.toInt() ?: continue
                    val st = num(e, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_COMMONSTATE_SENSORSSTATESLISTCHANGED_SENSORSTATE)?.toInt() ?: continue
                    t.sensors[SENSORS.getOrElse(n) { "sensor $n" }] = st == 1
                }
            }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKFILEPLAYINGSTATECHANGED ->
                num(s, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKFILEPLAYINGSTATECHANGED_STATE)?.toInt()?.let {
                    t.planStateRaw = it
                    t.planState = MAV_STATES.getOrElse(it) { "estado $it" }
                    t.planPlaying = it == 0
                    log(trf("Plan: %1\$s (%2\$s)", t.planState, s?.get(ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKFILEPLAYINGSTATECHANGED_FILEPATH) ?: ""))
                }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKPLAYERRORSTATECHANGED ->
                num(s, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MAVLINKPLAYERRORSTATECHANGED_ERROR)?.toInt()?.let {
                    t.planError = if (it == 0) "" else MAV_ERRORS.getOrElse(it) { "error $it" }
                    if (it != 0) log(trf("¡El dron rechaza el plan: %1\$s!", t.planError))
                }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MISSIONITEMEXECUTED ->
                num(s, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_MAVLINKSTATE_MISSIONITEMEXECUTED_IDX)?.let { t.missionItem = it.toInt(); missionItemEvents++ }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_AVAILABILITYSTATECHANGED ->
                num(s, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_AVAILABILITYSTATECHANGED_AVAILABILITYSTATE)?.let {
                    t.planAvailable = it.toInt() == 1
                }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_COMPONENTSTATELISTCHANGED -> {
                val entries = if (s != null) listOf(s) else all
                for (e in entries) {
                    val comp = num(e, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_COMPONENTSTATELISTCHANGED_COMPONENT)?.toInt() ?: continue
                    val st = num(e, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_FLIGHTPLANSTATE_COMPONENTSTATELISTCHANGED_STATE)?.toInt() ?: continue
                    t.components[COMPONENTS.getOrElse(comp) { trf("Componente %1\$s", comp) }] = st == 1
                }
            }
            ARCONTROLLER_DICTIONARY_KEY_ENUM.ARCONTROLLER_DICTIONARY_KEY_COMMON_CALIBRATIONSTATE_MAGNETOCALIBRATIONREQUIREDSTATE ->
                num(s, ARFeatureCommon.ARCONTROLLER_DICTIONARY_KEY_COMMON_CALIBRATIONSTATE_MAGNETOCALIBRATIONREQUIREDSTATE_REQUIRED)?.let {
                    t.magnetoCalibrationRequired = it.toInt() == 1
                }
            else -> return
        }
        listener.onTelemetry(t)
    }

    private fun resetTelemetry() {
        val t = telemetry
        t.battery = -1; t.controllerBattery = -1; t.gpsFix = false; t.satellites = -1; t.droneLinked = false; t.droneModel = 0
        t.accessories = ""; t.sequoiaSeen = false
        t.flyingState = "—"; t.flyingStateRaw = -1; t.altitude = Double.NaN; t.lat = 500.0; t.lon = 500.0
        t.speed = Double.NaN; t.yawDeg = Double.NaN; t.linkQuality = -1; t.rssi = 0; t.homeLat = 500.0; t.homeLon = 500.0; t.planState = "—"; t.planStateRaw = -1; t.planPlaying = false; t.planError = ""
        t.missionItem = -1; t.magnetoCalibrationRequired = null; t.planAvailable = null
        t.alert = -1; t.wind = -1; t.navHomeReason = -1; t.rthBattery = -1; t.motorError = 0
        t.rthOnDisconnect = -1; t.rthOnDisconnectReadOnly = false; t.sensors.clear()
        t.radioCalibStatus = -1; t.radioCalibResult = -1
        t.components.clear(); t.navigateHome = "—"; t.extension = ""; t.productName = ""
        extensionProduct = null
    }

    private fun log(msg: String) {
        Crumbs.write("log: $msg")
        if (Looper.myLooper() == Looper.getMainLooper()) listener.onLog(msg) else main.post { listener.onLog(msg) }
    }
}
