package com.racoonair.mapper

import android.content.Context

/** App «Racoon Mapper ANAFI»: familia ANAFI con GroundSDK. */
object Flavor {
    const val TITLE = "ANAFI Thermal / 4K / USA"
    val platforms = setOf(Platform.ANAFI)
    fun supports(p: Platform) = p in platforms
    fun newLink(p: Platform, ctx: Context, l: DroneLink.Listener): Link = AnafiLink(ctx, l)
}
