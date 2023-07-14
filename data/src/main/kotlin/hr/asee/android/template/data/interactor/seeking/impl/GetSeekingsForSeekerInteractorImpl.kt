package hr.asee.android.template.data.interactor.seeking.impl

import hr.asee.android.template.data.interactor.seeking.GetSeekingsForSeekerInteractor
import hr.asee.android.template.data.model.remote.response.ApiSeeking
import hr.asee.android.template.data.network.SeekingApiService

class GetSeekingsForSeekerInteractorImpl(private val seekingApiService: SeekingApiService): GetSeekingsForSeekerInteractor {

	override suspend fun invoke(seekerId: Int): List<ApiSeeking> {
		return seekingApiService.getSeekingsForSeeker(seekerId)
	}
}