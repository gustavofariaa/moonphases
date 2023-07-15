package com.gustavofaria.moonphases.ui.router

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gustavofaria.moonphases.constants.MoonPhaseRouteEnum.HOME_SCREEN
import com.gustavofaria.moonphases.ui.screen.HomeScreen

@Composable
fun RootRouter(
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_SCREEN.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = HOME_SCREEN.route) {
            HomeScreen(navController = navController)
        }
    }
}