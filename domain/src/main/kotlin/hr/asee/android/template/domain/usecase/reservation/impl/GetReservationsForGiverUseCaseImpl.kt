package hr.asee.android.template.domain.usecase.reservation.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.repository.ReservationRepository
import hr.asee.android.template.domain.usecase.reservation.GetReservationsForGiverUseCase

class GetReservationsForGiverUseCaseImpl(private val reservationRepository: ReservationRepository) : GetReservationsForGiverUseCase {

	override suspend fun invoke(giverId: Int): Resource<List<Reservation>> {
		return try {
			val reservations = reservationRepository.getReservationsForGiver(giverId)
			Resource.Success(reservations)
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}