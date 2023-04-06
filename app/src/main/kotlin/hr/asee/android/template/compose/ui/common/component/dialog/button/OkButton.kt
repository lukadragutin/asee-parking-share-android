package hr.asee.android.template.compose.ui.common.component.dialog.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import hr.asee.android.template.compose.R

@Composable
fun OkButton(onClick: () -> Unit) {
    DialogButtonLayout(
        label = stringResource(id = R.string.ok_button_label),
        onClick = onClick
    )
}
