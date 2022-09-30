package com.trackage.app.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sutton.jokeapp.R
import com.trackage.app.ui.custom.TrackageTextViewHeader
import com.trackage.app.ui.custom.TrackageWideButton
import com.trackage.app.ui.deliveries.DeliveriesActivity
import com.trackage.app.ui.delivery_preferences.DeliveryPreferencesActivity
import com.trackage.app.ui.profile.ProfileActivity
import com.trackage.app.ui.qr_code.QRCodeActivity
import com.trackage.app.ui.theme.AppTheme
import com.trackage.app.ui.theme.TrackageSecondary
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    HomeUIContainer(onYourDeliveriesButtonClick = {
                        startActivity(Intent(this, DeliveriesActivity::class.java))
                    }, onDeliveryPreferencesButtonClick = {
                        startActivity(Intent(this, DeliveryPreferencesActivity::class.java))
                    }, onYourProfileButtonClick = {
                        startActivity(Intent(this, ProfileActivity::class.java))
                    }, onSendWithTrackageButtonClick = {

                    }, onYourQRCodeButtonClicked = {
                        startActivity(Intent(this, QRCodeActivity::class.java))
                    })
                }
            }
        }
    }
}

@Composable
fun HomeUIContainer(onYourDeliveriesButtonClick: () -> Unit,
                    onDeliveryPreferencesButtonClick: () -> Unit,
                    onYourProfileButtonClick: () -> Unit,
                    onSendWithTrackageButtonClick: () -> Unit,
                    onYourQRCodeButtonClicked: () -> Unit) {
    Column {
        //Trackage Logo
        Row(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = TrackageSecondary)) {
            Image(painter = painterResource(id = R.drawable.trackage_logo_purple),
                contentDescription = "Trackage logo",
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .fillMaxHeight())
        }

        // Home Text
        Row(Modifier.fillMaxWidth()) {
            TrackageTextViewHeader(text = "HOME",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 16.dp))
        }

        //Buttons
        //Deliveries
        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                TrackageWideButton(text = "Your Deliveries", onClickListener = onYourDeliveriesButtonClick)
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                TrackageWideButton(text = "Delivery Preferences", onClickListener = onDeliveryPreferencesButtonClick, true)
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                TrackageWideButton(text = "Your Profile", onClickListener = onYourProfileButtonClick)
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(start = 16.dp, end = 16.dp, top = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                TrackageWideButton(text = "Send with Trackage", onClickListener = onSendWithTrackageButtonClick, true)
            }
        }

        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(start = 16.dp, end = 16.dp, top = 48.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                TrackageWideButton(text = "Your QR Code", onClickListener = onYourQRCodeButtonClicked)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PreviewLoginUiContainer() {
    AppTheme {
        HomeUIContainer({}, {}, {}, {}, {})
    }
}