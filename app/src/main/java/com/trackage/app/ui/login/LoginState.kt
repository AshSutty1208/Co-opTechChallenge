package com.trackage.app.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginState {
    var userEmail by mutableStateOf("ashley.suttondev@gmail.com")
    var userPassword by mutableStateOf("Password123")
    var isUserLoggedIn by mutableStateOf(false)
    var loginLoading by mutableStateOf(false)
    var loginFailed by mutableStateOf(false)
}