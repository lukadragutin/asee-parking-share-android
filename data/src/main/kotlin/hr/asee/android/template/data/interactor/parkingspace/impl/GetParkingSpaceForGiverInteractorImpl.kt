package hr.asee.android.template.data.interactor.parkingspace.impl

import hr.asee.android.template.data.interactor.parkingspace.GetParkingSpaceForGiverInteractor
import hr.asee.android.template.data.model.remote.response.ApiParkingSpace
import hr.asee.android.template.data.network.ParkingSpaceApiService

class GetParkingSpaceForGiverInteractorImpl(private val parkingSpaceApiService: ParkingSpaceApiService): GetParkingSpaceForGiverInteractor {

	override suspend fun invoke(giverId: Int): ApiParkingSpace {
		return parkingSpaceApiService.getParkingSpaceForGiver(giverId)
	}
}