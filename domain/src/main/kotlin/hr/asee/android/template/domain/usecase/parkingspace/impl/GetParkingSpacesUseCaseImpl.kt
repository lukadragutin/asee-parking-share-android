package hr.asee.android.template.domain.usecase.parkingspace.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.repository.ParkingSpaceRepository
import hr.asee.android.template.domain.usecase.parkingspace.GetParkingSpacesUseCase

class GetParkingSpacesUseCaseImpl(
    private val parkingSpaceRepository: ParkingSpaceRepository
): GetParkingSpacesUseCase {

    override suspend fun invoke(): Resource<List<ParkingSpace>> {
        val parkingSpacesList = try {
            parkingSpaceRepository.getParkingSpaces()
        } catch (throwable: Throwable) {
            return Resource.Error(GetParkingSpacesUseCase.GetParkingSpacesError.GENERAL_GET_PARKING_SPACES_ERROR, throwable)
        }

        return Resource.Success(parkingSpacesList)
    }
}