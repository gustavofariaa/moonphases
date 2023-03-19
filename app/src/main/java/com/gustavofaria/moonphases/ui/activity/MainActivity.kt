package com.gustavofaria.moonphases.ui.activity

import android.app.AlarmManager
import android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gustavofaria.moonphases.R
import com.gustavofaria.moonphases.constants.DatePatternsConstants.DEFAULT_DATE_PATTERN
import com.gustavofaria.moonphases.constants.MoonPhaseRouteEnum.HOME_SCREEN
import com.gustavofaria.moonphases.extension.format
import com.gustavofaria.moonphases.theme.MoonPhasesTheme
import com.gustavofaria.moonphases.ui.screen.HomeScreen
import com.gustavofaria.moonphases.utils.MoonPhaseUtils
import com.gustavofaria.moonphases.utils.NotificationReceiver
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.HOUR_OF_DAY
import java.util.Calendar.MINUTE

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureNotifications()
        setContent {
            MoonPhasesTheme {
                RootRouter()
            }
        }
    }

    private fun configureNotifications() {
        val todayMoonPhase = MoonPhaseUtils.getMoonPhase()
        val tomorrow = Calendar.getInstance()
        tomorrow.add(DAY_OF_MONTH, 1)
        val tomorrowMoonPhase =
            MoonPhaseUtils.getMoonPhase(tomorrow.format(pattern = DEFAULT_DATE_PATTERN))

        if (todayMoonPhase == tomorrowMoonPhase) {
            val nextMoonPhase = MoonPhaseUtils.getNextMoonPhase()
            val daysToNextPhase = MoonPhaseUtils.getDaysToNextPhase()
            val nextMoonPhaseDate = Calendar.getInstance()
            nextMoonPhaseDate.add(DAY_OF_MONTH, daysToNextPhase)
            nextMoonPhaseDate.set(HOUR_OF_DAY, 7)
            nextMoonPhaseDate.set(MINUTE, 30)
            nextMoonPhaseDate.set(MINUTE, 0)

            val notification = NotificationReceiver
                .getNotification(
                    context = this,
                    title = this.getString(
                        R.string.notification_current_moon_phase_title,
                        nextMoonPhase
                    ),
                    content = this.getString(R.string.notification_current_moon_phase_body)
                )
            val pendingIntent = NotificationReceiver
                .getPendingIntent(context = this, notification = notification)
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

            val difference = nextMoonPhaseDate.timeInMillis - System.currentTimeMillis()
            val futureInMillis = SystemClock.elapsedRealtime() + difference

            alarmManager[ELAPSED_REALTIME_WAKEUP, futureInMillis] = pendingIntent
        }
    }
}

@Composable
private fun RootRouter(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_SCREEN.route
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            composable(route = HOME_SCREEN.route) {
                HomeScreen(navController = navController)
            }
//            composable(route = CALENDAR_SCREEN.route) {
//                CalendarScreen(navController = navController)
//            }
        }
    }
}
