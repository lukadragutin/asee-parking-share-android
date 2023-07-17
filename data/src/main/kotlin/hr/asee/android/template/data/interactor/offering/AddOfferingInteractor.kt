package hr.asee.android.template.data.interactor.offering

import hr.asee.android.template.data.model.remote.body.ApiOfferingRequest

interface AddOfferingInteractor {

	suspend operator fun invoke(apiOfferingRequest: ApiOfferingRequest)
}