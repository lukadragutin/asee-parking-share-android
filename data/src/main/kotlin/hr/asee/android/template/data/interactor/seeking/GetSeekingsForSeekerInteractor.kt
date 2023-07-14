package hr.asee.android.template.data.interactor.seeking

import hr.asee.android.template.data.model.remote.response.ApiSeeking

fun interface GetSeekingsForSeekerInteractor {

	suspend operator fun invoke(seekerId: Int): List<ApiSeeking>
}