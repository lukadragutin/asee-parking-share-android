package hr.asee.android.template.compose.navigation.directions

import androidx.navigation.NamedNavArgument
import hr.asee.android.template.compose.navigation.NavigationCommand

object PreLoginDirection {

    val Root = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "prelogin"
    }

    val Onboarding = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "onboarding"
    }

    val Login = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "login"
    }

    val Register = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "register"
    }
}
