package hr.asee.android.template.domain.usecase.onboarding.impl

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.OnboardingRepository
import hr.asee.android.template.domain.usecase.onboarding.SetOnboardingCompleteUseCase

class SetOnboardingCompleteUseCaseImpl(private val onboardingRepository: OnboardingRepository): SetOnboardingCompleteUseCase {

	override suspend fun invoke(): EmptyResource {
		return try {
			onboardingRepository.setOnboardingComplete()
			Resource.Success(Unit)
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}