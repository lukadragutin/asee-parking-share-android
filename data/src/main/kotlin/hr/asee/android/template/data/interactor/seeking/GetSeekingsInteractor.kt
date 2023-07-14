package hr.asee.android.template.data.interactor.seeking

import hr.asee.android.template.data.model.remote.response.ApiSeeking
import org.threeten.bp.LocalDateTime

interface GetSeekingsInteractor {

    suspend operator fun invoke(
        dateStart: LocalDateTime?,
        dateEnd: LocalDateTime?
    ): List<ApiSeeking>
}