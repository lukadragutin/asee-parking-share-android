package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.navigation.NavigationItem
import hr.asee.android.template.domain.repository.impl.NavigationItemsRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NavigationItemsRepositoryTest {

    private val mockNavigationItems = listOf(NavigationItem(NavigationItem.NavigationDirection.USERS, 0, null, null))
    private lateinit var navigationItemsRepository: NavigationItemsRepository

    @BeforeEach
    fun setUp() {
        navigationItemsRepository = NavigationItemsRepositoryImpl(mockNavigationItems)
    }

    @Test
    fun `getAllMenuItems should return NavigationItem objects when invoked`(): Unit = runBlocking {
        // Arrange

        // Act
        val actualNavigationItems = navigationItemsRepository.getAllMenuItems()

        // Assert
        assertThat(actualNavigationItems).isEqualTo(mockNavigationItems)
    }
}
