package com.trackage.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.widget.ContentLoadingProgressBar
import com.amplifyframework.core.Amplify
import com.sutton.jokeapp.R
import com.trackage.app.ui.MainViewModel
import com.trackage.app.ui.custom.TrackageButton
import com.trackage.app.ui.custom.TrackageTextViewHeader
import com.trackage.app.ui.home.HomeActivity
import com.trackage.app.ui.sign_up.SignUpActivity
import com.trackage.app.ui.theme.AppTheme
import com.trackage.app.ui.theme.TrackagePrimary
import com.trackage.app.ui.theme.TrackageSecondary
import com.trackage.app.ui.theme.TrackageSecondaryVariant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val loadingState by viewModel.loginLoading.observeAsState()
                    val userLoggedIn by viewModel.userLoggedIn.observeAsState()

                    LoginUIContainer(onSignInButtonClick = {
                       viewModel.loginUser(resources.assets.open("user.json"),
                           resources.assets.open("deliveries.json"))
                    }, onSignUpButtonClick = {
                        startActivity(Intent(this, SignUpActivity::class.java))
                    })

                    if (loadingState == true) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            Card(
                                Modifier
                                    .width(100.dp)
                                    .height(100.dp), backgroundColor = TrackageSecondaryVariant) {
                                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                            }
                        }
                    }

                    if (userLoggedIn == true) {
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                }
            }
        }
    }
}

@Composable
fun LoginUIContainer(onSignInButtonClick: () -> Unit,
                     onSignUpButtonClick: () -> Unit) {
    var emailAddressText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

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

        // Sign in text
        Row(Modifier.fillMaxWidth()) {
            TrackageTextViewHeader(text = "Sign In",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, top = 16.dp))
        }

        // Email Input
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = emailAddressText,
                onValueChange = { emailAddressText = it },
                label = { Text("Email Address") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email)
            )
        }

        //Password Input
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(value = passwordText,
                onValueChange = { passwordText = it },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password)
            )
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
                TrackageButton(stringResource(R.string.sign_in), onClickListener = onSignInButtonClick)
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)) {
                TrackageButton(stringResource(R.string.sign_up), onClickListener = onSignUpButtonClick, true)
            }
        }
    }
}

/**
 * Shows an AlertDialog with the given text
 *
 * NOTE: There is no preview for this as Google states in the docs AlertDialogs
 *       do not work with Preview
 */
@Composable
fun JokeDialog(dialogText: String,
               onDialogDismissed: (() -> Unit)? = null,
               onDialogButtonPressed: (() -> Unit)? = null) {
    // We create these to remember the dialogs properties i.e. If it is visible
    val rememberText = rememberSaveable {
        mutableStateOf(dialogText)
    }

    AlertDialog(
        onDismissRequest = {
            onDialogDismissed?.invoke()
        },
        title = {
        },
        text = {
            Column(modifier = Modifier
                .fillMaxWidth()) {
                Text(text = rememberText.value)
            }
        },
        buttons = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onDialogButtonPressed?.invoke()
                    }
                ) {
                }
            }
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PreviewLoginUiContainer() {
    AppTheme {
        LoginUIContainer({}, {})
    }
}