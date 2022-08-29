package com.trackage.app.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginState {
    var userEmail by mutableStateOf("")
    var userPassword by mutableStateOf("")
    var isUserLoggedIn by mutableStateOf(false)
    var loginLoading by mutableStateOf(false)
}