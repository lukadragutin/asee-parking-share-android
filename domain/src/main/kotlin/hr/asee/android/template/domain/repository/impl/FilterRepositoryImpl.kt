package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.model.remote.exception.home.DatesNotSelectedException
import hr.asee.android.template.data.model.remote.exception.home.InvalidDatesException
import hr.asee.android.template.domain.repository.FilterRepository
import java.time.LocalDate

class FilterRepositoryImpl: FilterRepository {

    override suspend fun filter(dateStart: LocalDate?, dateEnd: LocalDate?) {
        if (dateStart == null || dateEnd == null) {
            throw (DatesNotSelectedException())
        }
        else if (dateStart.isAfter(dateEnd)) {
            throw (InvalidDatesException())
        }
    }
}