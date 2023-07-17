package hr.asee.android.template.domain.usecase.onboarding.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.OnboardingRepository
import hr.asee.android.template.domain.usecase.onboarding.GetIsOnboardingCompleteUseCase

class GetIsOnboardingCompleteUseCaseImpl(private val onboardingRepository: OnboardingRepository): GetIsOnboardingCompleteUseCase {

	override suspend fun invoke(): Resource<Boolean> {
		return try {
			Resource.Success(onboardingRepository.getIsOnboardingComplete())
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}