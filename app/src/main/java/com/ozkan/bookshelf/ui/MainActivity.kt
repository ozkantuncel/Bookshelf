package com.ozkan.bookshelf.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ozkan.bookshelf.ui.navigation.NavGraph
import com.ozkan.bookshelf.ui.screens.splash_screen.SplashViewModel
import com.ozkan.bookshelf.ui.theme.BookshelfTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }
        setContent {
            BookshelfTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                NavGraph(navController = navController, startDestination = screen)
            }
        }
    }
}

