package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.response.ApiParkingSpace

interface GetParkingSpacesInteractor {

    suspend operator fun invoke(): List<ApiParkingSpace>
}