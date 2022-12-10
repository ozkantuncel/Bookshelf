package com.ozkan.bookshelf.ui.screens.main_screens.favorite_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.ozkan.bookshelf.firebase.util.UiState
import com.ozkan.bookshelf.ui.screens.common.api_state.ApiLoadingState
import com.ozkan.bookshelf.ui.screens.main_screens.profile_screen.ProfileScreenViewModel

@Composable
fun FavoriteScreen(
    profileScreenViewModel:ProfileScreenViewModel = hiltViewModel()
){
    val profileDataState = profileScreenViewModel.getUserInfo.value
    val userId = remember {
        mutableStateOf("")
    }
    when (profileDataState) {
        is UiState.Loading -> {
            ApiLoadingState()
        }
        is UiState.Failure -> {
            profileDataState.error?.let {
            }
        }
        is UiState.Success -> {
            userId.value = profileDataState.data.id
        }
        is UiState.Empty -> {}
    }
}