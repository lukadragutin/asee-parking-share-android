package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import hr.asee.android.template.data.network.AuthenticationApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException

class LoginInteractorImpl(
    private val authenticationApiService: AuthenticationApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver,
) : LoginInteractor {

    override suspend fun invoke(request: ApiLoginRequest): ApiAccessToken {
        return try {
            authenticationApiService.login(request)
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
