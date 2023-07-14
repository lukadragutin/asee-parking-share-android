package hr.asee.android.template.data.interactor.offering

import hr.asee.android.template.data.model.remote.response.ApiOffer
import org.threeten.bp.LocalDateTime

interface GetOffersInteractor {

    suspend operator fun invoke(dateStart: LocalDateTime?, dateEnd: LocalDateTime?): List<ApiOffer>
}