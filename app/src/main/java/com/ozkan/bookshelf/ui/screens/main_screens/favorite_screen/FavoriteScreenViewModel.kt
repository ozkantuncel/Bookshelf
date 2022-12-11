package com.ozkan.bookshelf.ui.screens.main_screens.favorite_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ozkan.bookshelf.firebase.dto.Book
import com.ozkan.bookshelf.firebase.remote.BookRepository
import com.ozkan.bookshelf.firebase.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    private val repository: BookRepository,
 ) : ViewModel() {

    private val _bookMark: MutableState<UiState<String>> = mutableStateOf(UiState.Empty)
    val bookMarkState: State<UiState<String>>
        get() = _bookMark

    fun addBookMark(
        book: Book,
        userId: String
    ){
        _bookMark.value = UiState.Loading
        repository.addBookToMark(
            book = book,
            userId = userId
        ){
            _bookMark.value = it
        }
    }

    fun deleteBookMark(
        book:Book,
        userId: String
    ){
        _bookMark.value = UiState.Loading
        repository.deleteBookMark(
            book = book,
            userId = userId
        ){
            _bookMark.value = it
        }
    }
}