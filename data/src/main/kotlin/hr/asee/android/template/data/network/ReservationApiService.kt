package hr.asee.android.template.data.network

import hr.asee.android.template.data.model.remote.response.ApiReservation
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReservationApiService {

    @GET("/api/reservations")
    suspend fun getReservations(): List<ApiReservation>

    @GET("/api/reservations/{id}")
    suspend fun getReservationById(
        @Path("id") id: Int
    ): ApiReservation

    @PUT("/api/reservations/{id}")
    suspend fun putReservationById(
        @Path("id") id: Int,
        @Body apiReservation: ApiReservation
    ): ApiReservation
}