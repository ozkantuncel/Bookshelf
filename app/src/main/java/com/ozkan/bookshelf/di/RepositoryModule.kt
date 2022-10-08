package com.ozkan.bookshelf.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /*@Provides
    @Singleton
    fun provideAuthRepository(
        database:FirebaseFirestore,
        auth: FirebaseAuth,
        gson: Gson
    ):AuthRe*/
}