package hr.asee.android.template.domain.repository

interface OnboardingRepository {

	suspend fun setOnboardingComplete()

	suspend fun getIsOnboardingComplete(): Boolean
}