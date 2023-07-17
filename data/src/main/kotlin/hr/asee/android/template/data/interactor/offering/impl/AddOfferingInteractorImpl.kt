package hr.asee.android.template.data.interactor.offering.impl

import hr.asee.android.template.data.interactor.offering.AddOfferingInteractor
import hr.asee.android.template.data.model.remote.body.ApiOfferingRequest
import hr.asee.android.template.data.network.OfferApiService

class AddOfferingInteractorImpl(private val offerApiService: OfferApiService): AddOfferingInteractor {

	override suspend fun invoke(apiOfferingRequest: ApiOfferingRequest) {
		return offerApiService.addOffering(apiOfferingRequest)
	}
}