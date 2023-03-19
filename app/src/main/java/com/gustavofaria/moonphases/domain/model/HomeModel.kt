package com.gustavofaria.moonphases.domain.model

import androidx.annotation.DrawableRes

data class HomeModel(
    val date: String,
    val currentMoonPhase: String,
    val nextMoonPhase: String,
    val daysToNextMoonPhase: String,
    val moonVisibility: String,
    @DrawableRes
    val moonImage: Int
)
