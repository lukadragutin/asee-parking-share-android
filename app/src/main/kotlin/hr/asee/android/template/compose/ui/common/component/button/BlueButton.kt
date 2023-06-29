package hr.asee.android.template.compose.ui.common.component.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.LightGray

@Composable
fun BlueButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = AssecoBlue,
    enabled: Boolean = true
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = Color.White,
            disabledBackgroundColor = LightGray,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(15),
        onClick = onClick,
        enabled = enabled
    ) {
        LabelText(
            text = label,
            fontSize = 18.sp,
            color = Color.White
        )
    }

}