package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.data.model.remote.response.ApiAccessToken

interface LoginInteractor {
    suspend operator fun invoke(request: ApiLoginRequest): ApiAccessToken
}
