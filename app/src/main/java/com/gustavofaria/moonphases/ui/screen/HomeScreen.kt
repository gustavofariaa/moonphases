package com.gustavofaria.moonphases.ui.screen

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManagerFactory
import com.gustavofaria.moonphases.R
import com.gustavofaria.moonphases.constants.HomeScreenConstants.HOROSCOPE_IN_MARKET
import com.gustavofaria.moonphases.constants.HomeScreenConstants.HOROSCOPE_URI
import com.gustavofaria.moonphases.extension.setBold
import com.gustavofaria.moonphases.ui.bottomsheet.showBottomSheet
import com.gustavofaria.moonphases.ui.viewmodel.HomeViewModel
import com.gustavofaria.moonphases.utils.SharedPreferencesUtils

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = HomeViewModel()
) {
    val context = LocalContext.current

    val reviewInfo = remember { mutableStateOf<ReviewInfo?>(null) }
    val reviewManager = remember { ReviewManagerFactory.create(context) }

    reviewManager.requestReviewFlow().addOnCompleteListener {
        if (it.isSuccessful) reviewInfo.value = it.result
    }

    LaunchedEffect(reviewInfo) {
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
            showBottomSheet(
                context = context,
                title = context.getString(R.string.home_bottom_sheet_title),
                body = context.getString(R.string.home_bottom_sheet_body)
                    .setBold(
                        context.getString(R.string.home_bottom_sheet_body_bold_1),
                        context.getString(R.string.home_bottom_sheet_body_bold_2)
                    ),
                button = context.getString(R.string.home_bottom_sheet_button)
            )
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp
            )
        ) {
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
//            Icon(
//                modifier = Modifier
//                    .padding(all = 12.dp)
//                    .size(24.dp)
//                    .clickable {
//                        showBottomSheet(
//                            context = context,
//                            title = context.getString(R.string.ads_off_bottom_sheet_title),
//                            body = context.getString(R.string.ads_off_bottom_sheet_body)
//                                .setBold(
//                                    context.getString(R.string.ads_off_bottom_sheet_body_bold_1),
//                                    context.getString(R.string.ads_off_bottom_sheet_body_bold_2)
//                                ),
//                            button = context.getString(R.string.ads_off_bottom_sheet_button),
//                            onClickButton = {}
//                        )
//                    },
//                imageVector = ImageVector.vectorResource(id = R.drawable.ic_ads_off),
//                tint = Color.White,
//                contentDescription = null
//            )
            Icon(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .size(32.dp)
                    .clickable {
                        showBottomSheet(
                            context = context,
                            title = context.getString(R.string.horoscope_bottom_sheet_title),
                            body = context.getString(R.string.horoscope_bottom_sheet_body)
                                .setBold(
                                    context.getString(R.string.horoscope_bottom_sheet_body_bold_1),
                                    context.getString(R.string.horoscope_bottom_sheet_body_bold_2),
                                    context.getString(R.string.horoscope_bottom_sheet_body_bold_3)
                                ),
                            button = context.getString(R.string.horoscope_bottom_sheet_button),
                            onClickButton = {
                                try {
                                    context.startActivity(
                                        Intent(ACTION_VIEW, Uri.parse(HOROSCOPE_IN_MARKET))
                                    )
                                } catch (exception: ActivityNotFoundException) {
                                    context.startActivity(
                                        Intent(ACTION_VIEW, Uri.parse(HOROSCOPE_URI))
                                    )
                                }
                            }
                        )
                    },
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_crystal_ball),
                tint = Color.White,
                contentDescription = null
            )
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
                },
            painter = painterResource(
                id = homeViewModel.homeModel.value?.moonImage ?: R.drawable.full_moon
            ),
            contentDescription = null
        )
        homeViewModel.homeModel.value?.let { safeHomeModel ->
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = pluralStringResource(
                    id = R.plurals.text_home_screen_next_moon_phase,
                    safeHomeModel.daysToNextMoonPhase.toInt(),
                    safeHomeModel.daysToNextMoonPhase,
                    safeHomeModel.nextMoonPhase
                ).setBold(safeHomeModel.nextMoonPhase),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )
        }
        Spacer(
            modifier = Modifier
                .defaultMinSize(minHeight = 16.dp)
                .weight(weight = 1f)
        )
        AndroidView(
            modifier = Modifier,
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize(AdSize.FULL_WIDTH, 48))
                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
//                    adUnitId = context.getString(R.string.ads_banner_key)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}

@Preview()
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}
