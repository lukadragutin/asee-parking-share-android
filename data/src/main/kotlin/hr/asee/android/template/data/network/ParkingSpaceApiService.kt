package hr.asee.android.template.data.network

import hr.asee.android.template.data.model.remote.response.ApiParkingSpace
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ParkingSpaceApiService {

    @GET("/api/parking-spaces")
    suspend fun getParkingSpaces(): List<ApiParkingSpace>

    @GET("/api/parking-spaces/{id}")
    suspend fun getParkingSpaceById(
        @Path("id") id: Int
    ): ApiParkingSpace

    @GET("/api/parking-spaces/giver/{id}")
    suspend fun getParkingSpaceForGiver(
        @Path("id") id: Int
    ): ApiParkingSpace

    @POST("/api/parking-spaces")
    suspend fun addParkingSpace(
        @Body apiParkingSpace: ApiParkingSpace
    ): ApiParkingSpace

    @PATCH("/api/parking-spaces/{id}")
    suspend fun patchParkingSpace(
        @Path("id") id: Int,
        @Body apiParkingSpace: ApiParkingSpace
    ): ApiParkingSpace
}