package hr.asee.android.template.data.model.common.service

import hr.asee.android.template.data.model.common.User
import hr.asee.android.template.data.model.common.exampleSeeker
import java.time.LocalDateTime

class Seeking(
    val id: Int = generateId(),
    val dateStart: LocalDateTime,
    val dateEnd: LocalDateTime,
) {
    companion object {
        private var latestId = 1

        fun generateId(): Int {
            return latestId++
        }
    }
}

val exampleSeeking1 = Seeking(
    id = 1,
    dateStart = LocalDateTime.of(2023, 4, 13, 22, 35, 37),
    dateEnd = LocalDateTime.of(2023, 4, 16, 13, 4, 21),
)

val exampleSeeking2 = Seeking(
    id = 2,
    dateStart = LocalDateTime.of(2023, 4, 11, 21, 4, 9),
    dateEnd = LocalDateTime.of(2023, 4, 18, 21, 11, 44),
)