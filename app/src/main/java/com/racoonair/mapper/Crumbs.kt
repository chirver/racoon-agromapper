package com.racoonair.mapper

import android.content.Context
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Rastro persistente: cada paso delicado se escribe a disco ANTES de ejecutarlo, y los fallos Java
 * se guardan con su traza. Así, si la app se cierra de golpe (incluso por un fallo nativo),
 * en el siguiente arranque sabemos en qué paso murió.
 */
object Crumbs {
    private var file: File? = null
    private var crashFile: File? = null
    private val fmt = SimpleDateFormat("HH:mm:ss.SSS", Locale.ROOT)

    fun init(ctx: Context) {
        if (file != null) return
        file = File(ctx.filesDir, "crumbs.txt")
        crashFile = File(ctx.filesDir, "last_crash.txt")
        val previous = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            try {
                val sw = StringWriter(); e.printStackTrace(PrintWriter(sw))
                crashFile?.writeText("${fmt.format(Date())} hilo=${t.name}\n$sw")
                write("FALLO JAVA en hilo ${t.name}: ${e.javaClass.name}: ${e.message}")
            } catch (_: Throwable) {}
            previous?.uncaughtException(t, e)
        }
        // Recorta el rastro para que no crezca sin fin.
        file?.let { f -> if (f.length() > 200_000) f.writeText(f.readLines().takeLast(400).joinToString("\n") + "\n") }
        write("---- arranque de la app ----")
    }

    @Synchronized
    fun write(msg: String) {
        val f = file ?: return
        try { FileWriter(f, true).use { it.write("${fmt.format(Date())} $msg\n"); it.flush() } } catch (_: Throwable) {}
    }

    fun tail(lines: Int = 150): String = try { file?.readLines()?.takeLast(lines)?.joinToString("\n") ?: "" } catch (_: Throwable) { "" }

    /** Último fallo Java guardado (y lo borra), o null. */
    fun takeLastCrash(): String? = try {
        crashFile?.takeIf { it.exists() }?.let { val s = it.readText(); it.delete(); s }
    } catch (_: Throwable) { null }
}
