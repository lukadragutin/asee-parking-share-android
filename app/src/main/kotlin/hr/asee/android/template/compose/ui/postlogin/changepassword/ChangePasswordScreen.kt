package hr.asee.android.template.compose.ui.postlogin.changepassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.ui.common.component.InputField
import hr.asee.android.template.compose.ui.common.component.button.BackButton
import hr.asee.android.template.compose.ui.common.component.button.BlueButton
import hr.asee.android.template.compose.ui.common.component.icon.TextVisibilityIcon
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.ui.postlogin.settings.SettingsScreenContent
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import hr.asee.android.template.compose.ui.theme.AssecoBlue

@Composable
fun ChangePasswordScreen(viewModel: ChangePasswordViewModel = hiltViewModel()) {

    val oldPasswordState by viewModel.oldPasswordState.collectAsState()
    val newPasswordState by viewModel.newPasswordState.collectAsState()
    val confirmPasswordState by viewModel.confirmPasswordState.collectAsState()

    AndroidComposeCodingTemplateTheme(
        darkTheme = (if (Config.DARK_THEME == null) isSystemInDarkTheme() else Config.DARK_THEME) as Boolean
    ) {

        Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {

            Spacer(modifier = Modifier.height(10.dp))

            BackButton(viewModel::goBack)

            DefaultScreenLayout(
                screenTitle = stringResource(id = R.string.change_password_screen_title_label),
                background = Color.Transparent,
                modifier = Modifier
                    .fillMaxHeight()
            ) {

                ChangePasswordScreenContent(
                    onConfirmClicked = viewModel::onConfirmClicked,
                    oldPasswordState = oldPasswordState,
                    newPasswordState = newPasswordState,
                    confirmPasswordState = confirmPasswordState
                )
            }
        }
    }

}

@Composable
fun ChangePasswordScreenContent(
    onConfirmClicked: () -> Unit,
    oldPasswordState: InputFieldState,
    newPasswordState: InputFieldState,
    confirmPasswordState: InputFieldState
) {

    val focusManager = LocalFocusManager.current
    var oldPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var newPasswordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.SpaceBetween) {

        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(20),
                    color = Color.Transparent
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = RoundedCornerShape(20)
                ),
            state = oldPasswordState,
            label = stringResource(id = R.string.change_password_screen_old_password_field_label),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
            visualTransformation = if (oldPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIconContent = {
                TextVisibilityIcon(
                    isVisible = oldPasswordVisible,
                    onVisibilityClicked = { oldPasswordVisible = !oldPasswordVisible },
                    tint = AssecoBlue
                )
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(20),
                    color = Color.Transparent
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = RoundedCornerShape(20)
                ),
            state = newPasswordState,
            label = stringResource(id = R.string.change_password_screen_new_password_field_label),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
            visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIconContent = {
                TextVisibilityIcon(
                    isVisible = newPasswordVisible,
                    onVisibilityClicked = { newPasswordVisible = !newPasswordVisible },
                    tint = AssecoBlue
                )
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(20),
                    color = Color.Transparent
                )
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = RoundedCornerShape(20)
                ),
            state = confirmPasswordState,
            label = stringResource(id = R.string.change_password_screen_confirm_password_field_label),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(onGo = { onConfirmClicked() }),
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIconContent = {
                TextVisibilityIcon(
                    isVisible = confirmPasswordVisible,
                    onVisibilityClicked = { confirmPasswordVisible = !confirmPasswordVisible },
                    tint = AssecoBlue
                )
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        BlueButton(
            label = stringResource(id = R.string.change_password_screen_confirm_button_label),
            onClick = onConfirmClicked
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordPreview() {
    ChangePasswordScreen()
}