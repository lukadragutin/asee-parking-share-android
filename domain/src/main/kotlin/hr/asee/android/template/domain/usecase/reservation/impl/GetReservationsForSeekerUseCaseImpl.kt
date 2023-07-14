package hr.asee.android.template.domain.usecase.reservation.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.repository.ReservationRepository
import hr.asee.android.template.domain.usecase.reservation.GetReservationsForSeekerUseCase

class GetReservationsForSeekerUseCaseImpl(private val reservationRepository: ReservationRepository): GetReservationsForSeekerUseCase {

	override suspend fun invoke(seekerId: Int): Resource<List<Reservation>> {
		return try {
			val reservations = reservationRepository.getReservationsForSeeker(seekerId)
			Resource.Success(reservations)
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}