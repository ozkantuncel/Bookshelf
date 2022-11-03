package com.ozkan.bookshelf.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.ozkan.bookshelf.util.FirebaseStorageConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object FirebaseModele {
    @Provides
    @Singleton
    fun provideFirebaseDatabaseInstance(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStroageImageofUserInstance(): StorageReference {
        return FirebaseStorage.getInstance().getReference(FirebaseStorageConstants.USER_IMAGES)
    }

    @Singleton
    @Provides
    fun provideFirebaseStroageImageofBookInstance(): StorageReference {
        return FirebaseStorage.getInstance().getReference(FirebaseStorageConstants.BOOK_IMAGES)
    }

    @Singleton
    @Provides
    fun provideFirebaseStroageInfoofBooksInstance(): StorageReference {
        return FirebaseStorage.getInstance().getReference(FirebaseStorageConstants.BOOK_INFOS)
    }
}