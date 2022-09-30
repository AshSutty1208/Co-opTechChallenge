package com.trackage.app.trackage_api.remote

import android.util.Log
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.auth.result.AuthSignInResult
import com.amplifyframework.auth.result.AuthSignUpResult
import com.amplifyframework.auth.result.step.AuthNextSignUpStep
import com.amplifyframework.kotlin.core.Amplify
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
//    suspend fun getUserDetails() = getResult { trackageService.getUserDetails() }
//    suspend fun getUserDeliveries() = getResult { trackageService.getUserDeliveries() }

    suspend fun signUpUser(email: String, password: String) : AuthSignUpResult? {
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .build()

        return try {
            val result = Amplify.Auth.signUp(email, password, options)
            Log.i("AuthQuickStart", "Result: $result")
            result
        } catch (error: AuthException) {
            Log.e("AuthQuickStart", "Sign up failed", error)
            null
        }
    }

    suspend fun loginUser(email: String, password: String) : AuthSignInResult? {
        return try {
            val result = Amplify.Auth.signIn(email, password)
            Log.i("AuthQuickStart", "Result: $result")
            result
        } catch (error: AuthException) {
            Log.e("AuthQuickStart", "Sign up failed", error)
            error.recoverySuggestion
            null
        }
    }
}