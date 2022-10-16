package com.ozkan.bookshelf.ui.screens.main_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
 import androidx.navigation.NavController
import com.ozkan.bookshelf.R
 import com.ozkan.bookshelf.ui.screens.auth_screens.login.LoginViewModel
import com.ozkan.bookshelf.ui.screens.common.button.BKAIconButton

@Composable
fun HomePage(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BKAIconButton(
            modifier =Modifier,
            iconModifier = Modifier.size(50.dp),
            icon = R.drawable.logout_ico,
            iconTint = Color.Blue,
        ) {
            /*viewModel.logout {
                navController.popBackStack()
                navController.navigate(Screen.Register.route)
            }*/
        }
    }
}

