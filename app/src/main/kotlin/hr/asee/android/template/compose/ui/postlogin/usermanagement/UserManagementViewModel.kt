package hr.asee.android.template.compose.ui.postlogin.usermanagement

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.domain.model.common.Admin
import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.Role
import hr.asee.android.template.domain.model.common.Seeker
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.usecase.GetAccountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class UserManagementViewModel @Inject constructor(private val getAccountUseCase: GetAccountUseCase) : BaseViewModel() {

	private val _nameState = MutableStateFlow(InputFieldState(text = "", onTextChange = this::onNameTextChange))
	val nameState: StateFlow<InputFieldState> = _nameState

	private val _lastNameState = MutableStateFlow(InputFieldState(text = "", onTextChange = this::onLastNameTextChange))
	val lastNameState: StateFlow<InputFieldState> = _lastNameState

	private val _userRoleState = MutableStateFlow(Role.SEEKER)
	val userRoleState = _userRoleState.asStateFlow()

	private var userId by Delegates.notNull<Int>()

	override fun initializeInternal() {
		showLoading()
		runSuspend {
			getAccountUseCase().onFinished(
				this::getAccountSuccess,
				this::getAccountError
			)
		}
	}

	private fun getAccountSuccess(user: User) {
		_nameState.update { it.copy(text = user.firstName) }
		_lastNameState.update { it.copy(text = user.lastName) }
		_userRoleState.update { getRoleFromUser(user) }
		userId = user.id
		showSuccess()
	}

	private fun getRoleFromUser(user: User): Role {
		return when (user) {
			is Giver -> Role.GIVER
			is Seeker -> Role.SEEKER
			is Admin -> Role.ADMIN
		}
	}

	private fun getAccountError(errorData: ErrorData) {
		showError(CommonMessages.UNEXPECTED_ERROR)
	}

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
		router.navigateToParkingSpacesScreen(userId)
	}
}