package hr.asee.android.template.compose.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.main.model.BottomNavBarState
import hr.asee.android.template.domain.model.navigation.NavigationItem
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllBottomNavItemsUseCase: GetAllBottomNavItemsUseCase,
) : BaseViewModel() {

    private val _bottomNavBarState = MutableStateFlow(BottomNavBarState(items = Config.BOTTOM_NAV_BAR_ITEMS, onElementClicked = this::onNavElementClicked))
    val bottomNavBarState: StateFlow<BottomNavBarState> = _bottomNavBarState

    init {
        runSuspend { getAllBottomNavItemsUseCase().onSuccess { items -> _bottomNavBarState.update { it.copy(items = items, selectedItem = it.items.firstOrNull()) } } }
    }

    private fun onNavElementClicked(item: NavigationItem) {
        router.navigateToDirection(item.direction)

        if (item.direction != NavigationItem.NavigationDirection.LOGOUT) {
            _bottomNavBarState.update { it.copy(selectedItem = item) }
        }
    }

    fun showBottomNavBar() {
        _bottomNavBarState.update { it.copy(isVisible = true) }
    }

    fun hideBottomNavBar() {
        _bottomNavBarState.update { it.copy(isVisible = false) }
    }
}
