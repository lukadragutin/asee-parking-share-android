package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.repository.ReservationRepository
import hr.asee.android.template.domain.usecase.GetReservationsUseCase

class GetReservationsUseCaseImpl(
    private val reservationRepository: ReservationRepository
): GetReservationsUseCase {

    override suspend fun invoke(): Resource<List<Reservation>> {
        val reservationsList = try {
            reservationRepository.getReservations()
        } catch (throwable: Throwable) {
            return Resource.Error(GetReservationsUseCase.GetReservationsError.GENERAL_GET_RESERVATIONS_ERROR, throwable)
        }

        return Resource.Success(reservationsList)
    }
}