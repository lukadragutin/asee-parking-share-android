package hr.asee.android.template.domain.usecase.offering

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Offer

interface GetOfferingsForGiverUseCase {

	suspend operator fun invoke(giverId: Int): Resource<List<Offer>>
}