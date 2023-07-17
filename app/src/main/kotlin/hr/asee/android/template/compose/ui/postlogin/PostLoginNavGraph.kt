package hr.asee.android.template.compose.ui.postlogin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection.EditParkingSpace.KEY_PARKING_SPACE_ID
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection.ParkingSpace.PARKING_SPACE_USER_ID_KEY
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection.ReserveParkingSpaceGiver.KEY_RESERVATION_ID
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection.ReserveParkingSpaceSeeker.KEY_OFFER_ID
import hr.asee.android.template.compose.ui.postlogin.addparkingspace.AddParkingSpaceScreen
import hr.asee.android.template.compose.ui.postlogin.changepassword.ChangePasswordScreen
import hr.asee.android.template.compose.ui.postlogin.createoffer.CreateOfferScreen
import hr.asee.android.template.compose.ui.postlogin.createseeking.CreateSeekingScreen
import hr.asee.android.template.compose.ui.postlogin.editparkingspace.EditParkingSpaceScreen
import hr.asee.android.template.compose.ui.postlogin.home.HomeScreen
import hr.asee.android.template.compose.ui.postlogin.parkingspaces.ParkingSpacesScreen
import hr.asee.android.template.compose.ui.postlogin.reserveparkingspace.ReserveParkingSpaceGiverScreen
import hr.asee.android.template.compose.ui.postlogin.reserveparkingspace.ReserveParkingSpaceSeekerScreen
import hr.asee.android.template.compose.ui.postlogin.settings.SettingsScreen
import hr.asee.android.template.compose.ui.postlogin.usermanagement.UserManagementScreen
import hr.asee.android.template.compose.ui.postlogin.parking_manager.ParkingManagerScreen
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.ParkingOfferScreen
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.ReserveParkingSpaceScreen
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.SeekingRequestScreen

fun NavGraphBuilder.postLoginNavGraph() {
	navigation(startDestination = PostLoginDirection.Home.destination, route = PostLoginDirection.Root.destination) {
		composable(route = PostLoginDirection.Home.destination, arguments = PostLoginDirection.Home.arguments) {
			HomeScreen()
		}

		composable(route = PostLoginDirection.Settings.destination, arguments = PostLoginDirection.Settings.arguments) {
			SettingsScreen()
		}

		composable(route = PostLoginDirection.UserManagement.destination, arguments = PostLoginDirection.UserManagement.arguments) {
			UserManagementScreen()
		}

		composable(route = PostLoginDirection.ChangePassword.destination, arguments = PostLoginDirection.ChangePassword.arguments) {
			ChangePasswordScreen()
		}

		composable(route = PostLoginDirection.ParkingSpace.destination, arguments = PostLoginDirection.ParkingSpace.definedArguments) {
			ParkingSpacesScreen(userId = it.arguments?.getString(PARKING_SPACE_USER_ID_KEY)?.toInt() ?: -1)
		}

		composable(route = PostLoginDirection.EditParkingSpace.destination, arguments = PostLoginDirection.EditParkingSpace.definedArguments) {
			EditParkingSpaceScreen(parkingSpaceId = it.arguments?.getInt(KEY_PARKING_SPACE_ID) as Int)
		}

		composable(route = PostLoginDirection.AddParkingSpace.destination, arguments = PostLoginDirection.AddParkingSpace.arguments) {
			AddParkingSpaceScreen()
		}

		composable(route = PostLoginDirection.ReserveParkingSpaceSeeker.destination, arguments = PostLoginDirection.ReserveParkingSpaceSeeker.definedArguments) {
			ReserveParkingSpaceSeekerScreen(offerId = it.arguments?.getInt(KEY_OFFER_ID) as Int)
		}

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
		composable(route = PostLoginDirection.ReserveParkingSpaceGiver.destination, arguments = PostLoginDirection.ReserveParkingSpaceGiver.definedArguments) {
			ReserveParkingSpaceGiverScreen(reservationId = it.arguments?.getInt(KEY_RESERVATION_ID) as Int)
		}

		composable(route = PostLoginDirection.CreateSeeking.destination, arguments = PostLoginDirection.CreateSeeking.arguments) {
			CreateSeekingScreen()
		}

		composable(route = PostLoginDirection.CreateOffer.DESTINATION, arguments = PostLoginDirection.CreateOffer.definedArguments) {
			CreateOfferScreen(userId = it.arguments?.getInt(PostLoginDirection.CreateOffer.CREATE_OFFER_USER_ID_KEY) ?: 0)
		}
	}
}
