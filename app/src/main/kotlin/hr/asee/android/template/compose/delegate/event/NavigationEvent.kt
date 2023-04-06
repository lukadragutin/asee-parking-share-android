package hr.asee.android.template.compose.delegate.event

import androidx.navigation.NavOptionsBuilder
import hr.asee.android.template.compose.navigation.NavigationCommand

sealed class NavigationEvent {
    object None : NavigationEvent()
    object NavigateBack : NavigationEvent()

    class Directions(
        val navigationCommand: NavigationCommand,
        val navigationBuilder: NavOptionsBuilder.() -> Unit
    ) : NavigationEvent()
}
