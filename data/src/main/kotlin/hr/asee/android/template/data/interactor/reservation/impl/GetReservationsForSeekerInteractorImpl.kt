package hr.asee.android.template.data.interactor.reservation.impl

import hr.asee.android.template.data.interactor.reservation.GetReservationsForSeekerInteractor
import hr.asee.android.template.data.model.remote.response.ApiReservation
import hr.asee.android.template.data.network.ReservationApiService

class GetReservationsForSeekerInteractorImpl(private val reservationApiService: ReservationApiService): GetReservationsForSeekerInteractor {

	override suspend fun invoke(seekerId: Int): List<ApiReservation> {
		return reservationApiService.getReservationsForSeeker(seekerId)
	}
}