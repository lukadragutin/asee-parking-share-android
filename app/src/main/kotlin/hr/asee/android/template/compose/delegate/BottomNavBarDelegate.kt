package hr.asee.android.template.compose.delegate

import hr.asee.android.template.compose.delegate.event.NavBarEvent
import kotlinx.coroutines.flow.SharedFlow

interface BottomNavBarDelegate {

    fun getNavBarEvents(): SharedFlow<NavBarEvent>

    fun showBottomNavBar()

    fun hideBottomNavBar()
}
