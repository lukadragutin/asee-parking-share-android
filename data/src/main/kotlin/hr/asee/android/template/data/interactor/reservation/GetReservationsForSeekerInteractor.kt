package hr.asee.android.template.data.interactor.reservation

import hr.asee.android.template.data.model.remote.response.ApiReservation

interface GetReservationsForSeekerInteractor {

	suspend operator fun invoke(seekerId: Int): List<ApiReservation>
}