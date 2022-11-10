package com.ozkan.bookshelf.ui.screens.main_screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ozkan.bookshelf.ui.navigation.BottomBarScreen
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ozkan.bookshelf.ui.navigation.NavGraph
import com.ozkan.bookshelf.ui.theme.BottomBack

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(

) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == BottomBarScreen.Books.route || currentRoute == BottomBarScreen.Basket.route || currentRoute == BottomBarScreen.Favorite.route) {
                BottomAppBar(
                    modifier = Modifier
                        .height(50.dp),
                    backgroundColor = Color.White,
                ) {
                    BottomBar(navController = navController)
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavGraph(
                navController = navController,
                startDestination = BottomBarScreen.Books.route
            )
        }

    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val items = listOf(
        BottomBarScreen.Books,
        BottomBarScreen.Basket,
        BottomBarScreen.Favorite
    )
    BottomNavigation(
        backgroundColor = BottomBack,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->
            AddItem(
                screen = item,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(

        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = ""
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            screen.route.let {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }

        }
    )
}