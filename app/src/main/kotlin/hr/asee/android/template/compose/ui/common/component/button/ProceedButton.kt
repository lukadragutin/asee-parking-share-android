package hr.asee.android.template.compose.ui.common.component.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.dialog.button.DialogButtonLayout
import hr.asee.android.template.compose.ui.theme.AssecoBlue

@Composable
fun ProceedButton(onClick: () -> Unit) {
    DialogButtonLayout(
        label = stringResource(id = R.string.proceed_button_label),
        onClick = onClick,
        color = AssecoBlue,
    )
}