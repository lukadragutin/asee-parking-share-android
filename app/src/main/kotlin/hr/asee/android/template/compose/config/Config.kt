package hr.asee.android.template.compose.config

import hr.asee.android.template.compose.R
import hr.asee.android.template.domain.model.navigation.NavigationItem

object Config {
    const val DATA_STORE_PREFERENCES_NAME = "dataStorePreferences"
    const val REQRES_BASE_URL = "http://10.0.2.2:8080/"

    val BOTTOM_NAV_BAR_ITEMS = listOf(
        NavigationItem(
            direction = NavigationItem.NavigationDirection.USERS,
            icon = R.drawable.ic_outline_people_alt_24,
            label = R.string.bottom_nav_bar_users_label,
            contentDescription = R.string.bottom_nav_bar_users_icon_content_description,
        ),
        NavigationItem(
            direction = NavigationItem.NavigationDirection.API_INFO,
            icon = R.drawable.ic_outline_info_24,
            label = R.string.bottom_nav_bar_api_info_label,
            contentDescription = R.string.bottom_nav_bar_api_info_icon_content_description,
        ),
        NavigationItem(
            direction = NavigationItem.NavigationDirection.LOGOUT,
            icon = R.drawable.ic_baseline_logout_24,
            label = R.string.bottom_nav_bar_logout_label,
            contentDescription = R.string.bottom_nav_bar_logout_icon_content_description,
        )
    )
}
