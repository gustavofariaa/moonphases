package com.gustavofaria.moonphases.utils

import android.content.res.Resources
import com.gustavofaria.moonphases.constants.DatePatternsConstants.KEY_DATE_PATTERN
import com.gustavofaria.moonphases.constants.DatePatternsConstants.NEXT_PHASE_DATE_PATTERN
import com.gustavofaria.moonphases.domain.model.MoonPhaseModel
import com.gustavofaria.moonphases.extension.format
import com.gustavofaria.moonphases.extension.toCalendar
import java.util.Calendar

object MoonPhaseUtils {
    private val TODAY = Calendar.getInstance()
    private val TODAY_KEY = TODAY.format(pattern = KEY_DATE_PATTERN)

    fun getMoonPhases(
        resources: Resources,
        resourceId: Int
    ) = JsonUtils.readJsonFromResources<MoonPhaseModel>(
        resources = resources,
        resourceId = resourceId
    )

    fun getCurrentMoonPhase(moonPhases: List<MoonPhaseModel>) =
        moonPhases.find { it.id == TODAY_KEY }

    fun getMoonPhase(
        moonPhases: List<MoonPhaseModel>,
        key: String? = TODAY_KEY
    ) = moonPhases.find { it.id == key }

    fun getNextMoonPhase(
        moonPhases: List<MoonPhaseModel>,
        key: String? = TODAY_KEY
    ) = moonPhases.find {
        (it.id?.toLong() ?: 0) > (key?.toLong() ?: 0) &&
                it.phaseChangeTime != null
    }

    fun getDaysToNextPhase(
        moonPhases: List<MoonPhaseModel>,
        key: String? = TODAY_KEY
    ): Int {
        val moonPhase =
            getCurrentMoonPhase(moonPhases = moonPhases)
                ?.id?.toLong() ?: 0
        val nextMoonPhase =
            getNextMoonPhase(moonPhases = moonPhases, key = key)
                ?.id?.toLong() ?: 0
        return (nextMoonPhase - moonPhase).toInt()
    }

    fun getNextPhaseCalendar(
        moonPhases: List<MoonPhaseModel>,
        key: String? = TODAY_KEY
    ): Calendar {
        val moonPhase =
            getCurrentMoonPhase(moonPhases = moonPhases)

        if (moonPhase?.phaseChangeTime == null) {
            val nextMoonPhase =
                getNextMoonPhase(moonPhases = moonPhases, key = key)
            return nextMoonPhase?.phaseChangeTime
                ?.toCalendar(pattern = NEXT_PHASE_DATE_PATTERN)
                ?: Calendar.getInstance()
        }

        return moonPhase.phaseChangeTime
            .toCalendar(pattern = NEXT_PHASE_DATE_PATTERN)
            ?: Calendar.getInstance()
    }
}
