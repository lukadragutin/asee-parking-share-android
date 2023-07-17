package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.common.AccessToken
import hr.asee.android.template.domain.model.common.User

interface AuthenticationRepository {

    suspend fun login(email: String, password: String): AccessToken

    suspend fun isAccessTokenValid(): Boolean

    suspend fun storeAccessToken(accessToken: AccessToken)

    suspend fun getAccount(): User

    suspend fun logout()
}
