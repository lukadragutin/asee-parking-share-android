package hr.asee.android.template.data.interactor.parkingspace

import hr.asee.android.template.data.model.remote.response.ApiParkingSpace

interface GetParkingSpaceForGiverInteractor {

	suspend operator fun invoke(giverId: Int): ApiParkingSpace
}