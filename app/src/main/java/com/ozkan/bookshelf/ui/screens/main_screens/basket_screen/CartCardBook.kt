package com.ozkan.bookshelf.ui.screens.main_screens.basket_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ozkan.bookshelf.R
import com.ozkan.bookshelf.firebase.dto.Book
import com.ozkan.bookshelf.firebase.dto.Cart
import com.ozkan.bookshelf.ui.navigation.MainScreen
import com.ozkan.bookshelf.ui.screens.main_screens.profile_screen.ProfileScreenViewModel
import com.ozkan.bookshelf.ui.theme.AppBac
import com.ozkan.bookshelf.ui.theme.Navyblue

@Composable
fun CartCardBook(
    navController: NavController,
    book: Book,
    totalPrice: String,
    itemBook: String,
    cartScreenViewModel: CartScreenViewModel,
    profileScreenViewModel: ProfileScreenViewModel,
    userId: String
) {


    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 0.dp,
        backgroundColor = AppBac
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .background(Color.Black, RoundedCornerShape(0.dp))
                    .height(150.dp)
                    .width(100.dp)
                    .clickable {
                        val cart = Cart(item = itemBook, totalPrice = totalPrice, book = book)
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "cartItem",
                            value = cart
                        )
                        navController.navigate(MainScreen.CartDetailBook.route)
                    },
                painter = rememberAsyncImagePainter(
                    model = book.images[0]
                ),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 10.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .clickable {
                            val cart = Cart(item = itemBook, totalPrice = totalPrice, book = book)
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "cartItem",
                                value = cart
                            )
                            navController.navigate(MainScreen.CartDetailBook.route)
                        },
                    text = book.title,
                    color = Navyblue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = book.author,
                    color = Navyblue,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.weight(0.5f))

            Column(
                modifier = Modifier
                    .padding(start = 8.dp, top = 10.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = itemBook,
                    color = Navyblue,
                    fontSize = 20.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )

                IconButton(onClick = {
                    cartScreenViewModel.deleteCart(
                        book = book,
                        item = itemBook,
                        totalPrice = totalPrice,
                        userId = userId
                    )
                    profileScreenViewModel.updateUserInfo()
                }) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        painter = painterResource(id = R.drawable.fav_ico),
                        contentDescription = null
                    )
                }
                Text(
                    text = "${totalPrice}TL",
                    color = Navyblue,
                    fontSize = 14.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            }

        }
    }
}