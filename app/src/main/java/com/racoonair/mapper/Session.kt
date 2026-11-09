package com.racoonair.mapper

/** Estado de la app que sobrevive a la recreación de pantallas (el enlace con el dron NO se corta). */
object Session {
    var link: Link? = null
    var plan: FlightPlan? = null
    var planName = ""
    var planUploaded = false
    /** Se incrementa cada vez que cambia el plan, para que la pantalla principal lo refresque. */
    var planVersion = 0
    val logLines = ArrayDeque<String>()
    var lastCrash: String? = null
    /** Rastro real del dron (pantalla de vuelo). */
    val track = ArrayList<LatLon>()
    /** Hora de la última calibración radiométrica correcta en esta sesión. */
    var calibratedAt: String? = null
    /** Contador de eventos MissionItemExecuted en el momento del VOLAR (-1 = ninguna misión lanzada). */
    var eventsAtStart = -1
    /** Hueco de vuelo: true = vídeo en directo, false = mapa de trabajo. */
    var showVideo = false
    /** Cuándo se dejó armada la Sequoia por libre (Disco FPV), para la lista de comprobación. */
    var sequoiaArmedAt = 0L
    /** Estimación del planificador para el plan recién generado (s), para comparar con lo real. */
    var plannerEstimateS = 0.0
    /** Metros entre fotos del plan generado (para programar la Sequoia por libre en la Disco). */
    var plannerTriggerM = 0.0
    /** Cronómetro de la misión: inicio (ms) y si llegó a estar en el aire. */
    var missionStartedAt = 0L
    var missionAirborne = false
    /** Último informe de meteo/Kp consultado y para qué dron. */
    var weather: Weather.Report? = null
    var weatherAirframe: Airframe? = null
    /** 0 nada, 1 aviso 30 %, 2 alarma 20 %: para no repetir alarmas en el mismo vuelo. */
    var batteryAlarmLevel = 0
    /** Vuelta a casa automática: 0 armada, 1 ordenada (esperando confirmación), 2 en curso/hecha, 3 cancelada. */
    var autoRth = 0
    var autoRthSentAt = 0L
    var autoRthTries = 0
    /** Desde cuándo la batería está por debajo del umbral. */
    var belowSince = 0L

    // Estado del planificador (para volver a él sin perder el dibujo)
    var zone: MutableList<LatLon> = ArrayList()
    var params = GridParams()
    var manualHome: LatLon? = null
    /** Aterrizaje de la Disco en el planificador (punto, rumbo de la final, aproximación). */
    var fixedWing = FixedWing()
    /** Tipo de misión y parámetros 3D (ANAFI). */
    var spec3D = Spec3D()
    /** Térmica (ANAFI Thermal): paleta, aviso de puntos calientes y ajustes de mosaico. */
    var thermal = ThermalPrefs()
    /** Puntos calientes detectados en esta sesión (búsqueda y rescate). */
    val hotspots = ArrayList<Hotspot>()
    var zoneName = ""

    fun setPlan(p: FlightPlan, name: String) {
        plan = p; planName = name; planUploaded = false; planVersion++
    }
}
