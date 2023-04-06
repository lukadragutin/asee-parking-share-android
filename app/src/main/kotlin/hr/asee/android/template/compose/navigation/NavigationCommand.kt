package hr.asee.android.template.compose.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {
    val arguments: List<NamedNavArgument>
    val destination: String
}
