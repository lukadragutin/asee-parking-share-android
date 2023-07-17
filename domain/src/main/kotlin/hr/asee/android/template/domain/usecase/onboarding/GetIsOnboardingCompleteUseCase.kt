package hr.asee.android.template.domain.usecase.onboarding

import hr.asee.android.template.domain.model.common.resource.Resource

fun interface GetIsOnboardingCompleteUseCase {

	suspend operator fun invoke(): Resource<Boolean>
}