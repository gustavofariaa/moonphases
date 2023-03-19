package com.gustavofaria.moonphases.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gustavofaria.moonphases.constants.DatePatternsConstants.DEFAULT_DATE_PATTERN
import com.gustavofaria.moonphases.constants.MoonPhaseConstants
import com.gustavofaria.moonphases.extension.toCalendar

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    Column {
        LazyColumn(
            modifier = modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
                .weight(weight = 1f)
        ) {
            items(MoonPhaseConstants.list.keys.toList()) { key ->
                val date = key.toCalendar(pattern = DEFAULT_DATE_PATTERN)
            }
        }
    }
}

@Preview()
@Composable
private fun PreviewCalendarScreen() {
    CalendarScreen()
}
