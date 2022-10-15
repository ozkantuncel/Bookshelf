package com.ozkan.bookshelf.firebase.remote

import com.ozkan.bookshelf.firebase.dto.User
import com.ozkan.bookshelf.firebase.util.UiState

interface AuthRepository {

    fun registerUser(
        email: String,
        password: String,
        user: User,
        result: (UiState<String>) -> Unit
    )

    fun loginUser(
        email: String,
        password: String,
        rememberMeState: Boolean,
        result: (UiState<String>) -> Unit
    )

    fun storeSession(
        isLogin: Boolean,
        id: String,
        rememberMeState: Boolean = false,
        result: (User?) -> Unit
    )

    fun updateUserInfo(
        user: User,
        result: (UiState<String>) -> Unit
    )

    fun forgotPassword(
        email: String,
        result: (UiState<String>) -> Unit
    )

    fun logout(result: () -> Unit)

    fun storeSession(
        id: String,
        result: (User?) -> Unit
    )

    fun getSession(result: (User?) -> Unit)
}