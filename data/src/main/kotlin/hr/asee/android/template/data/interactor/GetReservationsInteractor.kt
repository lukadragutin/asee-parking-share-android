package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.response.ApiReservation

interface GetReservationsInteractor {

    suspend operator fun invoke(): List<ApiReservation>
}