package hr.asee.android.template.compose.ui.common.component.dialog.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.theme.AssecoBlue

@Composable
fun AllowButton(onClick: () -> Unit) {
    RequestButtonLayout(
        label = stringResource(id = R.string.reserve_parking_space_screen_request_popup_screen_allow_button),
        onClick = onClick,
        color = Color.White,
        contentColor = AssecoBlue
    )
}