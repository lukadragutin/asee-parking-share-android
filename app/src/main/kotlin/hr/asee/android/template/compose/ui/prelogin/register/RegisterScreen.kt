package hr.asee.android.template.compose.ui.prelogin.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.InputField
import hr.asee.android.template.compose.ui.common.component.icon.TextVisibilityIcon
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.ButtonState
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.AssecoYellow
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = hiltViewModel()){
    val nameState by viewModel.nameState.collectAsState()
    val emailState by viewModel.emailState.collectAsState()
    val passwordState by viewModel.passwordState.collectAsState()
    val confirmPasswordState by viewModel.confirmPasswordState.collectAsState()
    val buttonState by viewModel.buttonState.collectAsState()

    DefaultScreenLayout(screenTitle = stringResource(id = R.string.register_screen_register_with_email_label)) {
        RegisterScreenContent(
            nameState = nameState,
            emailState = emailState,
            passwordState = passwordState,
            confirmPasswordState = confirmPasswordState,
            buttonState = buttonState,
            onRegisterClicked = viewModel::register,
            onGoToLoginClicked = viewModel::goToLogin
        )
    }
}

@Composable
fun RegisterScreenContent(
    nameState: InputFieldState,
    emailState: InputFieldState,
    passwordState: InputFieldState,
    confirmPasswordState: InputFieldState,
    buttonState : ButtonState,
    onRegisterClicked: () -> Unit,
    onGoToLoginClicked: () -> Unit
){

    val focusManager = LocalFocusManager.current

    //FIRST AND LAST NAME FIELD

    Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(21.dp)
        ) {

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
            state = nameState,
            label = stringResource(R.string.register_screen_first_and_last_name_field_label),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        //EMAIL FIELD
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
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
                state = emailState,
                label = stringResource(R.string.register_screen_email_field_label),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down)}

                )
            )
            if(emailState.isError){
                Text(
                    text = stringResource(R.string.register_screen_email_not_correct_text_label),
                    color = AssecoYellow,
                    fontSize = 12.sp,
                    fontFamily = Geomanist,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        //PASSWORD FIELD

        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ){
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
                state = passwordState,
                label = stringResource(R.string.register_screen_password_field_label),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down)}
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIconContent = {
                    TextVisibilityIcon(
                        isVisible = passwordVisible,
                        onVisibilityClicked = { passwordVisible = !passwordVisible },
                        tint = AssecoBlue
                    )
                }
            )
            if (passwordState.isError) {
                Text(
                    text = stringResource(R.string.register_screen_password_not_correct_text_label),
                    color = AssecoYellow,
                    fontSize = 12.sp,
                    fontFamily = Geomanist,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        //CONFIRM PASSWORD FIELD

        var confirmPasswordVisible by rememberSaveable { mutableStateOf(false) }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ){
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
                label = stringResource(R.string.register_screen_confirm_password_field_label),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus()}
                ),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIconContent = {
                    TextVisibilityIcon(
                        isVisible = confirmPasswordVisible,
                        onVisibilityClicked = { confirmPasswordVisible = !confirmPasswordVisible },
                        tint = AssecoBlue
                    )
                }
            )
            if (confirmPasswordState.isError) {
                Text(
                    text = stringResource(R.string.register_screen_passwords_not_equal_text_label),
                    color = AssecoYellow,
                    fontSize = 12.sp,
                    fontFamily = Geomanist,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(1.dp))

        Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AssecoBlue,
                    contentColor = Color.White,
                    disabledBackgroundColor = LightGray,
                    disabledContentColor = Color.White
                ),
                onClick = onRegisterClicked,
                enabled = buttonState.isEnabled
            )
            {
            Text(
                text = stringResource(id = R.string.register_screen_register_button_label),
                fontSize = 18.sp,
                fontFamily = Geomanist
            )
            }

        Spacer(modifier = Modifier.height(20.dp))

        //Navigation to Login

        Row(horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.register_screen_link_to_login_field_label),
                color = LightGray,
                fontSize = 16.sp,
                fontFamily = Geomanist
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = stringResource(id = R.string.register_screen_go_to_login_field_label),
                color = MaterialTheme.colors.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Geomanist,
                modifier = Modifier.clickable { onGoToLoginClicked }
            )
        }

        Spacer(Modifier.weight(1f))

        //Asse-co logo
        Image(
            painter = painterResource(R.drawable.asee_black_blue_2),
            contentDescription = null,
            modifier = Modifier.size(width = 157.dp, height = 48.dp)

        )
    }
}








