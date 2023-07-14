package hr.asee.android.template.domain.model.common.service

import hr.asee.android.template.domain.model.common.Role
import hr.asee.android.template.domain.model.common.UserCompact
import org.threeten.bp.LocalDateTime

class Seeking(
    val id: Int = generateId(),
    val dateStart: LocalDateTime,
    val dateEnd: LocalDateTime,
    val seeker: UserCompact
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
    seeker = UserCompact.EMPTY.copy(role = Role.SEEKER)
)
val exampleSeeking2 = Seeking(
    id = 2,
    dateStart = LocalDateTime.of(2023, 4, 11, 21, 4, 9),
    dateEnd = LocalDateTime.of(2023, 4, 18, 21, 11, 44),
    seeker = UserCompact.EMPTY.copy(role = Role.SEEKER)
)