package com.ozkan.bookshelf.ui.screens.main_screens.home

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
class HomeScreenViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _books: MutableState<UiState<List<Book>>> = mutableStateOf(UiState.Empty)
    val book: State<UiState<List<Book>>>
        get() = _books

    init {
        getBooks()
    }

    private fun getBooks() {
        _books.value = UiState.Loading
        repository.getBooks { _books.value = it }
    }
}