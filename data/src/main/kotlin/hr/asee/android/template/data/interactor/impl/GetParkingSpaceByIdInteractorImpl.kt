package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.GetParkingSpaceByIdInteractor
import hr.asee.android.template.data.model.remote.response.ApiParkingSpace
import hr.asee.android.template.data.network.ParkingSpaceApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException

class GetParkingSpaceByIdInteractorImpl(
    private val parkingSpaceApiService: ParkingSpaceApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver
) : GetParkingSpaceByIdInteractor {

    override suspend fun invoke(id: Int): ApiParkingSpace {
        return try {
            parkingSpaceApiService.getParkingSpaceById(id = id)
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