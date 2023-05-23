package hr.asee.android.template.data.network

import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import retrofit2.http.Body
import retrofit2.http.POST

interface ReqresApiService {

    @POST("http://localhost:8080/api/login")
    suspend fun login(
        @Body request: ApiLoginRequest,
    ): ApiAccessToken
}
