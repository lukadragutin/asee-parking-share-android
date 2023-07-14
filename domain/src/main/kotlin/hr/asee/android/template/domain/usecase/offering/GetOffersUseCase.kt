package hr.asee.android.template.domain.usecase.offering

import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Offer
import org.threeten.bp.LocalDateTime

interface GetOffersUseCase {

    suspend operator fun invoke(dateStart: LocalDateTime? = null, dateEnd: LocalDateTime? = null): Resource<List<Offer>>

    enum class GetOffersError: ErrorType {
        GENERAL_GET_OFFERS_ERROR
    }
}