package com.trackage.app.trackage_api.remote

import android.util.Log
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.trackage.app.trackage_api.BaseDataSource
import com.trackage.app.trackage_api.testing.OpenForTesting
import javax.inject.Inject

/**
 * Calls the UserService and returns its Result
 */
@OpenForTesting
class TrackageRemoteDataSource @Inject constructor(
    private val trackageService: TrackageService
): BaseDataSource() {
    suspend fun getUserDetails() = getResult { trackageService.getUserDetails() }
    suspend fun getUserDeliveries() = getResult { trackageService.getUserDeliveries() }
    fun signUpUser(email: String, password: String) {
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), "my@email.com")
            .build()
        Amplify.Auth.signUp(email, password, options,
            { Log.i("AuthQuickStart", "Sign up succeeded: $it") },
            { Log.e ("AuthQuickStart", "Sign up failed", it) }
        )
    }
}