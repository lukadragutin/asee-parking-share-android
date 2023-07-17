package hr.asee.android.template.compose.ui.common.model.state

import androidx.compose.ui.graphics.Color

data class InputFieldState(
    val isEnabled: Boolean = true,
    val isVisible: Boolean = true,
    var isError: Boolean = false,
    val text: String,
    val onTextChange: (String) -> Unit
)
