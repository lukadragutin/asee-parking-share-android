package hr.asee.android.template.compose.ui.postlogin.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.common.base.BaseViewModel
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