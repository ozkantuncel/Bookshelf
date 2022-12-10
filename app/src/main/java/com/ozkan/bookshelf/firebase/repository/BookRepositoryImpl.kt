package com.ozkan.bookshelf.firebase.repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.ozkan.bookshelf.firebase.dto.Book
import com.ozkan.bookshelf.firebase.remote.BookRepository
import com.ozkan.bookshelf.firebase.util.UiState
import com.ozkan.bookshelf.util.FirebaseFireStoreConstants
import com.google.firebase.firestore.Query
import com.ozkan.bookshelf.util.FireStoreDocumentField

class BookRepositoryImpl(
    val database: FirebaseFirestore,
    val storageReference: StorageReference
) : BookRepository {
    override fun getBooks(result: (UiState<List<Book>>) -> Unit) {
        database.collection(FirebaseFireStoreConstants.BOOK)
            .orderBy(FireStoreDocumentField.BOOK_ID, Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener {
                val books = arrayListOf<Book>()
                for (doc in it) {
                    val book = doc.toObject(Book::class.java)
                    books.add(book)
                }
                result.invoke(
                    UiState.Success(books)
                )
            }.addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun addBookToMark(
        book: Book,
        userId: String,
        result: (UiState<String>) -> Unit
    ) {
        database.collection(FirebaseFireStoreConstants.USERS)
            .document(userId)
            .update("bookmarksProducts", FieldValue.arrayUnion(book))
            .addOnSuccessListener {
                result.invoke(UiState.Success("Book has been mark successfully"))
            }.addOnFailureListener {
                result.invoke(UiState.Failure(it.localizedMessage))
            }
    }

    override fun deleteBookMark(
        book: Book,
        userId: String,
        result: (UiState<String>) -> Unit
    ) {
        database.collection(FirebaseFireStoreConstants.USERS)
            .document(userId)
            .update("bookmarksProducts", FieldValue.arrayRemove(book))
            .addOnSuccessListener {
                result.invoke(UiState.Success("Book has been remove mark successfully"))
            }.addOnFailureListener {
                UiState.Failure(it.localizedMessage)
            }
    }

}