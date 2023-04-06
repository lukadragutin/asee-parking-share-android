package hr.asee.android.template.compose.delegate

import hr.asee.android.template.compose.delegate.event.NavigationEvent
import hr.asee.android.template.compose.delegate.impl.NavigationDelegateImpl
import hr.asee.android.template.compose.navigation.NavigationCommand
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection
import hr.asee.android.template.compose.navigation.directions.PreLoginDirection
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class NavigationDelegateTest {

    @Suppress("unused")
    companion object {
        @JvmStatic
        fun specialNavigationActionAndNavigationEventPairsDataSource(): Stream<Arguments> = Stream.of(
            Arguments.of(NavigationDelegate.SpecialNavigationAction.GO_BACK, NavigationEvent.NavigateBack),
        )

        @JvmStatic
        fun navigationCommandsDataSource(): Stream<Arguments> = Stream.of(
            Arguments.of(PostLoginDirection.Root),
            Arguments.of(PostLoginDirection.ApiInfo),
            Arguments.of(PostLoginDirection.Users),
            Arguments.of(PreLoginDirection.Root),
            Arguments.of(PreLoginDirection.Login),
            Arguments.of(PreLoginDirection.Onboarding)
        )
    }

    private lateinit var navigationDelegate: NavigationDelegate

    @BeforeEach
    fun setUpTest() {
        navigationDelegate = NavigationDelegateImpl()
    }

    @Test
    fun `getNavigationEvents should return valid SharedFlow of NavigationEvent items when invoked`() {
        // Arrange

        // Act
        val navigationEventsFlow = navigationDelegate.getNavigationEvents()

        // Assert
        assertThat(navigationEventsFlow).isNotNull.isInstanceOf(SharedFlow::class.java)
    }

    @ParameterizedTest
    @MethodSource("specialNavigationActionAndNavigationEventPairsDataSource")
    fun `navigate(specialNavigationAction) should emit correct NavigationEvent element to navigationEvents flow when invoked`(): Unit = runBlocking {
        // Arrange
        val navigationEventsFlow = navigationDelegate.getNavigationEvents()

        // Act
        navigationDelegate.navigate(NavigationDelegate.SpecialNavigationAction.GO_BACK)

        // Assert
        assertThat(navigationEventsFlow.first()).isEqualTo(NavigationEvent.NavigateBack)
    }

    @ParameterizedTest
    @MethodSource("navigationCommandsDataSource")
    fun `navigate(navigationCommand, builder) should emit correct NavigationEvent element to navigationEvents flow when invoked`(navigationCommandToEmit: NavigationCommand): Unit =
        runBlocking {
            // Arrange
            val navigationEventsFlow = navigationDelegate.getNavigationEvents()

            // Act
            navigationDelegate.navigate(navigationCommandToEmit)
            val emittedElement = navigationEventsFlow.first()

            // Assert
            assertThat(emittedElement).isInstanceOf(NavigationEvent.Directions::class.java)
            assertThat((emittedElement as NavigationEvent.Directions).navigationCommand).isEqualTo(navigationCommandToEmit)
        }
}
