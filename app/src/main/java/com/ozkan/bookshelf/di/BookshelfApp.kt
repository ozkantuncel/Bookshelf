package com.ozkan.bookshelf.di

 import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BookshelfApp:CoreLibApplication(){
    init {
        instance = this
    }

    companion object {
        lateinit var instance: BookshelfApp

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}