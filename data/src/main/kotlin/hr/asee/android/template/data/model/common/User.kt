package hr.asee.android.template.data.model.common

import hr.asee.android.template.data.model.common.service.*
import java.time.LocalDateTime

sealed class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val activated: Boolean,
    val langKey: String,
    val createdBy: String,
    val createdDate: LocalDateTime,
    val lastModifiedBy: String,
    val lastModifiedDate: LocalDateTime,
    val authorities: List<String>?,
    val resetDate: LocalDateTime?
) {
    companion object {
        private var latestId = 1

        fun generateId(): Int {
            return latestId++
        }
    }

    var reservations: MutableSet<Reservation> = mutableSetOf()
    var requests: MutableSet<Request> = mutableSetOf()
    var seekings: MutableSet<Seeking> = mutableSetOf()
    var offers: MutableSet<Offer> = mutableSetOf()

}
