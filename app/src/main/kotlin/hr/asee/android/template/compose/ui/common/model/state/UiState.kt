package hr.asee.android.template.compose.ui.common.model.state

import hr.asee.android.template.compose.ui.common.model.Message

sealed class UiState(val message: Message? = null) {
    object None : UiState()
    object Success : UiState()
    object Loading : UiState()
    class Error(message: Message? = null) : UiState(message = message)
    class Info(message: Message) : UiState(message = message)
}
