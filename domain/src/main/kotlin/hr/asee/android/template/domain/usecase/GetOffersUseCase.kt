package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Offer
import java.time.LocalDateTime

interface GetOffersUseCase {

    suspend operator fun invoke(dateStart: LocalDateTime, dateEnd: LocalDateTime): Resource<List<Offer>>

    enum class GetOffersError: ErrorType {
        GENERAL_GET_OFFERS_ERROR
    }
}