package hr.asee.android.template.compose.navigation

import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.delegate.NavigationDelegate
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection
import hr.asee.android.template.compose.navigation.directions.PreLoginDirection
import hr.asee.android.template.compose.navigation.impl.RouterImpl
import hr.asee.android.template.compose.util.MockitoHelper
import hr.asee.android.template.domain.model.navigation.NavigationItem
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.util.stream.Stream

@ExtendWith(MockitoExtension::class)
class RouterTest {

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun navigationDirectionsAndCommandsMethodSource(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(NavigationItem.NavigationDirection.USERS, PostLoginDirection.Users),
                Arguments.of(NavigationItem.NavigationDirection.API_INFO, PostLoginDirection.ApiInfo),
            )
        }
    }

    @Mock
    private lateinit var mockNavigationDelegate: NavigationDelegate

    @Mock
    private lateinit var mockBottomNavBarDelegate: BottomNavBarDelegate

    private lateinit var router: Router

    @BeforeEach
    fun setUp() {
        router = RouterImpl(mockNavigationDelegate, bottomNavBarDelegate = mockBottomNavBarDelegate)
    }

    @ParameterizedTest
    @MethodSource("navigationDirectionsAndCommandsMethodSource")
    fun `navigateToDirection should invoke NavigationDelegate with correct Users NavigationCommand when invoked with USERS NavigationDirection`(
        navigationDirection: NavigationItem.NavigationDirection,
        expectedNavigationCommand: NavigationCommand,
    ) {
        // Arrange

        // Act
        router.navigateToDirection(navigationDirection = navigationDirection)

        // Assert
        verify(mockNavigationDelegate).navigate(navigationCommand = MockitoHelper.eq(expectedNavigationCommand), builder = MockitoHelper.anyObject())
    }

    @Test
    fun `navigateBack should invoke NavigationDelegate with SpecialNavigationAction GO_BACK when invoked`() {
        // Arrange

        // Act
        router.navigateBack()

        // Assert
        verify(mockNavigationDelegate).navigate(specialNavigationAction = NavigationDelegate.SpecialNavigationAction.GO_BACK)
    }

    @Test
    fun `navigateToPostLogin should invoke BottomNavBarDelegate with ShowNavBar event when invoked`() {
        // Arrange

        // Act
        router.navigateToPostLoginScreen()

        //Assert
        verify(mockBottomNavBarDelegate).showBottomNavBar()
    }

    @Test
    fun `navigateToDirection should invoke BottomNavBarDelegate with HideNavBar event when invoked with LOGOUT direction`() {
        // Arrange

        // Act
        router.navigateToDirection(NavigationItem.NavigationDirection.LOGOUT)

        // Assert
        verify(mockBottomNavBarDelegate).hideBottomNavBar()
    }

    @Test
    fun `showBottomNavBar should invoke BottomNavBarDelegate when invoked`() {
        // Arrange

        // Act
        router.showBottomNavBar()

        // Assert
        verify(mockBottomNavBarDelegate).showBottomNavBar()
    }

    @Test
    fun `hideBottomNavBar should invoke BottomNavBarDelegate when invoked`() {
        // Arrange

        // Act
        router.hideBottomNavBar()

        // Assert
        verify(mockBottomNavBarDelegate).hideBottomNavBar()
    }

    @Test
    fun `navigateToLoginScreen should invoke NavigationDelegate with Login direction when invoked`() {
        // Arrange

        // Act
        router.navigateToLoginScreen()

        // Assert
        verify(mockNavigationDelegate).navigate(MockitoHelper.eq(PreLoginDirection.Login), MockitoHelper.anyObject())
    }
}
