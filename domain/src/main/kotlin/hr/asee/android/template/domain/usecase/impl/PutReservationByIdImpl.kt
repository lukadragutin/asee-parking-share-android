package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.repository.ReservationRepository
import hr.asee.android.template.domain.usecase.AddParkingSpaceUseCase
import hr.asee.android.template.domain.usecase.PutReservationByIdUseCase

class PutReservationByIdUseCaseImpl(
    private val reservationRepository: ReservationRepository
) : PutReservationByIdUseCase {

    override suspend fun invoke(reservation: Reservation, id: Int): EmptyResource {
        val addedParkingSpace = try {
            reservationRepository.putReservationById(reservation, id)
        } catch (throwable: Throwable) {
            return Resource.Error(AddParkingSpaceUseCase.AddParkingSpaceError.GENERAL_ADD_PARKING_SPACE_ERROR, throwable)
        }

        return Resource.Success.empty()
    }
}