package hr.asee.android.template.data.interactor.parkingspace.impl

import hr.asee.android.template.data.interactor.parkingspace.AddParkingSpaceInteractor
import hr.asee.android.template.data.model.remote.response.ApiParkingSpace
import hr.asee.android.template.data.network.ParkingSpaceApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException

class AddParkingSpaceInteractorImpl(
    private val parkingSpaceApiService: ParkingSpaceApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver
) : AddParkingSpaceInteractor {

    override suspend fun invoke(apiParkingSpace: ApiParkingSpace): ApiParkingSpace {
        return try {
            parkingSpaceApiService.addParkingSpace(apiParkingSpace = apiParkingSpace)
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