package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.navigation.NavigationItem
import hr.asee.android.template.domain.repository.NavigationItemsRepository
import hr.asee.android.template.domain.usecase.impl.GetAllBottomNavItemsUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GetAllBottomNavItemsUseCaseTest {

    private lateinit var mockNavigationItemsRepository: NavigationItemsRepository
    private lateinit var getAllBottomNavItemsUseCase: GetAllBottomNavItemsUseCase

    @BeforeEach
    fun setUp() {
        mockNavigationItemsRepository = mock(NavigationItemsRepository::class.java)
        getAllBottomNavItemsUseCase = GetAllBottomNavItemsUseCaseImpl(navigationItemsRepository = mockNavigationItemsRepository)
    }

    @Test
    fun `invoke should return list of valid NavigationItem models when invoked`(): Unit = runBlocking {
        // Arrange
        val mockNavigationItems = listOf(NavigationItem(NavigationItem.NavigationDirection.USERS, 0, null, null))
        `when`(mockNavigationItemsRepository.getAllMenuItems()).thenReturn(mockNavigationItems)

        // Act
        val actualResult = getAllBottomNavItemsUseCase()

        // Assert
        assertThat(actualResult.data).isNotNull
        assertThat(actualResult.data).isEqualTo(mockNavigationItems)
    }
}
