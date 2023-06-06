package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.model.common.service.Seeking
import java.time.LocalDateTime

interface AccountRepository {

    suspend fun getAccount(): User

    suspend fun getSeekings(dateStart: LocalDateTime, dateEnd: LocalDateTime): List<Seeking>

    suspend fun getOffers(dateStart: LocalDateTime, dateEnd: LocalDateTime): List<Offer>

    suspend fun getReservations(): List<Reservation>

    suspend fun getParkingSpaces(): List<ParkingSpace>
}