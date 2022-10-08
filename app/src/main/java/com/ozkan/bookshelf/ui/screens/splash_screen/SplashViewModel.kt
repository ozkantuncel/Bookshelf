package com.ozkan.bookshelf.ui.screens.splash_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozkan.bookshelf.data.repository.DataStoreRepository
import com.ozkan.bookshelf.ui.navigation.Screen
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

 class SplashViewModel   : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.Welcome.route)
    val startDestination: State<String> = _startDestination

    /*init {
        viewModelScope.launch {
            _startDestination.value = Screen.Home.route
            _isLoading.value = false
        }
    }*/
}