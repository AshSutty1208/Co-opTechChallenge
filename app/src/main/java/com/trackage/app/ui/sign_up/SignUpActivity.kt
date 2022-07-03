package com.trackage.app.ui.sign_up

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sutton.jokeapp.R
import com.trackage.app.ui.MainViewModel
import com.trackage.app.ui.custom.TrackageButton
import com.trackage.app.ui.custom.TrackageTextViewHeader
import com.trackage.app.ui.theme.AppTheme
import com.trackage.app.ui.theme.TrackagePrimary
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val loadingState by viewModel.loginLoading.observeAsState()
                val userLoggedIn by viewModel.userLoggedIn.observeAsState()

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SignUpUIContainer(signUpClicked = {
                        viewModel.signupUser()
                    })
                }
            }
        }
    }
}

@Composable
fun SignUpUIContainer(signUpClicked: () -> Unit) {
    Column {
        //Trackage Logo
        Row(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = TrackagePrimary)) {
            Image(painter = painterResource(id = R.drawable.trackage_logo),
                contentDescription = "Trackage logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(36.dp)
                    .fillMaxWidth()
                    .fillMaxHeight())
        }

        // Sign up text
        Row(Modifier.fillMaxWidth()) {
            TrackageTextViewHeader(text = "Sign Up",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, top = 16.dp))
        }

        // Email Input
        Row(Modifier.fillMaxWidth()) {
            EmailContainer(onValueChange = {})
        }

        //Password Input
        Row(Modifier.fillMaxWidth().padding(top = 8.dp)) {
            PasswordContainer(onValueChange = {})
        }

        //Login Buttons
        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                TrackageButton(stringResource(R.string.sign_up), onClickListener = {

                })
            }
        }
    }
}

@Composable
fun EmailContainer(onValueChange: (String) -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(value = "",
            onValueChange = onValueChange,
            label = { Text("Email Address") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email)
        )
    }
}

@Composable
fun PasswordContainer(onValueChange: (String) -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(value = "",
            onValueChange = onValueChange,
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmailInput() {
    AppTheme {
        EmailContainer(onValueChange = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordInput() {
    AppTheme {
        PasswordContainer(onValueChange = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PreviewLoginUiContainer() {
    AppTheme {
        SignUpUIContainer()
    }
}