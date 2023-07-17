package hr.asee.android.template.compose.ui.common.component.model

import hr.asee.android.template.data.model.common.User
import hr.asee.android.template.data.model.common.service.*

data class AccountState(
    val user: User? = null,
    val parkingSpaces: MutableList<ParkingSpace>? = null,
    val seekings: MutableList<Seeking>? = null,
    val offers: MutableList<Offer>? = null,
    val reservations: MutableList<Reservation>? = null
)