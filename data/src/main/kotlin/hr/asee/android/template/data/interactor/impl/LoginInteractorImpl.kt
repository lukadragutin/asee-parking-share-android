package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import hr.asee.android.template.data.network.ReqresApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException

class LoginInteractorImpl(
    private val reqresApiService: ReqresApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver,
) : LoginInteractor {

    override suspend fun invoke(request: ApiLoginRequest): ApiAccessToken {
        return try {
            reqresApiService.login(request)
        } catch (httpException: HttpException) {
            throw resolveToException(httpException)
        }
    }

    private fun resolveToException(httpException: HttpException): Exception {
        return httpException.response()?.errorBody()?.byteStream().use {
            reqResServiceErrorResolver.toException(String(it!!.readBytes()))
        }
    }
}
