package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.repository.ParkingSpaceRepository
import hr.asee.android.template.domain.usecase.ChangeParkingLocationUseCase
import hr.asee.android.template.domain.usecase.GetReservationsUseCase

class ChangeParkingLocationUseCaseImpl(
    private val parkingSpaceRepository: ParkingSpaceRepository
) : ChangeParkingLocationUseCase {

    override suspend fun invoke(id: Int, newParkingSpace: ParkingSpace): Resource<ParkingSpace> {
        val parkingSpace = try {
            parkingSpaceRepository.changeParkingLocation(id, newParkingSpace)
        } catch (throwable: Throwable) {
            return Resource.Error(ChangeParkingLocationUseCase.ChangeParkingLocationError.GENERAL_CHANGE_PARKING_LOCATION_ERROR, throwable)
        }

        return Resource.Success(parkingSpace)
    }
}