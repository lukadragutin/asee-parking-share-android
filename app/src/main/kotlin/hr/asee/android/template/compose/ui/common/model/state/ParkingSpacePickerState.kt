package hr.asee.android.template.compose.ui.common.model.state

import hr.asee.android.template.domain.model.common.service.ParkingSpace

data class ParkingSpacePickerState(
    var selectedOption: ParkingSpace,
    var radioOptions: List<ParkingSpace>
)