package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.response.ApiSeeking
import java.time.LocalDateTime

interface GetSeekingsInteractor {

    suspend operator fun invoke(
        dateStart: LocalDateTime,
        dateEnd: LocalDateTime
    ): List<ApiSeeking>
}