package com.trackage.app.ui.character_change

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trackage.app.JokeDialog
import com.sutton.jokeapp.R
import com.trackage.app.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterChangeActivity : ComponentActivity() {
    private val viewModel: CharacterChangeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Scaffold(topBar = {
                    Row(
                        Modifier
                            .background(MaterialTheme.colors.primaryVariant)
                            .fillMaxWidth()) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                onBackPressed()
                            }, tint = Color.White)
                    }
                }) {
                    // A surface container using the 'background' color from the theme
                    Surface(modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colors.background) {
                        val dialogState by viewModel.dialogState.observeAsState()
                        val dialogText by viewModel.dialogText.observeAsState()
                        val textFieldValue by viewModel.characterTextValue.observeAsState()
                        val isTextFieldValueValid by viewModel.characterTextInputValid.observeAsState()

                        EnterCharacterNameTextField(textFieldValue = textFieldValue ?: "",
                            onValueChanged = {
                                viewModel.characterTextValue.postValue(it)
                            }, isTextFieldValueValid = isTextFieldValueValid ?: true,
                            onSearchButtonClicked = {
                                viewModel.onSearchButtonClicked(textFieldValue)
                            })

                        if (dialogState == true) {
                            JokeDialog(dialogText = dialogText ?: "Error fetching joke",
                                onDialogButtonPressed = { viewModel.dialogState.postValue(false) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EnterCharacterNameTextField(textFieldValue: String,
                                onValueChanged: (String) -> Unit,
                                isTextFieldValueValid: Boolean,
                                onSearchButtonClicked: () -> Unit) {
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)) {
            Text(modifier = Modifier
                .fillMaxWidth(),
                text = ""
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(value = textFieldValue,
                onValueChange = onValueChanged)
        }

        if (!isTextFieldValueValid) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(modifier = Modifier
                    .fillMaxWidth(),
                    text = "",
                    color = Color.Red
                )
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)) {
            Button(enabled = isTextFieldValueValid, onClick = {
                onSearchButtonClicked.invoke()
            }) {
                Text(text = "")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterNameTextFieldValid() {
    AppTheme {
        EnterCharacterNameTextField(
            textFieldValue = "Enter Character Test",
            onValueChanged = {},
            isTextFieldValueValid = true,
            onSearchButtonClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterNameTextFieldInvalid() {
    AppTheme {
        EnterCharacterNameTextField(
            textFieldValue = "Enter Character Test",
            onValueChanged = {},
            isTextFieldValueValid = false,
            onSearchButtonClicked = {}
        )
    }
}