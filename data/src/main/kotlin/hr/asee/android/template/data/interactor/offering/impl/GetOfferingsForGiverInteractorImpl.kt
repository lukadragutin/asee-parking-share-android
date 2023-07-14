package hr.asee.android.template.data.interactor.offering.impl

import hr.asee.android.template.data.interactor.offering.GetOfferingsForGiverInteractor
import hr.asee.android.template.data.model.remote.response.ApiOffer
import hr.asee.android.template.data.network.OfferApiService

class GetOfferingsForGiverInteractorImpl(private val offerApiService: OfferApiService): GetOfferingsForGiverInteractor {

	override suspend fun invoke(giverId: Int): List<ApiOffer> {
		return offerApiService.getOfferingsForGiver(giverId)
	}
}