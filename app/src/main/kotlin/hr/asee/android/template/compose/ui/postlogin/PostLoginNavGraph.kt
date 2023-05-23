package hr.asee.android.template.compose.ui.postlogin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection
import hr.asee.android.template.compose.ui.postlogin.apiinfo.ApiInfoScreen
import hr.asee.android.template.compose.ui.postlogin.home.HomeScreen
import hr.asee.android.template.compose.ui.postlogin.users.UsersScreen

fun NavGraphBuilder.postLoginNavGraph() {
    navigation(startDestination = PostLoginDirection.Home.destination, route = PostLoginDirection.Root.destination) {
        composable(route = PostLoginDirection.Home.destination, arguments = PostLoginDirection.Home.arguments) {
            HomeScreen()
        }

        composable(route = PostLoginDirection.Users.destination, arguments = PostLoginDirection.Users.arguments) {
            UsersScreen()
        }

        composable(route = PostLoginDirection.ApiInfo.destination, arguments = PostLoginDirection.ApiInfo.arguments) {
            ApiInfoScreen()
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
