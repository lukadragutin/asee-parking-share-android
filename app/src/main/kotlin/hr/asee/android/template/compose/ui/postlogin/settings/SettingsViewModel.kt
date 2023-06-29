package hr.asee.android.template.compose.ui.postlogin.settings

import android.os.Parcel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.state.ToggleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : BaseViewModel() {

    fun onEditProfileButtonClicked() {
        router.navigateToUserManagementScreen()
    }

    fun onBackButtonClicked() {
        router.navigateToHomeScreen()
    }

}