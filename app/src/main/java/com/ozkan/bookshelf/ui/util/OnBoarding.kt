package com.ozkan.bookshelf.ui.util

import com.ozkan.bookshelf.R

sealed class OnBoarding(
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoarding(
        image = R.drawable.on1,
        title = "Çocuklar için kitaplar",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
    object Second : OnBoarding(
        image = R.drawable.on2,
        title = "Her hayel gücüne uygun",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
    object Third : OnBoarding(
        image = R.drawable.on3,
        title = "Eğitimde destek için",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
}
