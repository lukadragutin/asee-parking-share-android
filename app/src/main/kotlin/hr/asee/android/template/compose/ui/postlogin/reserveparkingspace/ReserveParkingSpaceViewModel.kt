package hr.asee.android.template.compose.ui.postlogin.reserveparkingspace

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.usecase.reservation.PutReservationByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ReserveParkingSpaceViewModel @Inject constructor(
    private val putReservationByIdUseCase: PutReservationByIdUseCase
) : BaseViewModel() {

    private val _offerState = MutableStateFlow(Offer.EMPTY)
    val offerState = _offerState.asStateFlow()
 private val _reservationState = MutableStateFlow(Reservation.EMPTY)
    val reservationState = _reservationState.asStateFlow()

    fun getOffer(id: Int) {
        // TODO get Offer
    }

    fun getReservation(id: Int) {

    }

    fun onReserveParkingSpaceClicked(offer: Offer) {
//        val reservation = Reservation(
//            dateStart = offer.dateStart,
//            dateEnd = offer.dateEnd,
//            parkingSpace = offer.parkingSpace,
//            seeker = accountState.value.user
//        )
//        val reservations = accountState.value.reservations
//        reservations?.add(reservation)
//        val offers = accountState.value.offers
//        offers?.remove(offer)
//        _accountState.update { it.copy(reservations = reservations, offers = offers) }

        goBack()
    }

    fun onRequestBackClicked(reservation: Reservation) {
        goBack()
    }
}