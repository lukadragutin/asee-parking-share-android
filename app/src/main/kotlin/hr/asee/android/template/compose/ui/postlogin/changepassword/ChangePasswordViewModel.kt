package hr.asee.android.template.compose.ui.postlogin.changepassword

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.common.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.util.empty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor() : BaseViewModel() {

    private val _oldPasswordState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onOldPasswordTextChange))
    val oldPasswordState: StateFlow<InputFieldState> = _oldPasswordState

    private val _newPasswordState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onNewPasswordTextChange))
    val newPasswordState: StateFlow<InputFieldState> = _newPasswordState

    private val _confirmPasswordState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onConfirmPasswordTextChange))
    val confirmPasswordState: StateFlow<InputFieldState> = _confirmPasswordState

    private fun onOldPasswordTextChange(newValue: String) {
        _oldPasswordState.update { it.copy(text = newValue, isError = false) }
    }
    private fun onNewPasswordTextChange(newValue: String) {
        _newPasswordState.update { it.copy(text = newValue, isError = false) }
    }
    private fun onConfirmPasswordTextChange(newValue: String) {
        _confirmPasswordState.update { it.copy(text = newValue, isError = false) }
    }

    fun onConfirmClicked() {
        /* TODO */
    }

}