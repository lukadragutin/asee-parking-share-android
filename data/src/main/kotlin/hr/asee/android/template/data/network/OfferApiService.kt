package hr.asee.android.template.data.network

import hr.asee.android.template.data.model.remote.response.ApiOffer
import org.threeten.bp.LocalDateTime
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OfferApiService {

    @GET("/api/offerings")
    suspend fun getOffers(
        @Query("date-start") dateStart: LocalDateTime?,
        @Query("date-end") dateEnd: LocalDateTime?
    ): List<ApiOffer>

    @GET("/api/offerings/{id}")
    suspend fun getOfferById(
        @Path("id") id: Int
    ): ApiOffer

    @GET("/api/offerings/giver/{id}")
    suspend fun getOfferingsForGiver(
        @Path("id") id: Int
    ): List<ApiOffer>
}