package hr.asee.android.template.compose.ui.common.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.Lifecycle
import hr.asee.android.template.compose.ui.common.lifecycle.LifecycleEventHandler
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.ui.common.modifiers.clearFocusOnKeyboardDismiss

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    state: InputFieldState,
    label: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isSingleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIconContent: @Composable (() -> Unit)? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    LifecycleEventHandler { _, event ->
        if (event == Lifecycle.Event.ON_PAUSE) {
            keyboardController?.hide()
        }
    }

    TextField(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .clearFocusOnKeyboardDismiss()
            .then(modifier),
        value = state.text,
        onValueChange = state.onTextChange,
        label = {
            Text(
                text = label,
                color = if (state.isError) MaterialTheme.colors.error else MaterialTheme.colors.primary.copy(alpha = 0.75f),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.25f),
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        enabled = state.isEnabled,
        trailingIcon = trailingIconContent,
        shape = MaterialTheme.shapes.small,
        singleLine = isSingleLine,
        visualTransformation = visualTransformation
    )
}
