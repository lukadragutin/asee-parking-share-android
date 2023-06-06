package hr.asee.android.template.domain.model.common.service

import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.exampleGiver

class ParkingSpace(
    val id: Int = generateId(),
    val location: String,
    val owner: Giver? = null
) {
    companion object {
        private var latestId = 1

        fun generateId(): Int {
            return latestId++
        }
    }
}

val exampleParkingSpace1 = ParkingSpace(
    location = "A2300",
    owner = exampleGiver
)
val exampleParkingSpace2 = ParkingSpace(
    location = "A2303",
    owner = exampleGiver
)