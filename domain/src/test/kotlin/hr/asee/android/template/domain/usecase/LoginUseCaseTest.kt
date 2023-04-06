package hr.asee.android.template.domain.usecase

import hr.asee.android.template.data.model.remote.exception.login.MissingEmailOrUsernameException
import hr.asee.android.template.data.model.remote.exception.login.MissingPasswordException
import hr.asee.android.template.data.model.remote.exception.login.UserNotFoundException
import hr.asee.android.template.domain.model.common.AccessToken
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.usecase.impl.LoginUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class LoginUseCaseTest {

    lateinit var authenticationRepository: AuthenticationRepository
    lateinit var loginUseCase: LoginUseCase

    @BeforeEach
    fun setUp() {
        authenticationRepository = mock(AuthenticationRepository::class.java)
        loginUseCase = LoginUseCaseImpl(authenticationRepository = authenticationRepository)
    }

    @Test
    fun `invoke should return valid EmptyResource when login is successful`(): Unit = runBlocking {
        // Arrange
        val email = "email"
        val password = "password"
        val mockRequest = LoginUseCase.LoginRequest(email = email, password = password)
        val mockAccessToken = AccessToken("token")
        `when`(authenticationRepository.login(email = email, password = password)).thenReturn(mockAccessToken)
        `when`(authenticationRepository.storeAccessToken(accessToken = mockAccessToken)).thenReturn(Unit)

        // Act
        val actualResult = loginUseCase(mockRequest)

        // Assert
        assertThat(actualResult).isInstanceOf(Resource.Success::class.java)
        assertThat(actualResult.data).isEqualTo(Unit)
        assertThat(actualResult.errorData).isNull()
    }

    @Test
    fun `invoke should return MISSING_PASSWORD_ERROR when invoked with empty password`(): Unit = runBlocking {
        val email = "email"
        val password = ""
        val mockRequest = LoginUseCase.LoginRequest(email = email, password = password)
        `when`(authenticationRepository.login(email = email, password = "")).then { throw MissingPasswordException() }

        val actualResult = loginUseCase(mockRequest)

        assertThat(actualResult).isInstanceOf(Resource.Error::class.java)
        assertThat(actualResult.errorData?.errorType).isEqualTo(LoginUseCase.LoginError.MISSING_PASSWORD_ERROR)
        assertThat(actualResult.errorData?.throwable).isInstanceOf(MissingPasswordException::class.java)
        assertThat(actualResult.data).isNull()
    }

    @Test
    fun `invoke should return USER_NOT_FOUND when invoked with unknown email`(): Unit = runBlocking {
        val email = "unknown"
        val password = "password"
        val mockRequest = LoginUseCase.LoginRequest(email = email, password = password)
        `when`(authenticationRepository.login(email = email, password = password)).then { throw UserNotFoundException() }

        val actualResult = loginUseCase(mockRequest)

        assertThat(actualResult).isInstanceOf(Resource.Error::class.java)
        assertThat(actualResult.errorData?.errorType).isEqualTo(LoginUseCase.LoginError.USER_NOT_FOUND_ERROR)
        assertThat(actualResult.errorData?.throwable).isInstanceOf(UserNotFoundException::class.java)
        assertThat(actualResult.data).isNull()
    }

    @Test
    fun `invoke should return MISSING_EMAIL_OR_USERNAME_ERROR when invoked with empty email`(): Unit = runBlocking {
        val email = ""
        val password = "password"
        val mockRequest = LoginUseCase.LoginRequest(email = email, password = password)
        `when`(authenticationRepository.login(email = "", password = password)).then { throw MissingEmailOrUsernameException() }

        val actualResult = loginUseCase(mockRequest)

        assertThat(actualResult).isInstanceOf(Resource.Error::class.java)
        assertThat(actualResult.errorData?.errorType).isEqualTo(LoginUseCase.LoginError.MISSING_EMAIL_OR_USERNAME_ERROR)
        assertThat(actualResult.errorData?.throwable).isInstanceOf(MissingEmailOrUsernameException::class.java)
        assertThat(actualResult.data).isNull()
    }

    @Test
    fun `invoke should return GENERAL_LOGIN_ERROR when login fails unexpectedly`(): Unit = runBlocking {
        val email = "email"
        val password = "password"
        val mockRequest = LoginUseCase.LoginRequest(email = email, password = password)
        `when`(authenticationRepository.login(email = email, password = password)).then { throw RuntimeException() }

        val actualResult = loginUseCase(mockRequest)

        assertThat(actualResult).isInstanceOf(Resource.Error::class.java)
        assertThat(actualResult.errorData?.errorType).isEqualTo(LoginUseCase.LoginError.GENERAL_LOGIN_ERROR)
        assertThat(actualResult.errorData?.throwable).isNotNull
        assertThat(actualResult.data).isNull()
    }

    @Test
    fun `invoke should return STORE_ACCESS_TOKEN_ERROR when storing access token fails`(): Unit = runBlocking {
        val email = "email"
        val password = "password"
        val mockRequest = LoginUseCase.LoginRequest(email = email, password = password)
        val mockAccessToken = AccessToken("token")
        `when`(authenticationRepository.login(email = email, password = password)).thenReturn(mockAccessToken)
        `when`(authenticationRepository.storeAccessToken(mockAccessToken)).then { throw RuntimeException() }

        val actualResult = loginUseCase(mockRequest)

        assertThat(actualResult).isInstanceOf(Resource.Error::class.java)
        assertThat(actualResult.errorData?.errorType).isEqualTo(LoginUseCase.LoginError.STORE_ACCESS_TOKEN_ERROR)
        assertThat(actualResult.errorData?.throwable).isNotNull
        assertThat(actualResult.data).isNull()
    }
}
