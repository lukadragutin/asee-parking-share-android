package hr.asee.android.template.data.network

import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import hr.asee.android.template.data.model.remote.response.ApiUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthenticationApiService {

    @POST("/api/authenticate")
    suspend fun login(
        @Body request: ApiLoginRequest,
    ): ApiAccessToken

    @GET("/api/account")
    suspend fun getAccount(): ApiUser

    @GET("/api/admin/users/{login}")
    suspend fun getUserByLogin(
        @Path("login") login: String
    ): ApiUser
}
