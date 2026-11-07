package com.racoonair.mapper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.io.ByteArrayInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.GZIPInputStream

/**
 * Peticiones a internet (mapas, SIGPAC, meteo). Si la app está atada a la WiFi del dron (sin internet),
 * sale por otra red que SÍ tenga internet (datos móviles), para no tener que desconectar el dron.
 */
object Net {
    const val UA = "RacoonMapper/0.9 (Android; racoon-air.com)"
    @Volatile var appContext: Context? = null

    fun open(url: String): HttpURLConnection {
        val u = URL(url)
        val ctx = appContext
        if (ctx != null) try {
            val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val bound = cm.boundNetworkForProcess
            if (bound != null) {
                @Suppress("DEPRECATION")
                val inet = cm.allNetworks.firstOrNull { n ->
                    val c = cm.getNetworkCapabilities(n)
                    n != bound && c != null && c.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                        c.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                }
                if (inet != null) return inet.openConnection(u) as HttpURLConnection
            }
        } catch (_: Throwable) {}
        return u.openConnection() as HttpURLConnection
    }

    /** GET → bytes (descomprime gzip aunque el servidor no lo anuncie). Lanza excepción si no es 200. */
    fun get(url: String, connectMs: Int = 8000, readMs: Int = 12000): ByteArray {
        val c = open(url)
        c.connectTimeout = connectMs; c.readTimeout = readMs
        c.setRequestProperty("User-Agent", UA)
        c.setRequestProperty("Accept-Encoding", "gzip")
        try {
            val code = c.responseCode
            if (code != 200) throw java.io.IOException("HTTP $code")
            var b = c.inputStream.use { it.readBytes() }
            if (b.size >= 2 && b[0] == 0x1f.toByte() && b[1] == 0x8b.toByte())
                b = GZIPInputStream(ByteArrayInputStream(b)).use { it.readBytes() }
            return b
        } finally { c.disconnect() }
    }

    fun getText(url: String) = String(get(url), Charsets.UTF_8)
}
