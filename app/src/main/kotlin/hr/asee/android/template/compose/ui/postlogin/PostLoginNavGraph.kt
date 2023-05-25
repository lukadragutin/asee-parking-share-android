package hr.asee.android.template.compose.ui.postlogin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection
import hr.asee.android.template.compose.ui.postlogin.home.HomeScreen

fun NavGraphBuilder.postLoginNavGraph() {
    navigation(startDestination = PostLoginDirection.Home.destination, route = PostLoginDirection.Root.destination) {
        composable(route = PostLoginDirection.Home.destination, arguments = PostLoginDirection.Home.arguments) {
            HomeScreen()
        }

        composable(route = PostLoginDirection.ParkingManager.destination, arguments = PostLoginDirection.ParkingManager.arguments) {
            /* ParkingManagerScreen() */
        }

        composable(route = PostLoginDirection.Settings.destination, arguments = PostLoginDirection.Settings.arguments) {
            /* SettingsScreen() */
        }

        composable(route = PostLoginDirection.UserManagement.destination, arguments = PostLoginDirection.UserManagement.arguments) {
            /* UserManagementScreen() */
        }
    }
}
