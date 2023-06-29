package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.repository.ParkingSpaceRepository
import hr.asee.android.template.domain.usecase.GetParkingSpaceByIdUseCase

class GetParkingSpaceByIdUseCaseImpl(
    private val parkingSpaceRepository: ParkingSpaceRepository
) : GetParkingSpaceByIdUseCase {

    override suspend fun invoke(id: Int): Resource<ParkingSpace> {
        val user = try {
            parkingSpaceRepository.getParkingSpaceById(id = id)
        } catch (throwable: Throwable) {
            return Resource.Error(GetParkingSpaceByIdUseCase.GetParkingSpaceByIdError.GENERAL_GET_PARKING_SPACE_BY_ID_ERROR, throwable)
        }

        return Resource.Success(user)
    }
}