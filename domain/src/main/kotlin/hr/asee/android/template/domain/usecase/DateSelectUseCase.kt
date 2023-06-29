package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.ErrorType
import java.time.LocalDateTime

interface DateSelectUseCase {

    suspend operator fun invoke(task: Dates): EmptyResource

    suspend fun selectDates(dateStart: LocalDateTime?, dateEnd: LocalDateTime?)

    data class Dates(val dateStart: LocalDateTime?, val dateEnd: LocalDateTime?)

    enum class DateSelectError : ErrorType {
        DATES_NOT_SELECTED_ERROR,
        INVALID_DATES_ERROR,
    }
}