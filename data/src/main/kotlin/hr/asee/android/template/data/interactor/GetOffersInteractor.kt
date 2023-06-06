package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.response.ApiOffer
import java.time.LocalDateTime

interface GetOffersInteractor {

    suspend operator fun invoke(dateStart: LocalDateTime, dateEnd: LocalDateTime): List<ApiOffer>
}