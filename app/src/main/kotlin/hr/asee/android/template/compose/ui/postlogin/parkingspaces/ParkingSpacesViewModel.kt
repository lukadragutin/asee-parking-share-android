package hr.asee.android.template.compose.ui.postlogin.parkingspaces

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ParkingSpacesViewModel @Inject constructor() : BaseViewModel(){

    private val _parkingSpacesState = MutableStateFlow(setOf<ParkingSpace>())
    val parkingSpacesState = _parkingSpacesState.asStateFlow()

    private val _userState = MutableStateFlow(User.EMPTY)
    val userState = _userState.asStateFlow()

    fun onParkingSpaceClicked(parkingSpaceId: Int) {
        router.navigateToEditParkingSpaceScreen(parkingSpaceId = parkingSpaceId)
    }

    fun onAddParkingSpaceClicked() {
        router.navigateToAddParkingSpaceScreen()
    }

}