package com.trackage.app.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amazonaws.mobile.auth.core.signin.AuthException
import com.amplifyframework.core.Amplify
import com.trackage.app.trackage_api.repository.TrackageRepository
import com.trackage.app.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val trackageRepository: TrackageRepository,
                                        private val dispatcher: DispatcherProvider
) : ViewModel() {
    var dialogState = MutableLiveData(false)
        private set

    var dialogText = MutableLiveData("")
        private set

    var userLoggedIn = MutableLiveData(false)
        private set

    var loginLoading = MutableLiveData(false)
        private set

    fun loginUser(usersInputStream: InputStream, deliveriesInputStream: InputStream) {
        loginLoading.value = true
        viewModelScope.launch {
            withContext(dispatcher.io()) {
                // Artificial loading to fake api
                delay(2000)
                val user = trackageRepository.putUserDetails(usersInputStream)
                trackageRepository.putUserDeliveries(deliveriesInputStream)

                withContext(dispatcher.main()) {
                    loginLoading.value = false
                    userLoggedIn.value = user
                }
            }
        }
    }

    fun signupUser(email: String, password: String) {
        loginLoading.value = true
        viewModelScope.launch {
            val result = trackageRepository.signupUser(email, password)
        }
    }
}