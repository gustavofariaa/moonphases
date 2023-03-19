package com.gustavofaria.moonphases.constants

import androidx.annotation.DrawableRes
import com.gustavofaria.moonphases.R

enum class MoonPhaseEnum(
    val moonPhaseName: String,
    val nextMoonPhase: String,
    @DrawableRes
    val moonImage: Int
) {
    FULL_MOON(
        moonPhaseName = "Lua Cheia",
        nextMoonPhase = "Lua Minguante",
        moonImage = R.drawable.full_moon
    ),
    WANING_MOON(
        moonPhaseName = "Lua Minguante",
        nextMoonPhase = "Lua Nova",
        moonImage = R.drawable.waning_moon
    ),
    NEW_MOON(
        moonPhaseName = "Lua Nova",
        nextMoonPhase = "Lua Crescente",
        moonImage = R.drawable.new_moon
    ),
    CRESCENT_MOON(
        moonPhaseName = "Lua Crescente",
        nextMoonPhase = "Lua Cheia",
        moonImage = R.drawable.crescent_moon
    );
}
