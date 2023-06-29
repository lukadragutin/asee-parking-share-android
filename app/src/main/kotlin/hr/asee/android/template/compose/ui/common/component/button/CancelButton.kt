package hr.asee.android.template.compose.ui.common.component.button

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.dialog.button.DialogButtonLayout
import hr.asee.android.template.compose.ui.theme.Orange

@Composable
fun CancelButton(onClick: () -> Unit) {
    DialogButtonLayout(
        label = stringResource(id = R.string.cancel_button_label),
        onClick = onClick,
        color = MaterialTheme.colors.onPrimary,
        contentColor = Orange
    )
}