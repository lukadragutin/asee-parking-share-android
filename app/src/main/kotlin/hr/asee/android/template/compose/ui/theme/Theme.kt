package hr.asee.android.template.compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = AssecoBlue,
    primaryVariant = Black200,
    secondary = Gray,
    secondaryVariant = Color.Black,
    surface = BackgroundBlack,
    onSurface = Color.White,
    onPrimary = Black200,
    background = BackgroundBlack,
)

private val LightColorPalette = lightColors(
    primary = AssecoBlue,
    primaryVariant = BackgroundGray,
    secondary = DarkGray,
    secondaryVariant = BackgroundGray,
    surface = Color.White,
    onSurface = Color.Black,
    onPrimary = Color.White,
    background = BackgroundGray,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AndroidComposeCodingTemplateTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
