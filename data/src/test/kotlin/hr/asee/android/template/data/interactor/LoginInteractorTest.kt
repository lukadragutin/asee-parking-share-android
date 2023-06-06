package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.interactor.impl.LoginInteractorImpl
import hr.asee.android.template.data.model.remote.body.ApiLoginRequest
import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import hr.asee.android.template.data.model.remote.response.common.ApiError
import hr.asee.android.template.data.network.AuthenticationApiService
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.HttpException
import retrofit2.Response

class LoginInteractorTest {

    private lateinit var reqResService: AuthenticationApiService
    lateinit var reqResServiceErrorResolver: ReqResServiceErrorResolver
    lateinit var loginInteractor: LoginInteractor

    @BeforeEach
    fun setUp() {
        reqResService = mock(AuthenticationApiService::class.java)
        reqResServiceErrorResolver = mock(ReqResServiceErrorResolver::class.java)
        loginInteractor = LoginInteractorImpl(authenticationApiService = reqResService, reqResServiceErrorResolver = reqResServiceErrorResolver)
    }

    @Test
    fun `invoke should return valid ApiLoginResponse response when invoked`(): Unit = runBlocking {
        // Arrange
        val mockApiRequest = ApiLoginRequest(email = "email", password = "password")
        val mockApiResponse = ApiAccessToken(accessToken = "token")
        `when`(reqResService.login(mockApiRequest)).thenReturn(mockApiResponse)

        // Act
        val actualResponse = loginInteractor(mockApiRequest)

        // Assert
        assertThat(actualResponse).isEqualTo(mockApiResponse)
    }

    @Test
    fun `invoke should resolve throwable to an Exception when API service throws error`(): Unit = runBlocking {
        // Arrange
        val mockApiRequest = ApiLoginRequest(email = "email", password = "password")
        val mockErrorJson = "{\"error\": \"Missing password\"}"
        val mockApiThrowable = HttpException(
            Response.error<ApiError>(
                400, mockErrorJson.toResponseBody("application/json; charset=utf-8".toMediaType())
            )
        )
        `when`(reqResService.login(mockApiRequest)).thenThrow(mockApiThrowable)

        // Act
        catchThrowable { runBlocking { loginInteractor(mockApiRequest) } }

        // Assert
        verify(reqResServiceErrorResolver).toException(mockErrorJson)
    }
}
