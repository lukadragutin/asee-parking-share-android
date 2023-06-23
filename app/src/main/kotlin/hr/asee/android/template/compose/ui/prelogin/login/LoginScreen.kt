package hr.asee.android.template.compose.ui.prelogin.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.InputField
import hr.asee.android.template.compose.ui.common.component.button.BasicButton
import hr.asee.android.template.compose.ui.common.component.dialog.ScreenStateDialog
import hr.asee.android.template.compose.ui.common.component.icon.InfoIcon
import hr.asee.android.template.compose.ui.common.component.icon.TextVisibilityIcon
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    val emailState by viewModel.emailState.collectAsState()
    val passwordState by viewModel.passwordState.collectAsState()

    DefaultScreenLayout(screenTitle = stringResource(id = R.string.login_screen_welcome_label)) {
        LoginScreenContent(
            emailState = emailState,
            passwordState = passwordState,
            onEmailInfoClicked = viewModel::showEmailInfo,
            onLoginClicked = viewModel::onLoginSuccessful,
        )
    }

    ScreenStateDialog(state = uiState, onDismiss = viewModel::onMessageDismissed)
}

@Composable
fun LoginScreenContent(
    emailState: InputFieldState,
    passwordState: InputFieldState,
    onEmailInfoClicked: () -> Unit,
    onLoginClicked: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(15.dp)) {

        Text(stringResource(id = R.string.login_screen_subtitle_label))

        InputField(
            modifier = Modifier.fillMaxWidth(),
            state = emailState,
            label = stringResource(id = R.string.login_screen_email_field_label),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {/*TODO*/ }),
            trailingIconContent = {
                InfoIcon(onClick = onEmailInfoClicked)
            }
        )

        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        InputField(
            modifier = Modifier.fillMaxWidth(),
            state = passwordState,
            label = stringResource(id = R.string.login_screen_password_field_label),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Go),
            keyboardActions = KeyboardActions(onGo = { onLoginClicked() }),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIconContent = {
                TextVisibilityIcon(isVisible = passwordVisible, onVisibilityClicked = { passwordVisible = !passwordVisible })
            }
        )

        BasicButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            label = stringResource(id = R.string.login_screen_sign_in_button_label),
            onClick = onLoginClicked
        )
    }
}


