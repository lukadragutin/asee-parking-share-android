package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.GetAccessTokenInteractor
import hr.asee.android.template.data.interactor.GetAccountInteractor
import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.LogoutInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.mapper.UserMapper
import hr.asee.android.template.domain.model.common.AccessToken
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
	private val loginInteractor: LoginInteractor,
	private val logoutInteractor: LogoutInteractor,
	private val accessTokenMapper: AccessTokenMapper,
	private val storeAccessTokenInteractor: StoreAccessTokenInteractor,
	private val getAccessTokenInteractor: GetAccessTokenInteractor,
	private val getAccountInteractor: GetAccountInteractor,
	private val userMapper: UserMapper
) : AuthenticationRepository {

	companion object {
		private const val KEY_ACCESS_TOKEN = "keyAccessToken"
	}

	override suspend fun login(email: String, password: String): AccessToken {
		return accessTokenMapper.toAccessToken(loginInteractor(ApiLoginRequest(username = email, password = password, rememberMe = true)))
	}

	override suspend fun isAccessTokenValid(): Boolean {
		return getAccessTokenInteractor(KEY_ACCESS_TOKEN).isNotEmpty()
	}

	override suspend fun storeAccessToken(accessToken: AccessToken) {
		storeAccessTokenInteractor(key = KEY_ACCESS_TOKEN, accessToken = accessToken.value)
	}

	override suspend fun getAccount(): User {
		return userMapper.toUser(getAccountInteractor())
	}

	override suspend fun logout() {
		logoutInteractor(KEY_ACCESS_TOKEN)
	}
}
