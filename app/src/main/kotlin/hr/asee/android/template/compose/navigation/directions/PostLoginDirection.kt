package hr.asee.android.template.compose.navigation.directions

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
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

    object EditParkingSpace {
        const val KEY_PARKING_SPACE_ID = "parkingSpaceId"
        const val destination: String = "editParkingSpace/{$KEY_PARKING_SPACE_ID}"

        val definedArguments: List<NamedNavArgument> = listOf(
            navArgument(KEY_PARKING_SPACE_ID) { type = NavType.IntType }
        )

        fun editParkingSpaceRoute(parkingSpaceId: Int) = object : NavigationCommand {
            override val destination: String
                get() = "editParkingSpace/$parkingSpaceId"
            override val arguments: List<NamedNavArgument>
                get() = definedArguments
        }
    }

    object ReserveParkingSpaceSeeker {
        const val KEY_OFFER_ID = "offerId"
        const val destination: String = "reserveParkingSpaceSeeker/{$KEY_OFFER_ID}"

        val definedArguments: List<NamedNavArgument> = listOf(
            navArgument(KEY_OFFER_ID) { type = NavType.IntType }
        )

        fun reserveParkingSpaceRoute(offerId: Int) = object : NavigationCommand {
            override val destination: String
                get() = "reserveParkingSpaceSeeker/$offerId"
            override val arguments: List<NamedNavArgument>
                get() = definedArguments
        }
    }

    object ReserveParkingSpaceGiver {
        const val KEY_RESERVATION_ID = "reservationId"
        const val destination: String = "reserveParkingSpaceGiver/{$KEY_RESERVATION_ID}"

        val definedArguments: List<NamedNavArgument> = listOf(
            navArgument(KEY_RESERVATION_ID) { type = NavType.IntType }
        )

        fun reserveParkingSpaceRoute(reservationId: Int) = object : NavigationCommand {
            override val destination: String
                get() = "reserveParkingSpaceGiver/$reservationId"
            override val arguments: List<NamedNavArgument>
                get() = definedArguments
        }
    }

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

    val Settings = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "settings"
    }

    val UserManagement = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "userManagement"
    }

    val ChangePassword = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "changePassword"
    }

    val ParkingSpaces = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "parkingSpaces"
    }

//    val EditParkingSpace = object : NavigationCommand {
//        override val arguments: List<NamedNavArgument>
//            get() = emptyList()
//        override val destination: String
//            get() = "editParkingSpace"
//    }

    val AddParkingSpace = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "addParkingSpace"
    }

    val CreateSeeking = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "createSeeking"
    }

    val CreateOffer = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()
        override val destination: String
            get() = "createOffer"
    }
}
