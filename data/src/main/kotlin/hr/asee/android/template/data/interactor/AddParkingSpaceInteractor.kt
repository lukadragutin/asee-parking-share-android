package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.response.ApiParkingSpace

interface AddParkingSpaceInteractor {

    suspend operator fun invoke(apiParkingSpace: ApiParkingSpace): ApiParkingSpace
}