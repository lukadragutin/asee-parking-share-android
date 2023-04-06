package hr.asee.android.template.compose.delegate.impl

import androidx.navigation.NavOptionsBuilder
import hr.asee.android.template.compose.delegate.NavigationDelegate
import hr.asee.android.template.compose.delegate.event.NavigationEvent
import hr.asee.android.template.compose.navigation.NavigationCommand
import kotlinx.coroutines.channels.BufferOverflow.DROP_OLDEST
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class NavigationDelegateImpl : NavigationDelegate {

    private var _navigationEvents = MutableSharedFlow<NavigationEvent>(replay = 1, onBufferOverflow = DROP_OLDEST)

    override fun getNavigationEvents(): SharedFlow<NavigationEvent> {
        return _navigationEvents
    }

    override fun navigate(specialNavigationAction: NavigationDelegate.SpecialNavigationAction) {
        when (specialNavigationAction) {
            NavigationDelegate.SpecialNavigationAction.GO_BACK -> navigateBack()
        }
    }

    private fun navigateBack() {
        _navigationEvents.tryEmit(NavigationEvent.NavigateBack)
    }

    override fun navigate(
        navigationCommand: NavigationCommand,
        builder: NavOptionsBuilder.() -> Unit
    ) {
        _navigationEvents.tryEmit(NavigationEvent.Directions(navigationCommand = navigationCommand, navigationBuilder = builder))
    }
}
