package hr.asee.android.template.data.interactor.reservation

import hr.asee.android.template.data.model.remote.response.ApiReservation

interface GetReservationsForGiverInteractor {

	suspend operator fun invoke(giverId: Int): List<ApiReservation>
}