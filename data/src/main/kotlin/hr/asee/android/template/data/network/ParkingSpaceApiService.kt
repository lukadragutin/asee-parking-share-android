package hr.asee.android.template.data.network

import hr.asee.android.template.data.model.remote.response.ApiParkingSpace
import retrofit2.http.GET

interface ParkingSpaceApiService {

    @GET("/api/parking-spaces")
    suspend fun getParkingSpaces(): List<ApiParkingSpace>
}