package com.gustavofaria.moonphases.domain.model

import com.google.gson.annotations.SerializedName

data class MoonPhaseModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("moon")
    val moon: String?,
    @SerializedName("illumination")
    val illumination: String?,
    @SerializedName("phaseChangeTime")
    val phaseChangeTime: String?
)
