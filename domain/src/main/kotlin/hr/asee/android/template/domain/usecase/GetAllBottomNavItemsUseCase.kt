package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.navigation.NavigationItem

interface GetAllBottomNavItemsUseCase {

    suspend operator fun invoke(): Resource<List<NavigationItem>>
}
