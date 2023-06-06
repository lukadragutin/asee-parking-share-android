package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.GetSeekingsInteractor
import hr.asee.android.template.data.model.remote.response.ApiSeeking
import hr.asee.android.template.data.network.SeekingApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException
import java.time.LocalDateTime

class GetSeekingsInteractorImpl(
    private val seekingApiService: SeekingApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver
): GetSeekingsInteractor {

    override suspend fun invoke(
        dateStart: LocalDateTime,
        dateEnd: LocalDateTime
    ): List<ApiSeeking> {
        return try {
            seekingApiService.getSeekings(dateStart, dateEnd)
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