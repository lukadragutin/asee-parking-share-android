package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.reservation.GetReservationsForGiverInteractor
import hr.asee.android.template.data.interactor.reservation.GetReservationsForSeekerInteractor
import hr.asee.android.template.data.interactor.reservation.GetReservationsInteractor
import hr.asee.android.template.data.interactor.reservation.PutReservationByIdInteractor
import hr.asee.android.template.domain.mapper.ReservationMapper
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.repository.ReservationRepository

class ReservationRepositoryImpl(
	private val getReservationsInteractor: GetReservationsInteractor,
	private val getReservationsForGiverInteractor: GetReservationsForGiverInteractor,
	private val getReservationsForSeekerInteractor: GetReservationsForSeekerInteractor,
	private val putReservationByIdInteractor: PutReservationByIdInteractor,
	private val reservationMapper: ReservationMapper
) : ReservationRepository {

    override suspend fun getReservations(): List<Reservation> {
        val reservationsList: MutableList<Reservation> = mutableListOf()

        getReservationsInteractor().forEach {
            reservationsList.add(reservationMapper.toReservation(it))
        }

        return reservationsList
    }

	override suspend fun getReservationsForGiver(giverId: Int): List<Reservation> {
		return getReservationsForGiverInteractor(giverId).map(reservationMapper::toReservation)
	}

	override suspend fun getReservationsForSeeker(seekerId: Int): List<Reservation> {
		return getReservationsForSeekerInteractor(seekerId).map(reservationMapper::toReservation)
	}

	override suspend fun putReservationById(reservation: Reservation, id: Int): Reservation {
        return reservationMapper.toReservation(putReservationByIdInteractor(reservationMapper.toApiReservation(reservation), id))
    }
}