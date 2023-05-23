package hr.asee.android.template.compose.ui.common.component.icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hr.asee.android.template.compose.R

@Composable
fun SettingsIcon(
    modifier: Modifier = Modifier,
    onSettingsClicked: () -> Unit,
    tint: Color = Color.Black
) {

    Icon(
        modifier = modifier
            .height(32.dp)
            .width(100.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = false),
                onClick = onSettingsClicked
            ),
        painter = painterResource(id = R.drawable.settings),
        contentDescription = stringResource(id = R.string.settings_icon_content_description),
        tint = tint
    )
}