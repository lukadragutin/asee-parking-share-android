package hr.asee.android.template.compose.ui.common.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import hr.asee.android.template.compose.ui.theme.Geomanist

@Composable
fun LabelText(
    text: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = Geomanist,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = Color.Unspecified
) {

    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = fontFamily
    )
}