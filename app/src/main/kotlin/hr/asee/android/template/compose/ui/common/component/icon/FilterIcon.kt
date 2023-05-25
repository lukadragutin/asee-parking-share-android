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
import hr.asee.android.template.compose.ui.theme.AssecoBlue

@Composable
fun FilterIcon(
    modifier: Modifier = Modifier,
    isFiltered: Boolean,
    onFilterClicked: () -> Unit,
    tint: Color = Color.Black
) {
    Icon(
        modifier = modifier
            .height(28.dp)
            .width(100.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = false),
                onClick = { onFilterClicked() }
            ),
        painter = painterResource(id = R.drawable.filter),
        contentDescription = stringResource(id = R.string.filter_icon_content_description),
        tint = if (isFiltered) AssecoBlue else tint
    )
}