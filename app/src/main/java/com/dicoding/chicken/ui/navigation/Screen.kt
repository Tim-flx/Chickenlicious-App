package com.dicoding.chicken.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Favorite: Screen("favorite")
    object Profile: Screen("profile")
    object DetailChicken : Screen("home/{chickenId}") {
        fun createRoute(chickenId: Int) = "home/$chickenId"
    }
}
