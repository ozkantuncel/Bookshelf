package com.ozkan.bookshelf.ui.screens.auth_screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ozkan.bookshelf.firebase.remote.AuthRepository
import com.ozkan.bookshelf.firebase.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
):ViewModel() {
    private val _login: MutableState<UiState<String>> = mutableStateOf(UiState.Empty)
    val loginState: State<UiState<String>>
        get() = _login

    private val _forgotPassword: MutableState<UiState<String>> = mutableStateOf(UiState.Empty)
    val forgotPassword: State<UiState<String>>
        get() = _forgotPassword

    fun login(
        email: String,
        password: String,
        rememberMeState: Boolean
    ) {
        _login.value = UiState.Loading
        repository.loginUser(
            email,
            password,
            rememberMeState
        ) {
            _login.value = it
        }
    }

    fun forgotPassword(email: String) {
        _forgotPassword.value = UiState.Loading
        repository.forgotPassword(
            email
        ) {
            _forgotPassword.value = it
        }
    }

    fun checkEmailAndPasswordState(email: String, password: String): Boolean {
        return !(email.isEmpty() || password.isEmpty())
    }

    fun logout(result: () -> Unit){
        repository.logout(result)
    }
}