package com.ozkan.bookshelf.firebase.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Book(
    var id: String = "",
    var title: String = "",
    var author: String = "",
    var description: String = "",
    var tags: List<String> = listOf(),
    var images: List<String> = listOf(),
    var price: String = ""
) : Parcelable
