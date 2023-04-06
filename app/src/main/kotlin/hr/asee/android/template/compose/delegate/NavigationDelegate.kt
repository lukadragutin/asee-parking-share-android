package hr.asee.android.template.compose.delegate

import androidx.navigation.NavOptionsBuilder
import hr.asee.android.template.compose.delegate.event.NavigationEvent
import hr.asee.android.template.compose.navigation.NavigationCommand
import kotlinx.coroutines.flow.SharedFlow

interface NavigationDelegate {
    fun getNavigationEvents(): SharedFlow<NavigationEvent>

    fun navigate(specialNavigationAction: SpecialNavigationAction)

    fun navigate(navigationCommand: NavigationCommand, builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true })

    enum class SpecialNavigationAction {
        GO_BACK,
    }
}
