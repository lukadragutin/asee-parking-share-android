package hr.asee.android.template.compose.ui.common.component.dialog.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
