package hr.asee.android.template.compose.ui.postlogin.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config.DARK_THEME
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.common.component.button.BackButton
import hr.asee.android.template.compose.ui.common.component.button.GrayButton
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.DarkGray

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {

    var darkTheme = (if (DARK_THEME == null) isSystemInDarkTheme() else DARK_THEME) as Boolean

    AndroidComposeCodingTemplateTheme(darkTheme = darkTheme) {
        Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {

            Spacer(modifier = Modifier.height(10.dp))

            BackButton(onClick = viewModel::onBackButtonClicked)

            DefaultScreenLayout(
                screenTitle = stringResource(id = R.string.settings_screen_title_label),
                background = Color.Transparent,
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Transparent)
            ) {

                SettingsScreenContent(
                    darkTheme = darkTheme,
                    onEditProfileButtonClicked = viewModel::onEditProfileButtonClicked
                )
            }
        }
    }
}

@Composable
fun SettingsScreenContent(
    darkTheme: Boolean,
    onEditProfileButtonClicked: () -> Unit
) {

    var switchToggled by rememberSaveable { mutableStateOf(darkTheme) }

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Card(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            shape = RoundedCornerShape(15),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {

                LabelText(
                    text = stringResource(id = R.string.settings_screen_dark_mode_toggle_label),
                    fontSize = 16.sp
                )

                Switch(
                    checked = switchToggled,
                    onCheckedChange = {
                        switchToggled = !switchToggled
                        DARK_THEME = if (DARK_THEME == null) switchToggled
                        else !DARK_THEME!!
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colors.surface,
                        uncheckedThumbColor = MaterialTheme.colors.surface,
                        checkedTrackColor = AssecoBlue,
                        uncheckedTrackColor = DarkGray
                    )
                )

            }

        }

        GrayButton(
            onClick = onEditProfileButtonClicked,
            label = stringResource(id = R.string.settings_screen_edit_profile_button_label)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
