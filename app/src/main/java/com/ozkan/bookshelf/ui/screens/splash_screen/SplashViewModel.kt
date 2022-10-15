package com.ozkan.bookshelf.ui.screens.splash_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozkan.bookshelf.data.repository.DataStoreRepository
import com.ozkan.bookshelf.ui.MainActivity
import com.ozkan.bookshelf.ui.navigation.Screen
import com.ozkan.bookshelf.util.pref.Prefs
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {


    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.OnBoarding.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->
                if (completed) {
                    if (getRememberMeState()){
                        _startDestination.value = Screen.Home.route
                    }else{
                        _startDestination.value = Screen.Register.route
                    }
                } else {
                    _startDestination.value = Screen.OnBoarding.route
                }
            }
            _isLoading.value = false
        }
    }

    fun getRememberMeState(): Boolean {
        return Prefs.rememberMeState()
    }

}