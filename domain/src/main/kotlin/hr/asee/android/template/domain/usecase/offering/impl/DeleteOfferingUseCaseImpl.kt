package hr.asee.android.template.domain.usecase.offering.impl

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.OfferRepository
import hr.asee.android.template.domain.usecase.offering.DeleteOfferingUseCase

class DeleteOfferingUseCaseImpl(private val offerRepository: OfferRepository): DeleteOfferingUseCase {

	override suspend fun invoke(offerId: Int): EmptyResource {
		return try {
			offerRepository.deleteOffering(offerId)
			Resource.Success(Unit)
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}