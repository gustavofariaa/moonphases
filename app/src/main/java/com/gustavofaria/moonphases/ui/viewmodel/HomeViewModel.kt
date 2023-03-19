package com.gustavofaria.moonphases.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.gustavofaria.moonphases.constants.DatePatternsConstants.DEFAULT_DATE_PATTERN
import com.gustavofaria.moonphases.constants.DatePatternsConstants.PRESENTATION_DATE_PATTERN
import com.gustavofaria.moonphases.constants.MoonPhaseConstants
import com.gustavofaria.moonphases.domain.model.HomeModel
import com.gustavofaria.moonphases.extension.format
import com.gustavofaria.moonphases.utils.MoonPhaseUtils
import java.util.Calendar


class HomeViewModel : ViewModel() {

    private val mHomeModel = mutableStateOf<HomeModel?>(value = null)
    val homeModel: State<HomeModel?>
        get() = mHomeModel

    init {
        val date = Calendar.getInstance()
        val key = date.format(pattern = DEFAULT_DATE_PATTERN)

        val nextMoonPhase = MoonPhaseUtils.getNextMoonPhase()
        val daysToNextMoonPhase = MoonPhaseUtils.getDaysToNextPhase()

        MoonPhaseConstants.list[key]?.let { safeMoonPhase ->
            mHomeModel.value = HomeModel(
                date = "${date.format(pattern = PRESENTATION_DATE_PATTERN)}",
                currentMoonPhase = safeMoonPhase.moonPhase.moonPhaseName,
                nextMoonPhase = "$nextMoonPhase",
                daysToNextMoonPhase = "$daysToNextMoonPhase",
                moonVisibility = "${safeMoonPhase.moonVisibility}",
                moonImage = safeMoonPhase.moonPhase.moonImage
            )
        }
    }
}
