package hr.asee.android.template.data.interactor.onboarding.impl

import hr.asee.android.template.data.interactor.onboarding.SetOnboardingCompleteInteractor
import hr.asee.android.template.data.local.storage.ApplicationStorage

class SetOnboardingCompleteInteractorImpl(private val applicationStorage: ApplicationStorage): SetOnboardingCompleteInteractor {

	override suspend fun invoke(onboardingKey: String) {
		applicationStorage.setBoolean(onboardingKey, true)
	}
}