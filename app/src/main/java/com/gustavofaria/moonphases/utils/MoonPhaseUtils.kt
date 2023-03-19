package com.gustavofaria.moonphases.utils

import com.gustavofaria.moonphases.constants.DatePatternsConstants.DEFAULT_DATE_PATTERN
import com.gustavofaria.moonphases.constants.MoonPhaseConstants
import com.gustavofaria.moonphases.constants.MoonPhaseEnum
import com.gustavofaria.moonphases.extension.format
import java.util.Calendar

object MoonPhaseUtils {
    private val TODAY = Calendar.getInstance()
    private val TODAY_KEY = TODAY.format(pattern = DEFAULT_DATE_PATTERN)

    fun getMoonPhase(dayKey: String? = TODAY_KEY): String? {
        val key = MoonPhaseConstants.list.keys.find { it == dayKey }
        return MoonPhaseConstants.list[key]?.moonPhase?.moonPhaseName
    }

    fun getNextMoonPhase(dayKey: String? = TODAY_KEY): String? {
        val key = MoonPhaseConstants.list.keys.find { it == dayKey }
        return MoonPhaseConstants.list[key]?.moonPhase?.nextMoonPhase
    }

    fun getDaysToNextPhase(dayKey: String? = TODAY_KEY): Int {
        var currentPhase: MoonPhaseEnum? = null
        var daysToNextPhase = 1
        run breaking@{
            MoonPhaseConstants.list.forEach {
                when {
                    currentPhase != null && currentPhase != it.value.moonPhase -> return@breaking
                    it.key == dayKey -> currentPhase = it.value.moonPhase
                    it.value.moonPhase == currentPhase -> daysToNextPhase++
                }
            }
        }
        return daysToNextPhase
    }
}
