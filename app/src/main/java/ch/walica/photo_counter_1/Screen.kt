package ch.walica.photo_counter_1

sealed class Screen(val route: String) {
    data object MainScreen : Screen(route = "main_screen")
    data object StatsScreen : Screen(route = "stats_screen")
}