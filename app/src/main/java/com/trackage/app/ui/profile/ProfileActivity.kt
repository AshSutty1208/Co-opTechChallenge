package com.trackage.app.ui.profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sutton.jokeapp.R
import com.trackage.app.ui.custom.TrackageButton
import com.trackage.app.ui.custom.TrackageTextViewHeader
import com.trackage.app.ui.theme.AppTheme
import com.trackage.app.ui.theme.TrackagePrimary
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ProfileUIContainer(onEditButtonClick = {})
                }
            }
        }
    }
}

@Composable
fun ProfileUIContainer(onEditButtonClick: () -> Unit) {
    var nameOnAccountText by remember { mutableStateOf("") }
    var emailAddressText by remember { mutableStateOf("") }
    var phoneNumberText by remember { mutableStateOf("") }

    Column {
        //Trackage Logo
        Row(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = TrackagePrimary)) {
            Image(painter = painterResource(id = R.drawable.trackage_logo),
                contentDescription = "Trackage logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .fillMaxHeight())
        }

        // Sign in text
        Row(Modifier.fillMaxWidth()) {
            TrackageTextViewHeader(text = "Your Profile",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, top = 16.dp))
        }
        
        // Profile Container
        Row(modifier = Modifier.fillMaxWidth()
            , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Box {
                OutlinedTextField(
                    value = "Ashley Sutton",
                    onValueChange = { nameOnAccountText = it },
                    label = { Text("Name On Account") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    modifier = Modifier.padding(end = 16.dp, start = 8.dp),
                    enabled = false
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            , verticalAlignment = Alignment.CenterVertically,  horizontalArrangement = Arrangement.Center) {
            Box(Modifier.padding(end = 16.dp, start = 8.dp)) {
                OutlinedTextField(
                    value = "ashley.suttondev@outlook.com",
                    onValueChange = { emailAddressText = it },
                    label = { Text("Email Address") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    enabled = false
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp)
            , verticalAlignment = Alignment.CenterVertically,  horizontalArrangement = Arrangement.Center) {
            Box(Modifier.padding(end = 16.dp, start = 8.dp)) {
                OutlinedTextField(
                    value = "077828394012",
                    onValueChange = { phoneNumberText = it },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    enabled = false
                )
            }
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
                TrackageButton("Edit", onClickListener = onEditButtonClick)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PreviewLoginUiContainer() {
    AppTheme {
        ProfileUIContainer({})
    }
}