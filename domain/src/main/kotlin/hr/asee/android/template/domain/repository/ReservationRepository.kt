package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.common.service.Reservation

interface ReservationRepository {

    suspend fun getReservations(): List<Reservation>

    suspend fun getReservationsForGiver(giverId: Int): List<Reservation>

    suspend fun getReservationsForSeeker(seekerId: Int): List<Reservation>

    suspend fun putReservationById(reservation: Reservation, id: Int): Reservation
}