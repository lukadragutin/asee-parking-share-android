package hr.asee.android.template.compose.ui.prelogin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import hr.asee.android.template.compose.navigation.directions.PreLoginDirection
import hr.asee.android.template.compose.ui.prelogin.login.LoginScreen
import hr.asee.android.template.compose.ui.prelogin.onboarding.OnboardingScreen

fun NavGraphBuilder.preLoginNavGraph() {

    navigation(startDestination = PreLoginDirection.Onboarding.destination, route = PreLoginDirection.Root.destination) {
        composable(route = PreLoginDirection.Onboarding.destination, arguments = PreLoginDirection.Onboarding.arguments) {
            OnboardingScreen()
        }

        composable(route = PreLoginDirection.Login.destination, arguments = PreLoginDirection.Login.arguments) {
            LoginScreen()
        }
    }
}
