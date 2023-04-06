package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.navigation.NavigationItem
import hr.asee.android.template.domain.repository.NavigationItemsRepository
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllBottomNavItemsUseCaseImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val navigationItemsRepository: NavigationItemsRepository,
) : GetAllBottomNavItemsUseCase {

    override suspend fun invoke(): Resource<List<NavigationItem>> {
        return withContext(dispatcher) {
            getAllBottomNavItems()
        }
    }

    private suspend fun getAllBottomNavItems(): Resource<List<NavigationItem>> {
        return try {
            Resource.Success(navigationItemsRepository.getAllMenuItems())
        } catch (throwable: Throwable) {
            Resource.Error(throwable = throwable)
        }
    }
}
