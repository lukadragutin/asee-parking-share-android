package hr.asee.android.template.compose.ui.common.model.state

import java.time.LocalDate
import java.time.LocalDateTime

data class DatePickerState(
    var dateStart: LocalDateTime? = null,
    var dateEnd: LocalDateTime? = null,
    var startFocused: Boolean = false,
    var endFocused: Boolean = false,
    var dateStartSelected: LocalDateTime? = LocalDateTime.MIN,
    var dateEndSelected: LocalDateTime? = LocalDateTime.MAX
) {
    companion object {
        const val DATE_FORMAT = "d MMMM yyyy"
    }
}
