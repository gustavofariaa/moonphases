package com.gustavofaria.moonphases.ui.viewmodel

import android.content.res.Resources
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gustavofaria.moonphases.constants.DatePatternsConstants.PRESENTATION_DATE_PATTERN
import com.gustavofaria.moonphases.constants.MoonPhaseEnum
import com.gustavofaria.moonphases.domain.model.HomeModel
import com.gustavofaria.moonphases.domain.model.MoonPhaseModel
import com.gustavofaria.moonphases.extension.format
import com.gustavofaria.moonphases.utils.MoonPhaseUtils
import java.util.Calendar

class HomeViewModel : ViewModel(), BaseHomeViewModel {

    private val mHomeModel = mutableStateOf<HomeModel?>(value = null)
    override val homeModel: State<HomeModel?>
        get() = mHomeModel

    private val mMoonPhases = mutableStateOf<List<MoonPhaseModel>>(value = listOf())

    private val mMoonPhasesLoading = mutableStateOf(value = true)
    override val moonPhasesLoading: State<Boolean>
        get() = mMoonPhasesLoading

    override fun getMoonPhases(resources: Resources, resourceId: Int) {
        mMoonPhasesLoading.value = true
        mMoonPhases.value = MoonPhaseUtils.getMoonPhases(
            resources = resources,
            resourceId = resourceId
        )

        val date = Calendar.getInstance()
        val moonPhase = MoonPhaseUtils.getCurrentMoonPhase(moonPhases = mMoonPhases.value)
        val nextMoonPhase = MoonPhaseUtils.getNextMoonPhase(moonPhases = mMoonPhases.value)
        val daysToNextPhase = MoonPhaseUtils.getDaysToNextPhase(moonPhases = mMoonPhases.value)

        mHomeModel.value = HomeModel(
            date = "${date.format(pattern = PRESENTATION_DATE_PATTERN)}",
            currentMoonPhase = MoonPhaseEnum.get(moonPhase = moonPhase?.moon).moonPhaseName,
            nextMoonPhase = MoonPhaseEnum.get(moonPhase = nextMoonPhase?.moon).moonPhaseName,
            daysToNextMoonPhase = "$daysToNextPhase",
            moonVisibility = "${moonPhase?.illumination}",
            moonImage = MoonPhaseEnum.get(moonPhase = moonPhase?.moon).moonImage
        )

        mMoonPhasesLoading.value = false
    }
}

interface BaseHomeViewModel {
    val homeModel: State<HomeModel?>
    val moonPhasesLoading: State<Boolean>
    fun getMoonPhases(resources: Resources, resourceId: Int)
}
