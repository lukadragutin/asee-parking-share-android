package hr.asee.android.template.compose.ui.postlogin.parkingspaces

import android.util.Log
import androidx.annotation.MainThread
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.common.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.usecase.parkingspace.GetParkingSpaceForGiverUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ParkingSpacesViewModel @Inject constructor(private val getParkingSpaceForGiverUseCase: GetParkingSpaceForGiverUseCase) : BaseViewModel(){

    private val _parkingSpaceState = MutableStateFlow(ParkingSpace.EMPTY)
    val parkingSpaceState = _parkingSpaceState.asStateFlow()

    private var initializeCalled = false

    @MainThread
    fun initialize(userId: Int) {
        if(initializeCalled) return
        initializeCalled = true

        getParkingSpace(userId)
    }

    private fun getParkingSpace(userId: Int) {
        runSuspend {
            getParkingSpaceForGiverUseCase(userId).onFinished(
                this::getParkingSpaceForGiverSuccess,
                this::getParkingSpaceForGiverError
            )
        }
    }

    private fun getParkingSpaceForGiverSuccess(parkingSpace: ParkingSpace) {
        _parkingSpaceState.update { parkingSpace }
        Log.d("luka_log", "Parking space state updates with $parkingSpace")
    }

    private fun getParkingSpaceForGiverError(errorData: ErrorData) {
        showError(CommonMessages.UNEXPECTED_ERROR)
    }

    fun onParkingSpaceClicked(parkingSpaceId: Int) {
        router.navigateToEditParkingSpaceScreen(parkingSpaceId = parkingSpaceId)
    }

    fun onAddParkingSpaceClicked() {
        router.navigateToAddParkingSpaceScreen()
    }

}