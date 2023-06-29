package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.response.ApiParkingSpace

interface ChangeParkingLocationInteractor {

    suspend operator fun invoke(id: Int, apiParkingSpace: ApiParkingSpace): ApiParkingSpace
}