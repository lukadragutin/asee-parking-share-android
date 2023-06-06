package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.GetOffersInteractor
import hr.asee.android.template.data.model.remote.response.ApiOffer
import hr.asee.android.template.data.network.OfferApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException
import java.time.LocalDateTime

class GetOffersInteractorImpl(
    private val offerApiService: OfferApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver
): GetOffersInteractor {

    override suspend fun invoke(
        dateStart: LocalDateTime,
        dateEnd: LocalDateTime
    ): List<ApiOffer> {
        return try {
            offerApiService.getOffers(dateStart, dateEnd)
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