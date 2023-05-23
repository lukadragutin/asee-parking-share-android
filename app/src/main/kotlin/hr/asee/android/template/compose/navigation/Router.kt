package hr.asee.android.template.compose.navigation

import hr.asee.android.template.domain.model.navigation.NavigationItem

interface Router {

    fun navigateToDirection(navigationDirection: NavigationItem.NavigationDirection)

    fun navigateToPostLoginScreen()

    fun navigateBack()

    fun showBottomNavBar()

    fun hideBottomNavBar()

    fun navigateToLoginScreen()

    fun navigateToRegistrationScreen()

    fun navigateToHomeScreen()

    fun navigateToParkingManagerScreen()

    fun navigateToSettingsScreen()

    fun navigateToUserManagementScreen()
}
