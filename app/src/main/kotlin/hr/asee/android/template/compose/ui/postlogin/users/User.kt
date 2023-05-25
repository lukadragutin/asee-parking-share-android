package hr.asee.android.template.compose.ui.postlogin.users

import hr.asee.android.template.compose.ui.common.service.Offer
import hr.asee.android.template.compose.ui.common.service.Reservation
import hr.asee.android.template.compose.ui.common.service.Seeking
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

    var seekings: MutableSet<Seeking> = mutableSetOf()
    var offers: MutableSet<Offer> = mutableSetOf()
    var reservations: MutableSet<Reservation> = mutableSetOf()
}
