package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.common.AccessToken

interface AuthenticationRepository {

    suspend fun login(email: String, password: String): AccessToken

    suspend fun storeAccessToken(accessToken: AccessToken)
}
