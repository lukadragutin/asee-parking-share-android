package hr.asee.android.template.compose.ui.prelogin.onboarding

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.domain.model.OnboardingItem
import hr.asee.android.template.domain.model.OnboardingType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
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
                    title = "Welcome to ASEE Parking",
                    message = "Reserve your parking spot on our own parking lot\nLorem ipsum ipsum dolor sit",
                    onboardingType = OnboardingType.WELCOME,
                ),
                OnboardingItem(
                    title = "Create your account",
                    message = "Manage your parking space or reserve\n" +
                            " one if available.\n",
                    onboardingType = OnboardingType.CREATE_ACCOUNT,
                )
            )
        }
    }

    @OptIn(ExperimentalPagerApi::class)
    fun onNextClicked(pagerState: PagerState) {
        runBlocking {pagerState.animateScrollToPage(pagerState.currentPage + 1)}
    }

    fun navigateToRegister() {
        router.navigateToRegistrationScreen()
    }

    fun navigateToLogin() {
        router.navigateToLoginScreen()
    }
}
