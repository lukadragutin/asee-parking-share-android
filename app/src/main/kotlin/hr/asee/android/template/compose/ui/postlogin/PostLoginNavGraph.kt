package hr.asee.android.template.compose.ui.postlogin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection
import hr.asee.android.template.compose.ui.postlogin.parking_manager.ParkingManagerScreen
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.ParkingOfferScreen
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.ReserveParkingSpaceScreen
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.SeekingRequestScreen

fun NavGraphBuilder.postLoginNavGraph() {
    navigation(startDestination = PostLoginDirection.ParkingManager.destination, route = PostLoginDirection.Root.destination) {

        composable(route = PostLoginDirection.ParkingManager.destination, arguments = PostLoginDirection.ParkingManager.arguments) {
            ParkingManagerScreen()
        }

        composable(route = PostLoginDirection.SeekingRequest.destination, arguments = PostLoginDirection.SeekingRequest.arguments) {
            SeekingRequestScreen()
        }

        composable(route = PostLoginDirection.ParkingOffer.destination, arguments = PostLoginDirection.ParkingOffer.arguments) {
            ParkingOfferScreen()
        }

        composable(route = PostLoginDirection.ReserveParkingSpace.destination, arguments = PostLoginDirection.ReserveParkingSpace.arguments) {
            ReserveParkingSpaceScreen()
        }
    }
}
