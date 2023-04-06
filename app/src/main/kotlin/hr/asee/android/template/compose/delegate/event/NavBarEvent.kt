package hr.asee.android.template.compose.delegate.event

sealed class NavBarEvent {
    object ShowNavBar : NavBarEvent()
    object HideNavBar : NavBarEvent()
}
