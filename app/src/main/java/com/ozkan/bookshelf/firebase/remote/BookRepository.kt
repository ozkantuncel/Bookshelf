package com.ozkan.bookshelf.firebase.remote

import com.ozkan.bookshelf.firebase.dto.Book
import com.ozkan.bookshelf.firebase.util.UiState




interface BookRepository {
    fun getBooks(result: (UiState<List<Book>>)->Unit)
    //fun addBookToBasket(user:User) TODO
}