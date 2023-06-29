package hr.asee.android.template.compose.ui.common.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config.DARK_THEME

@Composable
fun BackButton(
    onClick: () -> Unit
) {

    Box {
        Image(
            painter = painterResource(id = R.mipmap.forward),
            contentDescription = stringResource(
                id = R.string.back_button_icon_content_description
            ),
            colorFilter = if ((DARK_THEME == true) or isSystemInDarkTheme()) ColorFilter.tint(color = Color.White) else null,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .graphicsLayer {
                    rotationZ = 180f
                }
                .padding(15.dp)
                .clickable(onClick = onClick)
        )
    }

}