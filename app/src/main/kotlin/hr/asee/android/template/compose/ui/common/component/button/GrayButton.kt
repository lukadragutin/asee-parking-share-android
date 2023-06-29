package hr.asee.android.template.compose.ui.common.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config.DARK_THEME
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.theme.BackgroundGray

@Composable
fun GrayButton(
    onClick: () -> Unit,
    label: String
) {

    Card(
        backgroundColor = MaterialTheme.colors.secondaryVariant,
        shape = RoundedCornerShape(15),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .clickable(onClick = onClick)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

            LabelText(
                text = label,
                fontSize = 16.sp
            )

            Image(
                painter = painterResource(id = R.mipmap.forward),
                contentDescription = stringResource(id = R.string.settings_button_forward_icon_content_description),
                colorFilter = if (DARK_THEME == true or isSystemInDarkTheme()) ColorFilter.tint(color = Color.White) else null,
            )

        }

    }

}