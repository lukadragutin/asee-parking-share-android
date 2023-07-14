package hr.asee.android.template.domain.usecase.offering.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.repository.OfferRepository
import hr.asee.android.template.domain.usecase.offering.GetOfferingsForGiverUseCase

class GetOfferingsForGiverUseCaseImpl(private val offerRepository: OfferRepository): GetOfferingsForGiverUseCase {

	override suspend fun invoke(giverId: Int): Resource<List<Offer>> {
		return try {
			val offerings = offerRepository.getOfferingsForGiver(giverId)
			Resource.Success(offerings)
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}