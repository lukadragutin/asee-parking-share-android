package hr.asee.android.template.compose.delegate

import hr.asee.android.template.compose.delegate.event.NavBarEvent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BottomNavBarDelegate {

    val logoutPressed: StateFlow<Boolean>

    fun getNavBarEvents(): SharedFlow<NavBarEvent>

    fun showBottomNavBar()

    fun hideBottomNavBar()

    fun logout()

    fun loggedOut()
}
