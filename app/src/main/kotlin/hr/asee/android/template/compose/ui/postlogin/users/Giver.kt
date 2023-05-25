package hr.asee.android.template.compose.ui.postlogin.users

import hr.asee.android.template.compose.ui.common.service.Offer
import hr.asee.android.template.compose.ui.common.service.Reservation
import java.time.LocalDateTime

class Giver(
    id: Int = generateId(),
    firstName: String,
    lastName: String,
    email: String,
    password: String,
    activated: Boolean = true,
    langKey: String = "English",
    createdBy: String = "admin",
    createdDate: LocalDateTime,
    lastModifiedBy: String = createdBy,
    lastModifiedDate: LocalDateTime = createdDate,
    authorities: List<String>? = null,
    resetDate: LocalDateTime? = null
):
    User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password,
        activated = activated,
        langKey = langKey,
        createdBy = createdBy,
        createdDate = createdDate,
        lastModifiedBy = lastModifiedBy,
        lastModifiedDate = lastModifiedDate,
        authorities = authorities,
        resetDate = resetDate
    ) {

    fun addReservation(reservation: Reservation) {
        reservations.add(reservation)
        reservation.seeker.reservations.add(reservation)
    }
    fun addOffer(offer: Offer) {
        offers.add(offer)
    }
}

/*-------For testing-------*/
val exampleGiver = Giver(
    firstName = "Test",
    lastName = "User",
    email = "test.user@test.com",
    password = "testuser",
    createdDate = LocalDateTime.of(2023, 4, 17, 17, 8)
)
/*-------------------------*/
