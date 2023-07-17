package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.onboarding.GetIsOnboardingCompleteInteractor
import hr.asee.android.template.data.interactor.onboarding.SetOnboardingCompleteInteractor
import hr.asee.android.template.domain.repository.OnboardingRepository

class OnboardingRepositoryImpl(
	private val setOnboardingCompleteInteractor: SetOnboardingCompleteInteractor,
	private val getIsOnboardingCompleteInteractor: GetIsOnboardingCompleteInteractor
) : OnboardingRepository {

	companion object {
		private const val ONBOARDING_STATUS_KEY = "is_onboarding_complete_key"
	}

	override suspend fun setOnboardingComplete() {
		setOnboardingCompleteInteractor(ONBOARDING_STATUS_KEY)
	}

	override suspend fun getIsOnboardingComplete(): Boolean {
		return getIsOnboardingCompleteInteractor(ONBOARDING_STATUS_KEY)
	}
}