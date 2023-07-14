package hr.asee.android.template.domain.repository

import hr.asee.android.template.data.interactor.GetAccountInteractor
import hr.asee.android.template.data.interactor.LoginInteractor
import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import hr.asee.android.template.domain.mapper.AccessTokenMapper
import hr.asee.android.template.domain.mapper.UserMapper
import hr.asee.android.template.domain.model.common.AccessToken
import hr.asee.android.template.domain.repository.impl.AuthenticationRepositoryImpl
import hr.asee.android.template.domain.util.MockitoHelper
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class AuthenticationRepositoryTest {

    lateinit var loginInteractor: LoginInteractor
    lateinit var accessTokenMapper: AccessTokenMapper
    lateinit var storeAccessTokenInteractor: StoreAccessTokenInteractor
    lateinit var authenticationRepository: AuthenticationRepository

    @BeforeEach
    fun setUp() {
        loginInteractor = mock(LoginInteractor::class.java)
        accessTokenMapper = mock(AccessTokenMapper::class.java)
        storeAccessTokenInteractor = mock(StoreAccessTokenInteractor::class.java)
        authenticationRepository = AuthenticationRepositoryImpl(
            loginInteractor = loginInteractor,
            accessTokenMapper = accessTokenMapper,
            storeAccessTokenInteractor = storeAccessTokenInteractor,
            getAccountInteractor = mock(GetAccountInteractor::class.java),
            userMapper = mock(UserMapper::class.java)
        )
    }

    @Test
    fun `login should return AccessToken when invoked with email and password arguments`(): Unit = runBlocking {
        // Arrange
        val email = "email"
        val password = "password"
        val token = "token"
        val mockApiAccessToken = ApiAccessToken(accessToken = token)
        val expectedAccessToken = AccessToken(value = token)
        val mockApiRequest = ApiLoginRequest(username = email, password = password)
        `when`(loginInteractor(mockApiRequest)).thenReturn(mockApiAccessToken)
        `when`(accessTokenMapper.toAccessToken(mockApiAccessToken)).thenReturn(expectedAccessToken)

        // Act
        val actualAccessToken = authenticationRepository.login(email, password)

        // Assert
        assertThat(actualAccessToken).isEqualTo(expectedAccessToken)
    }

    @Test
    fun `storeAccessToken should invoke StoreAccessTokenInteractor when invoked`(): Unit = runBlocking {
        // Arrange
        val token = "token"
        val mockAccessToken = AccessToken(token)

        // Act
        authenticationRepository.storeAccessToken(mockAccessToken)

        // Assert
        verify(storeAccessTokenInteractor).invoke(MockitoHelper.anyObject(), MockitoHelper.eq(token))
    }
}
