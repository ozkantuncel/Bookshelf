package com.ozkan.bookshelf.ui.screens.main_screens.profile_screen

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
class ProfileScreenViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _getUserInfo: MutableState<UiState<User>> = mutableStateOf(UiState.Empty)
    val getUserInfo: State<UiState<User>>
        get() = _getUserInfo

    private val _getUserId: MutableState<String> = mutableStateOf("")
    val getUserId: State<String>
        get() = _getUserId

    init {
        repository.getSession {
            it?.let {
                getUserInfo(it.id)
                _getUserId.value = it.id
            }
        }
    }

    fun updateUserInfo() {
        repository.getSession {
            it?.let {
                getUserInfo(it.id)
            }
        }
    }

    private fun getUserInfo(
        id: String
    ) {
        _getUserInfo.value = UiState.Loading
        repository.getUserInfo(
            id = id,
        ) {
            it?.let {
                _getUserInfo.value =
                    UiState.Success(
                        User(
                            id = it.id,
                            nameSurname = it.nameSurname,
                            email = it.email,
                            profileImageUrl = it.profileImageUrl,
                            bookmarksProducts = it.bookmarksProducts,
                            cartBook = it.cartBook
                        )
                    )
            } ?: run {
                _getUserInfo.value = UiState.Failure(null)
            }
        }
    }

}