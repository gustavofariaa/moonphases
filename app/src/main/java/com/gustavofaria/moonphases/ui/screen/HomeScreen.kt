package com.gustavofaria.moonphases.ui.screen

import android.app.Activity
import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory
import com.gustavofaria.moonphases.R
import com.gustavofaria.moonphases.domain.model.HomeModel
import com.gustavofaria.moonphases.extension.setBold
import com.gustavofaria.moonphases.theme.MoonPhasesTheme
import com.gustavofaria.moonphases.ui.bottomsheet.WelcomeBottomSheet
import com.gustavofaria.moonphases.ui.shimmer.HomeShimmer
import com.gustavofaria.moonphases.ui.viewmodel.BaseHomeViewModel
import com.gustavofaria.moonphases.ui.viewmodel.HomeViewModel
import com.gustavofaria.moonphases.utils.SharedPreferencesUtils

@Composable
fun HomeScreen(
    homeViewModel: BaseHomeViewModel = viewModel<HomeViewModel>(),
    navController: NavController
) {
    val context = LocalContext.current

    var openBottomSheet by rememberSaveable { mutableStateOf(false) }

    val reviewManager = remember { ReviewManagerFactory.create(context) }
    val reviewInfo = remember { mutableStateOf<ReviewInfo?>(value = null) }

    LaunchedEffect(reviewInfo) {
        homeViewModel.getMoonPhases(
            resources = context.resources,
            resourceId = R.raw.moonphases
        )
        reviewInfo.value?.let { safeReviewInfo ->
            reviewManager.launchReviewFlow(context as Activity, safeReviewInfo)
        }
    }

    LaunchedEffect(Unit) {
        val alreadyPassedOnBoarding = SharedPreferencesUtils
            .getAlreadyPassedOnBoarding(context = context)
        if (!alreadyPassedOnBoarding) {
            SharedPreferencesUtils
                .setAlreadyPassedOnBoarding(context = context, value = true)
            openBottomSheet = true
        }
    }

    reviewManager.requestReviewFlow().addOnCompleteListener {
        if (it.isSuccessful) reviewInfo.value = it.result
    }

    if (openBottomSheet) {
        WelcomeBottomSheet(onDismissRequest = { openBottomSheet = false })
    }

    if (homeViewModel.moonPhasesLoading.value)
        HomeShimmer(isLoading = homeViewModel.moonPhasesLoading.value)
    else
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Column {
                    Text(
                        text = "${homeViewModel.homeModel.value?.currentMoonPhase}",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Spacer(modifier = Modifier.size(size = 4.dp))
                    Text(
                        text = "${homeViewModel.homeModel.value?.date}",
                        style = MaterialTheme.typography.displaySmall
                    )
                }
                Spacer(modifier = Modifier.weight(weight = 1f))
//                Icon(
//                    modifier = Modifier
//                        .padding(all = 12.dp)
//                        .size(24.dp)
//                        .clickable {
//                            showBottomSheet(
//                                context = context,
//                                title = context.getString(R.string.ads_off_bottom_sheet_title),
//                                body = context
//                                    .getString(R.string.ads_off_bottom_sheet_body)
//                                    .setBold(
//                                        context.getString(R.string.ads_off_bottom_sheet_body_bold_1),
//                                        context.getString(R.string.ads_off_bottom_sheet_body_bold_2)
//                                    ),
//                                button = context.getString(R.string.ads_off_bottom_sheet_button),
//                                onClickButton = {}
//                            )
//                        },
//                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_ads_off),
//                    tint = Color.White,
//                    contentDescription = null
//                )
            }
            Spacer(
                modifier = Modifier
                    .defaultMinSize(minHeight = 16.dp)
                    .weight(weight = 1f)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(
                    id = R.string.text_home_screen_actual_moon_phase,
                    homeViewModel.homeModel.value?.currentMoonPhase.toString(),
                    homeViewModel.homeModel.value?.moonVisibility.toString()
                ).setBold(
                    stringResource(
                        id = R.string.text_home_screen_actual_moon_phase_bold_1,
                        homeViewModel.homeModel.value?.currentMoonPhase.toString()
                    ),
                    stringResource(
                        id = R.string.text_home_screen_actual_moon_phase_bold_2,
                        homeViewModel.homeModel.value?.moonVisibility.toString()
                    )
                ),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )
            Image(
                modifier = Modifier
                    .size(size = 224.dp)
                    .padding(all = 24.dp)
                    .clickable {
                        reviewInfo.value?.let { safeReviewInfo ->
                            reviewManager.launchReviewFlow(context as Activity, safeReviewInfo)
                        }
                        // TODO REMOVER
                        openBottomSheet = true
                    },
                painter = painterResource(
                    id = homeViewModel.homeModel.value?.moonImage ?: R.drawable.full_moon
                ),
                contentDescription = null
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = pluralStringResource(
                    id = R.plurals.text_home_screen_next_moon_phase,
                    count = homeViewModel.homeModel.value?.daysToNextMoonPhase?.toInt() ?: 0,
                    homeViewModel.homeModel.value?.daysToNextMoonPhase ?: "",
                    homeViewModel.homeModel.value?.nextMoonPhase ?: ""
                ).setBold(homeViewModel.homeModel.value?.nextMoonPhase ?: ""),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .defaultMinSize(minHeight = 16.dp)
                    .weight(weight = 1f)
            )
//        AndroidView(
//            modifier = Modifier,
//            factory = { context ->
//                AdView(context).apply {
//                    setAdSize(AdSize(AdSize.FULL_WIDTH, 48))
//                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
////                    adUnitId = context.getString(R.string.ads_banner_key)
//                    loadAd(AdRequest.Builder().build())
//                }
//            }
//        )
        }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    MoonPhasesTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                homeViewModel = object : BaseHomeViewModel {
                    override val homeModel: State<HomeModel?>
                        get() = mutableStateOf(
                            value = HomeModel(
                                date = "13 de julho, 2023",
                                currentMoonPhase = "Lua Minguante",
                                nextMoonPhase = "Lua Nova",
                                daysToNextMoonPhase = "4",
                                moonVisibility = "20%",
                                moonImage = R.drawable.waning_moon
                            )
                        )
                    override val moonPhasesLoading: State<Boolean>
                        get() = mutableStateOf(value = false)

                    override fun getMoonPhases(resources: Resources, resourceId: Int) {}
                },
                navController = NavController(context = LocalContext.current),
            )
        }
    }
}
