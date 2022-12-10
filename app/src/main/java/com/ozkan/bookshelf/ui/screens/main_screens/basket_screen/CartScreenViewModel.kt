package com.ozkan.bookshelf.ui.screens.main_screens.basket_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ozkan.bookshelf.firebase.dto.Book
import com.ozkan.bookshelf.firebase.dto.Cart
import com.ozkan.bookshelf.firebase.remote.BookRepository
import com.ozkan.bookshelf.firebase.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CartScreenViewModel @Inject constructor(
    private val repository: BookRepository
): ViewModel() {


    private val _bookCart: MutableState<UiState<String>> = mutableStateOf(UiState.Empty)
    val bookCartState: State<UiState<String>>
        get() = _bookCart


    fun addCart(
        item: String,
        book: Book,
        userId: String
    ){
        _bookCart.value = UiState.Loading
        repository.addCart(
            cart = Cart(
                item = item,
                book = book
            ),
            userId = userId
        ){
            _bookCart.value = it
        }
    }

    fun deleteCart(
        item: String,
        book: Book,
        userId: String
    ){
        _bookCart.value = UiState.Loading
        repository.deleteCart(
            cart = Cart(
                item = item,
                book = book
            ),
            userId = userId
        ){
            _bookCart.value = it
        }
    }
}