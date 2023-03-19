package com.gustavofaria.moonphases.domain.model

import com.gustavofaria.moonphases.constants.MoonPhaseEnum

data class MoonPhaseModel(
    val moonPhase: MoonPhaseEnum,
    val moonVisibility: Long
)
