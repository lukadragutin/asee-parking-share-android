package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.model.common.AccessToken
import hr.asee.android.template.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val loginInteractor: LoginInteractor,
    private val accessTokenMapper: AccessTokenMapper,
    private val storeAccessTokenInteractor: StoreAccessTokenInteractor,
) : AuthenticationRepository {

    companion object {
        private const val KEY_ACCESS_TOKEN = "keyAccessToken"
    }

    override suspend fun login(email: String, password: String): AccessToken {
        return accessTokenMapper.toAccessToken(loginInteractor(ApiLoginRequest(email = email, password = password)))
    }

    override suspend fun storeAccessToken(accessToken: AccessToken) {
        storeAccessTokenInteractor(key = KEY_ACCESS_TOKEN, accessToken = accessToken.value)
    }
}
