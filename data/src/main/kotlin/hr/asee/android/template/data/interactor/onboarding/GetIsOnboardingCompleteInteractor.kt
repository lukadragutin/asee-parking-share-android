package hr.asee.android.template.data.interactor.onboarding

interface GetIsOnboardingCompleteInteractor {

	suspend operator fun invoke(onboardingKey: String): Boolean
}