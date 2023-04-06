package hr.asee.android.template.compose.delegate

import hr.asee.android.template.compose.delegate.event.NavBarEvent
import hr.asee.android.template.compose.delegate.impl.BottomNavBarDelegateImpl
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BottomNavBarDelegateTest {

    private val bottomNavBarDelegate: BottomNavBarDelegate = BottomNavBarDelegateImpl()

    @Test
    fun `getNavBarEvents should return SharedFlow of NavBarEvent items when invoked`() {
        // Arrange

        // Act
        val actualResult = bottomNavBarDelegate.getNavBarEvents()

        // Assert
        assertThat(actualResult)
            .isNotNull
            .isInstanceOf(SharedFlow::class.java)
    }

    @Test
    fun `showBottomNavBar should emit ShowNavBar NavBar event when invoked`(): Unit = runBlocking {
        // Arrange
        val emittedElements = bottomNavBarDelegate.getNavBarEvents()

        // Act
        bottomNavBarDelegate.showBottomNavBar()
        val emittedElement = emittedElements.first()

        // Assert
        assertThat(emittedElement).isEqualTo(NavBarEvent.ShowNavBar)
    }

    @Test
    fun `hideBottomNavBar should emit HideNavBar NavBar event when invoked`(): Unit = runBlocking {
        // Arrange
        val emittedElements = bottomNavBarDelegate.getNavBarEvents()

        // Act
        bottomNavBarDelegate.hideBottomNavBar()
        val emittedElement = emittedElements.first()

        // Assert
        assertThat(emittedElement).isEqualTo(NavBarEvent.HideNavBar)
    }
}
