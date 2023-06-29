package hr.asee.android.template.compose.ui.postlogin.usermanagement

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.util.empty
import hr.asee.android.template.domain.model.common.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserManagementViewModel @Inject constructor() : BaseViewModel(){

    private val _nameState = MutableStateFlow(InputFieldState(text = accountState.value.user!!.firstName, onTextChange = this::onNameTextChange))
    val nameState: StateFlow<InputFieldState> = _nameState

    private val _lastNameState = MutableStateFlow(InputFieldState(text = accountState.value.user!!.lastName, onTextChange = this::onLastNameTextChange))
    val lastNameState: StateFlow<InputFieldState> = _lastNameState

    private fun onNameTextChange(newValue: String) {
        _nameState.update { it.copy(text = newValue, isError = false) }
        _accountState.update { it.copy(user = User.changeFirstName(user = it.user as User, newName = newValue)) }
    }

    private fun onLastNameTextChange(newValue: String) {
        _lastNameState.update { it.copy(text = newValue, isError = false) }
        _accountState.update { it.copy(user = User.changeLastName(user = it.user as User, newName = newValue)) }
    }

    fun onChangePasswordClicked() {
        router.navigateToChangePasswordScreen()
    }

    fun onYourParkingSpacesClicked() {
        router.navigateToParkingSpacesScreen()
    }
}