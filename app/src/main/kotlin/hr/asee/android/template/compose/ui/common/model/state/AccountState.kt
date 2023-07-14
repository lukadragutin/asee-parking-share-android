package hr.asee.android.template.compose.ui.common.model.state

import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.model.common.service.Seeking

data class AccountState(
	val user: User = User.EMPTY,
	val parkingSpaces: Set<ParkingSpace> = setOf(),
	val seekings: Set<Seeking> = setOf(),
	val offers: Set<Offer> = setOf(),
	val reservations: Set<Reservation> = setOf()
)