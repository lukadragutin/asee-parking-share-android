package hr.asee.android.template.domain.usecase.onboarding

import hr.asee.android.template.domain.model.common.resource.EmptyResource

fun interface SetOnboardingCompleteUseCase {

	suspend operator fun invoke(): EmptyResource
}