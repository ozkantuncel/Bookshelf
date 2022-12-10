package com.ozkan.bookshelf.firebase.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    var item:String?="",
    var book: Book? = null
): Parcelable