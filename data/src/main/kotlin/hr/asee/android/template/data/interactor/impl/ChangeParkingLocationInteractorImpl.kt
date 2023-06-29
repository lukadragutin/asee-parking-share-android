package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.ChangeParkingLocationInteractor
import hr.asee.android.template.data.model.remote.response.ApiParkingSpace
import hr.asee.android.template.data.network.ParkingSpaceApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import retrofit2.HttpException

class ChangeParkingLocationInteractorImpl(
    private val parkingSpaceApiService: ParkingSpaceApiService,
    private val reqResServiceErrorResolver: ReqResServiceErrorResolver
) : ChangeParkingLocationInteractor {

    override suspend fun invoke(id: Int, apiParkingSpace: ApiParkingSpace): ApiParkingSpace {
        return try {
            parkingSpaceApiService.patchParkingSpace(id = id, apiParkingSpace = apiParkingSpace)
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
