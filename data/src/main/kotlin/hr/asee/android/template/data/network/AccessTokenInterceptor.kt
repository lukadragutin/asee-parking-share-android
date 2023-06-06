package hr.asee.android.template.data.network

import hr.asee.android.template.data.interactor.GetAccessTokenInteractor
import hr.asee.android.template.data.local.storage.StorageKeys.KEY_ACCESS_TOKEN
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor (
    private val getAccessTokenInteractor: GetAccessTokenInteractor
): Interceptor {

    private val token = runBlocking { getAccessTokenInteractor(KEY_ACCESS_TOKEN) }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}