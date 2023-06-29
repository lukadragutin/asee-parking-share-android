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
            NavigationItem.NavigationDirection.LOGOUT -> logout()
            NavigationItem.NavigationDirection.HOME -> navigate(PostLoginDirection.Home, clearBackstack = false)
            NavigationItem.NavigationDirection.PARKING_MANAGER -> navigate(PostLoginDirection.ParkingManager, clearBackstack = false)
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

    override fun navigateToHomeScreen() {
        showBottomNavBar()
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.Home)
    }

    override fun navigateToParkingManagerScreen() {
        showBottomNavBar()
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.ParkingManager)
    }

    override fun navigateToSettingsScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.Settings)
    }

    override fun navigateToUserManagementScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.UserManagement)
    }

    override fun navigateToChangePasswordScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.ChangePassword)
    }

    override fun navigateToParkingSpacesScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.ParkingSpaces)
    }

    override fun navigateToAddParkingSpaceScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.AddParkingSpace)
    }

    override fun navigateToEditParkingSpaceScreen(parkingSpaceId: Int) {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.EditParkingSpace.editParkingSpaceRoute(parkingSpaceId))
    }

    override fun navigateToReserveParkingSpaceSeekerScreen(offerId: Int) {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.ReserveParkingSpaceSeeker.reserveParkingSpaceRoute(offerId))
    }

    override fun navigateToReserveParkingSpaceGiverScreen(reservationId: Int) {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.ReserveParkingSpaceGiver.reserveParkingSpaceRoute(reservationId))
    }

    override fun navigateToCreateSeekingScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.CreateSeeking)
    }

    override fun navigateToCreateOfferScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.CreateOffer)
    }
}
