package com.racoonair.mapper

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbAccessory
import android.hardware.usb.UsbManager
import android.os.Handler
import android.os.Looper
import android.os.ParcelFileDescriptor
import com.parrot.arsdk.ardiscovery.ARDiscoveryMux
import com.parrot.mux.Mux

/**
 * Canal USB con el SkyController 2 (protocolo "mux" de Parrot), gestionado por nosotros
 * en vez de por UsbAccessoryMux del SDK, para controlar cada paso y dejar rastro si algo falla.
 */
object UsbMux {
    private const val ACTION_PERMISSION = "com.racoonair.mapper.USB_PERMISSION"
    private const val MANUFACTURER = "Parrot"

    @Volatile var mux: Mux? = null
        private set
    private var pfd: ParcelFileDescriptor? = null
    private var discovery: ARDiscoveryMux? = null
    private var receiverRegistered = false
    private val main = Handler(Looper.getMainLooper())

    /** Callback de estado legible para el registro de la app. */
    var onLog: ((String) -> Unit)? = null
    var onOpened: (() -> Unit)? = null

    fun findAccessory(ctx: Context): UsbAccessory? {
        val um = ctx.getSystemService(Context.USB_SERVICE) as UsbManager
        return um.accessoryList?.firstOrNull { it.manufacturer == MANUFACTURER }
    }

    /** Devuelve el mux si ya está abierto; si no, pide permiso o lo abre y devuelve null. */
    fun ensure(ctx: Context): Mux? {
        mux?.let { return it }
        val app = ctx.applicationContext
        val acc = findAccessory(app)
        if (acc == null) {
            log("No veo el SkyController por USB. Enciéndelo, conecta el cable y, si Android pregunta qué app abrir, cierra el aviso sin elegir FreeFlight.")
            return null
        }
        log("SkyController detectado: ${acc.model} (${acc.version})")
        val um = app.getSystemService(Context.USB_SERVICE) as UsbManager
        if (!um.hasPermission(acc)) {
            registerReceiver(app)
            um.requestPermission(acc, PendingIntent.getBroadcast(app, 0,
                Intent(ACTION_PERMISSION).setPackage(app.packageName), 0))
            log("Acepta el permiso USB; el canal se abrirá solo.")
            return null
        }
        open(app, acc)
        return mux
    }

    private fun registerReceiver(app: Context) {
        if (receiverRegistered) return
        receiverRegistered = true
        app.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(c: Context, i: Intent) {
                try {
                    val granted = i.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)
                    @Suppress("DEPRECATION")
                    val acc = i.getParcelableExtra<UsbAccessory>(UsbManager.EXTRA_ACCESSORY) ?: findAccessory(c)
                    if (!granted || acc == null) { log("Permiso USB denegado."); return }
                    log("Permiso USB concedido.")
                    open(c.applicationContext, acc)
                    if (mux != null) onOpened?.invoke()
                } catch (t: Throwable) {
                    Crumbs.write("usb receiver EXC ${t.javaClass.name}: ${t.message}")
                    log("ERROR abriendo USB: ${t.javaClass.simpleName}: ${t.message}")
                }
            }
        }, IntentFilter(ACTION_PERMISSION))
    }

    @Synchronized
    private fun open(app: Context, acc: UsbAccessory) {
        if (mux != null) return
        val um = app.getSystemService(Context.USB_SERVICE) as UsbManager
        Crumbs.write("usb: openAccessory")
        val fd = um.openAccessory(acc)
        if (fd == null) { log("No se pudo abrir el accesorio USB (¿lo tiene otra app, p. ej. FreeFlight? ciérrala)."); return }
        Crumbs.write("usb: new Mux fd=${fd.fd}")
        val m = Mux(fd) { main.post { log("El SkyController se ha desconectado del USB."); close() } }
        if (!m.isValid) {
            log("Canal USB no válido.")
            try { fd.close() } catch (_: Throwable) {}
            return
        }
        pfd = fd
        Crumbs.write("usb: reader thread")
        Thread({
            try { m.runReader() } catch (t: Throwable) { Crumbs.write("usb reader EXC ${t.javaClass.name}: ${t.message}") }
        }, "muxThread").start()
        Crumbs.write("usb: ARDiscoveryMux")
        discovery = try { ARDiscoveryMux(m) } catch (t: Throwable) {
            Crumbs.write("usb ARDiscoveryMux EXC ${t.javaClass.name}: ${t.message}"); null
        }
        mux = m
        Crumbs.write("usb: abierto OK")
        log("Canal USB abierto. Pulsa Conectar.")
    }

    @Synchronized
    fun close() {
        val m = mux ?: return
        mux = null
        Crumbs.write("usb: close")
        try { discovery?.destroy() } catch (_: Throwable) {}
        discovery = null
        try { m.stop(); m.destroy() } catch (_: Throwable) {}
        try { pfd?.close() } catch (_: Throwable) {}
        pfd = null
    }

    private fun log(msg: String) {
        Crumbs.write("usb log: $msg")
        val l = onLog ?: return
        if (Looper.myLooper() == Looper.getMainLooper()) l(msg) else main.post { l(msg) }
    }
}
