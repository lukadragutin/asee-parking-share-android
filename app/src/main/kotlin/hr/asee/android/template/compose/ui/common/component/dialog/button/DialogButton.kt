package hr.asee.android.template.compose.ui.common.component.dialog.button

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import hr.asee.android.template.compose.ui.theme.Purple700

@Composable
fun DialogButtonLayout(
    label: String,
    onClick: () -> Unit,
    color: Color = Purple700,
    contentColor: Color = Color.White
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
    ) {
            Text(
                text = label,
                color = contentColor
            )
    }
}
