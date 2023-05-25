package hr.asee.android.template.compose.ui.common.model.state

import java.time.LocalDate

data class DatePickerState(
    var dateStart: LocalDate? = null,
    var dateEnd: LocalDate? = null,
    var startFocused: Boolean = false,
    var endFocused: Boolean = false,
    var dateStartSelected: LocalDate? = LocalDate.MIN,
    var dateEndSelected: LocalDate? = LocalDate.MAX
) {
    companion object {
        const val DATE_FORMAT = "d MMMM yyyy"
    }
}
