package com.ozkan.bookshelf.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ozkan.bookshelf.ui.screens.main_screens.HomePage

@ExperimentalPagerApi
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination:String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Screen.Home.route){
            HomePage(name = "Ali")
        }
    }
}