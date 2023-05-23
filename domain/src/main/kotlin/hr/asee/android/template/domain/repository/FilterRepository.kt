package hr.asee.android.template.domain.repository

import java.time.LocalDate

interface FilterRepository {

    suspend fun filter(dateStart: LocalDate?, dateEnd: LocalDate?)
}