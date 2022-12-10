package com.ozkan.bookshelf.ui.screens.main_screens.favorite_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ozkan.bookshelf.R
import com.ozkan.bookshelf.firebase.dto.Book
import com.ozkan.bookshelf.firebase.util.UiState
import com.ozkan.bookshelf.ui.screens.common.api_state.ApiLoadingState
import com.ozkan.bookshelf.ui.screens.common.button.BKAIconButton
import com.ozkan.bookshelf.ui.screens.main_screens.profile_screen.ProfileScreenViewModel
import com.ozkan.bookshelf.ui.theme.Navyblue

@Composable
fun FavoriteScreen(
    navController: NavController,
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel(),
    favoriteScreenViewModel: FavoriteScreenViewModel = hiltViewModel()
) {
    val profileDataState = profileScreenViewModel.getUserInfo.value
    var data: List<Book> = listOf()
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
            data = profileDataState.data.bookmarksProducts
            userId.value = profileDataState.data.id
        }
        is UiState.Empty -> {}
    }

    FavoritePage(
        navController = navController,
        data = data,
        userId = userId.value,
        favoriteScreenViewModel = favoriteScreenViewModel,
        profileScreenViewModel = profileScreenViewModel
    )
}
@Composable
fun FavoritePage(
    navController: NavController,
    data: List<Book>,
    userId: String,
    favoriteScreenViewModel: FavoriteScreenViewModel,
    profileScreenViewModel: ProfileScreenViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                actions = {
                    BKAIconButton(
                        modifier = Modifier,
                        iconModifier = Modifier.size(45.dp),
                        icon = R.drawable.pers_ico,
                        iconTint = Color.Gray
                    ) {

                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Favorilerim",
                        color = Navyblue,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                LazyColumn(content = {
                    items(data) { book ->
                        FavoriteCardBook(
                            navController = navController,
                            book = book,
                            favoriteScreenViewModel = favoriteScreenViewModel,
                            profileScreenViewModel = profileScreenViewModel,
                            userId = userId
                        )
                    }
                })
            }
        })
}