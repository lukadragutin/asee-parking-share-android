package hr.asee.android.template.domain.model.common.service

import hr.asee.android.template.domain.model.common.UserCompact
import org.threeten.bp.LocalDateTime


class Reservation(
    val id: Int = generateId(),
    val dateStart: LocalDateTime,
    val dateEnd: LocalDateTime,
    var cancellationPending: Boolean = false,
    val parkingSpace: ParkingSpace,
    val seeker: UserCompact
) {
    companion object {
        val EMPTY = Reservation(dateStart = LocalDateTime.now(), dateEnd = LocalDateTime.now(), parkingSpace = ParkingSpace.EMPTY, seeker = UserCompact.EMPTY)

        private var latestId = 1

        fun generateId(): Int {
            return latestId++
        }
    }
}

val exampleReservation = Reservation(
    id = 1,
    dateStart = LocalDateTime.of(2023, 4, 13, 22, 27, 0),
    dateEnd = LocalDateTime.of(2023, 4, 14, 18, 40, 0),
    parkingSpace = exampleParkingSpace1,
    seeker = UserCompact.EMPTY
)