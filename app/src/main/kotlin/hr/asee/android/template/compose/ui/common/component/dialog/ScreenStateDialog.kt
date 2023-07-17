package hr.asee.android.template.compose.ui.common.component.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hr.asee.android.template.compose.ui.common.component.dialog.button.CancelButtonReservationPopUp
import hr.asee.android.template.compose.ui.common.component.dialog.button.ProceedButton
import hr.asee.android.template.compose.ui.common.model.state.UiState
import hr.asee.android.template.compose.util.empty

@Composable
fun ScreenStateDialog(modifier: Modifier = Modifier, state: UiState, onDismiss: () -> Unit) {
    if (state is UiState.None || state is UiState.Success) {
        return
    }

    BaseAlertDialog(
        modifier = modifier,
        isLoading = state is UiState.Loading,
        title = state.message?.getTitleResource()?.let { stringResource(id = it) } ?: String.empty(),
        message = state.message?.getMessageResource()?.let { messageId ->
            state.message.getMessageArguments()?.let {
                stringResource(id = messageId, *it.toTypedArray())
            } ?: stringResource(id = messageId)
        } ?: String.empty(),
        buttonsLayout = {
            ProceedButton(onClick = onDismiss)
            CancelButtonReservationPopUp(onClick = onDismiss)
        },
        onDismissRequest = onDismiss
    )
}
