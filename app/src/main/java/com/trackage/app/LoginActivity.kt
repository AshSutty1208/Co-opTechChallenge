package com.trackage.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sutton.jokeapp.R
import com.trackage.app.ui.MainViewModel
import com.trackage.app.ui.character_change.CharacterChangeActivity
import com.trackage.app.ui.custom.TrackageTextViewHeader
import com.trackage.app.ui.theme.AppTheme
import com.trackage.app.ui.theme.TrackagePrimary
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
                    val dialogState by viewModel.dialogState.observeAsState()

                    LoginUIContainer()

                    if (dialogState == true) {
                        JokeDialog(
                            viewModel.dialogText.value ?: "",
                            onDialogButtonPressed = {
                                viewModel.dialogState.value = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoginUIContainer() {
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
        Row(Modifier.fillMaxWidth()) {
            LoginEmailContainer(onValueChange = {})
        }

        //Password Input
        Row(Modifier.fillMaxWidth().padding(top = 16.dp)) {
            LoginPasswordContainer(onValueChange = {})
        }

        //Login Buttons
        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {}) {
                    Text(text = stringResource(R.string.sign_in))
                }
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                Button(onClick = {}) {
                    Text(text = stringResource(R.string.sign_up))
                }
            }
        }
    }
}

@Composable
fun LoginEmailContainer(onValueChange: (String) -> Unit) {
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
fun LoginPasswordContainer(onValueChange: (String) -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(value = "",
            onValueChange = onValueChange,
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password)
        )
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

@Preview(showBackground = true)
@Composable
fun PreviewEmailInput() {
    AppTheme {
        LoginEmailContainer(onValueChange = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPasswordInput() {
    AppTheme {
        LoginPasswordContainer(onValueChange = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PreviewLoginUiContainer() {
    AppTheme {
        LoginUIContainer()
    }
}