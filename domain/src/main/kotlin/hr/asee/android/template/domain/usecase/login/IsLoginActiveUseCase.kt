package hr.asee.android.template.domain.usecase.login

import hr.asee.android.template.domain.model.common.resource.Resource

fun interface IsLoginActiveUseCase {

	suspend operator fun invoke(): Resource<Boolean>
}