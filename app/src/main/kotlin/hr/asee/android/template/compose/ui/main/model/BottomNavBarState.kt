package hr.asee.android.template.compose.ui.main.model

import hr.asee.android.template.domain.model.navigation.NavigationItem

data class BottomNavBarState(
    val isVisible: Boolean = false,
    val items: List<NavigationItem> = emptyList(),
    val onElementClicked: (NavigationItem) -> Unit,
    val selectedItem: NavigationItem? = null,
)
