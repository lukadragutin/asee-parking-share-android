package hr.asee.android.template.compose.ui.common.component.button

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
        )
    ) {
        Text(text = label, color = MaterialTheme.colors.onPrimary)
    }
}
