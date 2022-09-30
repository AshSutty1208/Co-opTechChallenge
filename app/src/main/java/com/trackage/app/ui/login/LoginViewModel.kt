package com.trackage.app.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.auth.result.step.AuthSignInStep
import com.amplifyframework.auth.result.step.AuthSignUpStep
import com.amplifyframework.core.Amplify
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
        loginState.value.loginLoading = true

        viewModelScope.launch {
            withContext(dispatcher.io()) {
                val result = trackageRepository.loginUser(loginState.value.userEmail,
                    loginState.value.userPassword)

                withContext(dispatcher.main()) {
                    if (result == null) {
                        loginState.value.isUserLoggedIn = false
                        loginState.value.loginFailed = true
                        Log.e("LOGIN_VIEW_MODEL", "RESULT NULL")
                    } else {
                        if (!result.isSignInComplete) {
                            Log.e("LOGIN_VIEW_MODEL", "SIGN UP NOT COMPLETE")
                            loginState.value.isUserLoggedIn = false
                            loginState.value.loginFailed = true
                        } else if (result.nextStep.signInStep == AuthSignInStep.CONFIRM_SIGN_UP) {
                            Log.e("LOGIN_VIEW_MODEL", "CONFIRM NEXT STEP")
                        } else {
                            Log.e("LOGIN_VIEW_MODEL", result.nextStep.signInStep.name)
                        }
                    }

                    loginState.value.loginLoading = false
                }
            }
        }
    }
}