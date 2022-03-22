package com.trackage.app.ui.custom

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sutton.jokeapp.R
import com.trackage.app.ui.theme.AppTheme
import com.trackage.app.ui.theme.TextColor
import com.trackage.app.ui.theme.TrackagePrimary

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

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewRandomJokeButton() {
    AppTheme {
        TrackageTextViewHeader(text = "Preview Text",
            textAlign = TextAlign.Center)
    }
}