package com.ozkan.bookshelf.ui.navigation

sealed class Screen(val route: String) {
    object OnBoarding : Screen(route = "welcome_screen")
    object Home : Screen(route = "home_screen")
    object Register : Screen(route = "regiter_screen")
    object  Login : Screen(route = "login_screen")
}