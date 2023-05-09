package hr.asee.android.template.compose.ui.prelogin.login

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.ui.common.model.state.UiState
import hr.asee.android.template.compose.ui.prelogin.login.model.LoginMessages
import hr.asee.android.template.compose.util.empty
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {

    private val _emailState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onEmailTextChange))
    val emailState: StateFlow<InputFieldState> = _emailState

    private val _passwordState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onPasswordTextChange))
    val passwordState: StateFlow<InputFieldState> = _passwordState

    private fun onEmailTextChange(newValue: String) {
        _emailState.update { it.copy(text = newValue, isError = false) }
    }

    private fun onPasswordTextChange(newValue: String) {
        _passwordState.update { it.copy(text = newValue, isError = false) }
    }

    override fun onMessageDismissed() {
        _uiState.update { UiState.None }
    }

    fun showEmailInfo() {
        showMessage(LoginMessages.EMAIL_INFO)
    }

    fun login() {
        showLoading()
        runSuspend { loginInternal() }
    }

    private suspend fun loginInternal() {
        loginUseCase(LoginUseCase.LoginRequest(email = emailState.value.text, password = emailState.value.text)).onFinished(
            successCallback = this::onLoginSuccessful,
            errorCallback = this::onLoginError,
        )
    }

    private fun onLoginSuccessful() {
        router.navigateToPostLoginScreen()
    }


    private fun onLoginError(errorData: ErrorData) {
        when (errorData.errorType) {
            LoginUseCase.LoginError.MISSING_PASSWORD_ERROR -> showError(LoginMessages.MISSING_PASSWORD_ERROR)
            LoginUseCase.LoginError.STORE_ACCESS_TOKEN_ERROR -> showError(LoginMessages.STORE_ACCESS_TOKEN_ERROR)
            LoginUseCase.LoginError.USER_NOT_FOUND_ERROR -> showError(LoginMessages.USER_NOT_FOUND_ERROR)
            LoginUseCase.LoginError.MISSING_EMAIL_OR_USERNAME_ERROR -> showError(LoginMessages.MISSING_EMAIL_OR_USERNAME_ERROR)
            else -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    fun signUp() {
        router.navigateToRegistrationScreen()
    }
}
