package com.sutton.jokeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModel
import com.sutton.joke_api.data.remote.JokeRemoteDataSource
import com.sutton.joke_api.data.remote.JokeService
import com.sutton.joke_api.data.repository.JokesRepository
import com.sutton.jokeapp.ui.MainViewModel
import com.sutton.jokeapp.ui.character_change.CharacterChangeActivity
import com.sutton.jokeapp.ui.theme.JokeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colors.background) {
                    val dialogState by viewModel.dialogState.observeAsState()

                    ButtonsContainer(onRandomJokeButtonClick = {
                        viewModel.fetchRandomJoke()
                    },
                    onTextInputButtonClick = {
                        startActivity(Intent(this, CharacterChangeActivity::class.java))
                    })

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
fun ButtonsContainer(onRandomJokeButtonClick: () -> Unit,
                     onTextInputButtonClick: () -> Unit) {
    Column {
        RandomJokeButton(onButtonClick = {
            onRandomJokeButtonClick.invoke()
        })

        TextInputButton(onButtonClick = {
            onTextInputButtonClick.invoke()
        })
    }
}

@Composable
fun RandomJokeButton(onButtonClick: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            onButtonClick.invoke()
        }) {
            Text(text = stringResource(R.string.get_random_joke))
        }
    }
}

@Composable
fun TextInputButton(onButtonClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp)) {
        Button(onClick = {
            onButtonClick.invoke()
        }) {
            Text(text = stringResource(R.string.text_input))
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
            Text(text = stringResource(R.string.random_joke))
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
                    Text(text = stringResource(R.string.ok))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewRandomJokeButton() {
    JokeAppTheme {
        RandomJokeButton(onButtonClick = {})
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTextInputButton() {
    JokeAppTheme {
        TextInputButton(onButtonClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewButtonsContainer() {
    JokeAppTheme {
        ButtonsContainer(onRandomJokeButtonClick = {}, onTextInputButtonClick = {})
    }
}