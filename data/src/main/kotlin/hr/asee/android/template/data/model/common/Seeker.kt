package hr.asee.android.template.data.model.common

import hr.asee.android.template.data.model.common.service.Request
import hr.asee.android.template.data.model.common.service.Reservation
import hr.asee.android.template.data.model.common.service.Seeking
import java.time.LocalDateTime

class Seeker(
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

    fun addSeeking(seeking: Seeking) {
        seekings.add(seeking)
    }

    fun addReservation(reservation: Reservation) {
        reservations.add(reservation)
        reservation.parkingSpace.owner?.reservations!!.add(reservation)
    }

    fun addRequests(request: Request){
        requests.add(request)
    }
}

    val exampleSeeker = Seeker(
        firstName = "seeker",
        lastName = "one",
        email = "seeker1@test.com",
        password = "seeker1",
        createdDate = LocalDateTime.of(2023, 4, 18, 14, 1)
    )
