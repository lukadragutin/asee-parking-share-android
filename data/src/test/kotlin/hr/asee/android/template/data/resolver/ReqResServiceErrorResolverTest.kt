package hr.asee.android.template.data.resolver

import hr.asee.android.template.data.model.remote.exception.GeneralException
import hr.asee.android.template.data.model.remote.exception.login.MissingEmailOrUsernameException
import hr.asee.android.template.data.model.remote.exception.login.MissingPasswordException
import hr.asee.android.template.data.model.remote.exception.login.UserNotFoundException
import hr.asee.android.template.data.resolver.impl.ReqResServiceErrorResolverImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ReqResServiceErrorResolverTest {

    @Suppress("unused")
    companion object {
        @JvmStatic
        fun expectedReqResServiceErrorsSource(): Stream<Arguments> = Stream.of(
            Arguments.of("{\"error\": \"Missing password\"}", MissingPasswordException::class.java),
            Arguments.of("{\"error\": \"user not found\"}", UserNotFoundException::class.java),
            Arguments.of("{\"error\": \"Missing email or username\"}", MissingEmailOrUsernameException::class.java),
            Arguments.of("invalid-error-format", GeneralException::class.java)
        )
    }

    private lateinit var reqResServiceErrorResolver: ReqResServiceErrorResolver

    @BeforeEach
    fun setUp() {
        reqResServiceErrorResolver = ReqResServiceErrorResolverImpl()
    }

    @ParameterizedTest
    @MethodSource("expectedReqResServiceErrorsSource")
    fun `toException should return resolved Exception when invoked with expected json string error`(jsonError: String, expectedExceptionInstance: Class<Any>) {
        // Arrange

        // Act
        val resolvedException = reqResServiceErrorResolver.toException(jsonError)

        // Assert
        assertThat(resolvedException).isInstanceOf(expectedExceptionInstance)
    }
}
