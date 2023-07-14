package hr.asee.android.template.data.interactor.offering

import hr.asee.android.template.data.model.remote.response.ApiOffer

interface GetOfferingsForGiverInteractor {

	suspend operator fun invoke(giverId: Int): List<ApiOffer>
}