package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.data.model.remote.exception.home.DatesNotSelectedException
import hr.asee.android.template.data.model.remote.exception.home.InvalidDatesException
import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.usecase.DateSelectUseCase
import org.threeten.bp.LocalDateTime

class DateSelectUseCaseImpl: DateSelectUseCase {

    override suspend fun selectDates(dateStart: LocalDateTime?, dateEnd: LocalDateTime?) {
        if (dateStart == null || dateEnd == null) {
            throw (DatesNotSelectedException())
        }
        else if (dateStart.isAfter(dateEnd)) {
            throw (InvalidDatesException())
        }
    }
    override suspend fun invoke(task: DateSelectUseCase.Dates): EmptyResource {
        try {
            selectDates(task.dateStart, task.dateEnd)

        } catch (invalidDatesException: InvalidDatesException) {
            return Resource.Error(DateSelectUseCase.DateSelectError.INVALID_DATES_ERROR, invalidDatesException)

        } catch (dateNotSelectedException: DatesNotSelectedException) {
            return Resource.Error(DateSelectUseCase.DateSelectError.DATES_NOT_SELECTED_ERROR, dateNotSelectedException)

        }

        return Resource.Success.empty()
    }
}