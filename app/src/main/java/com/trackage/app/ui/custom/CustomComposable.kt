package com.trackage.app.ui.custom

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sutton.jokeapp.R
import com.trackage.app.ui.theme.AppTheme
import com.trackage.app.ui.theme.TrackagePrimary
import com.trackage.app.ui.theme.TrackageSecondary

val ShrimpFont = FontFamily(Font(R.font.shrimp_regular))

@Composable
fun TrackageTextViewHeader(text: String,
                           modifier: Modifier = Modifier,
                           textAlign: TextAlign = TextAlign.Left) {
    Text(text = text.uppercase(),
        fontSize = 32.sp,
        color = TrackagePrimary,
        fontFamily = ShrimpFont,
        modifier = modifier,
        textAlign = textAlign)
}

@Composable
fun TrackageTextViewBody(text: String,
                           modifier: Modifier = Modifier,
                           textAlign: TextAlign = TextAlign.Left) {
    Text(text = text,
        fontSize = 18.sp,
        color = TrackagePrimary,
        modifier = modifier,
        textAlign = textAlign)
}

@Composable
fun TrackageButton(text: String,
                   onClickListener: () -> Unit,
                   useSecondaryColours: Boolean = false) {

    val buttonColor = if(useSecondaryColours) {
        ButtonDefaults.buttonColors(backgroundColor = TrackageSecondary,
            contentColor = Color.White)
    } else {
        ButtonDefaults.buttonColors(backgroundColor = TrackagePrimary)
    }

    Button(onClickListener, modifier = Modifier.width(160.dp), colors = buttonColor) {
        Text(text,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))
    }
}

@Composable
fun TrackageWideButton(text: String,
                   onClickListener: () -> Unit,
                   useSecondaryColours: Boolean = false) {

    val buttonColor = if(useSecondaryColours) {
        ButtonDefaults.buttonColors(backgroundColor = TrackageSecondary,
            contentColor = Color.White)
    } else {
        ButtonDefaults.buttonColors(backgroundColor = TrackagePrimary)
    }

    Button(onClickListener, modifier = Modifier.width(240.dp), colors = buttonColor) {
        Text(text,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewTrackageButton() {
    AppTheme {
        TrackageButton(text = "Button Text",
            onClickListener = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewTrackageButtonSecondary() {
    AppTheme {
        TrackageButton(text = "Button Text",
            onClickListener = {},
            true)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewTrackageWideButton() {
    AppTheme {
        TrackageWideButton(text = "Button Text",
            onClickListener = {})
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewTrackageTextViewHeader() {
    AppTheme {
        TrackageTextViewHeader(text = "Preview Text",
            textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewTrackageTextViewBody() {
    AppTheme {
        TrackageTextViewBody(text = "Preview Text",
            textAlign = TextAlign.Center)
    }
}