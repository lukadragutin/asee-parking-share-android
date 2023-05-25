package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.ErrorType
import java.time.LocalDate

interface FilterByDateUseCase {

    suspend operator fun invoke(task: Filter): EmptyResource

    suspend fun filter(dateStart: LocalDate?, dateEnd: LocalDate?)

    data class Filter(val dateStart: LocalDate?, val dateEnd: LocalDate?)

    enum class FilterByDateError : ErrorType {
        DATES_NOT_SELECTED_ERROR,
        INVALID_DATES_ERROR,
    }
}