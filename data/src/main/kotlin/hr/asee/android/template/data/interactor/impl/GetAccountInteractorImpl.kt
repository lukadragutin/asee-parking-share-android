package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.GetAccountInteractor
import hr.asee.android.template.data.model.remote.response.ApiUser
import hr.asee.android.template.data.network.AuthenticationApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException

class GetAccountInteractorImpl(
    private val authenticationApiService: AuthenticationApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver
): GetAccountInteractor {

    override suspend fun invoke(): ApiUser {
        return try {
            authenticationApiService.getAccount()
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