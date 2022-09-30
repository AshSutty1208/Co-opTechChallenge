package com.trackage.app.ui.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.trackage.app.ui.theme.AppTheme
import com.trackage.app.ui.theme.TrackageSecondary

@ExperimentalMaterialApi
@Composable
fun TFAPopupComposable(onButtonClicked: (String) -> Unit) {
    val codeEntered = remember {
        mutableStateOf("")
    }

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.9f)
    ) {
        Column (Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .background(color = TrackageSecondary)) {
                Image(
                    painter = painterResource(id = R.drawable.trackage_logo_purple),
                    contentDescription = "Trackage logo",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(width = 50.dp, height = 50.dp)
                )
            }

            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 16.dp)) {
                TrackageTextViewBody(text = "You should receive a TFA code to the email you provided, please enter this in the box below.",
                    textAlign = TextAlign.Center)
            }

            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 16.dp)) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = codeEntered.value,
                        onValueChange = { codeEntered.value = it },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(top = 32.dp)) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    TrackageButton(
                        stringResource(R.string.authorize),
                        onClickListener = { onButtonClicked(codeEntered.value) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewTFAComposable() {
    AppTheme {
        TFAPopupComposable({ })
    }
}