package com.ozkan.bookshelf.ui.screens.main_screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ozkan.bookshelf.ui.theme.BookshelfTheme

@Composable
fun HomePage(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BookshelfTheme {
        HomePage("Android")
    }
}