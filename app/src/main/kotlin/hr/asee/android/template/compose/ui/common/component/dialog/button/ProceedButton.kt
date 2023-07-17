package hr.asee.android.template.compose.ui.common.component.dialog.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.theme.AssecoBlue

@Composable
fun ProceedButton(onClick: () -> Unit) {
    DialogButtonLayout(
        label = stringResource(id = R.string.parking_manager_screen_cancel_reservation_popup_screen_proceed_button),
        onClick = onClick,
        color = AssecoBlue,
    )
}