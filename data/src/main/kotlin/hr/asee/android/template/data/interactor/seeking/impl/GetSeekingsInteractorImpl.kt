package hr.asee.android.template.data.interactor.seeking.impl

import hr.asee.android.template.data.interactor.seeking.GetSeekingsInteractor
import hr.asee.android.template.data.model.remote.response.ApiSeeking
import hr.asee.android.template.data.network.SeekingApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import org.threeten.bp.LocalDateTime
import retrofit2.HttpException

class GetSeekingsInteractorImpl(
    private val seekingApiService: SeekingApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver
): GetSeekingsInteractor {

    override suspend fun invoke(
        dateStart: LocalDateTime?,
        dateEnd: LocalDateTime?
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