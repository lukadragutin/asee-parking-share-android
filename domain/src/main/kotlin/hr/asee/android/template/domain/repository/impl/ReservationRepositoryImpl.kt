package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.GetReservationsInteractor
import hr.asee.android.template.data.interactor.PutReservationByIdInteractor
import hr.asee.android.template.domain.mapper.ReservationMapper
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.repository.ReservationRepository

class ReservationRepositoryImpl(
    private val getReservationsInteractor: GetReservationsInteractor,
    private val putReservationByIdInteractor: PutReservationByIdInteractor,
    private val reservationMapper: ReservationMapper
) : ReservationRepository {

    override suspend fun getReservations(): List<Reservation> {
        val reservationsLst: MutableList<Reservation> = mutableListOf()

        getReservationsInteractor().forEach {
            reservationsLst.add(reservationMapper.toReservation(it))
        }

        return reservationsLst
    }

    override suspend fun putReservationById(reservation: Reservation, id: Int): Reservation {
        return reservationMapper.toReservation(putReservationByIdInteractor(reservationMapper.toApiReservation(reservation), id))
    }
}