package com.ozkan.bookshelf.ui.screens.main_screens.booksScreen.book_detail


import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ozkan.bookshelf.R
import com.ozkan.bookshelf.firebase.dto.Book
import com.ozkan.bookshelf.firebase.util.UiState
import com.ozkan.bookshelf.ui.navigation.BottomBarScreen
import com.ozkan.bookshelf.ui.screens.common.api_state.ApiLoadingState
import com.ozkan.bookshelf.ui.screens.common.button.BKAIconButton
import com.ozkan.bookshelf.ui.screens.common.button.BTKAddCartButton
import com.ozkan.bookshelf.ui.screens.main_screens.basket_screen.CartScreenViewModel
import com.ozkan.bookshelf.ui.screens.main_screens.profile_screen.ProfileScreenViewModel
import com.ozkan.bookshelf.ui.theme.AppBac
import com.ozkan.bookshelf.ui.theme.BottomBack
import com.ozkan.bookshelf.ui.theme.Navyblue
import com.ozkan.bookshelf.util.extension.toast

@Composable
fun BookDetailScreen(
    book: Book,
    navController: NavController,
    cartScreenViewModel: CartScreenViewModel = hiltViewModel(),
    profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity

    val imgURL = remember { mutableStateOf("") }
    val bookPrice = remember { mutableStateOf("") }
    val bookTitle = remember { mutableStateOf("") }
    val bookAuthor = remember { mutableStateOf("") }
    val bookDescription = remember { mutableStateOf("") }

    val bookItem = remember { mutableStateOf(1) }

    LaunchedEffect(key1 = true) {
        imgURL.value = book.images[0]
        bookPrice.value = book.price
        bookTitle.value = book.title
        bookAuthor.value = book.author
        bookDescription.value = book.description
    }

    val bookState = cartScreenViewModel.bookCartState.value
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
            LaunchedEffect(true) {
                navController.popBackStack()
                navController.navigate(BottomBarScreen.Books.route)
            }
        }
        is UiState.Empty -> {}
    }

    Scaffold(
        backgroundColor = AppBac,
        topBar = {
            TopAppBar(
                title = {},
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                    BKAIconButton(
                        modifier = Modifier,
                        iconModifier = Modifier.size(45.dp),
                        icon = R.drawable.left_ico,
                        iconTint = Color.Gray
                    ) {
                        navController.popBackStack()
                        navController.navigate(BottomBarScreen.Books.route)
                    }
                },
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
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Image(
                    modifier = Modifier
                        .background(Color.Black, RoundedCornerShape(0.dp))
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.5f),
                    painter = rememberAsyncImagePainter(
                        model = imgURL.value
                    ),
                    contentDescription = null
                )
                Card(
                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                    modifier = Modifier
                        .align(Alignment.Start),
                    backgroundColor = BottomBack,
                    contentColor = Color.Transparent
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp, vertical = 25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    modifier = Modifier.align(Alignment.Start),
                                    text = bookAuthor.value,
                                    color = Navyblue,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.W400,
                                    textAlign = TextAlign.Start
                                )

                                Text(
                                    modifier = Modifier.align(Alignment.Start),
                                    text = bookTitle.value,
                                    color = Navyblue,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                        Text(
                            modifier = Modifier.padding(top = 10.dp),
                            text = bookDescription.value,
                            color = Navyblue,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Start
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Box(
                                Modifier
                                    .size(
                                        width = 100.dp,
                                        height = 50.dp
                                    )
                                    .clip(RoundedCornerShape(50.dp))
                                    .background(Color.White)
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = bookPrice.value,
                                    color = Navyblue,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Start
                                )
                            }

                            Column(
                                modifier = Modifier.size(width = 155.dp, height = 85.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                BTKAddCartButton(
                                    modifierButton = Modifier.size(
                                        width = 155.dp,
                                        height = 40.dp
                                    )
                                ) {
                                    val userId = profileScreenViewModel.getUserId.value
                                    cartScreenViewModel.addCart(
                                        item = bookItem.value.toString(),
                                        book = book,
                                        userId = userId
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    BKAIconButton(
                                        modifier = Modifier,
                                        iconModifier = Modifier.size(35.dp),
                                        icon = R.drawable.min_ico,
                                        iconTint = Color.Black,
                                        iconContentDescription = null
                                    ) {
                                        if (bookItem.value > 1) {
                                            bookItem.value -= 1
                                        }
                                    }
                                    Text(
                                        text = "${bookItem.value}",
                                        color = Navyblue,
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    BKAIconButton(
                                        modifier = Modifier,
                                        iconModifier = Modifier.size(35.dp),
                                        icon = R.drawable.pls_ico,
                                        iconTint = Color.Black,
                                        iconContentDescription = null
                                    ) {
                                        if (bookItem.value < 5) {
                                            bookItem.value += 1
                                        } else {
                                            activity.toast("5'den fazla sipariş verilemez")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )

    BackHandler(onBack = {
        navController.popBackStack()
        navController.navigate(BottomBarScreen.Books.route)
    })
}
