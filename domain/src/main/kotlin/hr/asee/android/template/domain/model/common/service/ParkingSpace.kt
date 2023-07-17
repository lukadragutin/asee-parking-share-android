package hr.asee.android.template.domain.model.common.service

import hr.asee.android.template.domain.model.common.Role
import hr.asee.android.template.domain.model.common.UserCompact

data class ParkingSpace(
    val id: Int = generateId(),
    val location: String,
    val owner: UserCompact = UserCompact.EMPTY.copy(role = Role.GIVER)
) {
    companion object {
        private var latestId = 1

        fun generateId(): Int {
            return latestId++
        }

        val EMPTY = ParkingSpace(location = "")
    }
}

val exampleParkingSpace1 = ParkingSpace(
    location = "A2300",
    owner = UserCompact.EMPTY.copy(role = Role.GIVER)
)
val exampleParkingSpace2 = ParkingSpace(
    location = "A2303",
    owner = UserCompact.EMPTY.copy(role = Role.GIVER)
)