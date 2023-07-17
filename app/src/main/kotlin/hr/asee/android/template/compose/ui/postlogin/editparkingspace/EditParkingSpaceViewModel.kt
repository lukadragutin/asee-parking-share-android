package hr.asee.android.template.compose.ui.postlogin.editparkingspace

import androidx.annotation.MainThread
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.util.empty
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.usecase.parkingspace.ChangeParkingLocationUseCase
import hr.asee.android.template.domain.usecase.parkingspace.GetParkingSpaceByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditParkingSpaceViewModel @Inject constructor(
	private val getParkingSpaceByIdUseCase: GetParkingSpaceByIdUseCase,
	private val changeParkingLocationUseCase: ChangeParkingLocationUseCase
) : BaseViewModel() {

	private val _parkingSpaceState = MutableStateFlow(ParkingSpace.EMPTY)
	val parkingSpaceState = _parkingSpaceState.asStateFlow()

	private val _parkingNumberState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onParkingNumberTextChange))
	val parkingNumberState: StateFlow<InputFieldState> = _parkingNumberState

	private fun onParkingNumberTextChange(newValue: String) {
		_parkingNumberState.update { it.copy(text = newValue, isError = false) }
	}

	private var initializeCalled = false

	@MainThread
	fun initialize(parkingSpaceId: Int) {
		if (initializeCalled) return
		initializeCalled = true

		runSuspend {
			initializeParkingSpace(parkingSpaceId)
		}
	}

	private suspend fun initializeParkingSpace(parkingSpaceId: Int) {
		getParkingSpaceByIdUseCase(parkingSpaceId).onFinished(
			this::getParkingSpaceByIdSuccess,
			this::getParkingSpaceByIdError
		)
	}

	private fun getParkingSpaceByIdSuccess(parkingSpace: ParkingSpace) {
		_parkingSpaceState.update { parkingSpace }
        _parkingNumberState.update { it.copy(text = parkingSpace.location) }
	}

	private fun getParkingSpaceByIdError(errorData: ErrorData) {
		showError(CommonMessages.UNEXPECTED_ERROR)
	}

	private suspend fun changeParkingLocationInternal() {
        val parkingSpace = _parkingSpaceState.value.copy(location = parkingNumberState.value.text)
		changeParkingLocationUseCase(parkingSpace.id, parkingSpace).onFinished(
			successCallback = this::changeParkingLocationSuccess,
			errorCallback = this::changeParkingLocationError
		)
	}

	private fun changeParkingLocationSuccess(newParkingSpace: ParkingSpace) {
	}

	private fun changeParkingLocationError(errorData: ErrorData) {
		when (errorData.errorType) {
			ChangeParkingLocationUseCase.ChangeParkingLocationError.GENERAL_CHANGE_PARKING_LOCATION_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
		}
	}

	fun onConfirmClicked() {
		runSuspend { changeParkingLocationInternal() }
	}
}