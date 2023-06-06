package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace

interface GetParkingSpacesUseCase {

    suspend operator fun invoke(): Resource<List<ParkingSpace>>

    enum class GetParkingSpacesError: ErrorType {
        GENERAL_GET_PARKING_SPACES_ERROR
    }
}