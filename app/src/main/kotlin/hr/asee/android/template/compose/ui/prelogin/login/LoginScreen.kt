package hr.asee.android.template.compose.ui.prelogin.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.navigation.Router
import hr.asee.android.template.compose.ui.common.component.InputField
import hr.asee.android.template.compose.ui.common.component.button.BasicButton
import hr.asee.android.template.compose.ui.common.component.dialog.ScreenStateDialog
import hr.asee.android.template.compose.ui.common.component.icon.InfoIcon
import hr.asee.android.template.compose.ui.common.component.icon.TextVisibilityIcon
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.ui.theme.Geomanist

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsState()
    val emailState by viewModel.emailState.collectAsState()
    val passwordState by viewModel.passwordState.collectAsState()

    DefaultScreenLayout(screenTitle = stringResource(id = R.string.login_screen_welcome_label)) {
        LoginScreenContent(
            emailState = emailState,
            passwordState = passwordState,
            onLoginClicked = viewModel::login,
            onSignUpClicked = viewModel::signUp
        )
    }

    ScreenStateDialog(state = uiState, onDismiss = viewModel::onMessageDismissed)
}

@Composable
fun LoginScreenContent(
    emailState: InputFieldState,
    passwordState: InputFieldState,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(21.dp)
    ) {

        val focusManager = LocalFocusManager.current
        //Email field
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(20),
                    color = Color.Transparent
                )
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20)),
            state = emailState,
            label = stringResource(id = R.string.login_screen_email_field_label),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) })
        )

        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        //Password field
        InputField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    shape = RoundedCornerShape(20),
                    color = Color.Transparent
                )
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20)),
            state = passwordState,
            label = stringResource(id = R.string.login_screen_password_field_label),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Go),
            keyboardActions = KeyboardActions(onGo = { onLoginClicked() }),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIconContent = {
                TextVisibilityIcon(
                    isVisible = passwordVisible,
                    onVisibilityClicked = { passwordVisible = !passwordVisible },
                    tint = Color(0xFF2BA1DC)
                )
            }
        )
        
        Spacer(modifier = Modifier.height(1.dp))

        //Login button
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF2BA1DC),
                contentColor = Color.White,
                disabledBackgroundColor = Color(0xFFAAAFAF),
                disabledContentColor = Color.White
            ),
            onClick = onLoginClicked,
            enabled = !(emailState.text.isEmpty() || passwordState.text.isEmpty())
        ) {
            Text(
                text = stringResource(id = R.string.login),
                fontSize = 18.sp,
                fontFamily = Geomanist
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        //Sign up redirection
        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.dont_have_an_account),
                color = Color(0xFFAAAFAF),
                fontSize = 16.sp,
                fontFamily = Geomanist
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(id = R.string.sign_up),
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Geomanist,
                modifier = Modifier.clickable { onSignUpClicked }
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        
        //Asee logo
        Image(
            painter = painterResource(id = R.drawable.asee_logo),
            contentDescription = "logo",
            modifier = Modifier.size(width = 157.dp, height = 48.dp)
        )
    }
}
