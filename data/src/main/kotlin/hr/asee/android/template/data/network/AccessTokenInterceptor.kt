package hr.asee.android.template.data.network

import hr.asee.android.template.data.interactor.GetAccessTokenInteractor
import hr.asee.android.template.data.local.storage.StorageKeys.KEY_ACCESS_TOKEN
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor(
	private val getAccessTokenInteractor: GetAccessTokenInteractor
) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = if (chain.request().url.toString().endsWith("/api/authenticate")) {
			chain.request()
		} else {
			chain.request().newBuilder()
					.addHeader("Authorization", "Bearer ${getToken()}")
					.build()
		}
		return chain.proceed(request)
	}

	private fun getToken(): String {
		return runBlocking { getAccessTokenInteractor(KEY_ACCESS_TOKEN) }
	}
}