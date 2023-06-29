package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace

interface GetParkingSpaceByIdUseCase {

    suspend operator fun invoke(id: Int): Resource<ParkingSpace>

    enum class GetParkingSpaceByIdError : ErrorType {
        GENERAL_GET_PARKING_SPACE_BY_ID_ERROR
    }
}