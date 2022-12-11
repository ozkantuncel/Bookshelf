package com.ozkan.bookshelf.firebase.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class User(
    var id: String = "",
    var nameSurname: String = "",
    var email: String = "",
    var profileImageUrl: String = "",
    var cartBook: List<Cart> = listOf(),
    var bookmarksProducts: List<Book> = listOf(),
): Parcelable
