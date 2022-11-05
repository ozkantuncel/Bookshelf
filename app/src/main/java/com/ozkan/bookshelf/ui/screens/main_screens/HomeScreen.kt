package com.ozkan.bookshelf.ui.screens.main_screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ozkan.bookshelf.R
import com.ozkan.bookshelf.firebase.dto.Book
import com.ozkan.bookshelf.firebase.util.UiState
import com.ozkan.bookshelf.ui.navigation.Screen
import com.ozkan.bookshelf.ui.screens.auth_screens.login.LoginViewModel
import com.ozkan.bookshelf.ui.screens.common.api_state.ApiLoadingState
import com.ozkan.bookshelf.ui.screens.common.button.BKAIconButton

@Composable
fun HomeScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity
    val bookstate = homeScreenViewModel.book.value
    var data: MutableList<Book> = arrayListOf()
    val errorDialogState = remember { mutableStateOf(false) }
    val errorTitle = remember { mutableStateOf("") }

    homeScreenViewModel.getBooks()//TODO

    when (bookstate) {
        is UiState.Loading -> {
            ApiLoadingState()
        }
        is UiState.Failure -> {
            bookstate.error?.let {
                errorTitle.value = it
                errorDialogState.value = true
            }
        }
        is UiState.Success -> {
            data = bookstate.data.toMutableList()
        }
        is UiState.Empty -> {}
    }

    HomePage(
        loginViewModel = loginViewModel,
        homeScreenViewModel = homeScreenViewModel,
        navController = navController,
        data = data
    )
}

@Composable
fun HomePage(
    loginViewModel: LoginViewModel,
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController,
    data: MutableList<Book>
) {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BKAIconButton(
            modifier = Modifier,
            iconModifier = Modifier.size(50.dp),
            icon = R.drawable.logout_ico,
            iconTint = Color.Blue,
        ) {
            loginViewModel.logout {
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            }

        }
        BKAIconButton(
            modifier = Modifier,
            iconModifier = Modifier.size(50.dp),
            icon = R.drawable.logout_ico,
            iconTint = Color.Blue,
        ) {

        }
    }
}

