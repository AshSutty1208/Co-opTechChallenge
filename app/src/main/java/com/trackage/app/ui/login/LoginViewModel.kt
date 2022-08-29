package com.trackage.app.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trackage.app.trackage_api.repository.TrackageRepository
import com.trackage.app.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val trackageRepository: TrackageRepository,
                                        private val dispatcher: DispatcherProvider
) : ViewModel() {
    var loginState = mutableStateOf(LoginState())
        private set

    fun loginUser() {
        loginState
        viewModelScope.launch {
            withContext(dispatcher.io()) {

                withContext(dispatcher.main()) {
                    loginState.value.isUserLoggedIn
                }
            }
        }
    }
}