package hr.asee.android.template.domain.usecase.login.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.usecase.login.IsLoginActiveUseCase

class IsLoginActiveUseCaseImpl(private val authenticationRepository: AuthenticationRepository) : IsLoginActiveUseCase {

	override suspend fun invoke(): Resource<Boolean> {
		return try {
			Resource.Success(authenticationRepository.isAccessTokenValid())
		} catch (throwable: Throwable) {
			Resource.Error(throwable = throwable)
		}
	}
}