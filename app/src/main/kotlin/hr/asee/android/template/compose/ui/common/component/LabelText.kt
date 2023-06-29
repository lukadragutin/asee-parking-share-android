package hr.asee.android.template.compose.ui.common.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import hr.asee.android.template.compose.ui.theme.Geomanist

@Composable
fun LabelText(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = Geomanist,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = MaterialTheme.colors.onBackground,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null
) {

        Text(
            text = text,
            modifier = modifier,
            color = color,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            textDecoration = textDecoration,
            textAlign = textAlign
        )
}