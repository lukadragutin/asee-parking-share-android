package hr.asee.android.template.compose.ui.common.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.theme.AssecoBlue

@Composable
fun PlusButton(
    onClick: () -> Unit
) {

    Box(modifier = Modifier.clickable(onClick = onClick)) {
        Text(
            text = stringResource(id = R.string.parking_spaces_screen_add_parking_space_button_text_label),
            fontSize = 40.sp,
            color = AssecoBlue,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(start = 25.dp, end = 25.dp, top = 5.dp)
        )
    }

}