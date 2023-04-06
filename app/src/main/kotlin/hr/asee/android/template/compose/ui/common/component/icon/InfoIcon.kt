package hr.asee.android.template.compose.ui.common.component.icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import hr.asee.android.template.compose.R

@Composable
fun InfoIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    tint: Color = MaterialTheme.colors.primary
) {
    Icon(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = false),
            onClick = onClick,
        ),
        painter = painterResource(id = R.drawable.ic_outline_info_24),
        contentDescription = stringResource(id = R.string.info),
        tint = tint,
    )
}
