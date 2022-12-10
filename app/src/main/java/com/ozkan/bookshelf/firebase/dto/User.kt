package com.ozkan.bookshelf.firebase.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class User(
    var id: String = "",
    var nameSurname: String = "",
    var email: String = "",
    var profileImageUrl: String = "",

    val cartProducts: List<Book> = listOf(),
    val bookmarksProducts: List<Book> = listOf(),
    val oldOrders: List<Book> = listOf(),
    val activeOrders: List<Book> = listOf(),
): Parcelable
