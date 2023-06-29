package hr.asee.android.template.compose.ui.common.model.state

import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.exampleGiver
import hr.asee.android.template.domain.model.common.exampleSeeker
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.model.common.service.Seeking
import hr.asee.android.template.domain.model.common.service.exampleOffer1
import hr.asee.android.template.domain.model.common.service.exampleOffer2
import hr.asee.android.template.domain.model.common.service.exampleParkingSpace1
import hr.asee.android.template.domain.model.common.service.exampleParkingSpace2
import hr.asee.android.template.domain.model.common.service.exampleReservation
import hr.asee.android.template.domain.model.common.service.exampleSeeking1
import hr.asee.android.template.domain.model.common.service.exampleSeeking2
import java.time.LocalDateTime

data class AccountState(
    val user: User? = exampleGiver/*null*/,
    val parkingSpaces: MutableSet<ParkingSpace>? = mutableSetOf(
        exampleParkingSpace1,
        exampleParkingSpace2
    )/*null*/,
    val seekings: MutableSet<Seeking>? = mutableSetOf(
        exampleSeeking1,
        exampleSeeking2
    )/*null*/,
    val offers: MutableSet<Offer>? = mutableSetOf(
        exampleOffer1,
    )/*null*/,
    val reservations: MutableSet<Reservation>? = mutableSetOf(
        exampleReservation
    )/*null*/
)