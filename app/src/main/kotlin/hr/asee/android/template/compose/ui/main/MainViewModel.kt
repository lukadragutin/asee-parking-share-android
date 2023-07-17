package hr.asee.android.template.compose.ui.main

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.common.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.main.model.StartDestination
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.usecase.login.IsLoginActiveUseCase
import hr.asee.android.template.domain.usecase.onboarding.GetIsOnboardingCompleteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
	private val getIsOnboardingCompleteUseCase: GetIsOnboardingCompleteUseCase,
	private val isLoginActiveUseCase: IsLoginActiveUseCase
) : BaseViewModel() {

	private val _startingNavigationDestinationState = MutableStateFlow(StartDestination.DEFAULT)
	val startingNavigationDestinationState = _startingNavigationDestinationState.asStateFlow()

	override fun initializeInternal() {
		showLoading()
		runSuspend {
			checkIsOnboardingComplete()
		}
	}

	private suspend fun  checkIsOnboardingComplete() {
		getIsOnboardingCompleteUseCase().onFinished(
			this::getIsOnboardingCompleteSuccess,
			this::getIsOnboardingCompleteError
		)
	}

	private fun getIsOnboardingCompleteSuccess(isOnboardingComplete: Boolean) {
		if(isOnboardingComplete) {
			runSuspend {
				checkIsLoginActive()
			}
		} else {
			_startingNavigationDestinationState.update { StartDestination.ONBOARDING }
			showSuccess()
		}
	}

	private fun getIsOnboardingCompleteError(errorData: ErrorData) {
		showError(CommonMessages.UNEXPECTED_ERROR)
	}

	private suspend fun checkIsLoginActive() {
		isLoginActiveUseCase().onFinished(
			this::isLoginActiveSuccess,
			this::isLoginActiveError
		)
	}

	private fun isLoginActiveSuccess(isLoginActive: Boolean) {
		if(isLoginActive) {
			_startingNavigationDestinationState.update { StartDestination.HOME }
		} else {
			_startingNavigationDestinationState.update { StartDestination.LOGIN }
		}

		showSuccess()
	}

	private fun isLoginActiveError(errorData: ErrorData) {
		showError(CommonMessages.UNEXPECTED_ERROR)
	}
}
