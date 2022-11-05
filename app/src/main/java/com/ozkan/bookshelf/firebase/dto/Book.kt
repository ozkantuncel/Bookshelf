package com.ozkan.bookshelf.firebase.dto


data class Book(
    var id: String = "",
    var title: String = "",
    var author: String = "",
    var description: String = "",
    val tags: MutableList<String> = arrayListOf(),
    val images: List<String> = arrayListOf(),
    var price: String = ""
)
