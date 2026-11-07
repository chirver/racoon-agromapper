package com.racoonair.mapper

import android.content.Context

/** App «Racoon Mapper» (Parrot): Bluegrass y Disco con ARSDK3. Sin GroundSDK. */
object Flavor {
    const val TITLE = "Bluegrass · Disco"
    val platforms = setOf(Platform.BLUEGRASS_SEQUOIA)
    fun supports(p: Platform) = p in platforms
    fun newLink(p: Platform, ctx: Context, l: DroneLink.Listener): Link = DroneLink(ctx, l)
}
