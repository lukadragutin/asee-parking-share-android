package hr.asee.android.template.compose.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.ui.common.base.BaseViewModel
import hr.asee.android.template.compose.ui.main.model.BottomNavBarState
import hr.asee.android.template.domain.model.navigation.NavigationItem
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
) : BaseViewModel() {

}
