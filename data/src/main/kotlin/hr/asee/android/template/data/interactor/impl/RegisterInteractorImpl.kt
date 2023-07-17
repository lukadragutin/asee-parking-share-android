package hr.asee.android.template.data.interactor.impl

import android.util.Log
import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.RegisterInteractor
import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.data.model.remote.body.ApiRegisterRequest
import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import hr.asee.android.template.data.network.ReqresApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException

class RegisterInteractorImpl(
    private val reqresApiService: ReqresApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver,
) : RegisterInteractor {

    override suspend fun invoke(request: ApiRegisterRequest): Unit {
        return try {
            reqresApiService.register(request)
        } catch (httpException: HttpException) {
            throw httpException
        }
    }

    private fun resolveToException(httpException: HttpException): Exception {
        return httpException.response()?.errorBody()?.byteStream().use {
            reqResServiceErrorResolver.toException(String(it!!.readBytes()))
        }
    }
}