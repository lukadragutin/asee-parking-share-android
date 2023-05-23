package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.ErrorType
import java.time.LocalDate

interface HomeUseCase {

    suspend operator fun invoke(task: Filter): EmptyResource

    data class Filter(val dateStart: LocalDate?, val dateEnd: LocalDate?)

    enum class HomeError : ErrorType {
        DATES_NOT_SELECTED_ERROR,
        INVALID_DATES_ERROR,
    }
}