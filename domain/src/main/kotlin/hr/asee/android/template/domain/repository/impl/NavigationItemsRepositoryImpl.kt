package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.domain.model.navigation.NavigationItem
import hr.asee.android.template.domain.repository.NavigationItemsRepository

class NavigationItemsRepositoryImpl(
    private val menuItems: List<NavigationItem>
) : NavigationItemsRepository {

    override suspend fun getAllMenuItems(): List<NavigationItem> {
        return menuItems
    }
}
