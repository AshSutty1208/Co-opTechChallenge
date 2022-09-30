package com.trackage.app.ui.deliveries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sutton.jokeapp.R
import com.trackage.app.trackage_api.models.Delivery
import com.trackage.app.ui.custom.TrackageButton
import com.trackage.app.ui.custom.TrackageTextViewHeader
import com.trackage.app.ui.theme.AppTheme
import com.trackage.app.ui.theme.TrackagePrimary
import com.trackage.app.ui.theme.TrackageSecondaryVariant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeliveriesActivity : ComponentActivity() {
    private val viewModel: DeliveriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                viewModel.populateData()

                val uiState = viewModel.uiState

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DeliveriesUIContainer(uiState.deliveriesToDisplay,
                        uiState.deliveriesCustomerId)
                }
            }
        }
    }
}

@Composable
fun DeliveriesUIContainer(deliveries: List<Delivery>,
                          customerId: Int) {
    var nameOnAccountText by remember { mutableStateOf("") }

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

        // Your deliveries text
        Row(Modifier.fillMaxWidth()) {
            TrackageTextViewHeader(text = "Your Deliveries",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, top = 16.dp))
        }

        // Deliveries Container
        Row(modifier = Modifier.fillMaxWidth()
            , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            DeliveriesList(deliveries)
        }

//
//        //Contact Us Button
//        Row(
//            Modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colors.background)
//                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
//                TrackageButton("Contact Us", onClickListener = onEditButtonClick)
//            }
//        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeliveriesList(deliveries: List<Delivery>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("deliveriesList")
    ) {
        stickyHeader {
            DeliveriesHeader()
        }

        items(deliveries) { delivery ->
            DeliveryRow(delivery = delivery)
            DeliveryRow(delivery = delivery)
            DeliveryRow(delivery = delivery)
            DeliveryRow(delivery = delivery)
        }
    }

}

@Composable
fun DeliveriesHeader() {
    Row(Modifier.fillMaxSize().padding(bottom = 8.dp).background(TrackageSecondaryVariant)) {
        Column(Modifier.weight(0.3f)) {
            Text(text = "Item Location", Modifier.fillMaxSize(), color = Color.White)
        }

        Column(Modifier.weight(0.3f)) {
            Text(text = "Delivered?", Modifier.fillMaxSize(), color = Color.White)
        }

        Column(Modifier.weight(0.4f)) {
            Text(text = "Deliveries", Modifier.fillMaxSize(), color = Color.White)
        }
    }
}

@Composable
fun DeliveryRow(delivery: Delivery) {
    Row(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(0.3f)) {
            Text(text = "O", Modifier.fillMaxSize())
        }

        Column(modifier = Modifier.weight(0.3f)) {
            Text(text = "Yes", Modifier.fillMaxSize())
        }

        Column(modifier = Modifier.weight(0.4f)) {
            TrackageButton("Delivery ${delivery.id}", {})
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun PreviewLoginUiContainer() {
    AppTheme {
        DeliveriesUIContainer(emptyList(), 1)
    }
}