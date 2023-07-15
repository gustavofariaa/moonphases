package com.gustavofaria.moonphases.ui.activity

import android.app.AlarmManager
import android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.gustavofaria.moonphases.R
import com.gustavofaria.moonphases.constants.DatePatternsConstants.NEXT_PHASE_HOUR_PATTERN
import com.gustavofaria.moonphases.constants.MoonPhaseEnum
import com.gustavofaria.moonphases.extension.format
import com.gustavofaria.moonphases.theme.MoonPhasesTheme
import com.gustavofaria.moonphases.ui.router.RootRouter
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

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MoonPhasesTheme {
                Surface(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootRouter()
                }
            }
        }
    }

    private fun configureNotifications() {
        val tomorrow = Calendar.getInstance()
        tomorrow.add(DAY_OF_MONTH, 1)

        val moonPhases = MoonPhaseUtils.getMoonPhases(
            resources = resources,
            resourceId = R.raw.moonphases
        )

        val nextMoonPhaseDate = Calendar.getInstance()

        val nextMoonPhase = MoonPhaseUtils
            .getNextMoonPhase(moonPhases = moonPhases)
        val daysToNextPhase = MoonPhaseUtils
            .getDaysToNextPhase(moonPhases = moonPhases)
        val nextPhaseCalendar = MoonPhaseUtils
            .getNextPhaseCalendar(moonPhases = moonPhases)

        nextMoonPhaseDate.add(DAY_OF_MONTH, daysToNextPhase)
        nextMoonPhaseDate.set(HOUR_OF_DAY, nextPhaseCalendar.get(HOUR_OF_DAY))
        nextMoonPhaseDate.set(MINUTE, nextPhaseCalendar.get(MINUTE))

        val notification = NotificationReceiver
            .getNotification(
                context = this,
                title = this.getString(R.string.notification_current_moon_phase_title),
                content = this.getString(
                    R.string.notification_current_moon_phase_body,
                    nextMoonPhaseDate.format(pattern = NEXT_PHASE_HOUR_PATTERN),
                    MoonPhaseEnum.get(moonPhase = nextMoonPhase?.moon).moonPhaseName,
                    nextMoonPhase?.illumination,
                )
            )
        val pendingIntent = NotificationReceiver
            .getPendingIntent(context = this, notification = notification)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val difference = nextMoonPhaseDate.timeInMillis - System.currentTimeMillis()
        val futureInMillis = SystemClock.elapsedRealtime() + difference

        alarmManager[ELAPSED_REALTIME_WAKEUP, futureInMillis] = pendingIntent
    }
}
