package com.ozkan.bookshelf.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ozkan.bookshelf.ui.screens.auth_screens.login.LoginScreen
import com.ozkan.bookshelf.ui.screens.auth_screens.register.RegisterPage
import com.ozkan.bookshelf.ui.screens.main_screens.HomeScreen
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
            HomeScreen(navController= navController)
        }
        composable(route = Screen.OnBoarding.route) {
            OnBoardingPage(navController = navController)
        }
        composable(route = Screen.Register.route) {
            RegisterPage(navController = navController)
        }
        composable(route = Screen.Login.route){
            LoginScreen(navController = navController)
        }
    }
}