package ch.walica.photo_counter_1

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ch.walica.photo_counter_1.views.MainScreen
import ch.walica.photo_counter_1.views.StatsScreen

@Composable
fun Navigation(state: DayState, onEvent: (DayEvents) -> Unit) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(state = state, onEvent = onEvent, navController = navController)
        }

        composable(route = Screen.StatsScreen.route) {
            StatsScreen(state = state, navController = navController)
        }
    }
}