package hr.asee.android.template.compose.ui.common.component.model

import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.model.common.service.Seeking

data class AccountState(
    val user: User? = null,
    val parkingSpaces: MutableList<ParkingSpace>? = null,
    val seekings: MutableList<Seeking>? = null,
    val offers: MutableList<Offer>? = null,
    val reservations: MutableList<Reservation>? = null
)