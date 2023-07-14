package hr.asee.android.template.data.interactor.parkingspace

import hr.asee.android.template.data.model.remote.response.ApiParkingSpace

interface GetParkingSpaceByIdInteractor {

    suspend operator fun invoke(id: Int): ApiParkingSpace

}