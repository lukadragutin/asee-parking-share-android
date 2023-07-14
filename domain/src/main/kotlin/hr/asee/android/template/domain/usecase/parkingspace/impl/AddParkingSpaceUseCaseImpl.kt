package hr.asee.android.template.domain.usecase.parkingspace.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.repository.ParkingSpaceRepository
import hr.asee.android.template.domain.usecase.parkingspace.AddParkingSpaceUseCase

class AddParkingSpaceUseCaseImpl(
    private val parkingSpaceRepository: ParkingSpaceRepository
) : AddParkingSpaceUseCase {

    override suspend fun invoke(parkingSpace: ParkingSpace): Resource<ParkingSpace> {
        val addedParkingSpace = try {
            parkingSpaceRepository.addParkingSpace(parkingSpace = parkingSpace)
        } catch (throwable: Throwable) {
            return Resource.Error(AddParkingSpaceUseCase.AddParkingSpaceError.GENERAL_ADD_PARKING_SPACE_ERROR, throwable)
        }

        return Resource.Success(addedParkingSpace)
    }
}