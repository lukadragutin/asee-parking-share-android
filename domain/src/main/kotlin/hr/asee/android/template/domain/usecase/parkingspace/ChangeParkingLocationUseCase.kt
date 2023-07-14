package hr.asee.android.template.domain.usecase.parkingspace

import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace

interface ChangeParkingLocationUseCase {

    suspend operator fun invoke(id: Int, newParkingSpace: ParkingSpace): Resource<ParkingSpace>

    enum class ChangeParkingLocationError : ErrorType {
        GENERAL_CHANGE_PARKING_LOCATION_ERROR
    }
}