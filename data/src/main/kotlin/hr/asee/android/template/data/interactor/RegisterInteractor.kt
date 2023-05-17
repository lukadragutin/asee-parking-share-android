package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.body.ApiRegisterRequest
import hr.asee.android.template.data.model.remote.response.ApiAccessToken

interface RegisterInteractor {
    suspend operator fun invoke(request: ApiRegisterRequest): ApiAccessToken
}