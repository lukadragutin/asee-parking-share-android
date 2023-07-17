package hr.asee.android.template.domain.usecase.offering.impl

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.OfferRepository
import hr.asee.android.template.domain.usecase.offering.AddOfferingUseCase
import org.threeten.bp.LocalDateTime

class AddOfferingUseCaseImpl(private val offeringRepository: OfferRepository) : AddOfferingUseCase {

	override suspend fun invoke(dateStart: LocalDateTime, dateEnd: LocalDateTime, parkingSpaceId: Int): EmptyResource {
		return try {
			val response = offeringRepository.addOffering(dateStart, dateEnd, parkingSpaceId)
			Resource.Success(response)
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}