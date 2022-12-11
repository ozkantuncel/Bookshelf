package com.ozkan.bookshelf.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ozkan.bookshelf.firebase.dto.Book
import com.ozkan.bookshelf.firebase.dto.Cart
import com.ozkan.bookshelf.ui.screens.auth_screens.login.LoginScreen
import com.ozkan.bookshelf.ui.screens.auth_screens.register.RegisterPage
import com.ozkan.bookshelf.ui.screens.main_screens.basket_screen.CartScreen
import com.ozkan.bookshelf.ui.screens.main_screens.basket_screen.detail_cart_book.CartBookDetailScreen
import com.ozkan.bookshelf.ui.screens.main_screens.booksScreen.BookScreen
import com.ozkan.bookshelf.ui.screens.main_screens.booksScreen.book_detail.BookDetailScreen
import com.ozkan.bookshelf.ui.screens.main_screens.favorite_screen.FavoriteScreen
import com.ozkan.bookshelf.ui.screens.main_screens.home.HomeScreen
import com.ozkan.bookshelf.ui.screens.on_boarding_screens.OnBoardingPage

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NavGraph(

    navController: NavHostController,
    startDestination: String,
) {

    NavHost(

        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.OnBoarding.route) {
            OnBoardingPage(navController = navController)
        }
        composable(route = Screen.Register.route) {
            RegisterPage(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Books.route) {
            BookScreen(navController)
        }
        composable(route = BottomBarScreen.Basket.route) {
            CartScreen(navController)
        }
        composable(route = BottomBarScreen.Favorite.route) {
            FavoriteScreen(navController)
        }

        composable(route = MainScreen.DetailBook.route) {
            val objBook =
                navController.previousBackStackEntry?.savedStateHandle?.get<Book>("bookItem")
            BookDetailScreen(book = objBook!!, navController = navController)
        }
        composable(route = MainScreen.CartDetailBook.route) {
            val objCart =
                navController.previousBackStackEntry?.savedStateHandle?.get<Cart>("cartItem")
            CartBookDetailScreen(cart = objCart!!, navController = navController)
        }
    }
}


