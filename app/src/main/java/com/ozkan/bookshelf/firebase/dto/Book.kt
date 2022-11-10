package com.ozkan.bookshelf.firebase.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Book(
    var id: String = "",
    var title: String = "",
    var author: String = "",
    var description: String = "",
    val tags: List<String> = listOf(),
    val images: List<String> = listOf(),
    var price: String = ""
) : Parcelable
