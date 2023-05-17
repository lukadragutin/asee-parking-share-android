package hr.asee.android.template.compose.ui.prelogin.register

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.ui.prelogin.login.model.LoginMessages
import hr.asee.android.template.compose.ui.prelogin.register.model.RegisterMessages
import hr.asee.android.template.compose.util.empty
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.usecase.LoginUseCase
import hr.asee.android.template.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class
RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel() {

    private val _nameState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onNameTextChange))
    val nameState: StateFlow<InputFieldState> = _nameState

    private val _emailState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onEmailTextChange))
    val emailState: StateFlow<InputFieldState> = _emailState

    private val _passwordState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onPasswordTextChange))
    val passwordState: StateFlow<InputFieldState> = _passwordState

    private val _confirmpasswordState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onConfpassTextChange))
    val confirmpasswordState: StateFlow<InputFieldState> = _confirmpasswordState

    private fun onNameTextChange(newValue: String) {
        _nameState.update { it.copy(text = newValue, isError = false) }
    }

    private fun onEmailTextChange(newValue: String) {
        _emailState.update { it.copy(text = newValue, isError = false) }
    }

    private fun onPasswordTextChange(newValue: String) {
        _passwordState.update { it.copy(text = newValue, isError = false) }
    }

    private fun onConfpassTextChange(newValue: String) {
        _confirmpasswordState.update { it.copy(text = newValue, isError = false) }
    }

    fun register() {
        showLoading()
        runSuspend { registerInternal() }
    }

    private suspend fun registerInternal() {
        registerUseCase(RegisterUseCase.RegisterRequest(name = nameState.value.text, email = emailState.value.text, password = emailState.value.text, confirmpassword = confirmpasswordState.value.text)).onFinished(
            successCallback = this::onRegisterSuccessful,
            errorCallback = this::onRegisterError,
        )
    }

    private fun onRegisterSuccessful() {
        router.navigateToPostLoginScreen()
    }

    private fun onRegisterError(errorData: ErrorData) {
        when (errorData.errorType) {
            RegisterUseCase.RegisterError.MISSING_PASSWORD_ERROR -> showError(RegisterMessages.MISSING_PASSWORD_ERROR)
            RegisterUseCase.RegisterError.STORE_ACCESS_TOKEN_ERROR -> showError(RegisterMessages.STORE_ACCESS_TOKEN_ERROR)
            RegisterUseCase.RegisterError.USER_NOT_FOUND_ERROR -> showError(RegisterMessages.USER_NOT_FOUND_ERROR)
            RegisterUseCase.RegisterError.MISSING_EMAIL_OR_USERNAME_ERROR -> showError(RegisterMessages.MISSING_EMAIL_OR_USERNAME_ERROR)
            else -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    fun gotologin() {
        router.navigateToLoginScreen()
    }
}