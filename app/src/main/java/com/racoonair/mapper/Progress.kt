package com.racoonair.mapper

import android.content.Context

/**
 * Progreso de la misión EN VUELO: se guarda el texto completo del plan que se lanzó con VOLAR
 * y el último ítem que el dron confirmó haber ejecutado. Sobrevive a cerrar la app al cambiar
 * de batería, y no depende del plan que esté cargado en pantalla.
 */
object Progress {
    private fun prefs(c: Context) = c.getSharedPreferences("progreso", Context.MODE_PRIVATE)

    /** Llamar al pulsar VOLAR: este plan pasa a ser el activo, sin progreso todavía. */
    fun start(c: Context, plan: FlightPlan, name: String) {
        prefs(c).edit().putString("text", plan.text).putString("name", name).putInt("last", -1).apply()
    }

    /** Solo cuenta ítems confirmados por el dron (evento MissionItemExecuted) tras el VOLAR. */
    fun record(c: Context, item: Int) {
        val p = prefs(c)
        if (p.getString("text", null) != null && item > p.getInt("last", -1)) p.edit().putInt("last", item).apply()
    }

    data class Active(val plan: FlightPlan, val name: String, val last: Int)

    fun active(c: Context): Active? {
        val p = prefs(c)
        val text = p.getString("text", null) ?: return null
        return try { Active(FlightPlan.parse(text), p.getString("name", "plan") ?: "plan", p.getInt("last", -1)) } catch (_: Exception) { null }
    }

    fun clear(c: Context) = prefs(c).edit().clear().apply()
}
