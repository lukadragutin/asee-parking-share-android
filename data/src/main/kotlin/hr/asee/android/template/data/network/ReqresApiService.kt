package hr.asee.android.template.data.network

import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.data.model.remote.body.ApiRegisterRequest
import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import retrofit2.http.Body
import retrofit2.http.POST

interface ReqresApiService {

    @POST("/api/register")
    suspend fun register(
        @Body request: ApiRegisterRequest,
    ): Unit



    @POST("/api/login")
    suspend fun login(
        @Body request: ApiLoginRequest,
    ): ApiAccessToken


}
