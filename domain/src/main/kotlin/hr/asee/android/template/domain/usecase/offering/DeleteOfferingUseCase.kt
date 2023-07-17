package hr.asee.android.template.domain.usecase.offering

import hr.asee.android.template.domain.model.common.resource.EmptyResource

interface DeleteOfferingUseCase {

	suspend operator fun invoke(offerId: Int): EmptyResource
}