package com.ozkan.bookshelf.ui.screens.auth_screens.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ozkan.bookshelf.firebase.dto.User
import com.ozkan.bookshelf.firebase.remote.AuthRepository
import com.ozkan.bookshelf.firebase.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _register: MutableState<UiState<String>> = mutableStateOf(UiState.Empty)
    val registerState: State<UiState<String>>
        get() = _register

    fun register(
        email: String,
        password: String,
        nameSurname: String
    ) {
        _register.value = UiState.Loading
        repository.registerUser(
            email = email,
            password = password,
            user = User(nameSurname = nameSurname, email = email)
        ) {
            _register.value = it
        }
    }

    fun checkEmailPasswordNameState(email: String, password: String, nameSurname: String): Boolean {
        return !(email.isEmpty() || password.isEmpty() || nameSurname.isEmpty())
    }


}