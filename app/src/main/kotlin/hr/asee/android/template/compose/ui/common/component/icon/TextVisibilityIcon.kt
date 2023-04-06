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
fun TextVisibilityIcon(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onVisibilityClicked: () -> Unit,
    tint: Color = MaterialTheme.colors.primary
) {
    Icon(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(bounded = false),
            onClick = onVisibilityClicked
        ),
        painter = painterResource(id = if (isVisible) R.drawable.ic_baseline_visibility_24 else R.drawable.ic_baseline_visibility_off_24),
        contentDescription = stringResource(id = R.string.text_visibility_icon_content_description),
        tint = tint,
    )
}
