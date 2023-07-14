package hr.asee.android.template.compose.ui.postlogin.usermanagement

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserManagementViewModel @Inject constructor() : BaseViewModel() {

	private val _nameState = MutableStateFlow(InputFieldState(text = "", onTextChange = this::onNameTextChange))
	val nameState: StateFlow<InputFieldState> = _nameState

	private val _lastNameState = MutableStateFlow(InputFieldState(text = "", onTextChange = this::onLastNameTextChange))
	val lastNameState: StateFlow<InputFieldState> = _lastNameState

	private fun onNameTextChange(newValue: String) {
		_nameState.update { it.copy(text = newValue, isError = false) }
	}

	private fun onLastNameTextChange(newValue: String) {
		_lastNameState.update { it.copy(text = newValue, isError = false) }
	}

	fun onChangePasswordClicked() {
		router.navigateToChangePasswordScreen()
	}

	fun onYourParkingSpacesClicked() {
		router.navigateToParkingSpacesScreen()
	}
}