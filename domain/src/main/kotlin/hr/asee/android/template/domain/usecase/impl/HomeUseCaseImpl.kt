package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.data.model.remote.exception.home.DatesNotSelectedException
import hr.asee.android.template.data.model.remote.exception.home.InvalidDatesException
import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.FilterRepository
import hr.asee.android.template.domain.usecase.HomeUseCase

class HomeUseCaseImpl(
    private val filterRepository: FilterRepository
) : HomeUseCase {

    override suspend fun invoke(task: HomeUseCase.Filter): EmptyResource {
        try {
            filterRepository.filter(task.dateStart, task.dateEnd)

        } catch (invalidDatesException: InvalidDatesException) {
            return Resource.Error(HomeUseCase.HomeError.INVALID_DATES_ERROR, invalidDatesException)

        } catch (dateNotSelectedException: DatesNotSelectedException) {
            return Resource.Error(HomeUseCase.HomeError.DATES_NOT_SELECTED_ERROR, dateNotSelectedException)

        }

        return Resource.Success.empty()
    }
}