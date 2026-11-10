package com.racoonair.mapper

/**
 * Enlace con un dron, independiente del SDK:
 *  - DroneLink: ARSDK3 (Bluegrass, Disco, vía SkyController 2 o WiFi).
 *  - AnafiLink: GroundSDK (ANAFI 4K / Thermal / USA, vía SkyController 3 o WiFi).
 * La pantalla de vuelo solo habla con esta interfaz.
 */
interface Link {
    var listener: DroneLink.Listener
    val telemetry: DroneLink.Telemetry
    /** Nombre corto del tipo de enlace, para diagnósticos. */
    val modeName: String
    val isConnected: Boolean
    val connecting: Boolean
    val uploading: Boolean
    /** Contador de eventos "ítem de misión ejecutado" (para guardar el progreso de ESTE vuelo). */
    val missionItemEvents: Int
    /** Familia de dron del enlace: decide el formato del plan que hay que generar. */
    val platform: Platform

    fun connect(choice: ConnectChoice)
    fun disconnect()
    fun uploadPlan(plan: FlightPlan, done: (Boolean) -> Unit)
    fun startMission()
    fun resumeMission()
    fun pauseMission()
    fun stopMission()
    fun returnHome()
    fun cancelReturnHome()
    fun land()
    fun emergency()
    fun setReturnHomeOnDisconnect(on: Boolean)
    /** Sequoia: calibración radiométrica. ANAFI Thermal: calibración del sensor térmico. */
    fun cameraCalibration()

    /** Vídeo en directo: el enlace entrega los fotogramas a [videoSink] mientras esté activado. */
    val supportsVideo: Boolean
    var videoSink: VideoSink?
    fun setVideoEnabled(on: Boolean)
    /** Visor propio del SDK (GroundSDK lo decodifica y pinta él). null = usar VideoView con [videoSink]. */
    fun createStreamView(ctx: android.content.Context): android.view.View? = null
    /** La pantalla que tenía el visor se cierra. */
    fun releaseStreamView() {}
    /** Modo gafas con el visor del SDK (GroundSDK). null = el enlace no lo tiene (se usa GogglesView). */
    /** Modo gafas (pantalla partida con corrección de lente) disponible con este dron. */
    val supportsGoggles: Boolean get() = true
    /** ¿Está llegando vídeo al visor propio? null si el enlace no tiene visor propio. */
    fun streamAlive(): Boolean? = null
    /** ANAFI Thermal: ver la cámara térmica (true) o la RGB (false) en el vídeo. */
    val hasThermal: Boolean get() = false
    val thermalView: Boolean get() = false
    fun setThermalView(on: Boolean) {}
    /** Reenviar la paleta según [Session.thermal]. */
    fun applyThermalPrefs() {}
    /** Copia del fotograma que se ve (para leer temperaturas). false si no se puede ahora. */
    fun captureFrame(cb: (android.graphics.Bitmap?) -> Unit): Boolean = false
    /** Foto suelta con la cámara del dron. false si no se puede ahora (p. ej. ya está disparando). */
    fun takePhoto(): Boolean = false
    /** Inclinación actual de la cámara en grados (−90 = abajo), o null si no se sabe. */
    fun gimbalPitch(): Double? = null
}

/** Opciones del desplegable de conexión. */
enum class ConnectChoice(val label: String, val platform: Platform, val arsdkMode: DroneLink.Mode?) {
    SC2_USB("Bluegrass/Disco · SkyController 2 (cable USB)", Platform.BLUEGRASS_SEQUOIA, DroneLink.Mode.SKYCONTROLLER2_USB),
    BLUEGRASS_WIFI("Bluegrass · WiFi directa", Platform.BLUEGRASS_SEQUOIA, DroneLink.Mode.BLUEGRASS_WIFI),
    DISCO_WIFI("Disco · WiFi directa", Platform.BLUEGRASS_SEQUOIA, DroneLink.Mode.DISCO_WIFI),
    SC2_WIFI("SkyController 2 · WiFi (experimental)", Platform.BLUEGRASS_SEQUOIA, DroneLink.Mode.SKYCONTROLLER2_WIFI),
    ANAFI("ANAFI Thermal / 4K / USA · SkyController 3 o WiFi", Platform.ANAFI, null);
    override fun toString() = label
}
