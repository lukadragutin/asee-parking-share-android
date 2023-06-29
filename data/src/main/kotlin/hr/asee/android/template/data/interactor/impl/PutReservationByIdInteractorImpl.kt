package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.PutReservationByIdInteractor
import hr.asee.android.template.data.model.remote.response.ApiReservation
import hr.asee.android.template.data.network.ReservationApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException

class PutReservationByIdInteractorImpl(
    private val reservationApiService: ReservationApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver
) : PutReservationByIdInteractor {

    override suspend fun invoke(apiReservation: ApiReservation, id: Int): ApiReservation {
        return try {
            reservationApiService.putReservationById(id, apiReservation)
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