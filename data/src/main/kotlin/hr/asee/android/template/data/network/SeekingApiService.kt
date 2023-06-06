package hr.asee.android.template.data.network

import hr.asee.android.template.data.model.remote.response.ApiSeeking
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDateTime

interface SeekingApiService {

    @GET("/api/seekings")
    suspend fun getSeekings(
        @Query("date-start") dateStart: LocalDateTime,
        @Query("date-end") dateEnd: LocalDateTime
    ): List<ApiSeeking>

    @GET("/api/seekings/{id}")
    suspend fun getSeekingById(
        @Path("id") id: Int
    ): ApiSeeking
}