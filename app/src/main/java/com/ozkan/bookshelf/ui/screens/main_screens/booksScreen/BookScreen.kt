package com.ozkan.bookshelf.ui.screens.main_screens.booksScreen

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
import com.ozkan.bookshelf.ui.screens.main_screens.favorite_screen.FavoriteScreenViewModel
import com.ozkan.bookshelf.ui.screens.main_screens.home.CardBook
import com.ozkan.bookshelf.ui.screens.main_screens.home.HomeScreenViewModel
import com.ozkan.bookshelf.ui.screens.main_screens.profile_screen.ProfileScreenViewModel
import com.ozkan.bookshelf.ui.theme.AppBac
import com.ozkan.bookshelf.ui.theme.Navyblue


@Composable
fun BookScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    favoriteScreenViewModel: FavoriteScreenViewModel = hiltViewModel(),
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {

    val bookState = homeScreenViewModel.book.value
    var data: List<Book> = listOf()
    val errorDialogStateBook = remember { mutableStateOf(false) }//TODO
    val errorTitleBook = remember { mutableStateOf("") }//TODO

    when (bookState) {
        is UiState.Loading -> {
            ApiLoadingState()
        }
        is UiState.Failure -> {
            bookState.error?.let {
                errorTitleBook.value = it
                errorDialogStateBook.value = true
            }
        }
        is UiState.Success -> {
            data = bookState.data
        }
        is UiState.Empty -> {}
    }

    val profileDataState = profileScreenViewModel.getUserInfo.value
    val errorDialogStateProfile = remember { mutableStateOf(false) }//TODO
    val errorTitleProfile = remember { mutableStateOf("") }//TODO
    val userId = remember {
        mutableStateOf("")
    }

    when (profileDataState) {
        is UiState.Loading -> {}

        is UiState.Failure -> {
            profileDataState.error?.let {
                errorTitleProfile.value = it
                errorDialogStateProfile.value = true
            }
        }
        is UiState.Success -> {
            userId.value = profileDataState.data.id
        }

        is UiState.Empty -> {}
    }

    BookPage(
        navController = navController,
        data = data,
        favoriteScreenViewModel,
        userId.value
    )
}

@Composable
fun BookPage(
    navController: NavController,
    data: List<Book>,
    favoriteScreenViewModel: FavoriteScreenViewModel,
    userId: String
) {
    Scaffold(
        backgroundColor = AppBac,
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
                        text = "Çok Satan Kitaplar",
                        color = Navyblue,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                LazyColumn(content = {
                    items(data) { book ->

                        CardBook(
                            navController = navController,
                            book = book,
                            favoriteScreenViewModel = favoriteScreenViewModel,
                            userId = userId
                        )
                    }
                })
            }
        }
    )

}