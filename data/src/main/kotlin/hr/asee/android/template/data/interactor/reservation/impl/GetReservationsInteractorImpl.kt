package hr.asee.android.template.data.interactor.reservation.impl

import hr.asee.android.template.data.interactor.reservation.GetReservationsInteractor
import hr.asee.android.template.data.model.remote.response.ApiReservation
import hr.asee.android.template.data.network.ReservationApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException

class GetReservationsInteractorImpl(
    private val reservationApiService: ReservationApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver
): GetReservationsInteractor {

    override suspend fun invoke(): List<ApiReservation> {
        return try {
            reservationApiService.getReservations()
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