package hr.asee.android.template.domain.model.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NavigationItem(
    val direction: NavigationDirection,
    @DrawableRes val icon: Int,
    @StringRes val label: Int? = null,
    @StringRes val contentDescription: Int? = null,
) {
    enum class NavigationDirection {
        USERS,
        API_INFO,
        LOGOUT,
        HOME,
        PARKING_MANAGER
    }
}
