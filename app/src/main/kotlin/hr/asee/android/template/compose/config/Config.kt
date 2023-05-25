package hr.asee.android.template.compose.config

import hr.asee.android.template.compose.R
import hr.asee.android.template.domain.model.navigation.NavigationItem

object Config {
    const val DATA_STORE_PREFERENCES_NAME = "dataStorePreferences"
    const val REQRES_BASE_URL = "https://localhost:8080"

    val BOTTOM_NAV_BAR_ITEMS = listOf(
        NavigationItem(
            direction = NavigationItem.NavigationDirection.HOME,
            icon = R.mipmap.car_sharing,
            label = R.string.bottom_nav_bar_home_label,
            contentDescription = R.string.bottom_nav_bar_home_icon_content_description
        ),
        NavigationItem(
            direction = NavigationItem.NavigationDirection.PARKING_MANAGER,
            icon = R.mipmap.parking,
            label = R.string.bottom_nav_bar_parking_manager_label,
            contentDescription = R.string.bottom_nav_bar_parking_manager_icon_content_description
        )
    )
}
