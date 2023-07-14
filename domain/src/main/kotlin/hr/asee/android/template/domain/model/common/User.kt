package hr.asee.android.template.domain.model.common

import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.model.common.service.Seeking
import org.threeten.bp.LocalDateTime

sealed class User(
    val id: Int,
    var firstName: String,
    var lastName: String,
    val email: String,
    var activated: Boolean,
    val langKey: String,
    val createdBy: String,
    val createdDate: LocalDateTime,
    var lastModifiedBy: String,
    var lastModifiedDate: LocalDateTime,
    val authorities: List<String>,
    var resetDate: LocalDateTime?
) {
    companion object {
        private var latestId = 1

        fun generateId(): Int {
            return latestId++
        }

        fun changeFirstName(user: User, newName: String): User {
            user.firstName = newName
            return user
        }

        fun changeLastName(user: User, newName: String): User {
            user.lastName = newName
            return user
        }

        val EMPTY = Seeker(0, "", "", "", false, "", "", LocalDateTime.now(), "", LocalDateTime.now(), listOf(), LocalDateTime.now())
    }

    var seekings: MutableSet<Seeking> = mutableSetOf()
    var offers: MutableSet<Offer> = mutableSetOf()
    var reservations: MutableSet<Reservation> = mutableSetOf()
}
