package hr.asee.android.template.compose.delegate.impl

import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.delegate.event.NavBarEvent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class BottomNavBarDelegateImpl : BottomNavBarDelegate {

    private var _navBarEvents = MutableSharedFlow<NavBarEvent>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getNavBarEvents(): SharedFlow<NavBarEvent> {
        return _navBarEvents
    }

    override fun showBottomNavBar() {
        _navBarEvents.tryEmit(NavBarEvent.ShowNavBar)
    }

    override fun hideBottomNavBar() {
        _navBarEvents.tryEmit(NavBarEvent.HideNavBar)
    }
}
