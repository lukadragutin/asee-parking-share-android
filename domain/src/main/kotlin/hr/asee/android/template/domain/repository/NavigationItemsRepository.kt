package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.navigation.NavigationItem

interface NavigationItemsRepository {

    suspend fun getAllMenuItems(): List<NavigationItem>
}
