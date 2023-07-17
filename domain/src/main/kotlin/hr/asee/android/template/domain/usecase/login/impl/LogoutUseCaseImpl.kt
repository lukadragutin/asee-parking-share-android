package hr.asee.android.template.domain.usecase.login.impl

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.usecase.login.LogoutUseCase

class LogoutUseCaseImpl(private val authenticationRepository: AuthenticationRepository): LogoutUseCase {

	override suspend fun invoke(): EmptyResource {
		return try {
			Resource.Success(authenticationRepository.logout())
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}