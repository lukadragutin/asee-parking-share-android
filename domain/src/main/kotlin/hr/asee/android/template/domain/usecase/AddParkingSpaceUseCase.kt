package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace

interface AddParkingSpaceUseCase {

    suspend operator fun invoke(parkingSpace: ParkingSpace): Resource<ParkingSpace>

    enum class AddParkingSpaceError: ErrorType {
        GENERAL_ADD_PARKING_SPACE_ERROR
    }
}