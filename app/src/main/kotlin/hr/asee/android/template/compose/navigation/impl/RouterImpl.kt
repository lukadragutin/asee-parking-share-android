package hr.asee.android.template.compose.navigation.impl

import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.delegate.NavigationDelegate
import hr.asee.android.template.compose.navigation.NavigationCommand
import hr.asee.android.template.compose.navigation.Router
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection
import hr.asee.android.template.compose.navigation.directions.PreLoginDirection
import hr.asee.android.template.domain.model.navigation.NavigationItem

class RouterImpl(private val navigationDelegate: NavigationDelegate, private val bottomNavBarDelegate: BottomNavBarDelegate) : Router {

    override fun navigateToDirection(navigationDirection: NavigationItem.NavigationDirection) {
        when (navigationDirection) {
            NavigationItem.NavigationDirection.USERS -> navigate(PostLoginDirection.Users, clearBackstack = true)
            NavigationItem.NavigationDirection.API_INFO -> navigate(PostLoginDirection.ApiInfo, clearBackstack = true)
            NavigationItem.NavigationDirection.LOGOUT -> logout()
        }
    }

    private fun navigate(navigationCommand: NavigationCommand, clearBackstack: Boolean = false, singleTop: Boolean = true) {
        navigationDelegate.navigate(navigationCommand) {
            if (clearBackstack) {
                popUpTo(0)
            }

            launchSingleTop = singleTop
        }
    }

    private fun logout() {
        hideBottomNavBar()
        navigate(PreLoginDirection.Login, clearBackstack = true)
    }

    override fun hideBottomNavBar() {
        bottomNavBarDelegate.hideBottomNavBar()
    }

    override fun navigateToPostLoginScreen() {
        showBottomNavBar()
        navigate(PostLoginDirection.Root, clearBackstack = true)
    }

    override fun showBottomNavBar() {
        bottomNavBarDelegate.showBottomNavBar()
    }

    override fun navigateBack() {
        navigationDelegate.navigate(NavigationDelegate.SpecialNavigationAction.GO_BACK)
    }

    override fun navigateToLoginScreen() {
        navigationDelegate.navigate(navigationCommand = PreLoginDirection.Login)
    }

    override fun navigateToRegistrationScreen() {
        navigationDelegate.navigate(navigationCommand = PreLoginDirection.Registration)
    }
}
