package hr.asee.android.template.domain.usecase.login

import hr.asee.android.template.domain.model.common.resource.EmptyResource

fun interface LogoutUseCase {

	suspend operator fun invoke(): EmptyResource
}