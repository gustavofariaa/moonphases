package com.gustavofaria.moonphases.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gustavofaria.moonphases.constants.montserratFamily

private val MoonPhasesColorScheme = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    secondary = Color.Black,
    onSecondary = Color.White,
    background = Color.Black
)

private val MoonPhasesTypography = Typography(
    displayLarge = TextStyle(
        color = Color.White,
        fontSize = 28.sp,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold
    ),
    displayMedium = TextStyle(
        color = Color.White,
        fontSize = 20.sp,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium
    ),
    displaySmall = TextStyle(
        color = Color.White,
        fontSize = 14.sp,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = TextStyle(
        color = Color.Black,
        fontSize = 14.sp,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold
    ),
    bodySmall = TextStyle(
        color = Color.Black,
        fontSize = 10.sp,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold
    )
//    headlineLarge = TextStyle(),
//    headlineMedium = TextStyle(),
//    headlineSmall = TextStyle(),
//    titleLarge = TextStyle(),
//    titleMedium = TextStyle(),
//    titleSmall = TextStyle(),
//    bodyLarge = TextStyle(),
//    labelLarge = TextStyle(),
//    labelMedium = TextStyle(),
//    labelSmall = TextStyle()
)

@Composable
fun MoonPhasesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MoonPhasesColorScheme,
        typography = MoonPhasesTypography,
        content = content
    )
}
