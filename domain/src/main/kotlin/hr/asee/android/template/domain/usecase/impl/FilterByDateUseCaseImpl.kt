package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.data.model.remote.exception.home.DatesNotSelectedException
import hr.asee.android.template.data.model.remote.exception.home.InvalidDatesException
import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.usecase.FilterByDateUseCase
import java.time.LocalDate

class FilterByDateUseCaseImpl: FilterByDateUseCase {

    override suspend fun filter(dateStart: LocalDate?, dateEnd: LocalDate?) {
        if (dateStart == null || dateEnd == null) {
            throw (DatesNotSelectedException())
        }
        else if (dateStart.isAfter(dateEnd)) {
            throw (InvalidDatesException())
        }
    }
    override suspend fun invoke(task: FilterByDateUseCase.Filter): EmptyResource {
        try {
            filter(task.dateStart, task.dateEnd)

        } catch (invalidDatesException: InvalidDatesException) {
            return Resource.Error(FilterByDateUseCase.FilterByDateError.INVALID_DATES_ERROR, invalidDatesException)

        } catch (dateNotSelectedException: DatesNotSelectedException) {
            return Resource.Error(FilterByDateUseCase.FilterByDateError.DATES_NOT_SELECTED_ERROR, dateNotSelectedException)

        }

        return Resource.Success.empty()
    }
}