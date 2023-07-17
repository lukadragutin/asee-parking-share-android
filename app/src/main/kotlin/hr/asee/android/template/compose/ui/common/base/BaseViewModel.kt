package hr.asee.android.template.compose.ui.common.base

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.navigation.Router
import hr.asee.android.template.compose.ui.common.model.Message
import hr.asee.android.template.compose.ui.common.model.state.UiState
import hr.asee.android.template.compose.ui.main.model.BottomNavBarState
import hr.asee.android.template.compose.util.log.Logger
import hr.asee.android.template.domain.model.navigation.NavigationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var router: Router

    @Inject
    protected lateinit var logger: Logger

    protected val _bottomNavBarState = MutableStateFlow(BottomNavBarState(items = Config.BOTTOM_NAV_BAR_ITEMS, onElementClicked = this::onNavElementClicked))
    val bottomNavBarState: StateFlow<BottomNavBarState> = _bottomNavBarState

    private fun onNavElementClicked(item: NavigationItem) {
        router.navigateToDirection(item.direction)

        if (item.direction != NavigationItem.NavigationDirection.LOGOUT) {
            _bottomNavBarState.update { it.copy(selectedItem = item) }
        }
    }

    private var initializeCalled = false

    fun showBottomNavBar() {
        _bottomNavBarState.update { it.copy(isVisible = true) }
    }

    fun hideBottomNavBar() {
        _bottomNavBarState.update { it.copy(isVisible = false) }
    }

    protected val _uiState = MutableStateFlow<UiState>(UiState.None)
    val uiState: StateFlow<UiState> = _uiState

    protected fun runSuspend(job: suspend () -> Unit) {
        viewModelScope.launch { job() }
    }

    protected fun showMessage(message: Message) {
        _uiState.update { UiState.Info(message = message) }
    }

    protected fun showLoading() {
        _uiState.update { UiState.Loading }
    }

    protected fun showError(message: Message? = null) {
        _uiState.update { UiState.Error(message = message) }
    }

    protected fun showSuccess() {
        _uiState.update { UiState.Success }
    }

    open fun onMessageDismissed() {
        _uiState.update { UiState.None }
    }

    fun goBack() {
        router.navigateBack()
    }

    // https://developer.android.google.cn/topic/architecture/ui-layer/state-production#initializing-state-production
    @MainThread
    fun initialize() {
        if(initializeCalled) return
        initializeCalled = true

        initializeInternal()
    }

    protected open fun initializeInternal() {
        // NO_OP
    }
}