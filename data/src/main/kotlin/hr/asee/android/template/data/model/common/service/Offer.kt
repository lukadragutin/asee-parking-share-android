package hr.asee.android.template.data.model.common.service

import java.time.LocalDateTime

class Offer(
    val id: Int = generateId(),
    val dateStart: LocalDateTime,
    val dateEnd: LocalDateTime,
    val parkingSpace: ParkingSpace
) {

    companion object {
        private var latestId = 1

        fun generateId(): Int {
            return latestId++
        }
    }
}

val exampleOffer1 = Offer(
    id = 1,
    dateStart = LocalDateTime.of(2023, 4, 13, 15, 55, 0),
    dateEnd = LocalDateTime.of(2023, 4, 15, 6, 54, 0),
    parkingSpace = exampleParkingSpace2
)
val exampleOffer2 = Offer(
    id = 2,
    dateStart = LocalDateTime.of(2023, 4, 21, 16, 18, 0),
    dateEnd = LocalDateTime.of(2023, 4, 23, 6, 50, 0),
    parkingSpace = exampleParkingSpace2
)