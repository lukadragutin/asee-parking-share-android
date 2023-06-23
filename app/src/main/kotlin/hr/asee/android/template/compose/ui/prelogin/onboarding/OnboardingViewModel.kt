package hr.asee.android.template.compose.ui.prelogin.onboarding

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.common.base.BaseViewModel
import hr.asee.android.template.domain.model.OnboardingItem
import hr.asee.android.template.domain.model.OnboardingType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(

) : BaseViewModel() {

    private val _onboardingItems = MutableStateFlow(listOf<OnboardingItem>())
    val onboardingItems: StateFlow<List<OnboardingItem>> = _onboardingItems

    init {
        _onboardingItems.update {
            listOf(
                OnboardingItem(
                    title = "Welcome",
                    message = "Welcome to Jetpack Compose coding template app. Swipe right for more info.",
                    onboardingType = OnboardingType.APPLICATION,
                ),
                OnboardingItem(
                    title = "Jetpack Compose + MVVM",
                    message = "This application demonstrates usage of Jetpack Compose framework by using slightly modified Android-recommended architecture found on: https://developer.android.com/topic/architecture.",
                    onboardingType = OnboardingType.JETPACK_COMPOSE,
                )
            )
        }
    }

    fun navigateToLogin() {
        router.navigateToLoginScreen()
    }

    fun navigateToRegister() {
        router.navigateToRegisterScreen()
    }
}
