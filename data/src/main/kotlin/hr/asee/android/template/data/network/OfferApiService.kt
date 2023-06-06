package hr.asee.android.template.data.network

import hr.asee.android.template.data.model.remote.response.ApiOffer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDateTime

interface OfferApiService {

    @GET("/api/offerings")
    suspend fun getOffers(
        @Query("date-start") dateStart: LocalDateTime,
        @Query("date-end") dateEnd: LocalDateTime
    ): List<ApiOffer>

    @GET("/api/offerings/{id}")
    suspend fun getOfferById(
        @Path("id") id: Int
    ): ApiOffer
}