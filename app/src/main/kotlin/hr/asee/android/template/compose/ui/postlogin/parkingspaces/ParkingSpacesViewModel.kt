package hr.asee.android.template.compose.ui.postlogin.parkingspaces

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import javax.inject.Inject

@HiltViewModel
class ParkingSpacesViewModel @Inject constructor() : BaseViewModel(){

    fun onParkingSpaceClicked(parkingSpaceId: Int) {
        router.navigateToEditParkingSpaceScreen(parkingSpaceId = parkingSpaceId)
    }

    fun onAddParkingSpaceClicked() {
        router.navigateToAddParkingSpaceScreen()
    }

}