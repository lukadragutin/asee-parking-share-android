package hr.asee.android.template.domain.usecase.parkingspace.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.repository.ParkingSpaceRepository
import hr.asee.android.template.domain.usecase.parkingspace.GetParkingSpaceForGiverUseCase

class GetParkingSpaceForGiverUseCaseImpl(private val parkingSpaceRepository: ParkingSpaceRepository): GetParkingSpaceForGiverUseCase {

	override suspend fun invoke(giverId: Int): Resource<ParkingSpace> {
		return try {
			val parkingSpace = parkingSpaceRepository.getParkingSpaceByGiver(giverId)
			Resource.Success(parkingSpace)
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}