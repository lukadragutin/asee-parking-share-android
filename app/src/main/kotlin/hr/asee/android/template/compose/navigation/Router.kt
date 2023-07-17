package hr.asee.android.template.compose.navigation

import hr.asee.android.template.domain.model.navigation.NavigationItem

interface Router {

    fun navigateToDirection(navigationDirection: NavigationItem.NavigationDirection)

    fun navigateToPostLoginScreen()

    fun navigateBack()

    fun showBottomNavBar()

    fun hideBottomNavBar()

    fun logout()

    fun navigateToLoginScreen()

    fun navigateToRegistrationScreen()

    fun navigateToHomeScreen()

    fun navigateToParkingManagerScreen()

    fun navigateToSettingsScreen()

    fun navigateToUserManagementScreen()

    fun navigateToChangePasswordScreen()

    fun navigateToParkingSpacesScreen(userId: Int)

    fun navigateToEditParkingSpaceScreen(parkingSpaceId: Int)

    fun navigateToAddParkingSpaceScreen()

    fun navigateToReserveParkingSpaceSeekerScreen(offerId: Int)

    fun navigateToReserveParkingSpaceGiverScreen(reservationId: Int)

    fun navigateToCreateSeekingScreen()

    fun navigateToCreateOfferScreen(userId: Int)

    fun navigateToRegisterScreen()

    fun navigateToParkingManagerScreen()

    fun navigateToSeekingRequestScreen()

    fun navigateToReserveParkingSpaceScreen()

    fun navigateToParkingOfferScreen()
}
