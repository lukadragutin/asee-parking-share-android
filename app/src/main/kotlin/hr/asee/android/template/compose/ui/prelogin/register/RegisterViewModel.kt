package hr.asee.android.template.compose.ui.prelogin.register

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.common.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.ButtonState
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.ui.prelogin.register.model.RegisterMessages
import hr.asee.android.template.compose.ui.theme.AssecoYellow
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.util.empty
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel() {

    private val _nameState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onNameTextChange))
    val nameState: StateFlow<InputFieldState> = _nameState

    private val _emailState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onEmailTextChange))
    val emailState: StateFlow<InputFieldState> = _emailState

    private val _passwordState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onPasswordTextChange))
    val passwordState: StateFlow<InputFieldState> = _passwordState

    private val _confirmPasswordState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onConfirmPasswordTextChange))
    val confirmPasswordState: StateFlow<InputFieldState> = _confirmPasswordState

    private val _buttonState = MutableStateFlow(ButtonState())
    val buttonState: StateFlow<ButtonState> = _buttonState

    private fun onNameTextChange(newValue: String) {
        _nameState.update { it.copy(text = newValue, isError = false) }
        isButtonEnabled()
    }

    private fun onEmailTextChange(newValue: String) {
        _emailState.update { it.copy(text = newValue, isError = false) }
        emailValidation()
        isButtonEnabled()
    }

    private fun onPasswordTextChange(newValue: String) {
        _passwordState.update { it.copy(text = newValue, isError = false) }
        passwordValidation()
        isButtonEnabled()
    }

    private fun onConfirmPasswordTextChange(newValue: String) {
        _confirmPasswordState.update { it.copy(text = newValue, isError = false) }
        confirmPasswordValidation()
        isButtonEnabled()
    }

    fun register() {
        showLoading()
        runSuspend { registerInternal() }
    }

    private suspend fun registerInternal() {
        registerUseCase(RegisterUseCase.RegisterRequest(login = nameState.value.text, email = emailState.value.text, password = emailState.value.text)).onFinished(
            successCallback = this::onRegisterSuccessful,
            errorCallback = this::onRegisterError,
        )
    }

    private fun onRegisterSuccessful() {
        router.navigateToLoginScreen()
    }

    private fun onRegisterError(errorData: ErrorData) {
        when (errorData.errorType) {
            RegisterUseCase.RegisterError.MISSING_PASSWORD_ERROR -> showError(RegisterMessages.MISSING_PASSWORD_ERROR)
            RegisterUseCase.RegisterError.MISSING_EMAIL_ERROR -> showError(RegisterMessages.MISSING_EMAIL_ERROR)
            RegisterUseCase.RegisterError.MISSING_CONFIRM_PASSWORD_ERROR -> showError(RegisterMessages.MISSING_CONFIRM_PASSWORD_ERROR)
            RegisterUseCase.RegisterError.MISSING_USERNAME_ERROR -> showError(RegisterMessages.MISSING_USERNAME_ERROR)
            else -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    fun goToLogin() {
        router.navigateToLoginScreen()
    }

    private fun emailValidation(){
        if (emailState.value.text.isNotEmpty() && !hasValidEmailDomain(emailState.value.text)){
            _emailState.update { it.copy(isError = true) }
        }
    }

    private fun passwordValidation(){
        if (passwordState.value.text.isNotEmpty() && (!isValidPasswordFormat(passwordState.value.text) || passwordState.value.text.length < 8)) {
            _passwordState.update { it.copy(isError = true) }
        }
    }

    private fun confirmPasswordValidation(){
        if (confirmPasswordState.value.text.isNotEmpty() || !isValidPasswordFormat(confirmPasswordState.value.text)){
            if (passwordState.value.text != confirmPasswordState.value.text) {
                _confirmPasswordState.update { it.copy(isError = true) }
            }
        }
    }

    private fun isFlagUp() : Boolean{
        if (isValidPasswordFormat(passwordState.value.text) && passwordState.value.text.length >= 8) {
            if (hasValidEmailDomain(emailState.value.text)) {
                if (passwordState.value.text == confirmPasswordState.value.text) {
                    return true
                }
            }
        }
        return false
    }

    private fun isButtonEnabled(){
        if(!(emailState.value.text.isEmpty() || passwordState.value.text.isEmpty() || nameState.value.text.isEmpty() || confirmPasswordState.value.text.isEmpty()) && isFlagUp()){
            if(!buttonState.value.isEnabled){
                _buttonState.update { it.copy(isEnabled = true) }
            }
        }
    }

    private fun isValidPasswordFormat(password: String): Boolean {
        var hasLowercaseLetter = false
        var hasUppercaseLetter = false
        var hasNumber = false
        var hasSpecialCharacter = false

        val lowercaseLetters = ('a'..'z')
        val uppercaseLetters = ('A'..'Z')
        val numbers = ('0'..'9')
        val specialCharacters = setOf('@', '$', '!', '%', '*', '#', '?', '&')

        for (char in password) {
            if (char in lowercaseLetters) {
                hasLowercaseLetter = true
            } else if (char in uppercaseLetters) {
                hasUppercaseLetter = true
            } else if (char in numbers) {
                hasNumber = true
            } else if (char in specialCharacters) {
                hasSpecialCharacter = true
            }
        }

        return hasLowercaseLetter && hasUppercaseLetter && hasNumber && hasSpecialCharacter
    }

    private fun hasValidEmailDomain(email: String): Boolean {
        val emailParts = email.split("@")
        if (emailParts.size == 2) {
            val domain = emailParts[1]
            val domainParts = domain.split(".")
            if (domainParts.size >= 2) {
                val lastDomainPart = domainParts.last()
                return lastDomainPart.length >= 2
            }
        }
        return false
    }

}