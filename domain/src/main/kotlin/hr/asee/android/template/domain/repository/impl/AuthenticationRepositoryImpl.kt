package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.RegisterInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.data.model.remote.body.ApiRegisterRequest
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.model.common.AccessToken
import hr.asee.android.template.domain.repository.AuthenticationRepository
import java.util.Calendar

class AuthenticationRepositoryImpl(
    private val loginInteractor: LoginInteractor,
    private val registerInteractor: RegisterInteractor,
    private val accessTokenMapper: AccessTokenMapper,
    private val storeAccessTokenInteractor: StoreAccessTokenInteractor,
) : AuthenticationRepository {

    companion object {
        private const val KEY_ACCESS_TOKEN = "keyAccessToken"
    }

    override suspend fun login(email: String, password: String): AccessToken {
        return accessTokenMapper.toAccessToken(loginInteractor(ApiLoginRequest(email = email, password = password)))
    }

    override suspend fun register(id: Int,
                                  login: String,
                                  firstName: String,
                                  lastName: String,
                                  email: String,
                                  password: String,
                                  role : String,
                                  activated: Boolean,
                                  langKey: String,
                                  createdBy: String,
                                  createdDate: String,
                                  lastModifiedBy: String,
                                  lastModifiedDate: String,
                                  authorities: Array<String>
                                  ): Unit {
        registerInteractor(ApiRegisterRequest(id = id,
                                              login = login,
                                              firstName = firstName,
                                              lastName = firstName,
                                              email = email,
                                              password = password,
                                              role = role,
                                              activated = activated,
                                              langKey = langKey,
                                              createdBy = createdBy,
                                              createdDate = createdDate,
                                              lastModifiedBy = lastModifiedBy,
                                              lastModifiedDate = lastModifiedDate,
                                              authorities = authorities))
    }

    override suspend fun storeAccessToken(accessToken: AccessToken) {
        storeAccessTokenInteractor(key = KEY_ACCESS_TOKEN, accessToken = accessToken.value)
    }
}
