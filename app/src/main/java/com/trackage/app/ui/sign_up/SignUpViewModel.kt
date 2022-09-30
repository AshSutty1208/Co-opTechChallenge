package com.trackage.app.ui.sign_up

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.trackage.app.trackage_api.repository.TrackageRepository
import com.trackage.app.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val trackageRepository: TrackageRepository,
                                        private val dispatcher: DispatcherProvider
) : ViewModel() {
//    var loginState = mutableStateOf(LoginState())
//        private set

    fun loginUser() {
//        loginState
        viewModelScope.launch {
            withContext(dispatcher.io()) {
                val options = AuthSignUpOptions.builder()
                    .userAttribute(AuthUserAttributeKey.email(), "ashley.suttondev@gmail.com")
                    .build()
                Amplify.Auth.signUp("ashley.suttondev@gmail.com", "Password123", options,
                    { Log.i("AuthQuickStart", "Sign up succeeded: $it") },
                    { Log.e ("AuthQuickStart", "Sign up failed", it) }
                )

//                Amplify.Auth.confirmSignUp(
//                    "my@email.com", "the code you received via email",
//                    { result ->
//                        if (result.isSignUpComplete) {
//                            Log.i("AuthQuickstart", "Confirm signUp succeeded")
//                        } else {
//                            Log.i("AuthQuickstart","Confirm sign up not complete")
//                        }
//                    },
//                    { Log.e("AuthQuickstart", "Failed to confirm sign up", it) }
//                )

                withContext(dispatcher.main()) {
//                    loginState.value.isUserLoggedIn = true
                }
            }
        }
    }
}