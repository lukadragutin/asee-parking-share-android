package hr.asee.android.template.compose.navigation.directions

import androidx.navigation.NamedNavArgument
import hr.asee.android.template.compose.navigation.NavigationCommand

object PostLoginDirection {

    val Root = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "postLogin"
    }

    // Example of a route with arguments
//    object User {
//        const val KEY_USER_ID = "userId"
//        const val destination: String = "user/{$KEY_USER_ID}"
//
//        val definedArguments: List<NamedNavArgument> = listOf(
//            navArgument(KEY_USER_ID) { type = NavType.IntType }
//        )
//
//        fun userRoute(userId: Int) = object : NavigationCommand {
//            override val arguments: List<NamedNavArgument>
//                get() = definedArguments
//            override val destination: String
//                get() = "user/$userId"
//        }
//    }

    /*val ApiInfo = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "apiInfo"
    }*/

    val Home = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "home"
    }

    val ParkingManager = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "parkingManager"
    }

    val SeekingRequest = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "seekingRequest"
    }

    val ParkingOffer = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "parkingOffer"
    }

    val ReserveParkingSpace = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "reserveParkingSpace"
    }

    val Settings = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "settings"
    }
}
