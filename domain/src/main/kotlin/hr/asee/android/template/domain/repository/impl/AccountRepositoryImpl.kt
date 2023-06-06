package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.GetAccountInteractor
import hr.asee.android.template.data.interactor.GetOffersInteractor
import hr.asee.android.template.data.interactor.GetParkingSpacesInteractor
import hr.asee.android.template.data.interactor.GetReservationsInteractor
import hr.asee.android.template.data.interactor.GetSeekingsInteractor
import hr.asee.android.template.domain.mapper.OfferMapper
import hr.asee.android.template.domain.mapper.ParkingSpaceMapper
import hr.asee.android.template.domain.mapper.ReservationMapper
import hr.asee.android.template.domain.mapper.SeekingMapper
import hr.asee.android.template.domain.mapper.UserMapper
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.model.common.service.Seeking
import hr.asee.android.template.domain.repository.AccountRepository
import java.time.LocalDateTime

class AccountRepositoryImpl(
    private val getAccountInteractor: GetAccountInteractor,
    private val getSeekingsInteractor: GetSeekingsInteractor,
    private val getOffersInteractor: GetOffersInteractor,
    private val getReservationsInteractor: GetReservationsInteractor,
    private val getParkingSpacesInteractor: GetParkingSpacesInteractor,
    private val parkingSpaceMapper: ParkingSpaceMapper,
    private val reservationMapper: ReservationMapper,
    private val offerMapper: OfferMapper,
    private val seekingMapper: SeekingMapper,
    private val userMapper: UserMapper
): AccountRepository {

    override suspend fun getAccount(): User {
        return userMapper.toUser(getAccountInteractor())
    }

    override suspend fun getSeekings(
        dateStart: LocalDateTime,
        dateEnd: LocalDateTime
    ): List<Seeking> {
        val seekingsList: MutableList<Seeking> = mutableListOf()

        getSeekingsInteractor(dateStart = dateStart, dateEnd = dateEnd).forEach {
            seekingsList.add(seekingMapper.toSeeking(it))
        }

        return seekingsList
    }

    override suspend fun getOffers(
        dateStart: LocalDateTime,
        dateEnd: LocalDateTime
    ): List<Offer> {
        val offersList: MutableList<Offer> = mutableListOf()

        getOffersInteractor(dateStart = dateStart, dateEnd = dateEnd).forEach {
            offersList.add(offerMapper.toOffer(it))
        }

        return offersList
    }

    override suspend fun getReservations(): List<Reservation> {
        val reservationsLst: MutableList<Reservation> = mutableListOf()

        getReservationsInteractor().forEach {
            reservationsLst.add(reservationMapper.toReservation(it))
        }

        return reservationsLst
    }

    override suspend fun getParkingSpaces(): List<ParkingSpace> {
        val parkingSpacesList: MutableList<ParkingSpace> = mutableListOf()

        getParkingSpacesInteractor().forEach {
            parkingSpacesList.add(parkingSpaceMapper.toParkingSpace(it))
        }

        return parkingSpacesList
    }
}