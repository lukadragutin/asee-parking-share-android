package hr.asee.android.template.data.interactor.onboarding.impl

import hr.asee.android.template.data.interactor.onboarding.GetIsOnboardingCompleteInteractor
import hr.asee.android.template.data.local.storage.ApplicationStorage

class GetIsOnboardingCompleteInteractorImpl(private val applicationStorage: ApplicationStorage): GetIsOnboardingCompleteInteractor {

	override suspend fun invoke(onboardingKey: String): Boolean {
		return applicationStorage.getBoolean(onboardingKey)
	}
}