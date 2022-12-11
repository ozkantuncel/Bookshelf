package com.ozkan.bookshelf.ui.navigation

import com.ozkan.bookshelf.R

sealed class Screen(val route: String) {
    object OnBoarding : Screen(route = "welcome_screen")
    object Home : Screen(route = "home_screen")
    object Register : Screen(route = "register_screen")
    object Login : Screen(route = "login_screen")
}

sealed class BottomBarScreen(val icon: Int, val route: String) {
    object Books : BottomBarScreen(R.drawable.book_ico, route = "books_screen")
    object Basket : BottomBarScreen(R.drawable.basket_ico, route = "basket_screen")
    object Favorite : BottomBarScreen(R.drawable.fav_ico, route = "favorite_screen")
}

sealed class MainScreen(val route: String) {
    object EditPerson : MainScreen(route = "edit_person")
    object DetailBook : MainScreen(route = "detail_book")
    object CartDetailBook : MainScreen(route = "cart_detail_book")
    object SettingPerson : MainScreen(route = "setting_person")
}


