package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.repository.OfferRepository
import hr.asee.android.template.domain.usecase.GetOffersUseCase
import java.time.LocalDateTime

class GetOffersUseCaseImpl(
    private val offerRepository: OfferRepository
): GetOffersUseCase {

    override suspend fun invoke(dateStart: LocalDateTime, dateEnd: LocalDateTime): Resource<List<Offer>> {
        val offersList = try {
            offerRepository.getOffers(dateStart = dateStart, dateEnd = dateEnd)
        } catch (throwable: Throwable) {
            return Resource.Error(GetOffersUseCase.GetOffersError.GENERAL_GET_OFFERS_ERROR, throwable)
        }

        return Resource.Success(offersList)
    }
}