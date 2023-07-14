package hr.asee.android.template.data.interactor.reservation.impl

import hr.asee.android.template.data.interactor.reservation.GetReservationsForGiverInteractor
import hr.asee.android.template.data.model.remote.response.ApiReservation
import hr.asee.android.template.data.network.ReservationApiService

class GetReservationsForGiverInteractorImpl(private val reservationApiService: ReservationApiService): GetReservationsForGiverInteractor {

	override suspend fun invoke(giverId: Int): List<ApiReservation> {
		return reservationApiService.getReservationsForGiver(giverId)
	}
}