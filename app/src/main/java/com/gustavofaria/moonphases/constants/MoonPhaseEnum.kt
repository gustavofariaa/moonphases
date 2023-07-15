package com.gustavofaria.moonphases.constants

import androidx.annotation.DrawableRes
import com.gustavofaria.moonphases.R

enum class MoonPhaseEnum(
    val apiMoonPhaseName: String,
    val moonPhaseName: String,
    val nextMoonPhase: String,
    @DrawableRes
    val moonImage: Int
) {
    FULL_MOON(
        apiMoonPhaseName = "fullMoon",
        moonPhaseName = "Lua Cheia",
        nextMoonPhase = "Lua Minguante",
        moonImage = R.drawable.full_moon
    ),
    WANING_MOON(
        apiMoonPhaseName = "waningMoon",
        moonPhaseName = "Lua Minguante",
        nextMoonPhase = "Lua Nova",
        moonImage = R.drawable.waning_moon
    ),
    NEW_MOON(
        apiMoonPhaseName = "newMoon",
        moonPhaseName = "Lua Nova",
        nextMoonPhase = "Lua Crescente",
        moonImage = R.drawable.new_moon
    ),
    CRESCENT_MOON(
        apiMoonPhaseName = "crescentMoon",
        moonPhaseName = "Lua Crescente",
        nextMoonPhase = "Lua Cheia",
        moonImage = R.drawable.crescent_moon
    );

    companion object {
        fun get(moonPhase: String?) = values().find { it.apiMoonPhaseName == moonPhase } ?: FULL_MOON
    }
}
