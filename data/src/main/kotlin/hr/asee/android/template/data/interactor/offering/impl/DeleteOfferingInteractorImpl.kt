package hr.asee.android.template.data.interactor.offering.impl

import hr.asee.android.template.data.interactor.offering.DeleteOfferingInteractor
import hr.asee.android.template.data.network.OfferApiService

class DeleteOfferingInteractorImpl(private val offerApiService: OfferApiService): DeleteOfferingInteractor {

	override suspend fun invoke(offerId: Int) {
		offerApiService.removeOfferingWithId(offerId)
	}
}