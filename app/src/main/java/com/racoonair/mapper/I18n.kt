package com.racoonair.mapper

import android.content.Context
import org.json.JSONObject
import java.util.Locale

/**
 * Traducciones. El texto original (español) de cada mensaje es su propia clave; las traducciones viven en
 * assets/i18n/<idioma>.json como { "texto en español": "translation" }. Lo que falte se queda en español
 * (o en inglés, si el idioma elegido no lo tiene y el inglés sí). Para añadir o corregir un idioma basta
 * con editar o copiar uno de esos ficheros: ver TRANSLATING.md.
 *
 * Los textos con datos usan marcadores posicionales %1$s, %2$s… que cada idioma puede reordenar.
 */
object I18n {
    /** Idiomas incluidos (código ISO 639-1) y su nombre en su propio idioma. */
    val LANGS = linkedMapOf("es" to "Español", "en" to "English", "fr" to "Français", "de" to "Deutsch", "it" to "Italiano")
    const val PREF = "idioma"
    @Volatile var lang = "es"; private set
    @Volatile private var map: Map<String, String> = emptyMap()
    @Volatile private var fallback: Map<String, String> = emptyMap()

    /** Idioma elegido en la app («auto» = el del móvil; si el móvil está en otro idioma no incluido, inglés). */
    fun chosen(ctx: Context): String = ctx.getSharedPreferences("ajustes", Context.MODE_PRIVATE).getString(PREF, "auto") ?: "auto"

    fun init(ctx: Context) {
        val pick = chosen(ctx)
        val sys = Locale.getDefault().language
        lang = when {
            pick != "auto" && pick in LANGS -> pick
            sys in LANGS -> sys
            else -> "en"
        }
        map = if (lang == "es") emptyMap() else load(ctx, lang)
        fallback = if (lang == "es" || lang == "en") emptyMap() else load(ctx, "en")
    }

    private fun load(ctx: Context, code: String): Map<String, String> = try {
        val o = JSONObject(ctx.assets.open("i18n/$code.json").use { String(it.readBytes(), Charsets.UTF_8) })
        val m = HashMap<String, String>(o.length() * 2)
        for (k in o.keys()) { val v = o.optString(k); if (v.isNotEmpty()) m[k] = v }
        m
    } catch (_: Throwable) { emptyMap() }

    fun tr(es: String): String = map[es] ?: fallback[es] ?: es

    fun trf(esFmt: String, vararg args: Any?): String {
        val f = tr(esFmt)
        return try { String.format(Locale.ROOT, f, *args) }
        catch (_: Throwable) { try { String.format(Locale.ROOT, esFmt, *args) } catch (_: Throwable) { esFmt } }
    }
}

/** Traduce un texto fijo. */
fun tr(es: String): String = I18n.tr(es)

/** Traduce un texto con datos (%1$s, %2$s…). */
fun trf(esFmt: String, vararg args: Any?): String = I18n.trf(esFmt, *args)
