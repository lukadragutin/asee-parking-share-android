package hr.asee.android.template.compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R

// Set of Material typography styles to start with

val Geomanist = FontFamily(
    Font(R.font.geomanist_regular)
)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Geomanist,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    h1 = TextStyle(
        fontFamily = Geomanist,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
    ),
    h2 = TextStyle(
        fontFamily = Geomanist,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val alertDialogTitleStyle = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp
)

val alertDialogMessageStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp
)