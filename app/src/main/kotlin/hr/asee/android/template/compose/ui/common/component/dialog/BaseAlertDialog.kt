package hr.asee.android.template.compose.ui.common.component.dialog

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import hr.asee.android.template.compose.ui.theme.alertDialogMessageStyle
import hr.asee.android.template.compose.ui.theme.alertDialogTitleStyle

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BaseAlertDialog(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    title: String,
    message: String,
    dismissOnBackPressed: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    buttonsLayout: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) {

    val backgroundColor = animateColorAsState(targetValue = if (isLoading) Color.Transparent else MaterialTheme.colors.background)
    val widthModifier = if (isLoading) Modifier.fillMaxWidth() else Modifier.width(280.dp)

    AlertDialog(
        modifier = modifier
            .animateContentSize()
            .then(widthModifier),
        onDismissRequest = onDismissRequest,
        // 'usePlatformDefaultWidth=false' DialogProperties are required because of a bug where AlertDialog will not resize when content changes.
        // Related issue tracker URL: https://issuetracker.google.com/issues/194911971
        properties = DialogProperties(
            dismissOnBackPress = if (isLoading) false else dismissOnBackPressed,
            dismissOnClickOutside = if (isLoading) false else dismissOnClickOutside,
            usePlatformDefaultWidth = false
        ),
        buttons = {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                buttonsLayout()
            }
        },
        title = if (!isLoading) {
            {
                Text(text = title, style = alertDialogTitleStyle.copy(color = MaterialTheme.colors.onBackground))
            }
        } else null,
        text = if (!isLoading) {
            {
                Text(text = message, style = alertDialogMessageStyle.copy(color = MaterialTheme.colors.onBackground))
            }
        } else null,
        backgroundColor = backgroundColor.value
    )
}
