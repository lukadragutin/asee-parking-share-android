package hr.asee.android.template.data.model.common.service

import java.time.LocalDateTime

class Reservation(
    val id: Int = generateId(),
    val dateStart: LocalDateTime,
    val dateEnd: LocalDateTime,
    val parkingSpace: ParkingSpace,
) {
    companion object {
        private var latestId = 1

        fun generateId(): Int {
            return latestId++
        }
    }
}

val exampleReservation1 = Reservation(
    id = 1,
    dateStart = LocalDateTime.of(2023, 4, 13, 22, 27, 0),
    dateEnd = LocalDateTime.of(2023, 4, 14, 18, 40, 0),
    parkingSpace = exampleParkingSpace1
)

val exampleReservation2 = Reservation(
    id = 2,
    dateStart = LocalDateTime.of(2023, 6, 13, 22, 27, 0),
    dateEnd = LocalDateTime.of(2023, 6, 16, 18, 40, 0),
    parkingSpace = exampleParkingSpace2
)

val exampleReservation3 = Reservation(
    id = 3,
    dateStart = LocalDateTime.of(2023, 7, 13, 22, 27, 0),
    dateEnd = LocalDateTime.of(2023, 7, 16, 18, 40, 0),
    parkingSpace = exampleParkingSpace2
)

val exampleReservation4 = Reservation(
    id = 4,
    dateStart = LocalDateTime.of(2023, 8, 17, 22, 27, 0),
    dateEnd = LocalDateTime.of(2023, 8, 21, 18, 40, 0),
    parkingSpace = exampleParkingSpace2
)