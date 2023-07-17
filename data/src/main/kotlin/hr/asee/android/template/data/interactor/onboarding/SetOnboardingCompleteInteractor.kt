package hr.asee.android.template.data.interactor.onboarding

fun interface SetOnboardingCompleteInteractor {

	suspend operator fun invoke(onboardingKey: String)
}