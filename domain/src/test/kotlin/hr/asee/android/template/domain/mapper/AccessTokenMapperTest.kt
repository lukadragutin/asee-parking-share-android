package hr.asee.android.template.domain.mapper

import hr.asee.android.template.data.model.remote.response.ApiAccessToken
import hr.asee.android.template.domain.mapper.impl.AccessTokenMapperImpl
import hr.asee.android.template.domain.model.common.AccessToken
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AccessTokenMapperTest {

    lateinit var accessTokenMapper: AccessTokenMapper

    @BeforeEach
    fun setUp() {
        accessTokenMapper = AccessTokenMapperImpl()
    }

    @Test
    fun `toAccessToken should return AccessToken when invoked with ApiAccessToken argument`() {
        // Arrange
        val mockApiAccessToken = ApiAccessToken(accessToken = "token")
        val expectedAccessToken = AccessToken(value = "token")

        // Act
        val actualAccessToken = accessTokenMapper.toAccessToken(mockApiAccessToken)

        // Assert
        assertThat(actualAccessToken).isEqualTo(expectedAccessToken)
    }
}
