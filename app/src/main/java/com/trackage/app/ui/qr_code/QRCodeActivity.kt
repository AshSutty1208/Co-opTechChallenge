package com.trackage.app.ui.qr_code

import android.os.Bundle
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
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
class QRCodeActivity: ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    QRCodeUIContainer(onEditButtonClick = {})
                }
            }
        }
    }
}

@Composable
fun QRCodeUIContainer(onEditButtonClick: () -> Unit) {
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
            TrackageTextViewHeader(text = "Your QR Code",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, top = 16.dp))
        }

        // Profile Container
        Row(modifier = Modifier.fillMaxWidth()
            , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Box {
                val qrgEncoder = QRGEncoder("sssasdadasdasdasdadsasdada", null, QRGContents.Type.TEXT, 150)
                Image(painter = BitmapPainter(qrgEncoder.encodeAsBitmap().asImageBitmap()),
                    contentDescription = "Your QR code",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                        .height(350.dp))
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PreviewLoginUiContainer() {
    AppTheme {
        QRCodeUIContainer({})
    }
}