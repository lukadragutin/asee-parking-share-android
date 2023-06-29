package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.response.ApiReservation

interface PutReservationByIdInteractor {

    suspend operator fun invoke(apiReservation: ApiReservation, id: Int): ApiReservation
}