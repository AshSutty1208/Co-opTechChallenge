package com.trackage.app.ui.sign_up

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SignUpState {
    var userEmail by mutableStateOf("ashley.suttondev@gmail.com")
    var userPassword by mutableStateOf("Password123")
    var firstName by mutableStateOf("Password123")
    var surname by mutableStateOf("Password123")
    var isUserLoggedIn by mutableStateOf(false)
    var loginLoading by mutableStateOf(false)
    var loginFailed by mutableStateOf(false)
}