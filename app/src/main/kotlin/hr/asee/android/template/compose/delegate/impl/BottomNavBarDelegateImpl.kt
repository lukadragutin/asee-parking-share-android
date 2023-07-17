package hr.asee.android.template.compose.delegate.impl

import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.delegate.event.NavBarEvent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BottomNavBarDelegateImpl : BottomNavBarDelegate {

    private var _navBarEvents = MutableSharedFlow<NavBarEvent>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val _logoutPressed = MutableStateFlow(false)
    override val logoutPressed = _logoutPressed.asStateFlow()

    override fun getNavBarEvents(): SharedFlow<NavBarEvent> {
        return _navBarEvents
    }

    override fun showBottomNavBar() {
        _navBarEvents.tryEmit(NavBarEvent.ShowNavBar)
    }

    override fun hideBottomNavBar() {
        _navBarEvents.tryEmit(NavBarEvent.HideNavBar)
    }

    override fun logout() {
        _logoutPressed.update { true }
    }

    override fun loggedOut() {
        _logoutPressed.update { false }
    }
}
