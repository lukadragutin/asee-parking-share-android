package hr.asee.android.template.compose.ui.postlogin.reserveparkingspace

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.state.AccountState
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.usecase.PutReservationByIdUseCase
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ReserveParkingSpaceViewModel @Inject constructor(
    private val putReservationByIdUseCase: PutReservationByIdUseCase
) : BaseViewModel() {

    fun getOffer(accountState: AccountState, id: Int) : Offer? {
        for (offer in accountState.offers!!) {
            if (offer.id == id) return offer
        }

        return null
    }

    fun getReservation(accountState: AccountState, id: Int) : Reservation? {
        for (reservation in accountState.reservations!!) {
            if (reservation.id == id) return reservation
        }

        return null
    }

    fun onReserveParkingSpaceClicked(offer: Offer) {
        val reservation = Reservation(
            dateStart = offer.dateStart,
            dateEnd = offer.dateEnd,
            parkingSpace = offer.parkingSpace,
            seeker = accountState.value.user!!
        )
        val reservations = accountState.value.reservations
        reservations?.add(reservation)
        val offers = accountState.value.offers
        offers?.remove(offer)
        _accountState.update { it.copy(reservations = reservations, offers = offers) }

        goBack()
    }

    fun onRequestBackClicked(reservation: Reservation) {
        val reservations = accountState.value.reservations
        reservations?.remove(reservation)
        _accountState.update { it.copy(reservations = reservations) }

        goBack()
    }
}