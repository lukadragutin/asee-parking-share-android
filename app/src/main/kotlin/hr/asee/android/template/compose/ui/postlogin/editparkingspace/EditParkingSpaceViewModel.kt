package hr.asee.android.template.compose.ui.postlogin.editparkingspace

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.AccountState
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.util.empty
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.usecase.ChangeParkingLocationUseCase
import hr.asee.android.template.domain.usecase.GetParkingSpaceByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditParkingSpaceViewModel @Inject constructor(
    private val getParkingSpaceByIdUseCase: GetParkingSpaceByIdUseCase,
    private val changeParkingLocationUseCase: ChangeParkingLocationUseCase
) : BaseViewModel() {

    private val _parkingNumberState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onParkingNumberTextChange))
    val parkingNumberState: StateFlow<InputFieldState> = _parkingNumberState

    private fun onParkingNumberTextChange(newValue: String) {
        _parkingNumberState.update { it.copy(text = newValue, isError = false) }
    }

//    fun getParkingSpace(id: Int) {
//        runSuspend { getParkingSpaceByIdInternal(id) }
//    }
//
//    private suspend fun getParkingSpaceByIdInternal(id: Int) {
//        getParkingSpaceByIdUseCase(id = id).onFinished(
//            successCallback = this::getParkingSpaceByIdSuccess,
//            errorCallback = this::getParkingSpaceByIdError
//        )
//    }
//
//    private suspend fun getParkingSpaceByIdSuccess(): ParkingSpace? {
//        return getParkingSpaceByIdUseCase(2).data
//    }
//
//    private fun getParkingSpaceByIdError(errorData: ErrorData) {
//        when (errorData.errorType) {
//            GetParkingSpaceByIdUseCase.GetParkingSpaceByIdError.GENERAL_GET_PARKING_SPACE_BY_ID_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
//        }
//    }

    private suspend fun changeParkingLocationInternal(id: Int, newParkingSpace: ParkingSpace) {
        changeParkingLocationUseCase(id, newParkingSpace).onFinished(
            successCallback = this::changeParkingLocationSuccess,
            errorCallback = this::changeParkingLocationError
        )
    }

    private suspend fun changeParkingLocationSuccess(newParkingSpace: ParkingSpace) {
        val parkingSpaces = accountState.value.parkingSpaces
        for (parkingSpace in parkingSpaces!!) {
            if (parkingSpace.id == newParkingSpace.id) {
                parkingSpaces.remove(parkingSpace)
                parkingSpaces.add(newParkingSpace)
            }
        }
    }

    private fun changeParkingLocationError(errorData: ErrorData) {
        when (errorData.errorType) {
            ChangeParkingLocationUseCase.ChangeParkingLocationError.GENERAL_CHANGE_PARKING_LOCATION_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    fun onConfirmClicked(parkingSpace: ParkingSpace) {
        runSuspend { changeParkingLocationInternal(parkingSpace.id, parkingSpace) }
    }

    fun getParkingSpace(accountState: AccountState, id: Int): ParkingSpace? {
        for (parkingSpace in accountState.parkingSpaces!!) {
            if (parkingSpace.id == id) return parkingSpace
        }

        return null
    }

}