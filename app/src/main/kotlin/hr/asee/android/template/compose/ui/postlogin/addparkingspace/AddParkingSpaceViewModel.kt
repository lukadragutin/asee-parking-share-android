package hr.asee.android.template.compose.ui.postlogin.addparkingspace

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.state.AccountState
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.util.empty
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.usecase.parkingspace.AddParkingSpaceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddParkingSpaceViewModel @Inject constructor(
    private val addParkingSpaceUseCase: AddParkingSpaceUseCase
) : BaseViewModel() {

    private val _parkingNumberState = MutableStateFlow(InputFieldState(text = String.empty(), onTextChange = this::onParkingNumberTextChange))
    val parkingNumberState: StateFlow<InputFieldState> = _parkingNumberState

    private fun onParkingNumberTextChange(newValue: String) {
        _parkingNumberState.update { it.copy(text = newValue, isError = false) }
    }

    fun onAddParkingSpaceClicked(parkingSpace: ParkingSpace) {
//        if (accountState.value.parkingSpaces?.contains(parkingSpace) == true) return
//        val parkingSpaces = accountState.value.parkingSpaces
//        parkingSpaces?.add(parkingSpace)
//        _accountState.update {it.copy(parkingSpaces = parkingSpaces) }

    }

    fun getParkingSpace(accountState: AccountState, id: Int): ParkingSpace? {
        for (parkingSpace in accountState.parkingSpaces!!) {
            if (parkingSpace.id == id) return parkingSpace
        }

        return null
    }

}