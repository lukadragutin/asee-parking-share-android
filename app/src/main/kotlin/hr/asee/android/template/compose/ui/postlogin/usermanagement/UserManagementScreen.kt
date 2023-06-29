package hr.asee.android.template.compose.ui.postlogin.usermanagement

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.ui.common.component.InputField
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.common.component.button.BackButton
import hr.asee.android.template.compose.ui.common.component.button.GrayButton
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.ui.postlogin.home.contents.ProfilePicture
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import hr.asee.android.template.compose.ui.theme.AssecoBlue

@Composable
fun UserManagementScreen(viewModel: UserManagementViewModel = hiltViewModel()) {

    val nameState by viewModel.nameState.collectAsState()
    val lastNameState by viewModel.lastNameState.collectAsState()

    AndroidComposeCodingTemplateTheme(
        darkTheme = (if (Config.DARK_THEME == null) isSystemInDarkTheme() else Config.DARK_THEME) as Boolean
    ) {

        Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {

            Spacer(modifier = Modifier.height(10.dp))

            BackButton(onClick = viewModel::goBack)

            DefaultScreenLayout(
                screenTitle = stringResource(id = R.string.user_management_screen_title_label),
                background = Color.Transparent,
                modifier = Modifier
                    .fillMaxHeight()
            ) {

                UserManagementScreenContent(
                    nameState = nameState,
                    lastNameState = lastNameState,
                    onChangePasswordClicked = viewModel::onChangePasswordClicked,
                    onYourParkingSpacesClicked = viewModel::onYourParkingSpacesClicked
                )
            }
        }

    }

}

@Composable
fun UserManagementScreenContent(
    nameState: InputFieldState,
    lastNameState: InputFieldState,
    onChangePasswordClicked: () -> Unit,
    onYourParkingSpacesClicked: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        
        Spacer(modifier = Modifier.height(10.dp))

        ProfilePicture(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(30.dp))

        val focusManager = LocalFocusManager.current
        val unfocusedBorderColor = MaterialTheme.colors.onSurface
        var nameFieldBorderColor by remember { mutableStateOf(unfocusedBorderColor) }
        var lastNameFieldBorderColor by remember { mutableStateOf(unfocusedBorderColor) }

        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(20),
                    color = Color.Transparent
                )
                .onFocusChanged {
                    nameFieldBorderColor = if (it.isFocused) AssecoBlue else unfocusedBorderColor
                }
                .border(
                    width = 1.dp,
                    color = nameFieldBorderColor,
                    shape = RoundedCornerShape(20)
                ),
            state = nameState,
            label = stringResource(id = R.string.user_management_screen_name_field_label),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) })
        )

        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(20),
                    color = Color.Transparent
                )
                .onFocusChanged {
                    lastNameFieldBorderColor =
                        if (it.isFocused) AssecoBlue else unfocusedBorderColor
                }
                .border(
                    width = 1.dp,
                    color = lastNameFieldBorderColor,
                    shape = RoundedCornerShape(20)
                ),
            state = lastNameState,
            label = stringResource(id = R.string.user_management_screen_last_name_field_label),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) })
        )
        
        GrayButton(
            onClick = onChangePasswordClicked,
            label = stringResource(id = R.string.user_management_screen_change_password_button_label)
        )

        LabelText(
            text = stringResource(id = R.string.user_management_screen_parking_information_label),
            fontSize = 20.sp
        )
        
        GrayButton(
            onClick = onYourParkingSpacesClicked,
            label = stringResource(id = R.string.user_management_screen_your_parking_spaces_button_label)
        )

    }

}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    UserManagementScreen()
}