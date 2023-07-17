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
			NavigationItem.NavigationDirection.LOGOUT -> bottomNavBarDelegate.logout()
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

	override fun logout() {
		bottomNavBarDelegate.loggedOut()
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
		navigate(navigationCommand = PreLoginDirection.Login)
	}

	override fun navigateToRegistrationScreen() {
		navigate(navigationCommand = PreLoginDirection.Registration)
	}

	override fun navigateToHomeScreen() {
		showBottomNavBar()
		navigate(navigationCommand = PostLoginDirection.Home)
	}

	override fun navigateToParkingManagerScreen() {
		showBottomNavBar()
		navigate(navigationCommand = PostLoginDirection.ParkingManager)
	}

	override fun navigateToSettingsScreen() {
		navigate(navigationCommand = PostLoginDirection.Settings)
	}

	override fun navigateToUserManagementScreen() {
		navigate(navigationCommand = PostLoginDirection.UserManagement)
	}

	override fun navigateToChangePasswordScreen() {
		navigate(navigationCommand = PostLoginDirection.ChangePassword)
	}

	override fun navigateToParkingSpacesScreen(userId: Int) {
		navigate(navigationCommand = PostLoginDirection.ParkingSpace.parkingSpaceRoute(userId))
	}

	override fun navigateToAddParkingSpaceScreen() {
		navigate(navigationCommand = PostLoginDirection.AddParkingSpace)
	}

	override fun navigateToEditParkingSpaceScreen(parkingSpaceId: Int) {
		navigate(navigationCommand = PostLoginDirection.EditParkingSpace.editParkingSpaceRoute(parkingSpaceId))
	}

	override fun navigateToReserveParkingSpaceSeekerScreen(offerId: Int) {
		navigate(navigationCommand = PostLoginDirection.ReserveParkingSpaceSeeker.reserveParkingSpaceRoute(offerId))
	}

	override fun navigateToReserveParkingSpaceGiverScreen(reservationId: Int) {
		navigate(navigationCommand = PostLoginDirection.ReserveParkingSpaceGiver.reserveParkingSpaceRoute(reservationId))
	}

	override fun navigateToCreateSeekingScreen() {
		navigate(navigationCommand = PostLoginDirection.CreateSeeking)
	}

	override fun navigateToCreateOfferScreen(userId: Int) {
		navigate(navigationCommand = PostLoginDirection.CreateOffer.createOfferRoute(userId))
	}
    override fun navigateToLoginScreen() {
        navigationDelegate.navigate(navigationCommand = PreLoginDirection.Login)
    }

    override fun navigateToRegisterScreen() {
        navigationDelegate.navigate(navigationCommand = PreLoginDirection.Register)
    }

    override fun navigateToParkingManagerScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.ParkingManager)
    }

    override fun navigateToSeekingRequestScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.SeekingRequest)
    }

    override fun navigateToReserveParkingSpaceScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.ReserveParkingSpace)
    }

    override fun navigateToParkingOfferScreen() {
        navigationDelegate.navigate(navigationCommand = PostLoginDirection.ParkingOffer)
    }
}
