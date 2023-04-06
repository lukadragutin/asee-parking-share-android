package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.data.model.remote.exception.login.MissingEmailOrUsernameException
import hr.asee.android.template.data.model.remote.exception.login.MissingPasswordException
import hr.asee.android.template.data.model.remote.exception.login.UserNotFoundException
import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.usecase.LoginUseCase

class LoginUseCaseImpl(
    private val authenticationRepository: AuthenticationRepository,
) : LoginUseCase {

    override suspend fun invoke(request: LoginUseCase.LoginRequest): EmptyResource {
        val token = try {
            authenticationRepository.login(email = request.email, password = request.password)

        } catch (missingPasswordException: MissingPasswordException) {
            return Resource.Error(LoginUseCase.LoginError.MISSING_PASSWORD_ERROR, missingPasswordException)

        } catch (userNotFoundException: UserNotFoundException) {
            return Resource.Error(LoginUseCase.LoginError.USER_NOT_FOUND_ERROR, userNotFoundException)

        } catch (missingEmailOrUsernameException: MissingEmailOrUsernameException) {
            return Resource.Error(LoginUseCase.LoginError.MISSING_EMAIL_OR_USERNAME_ERROR, missingEmailOrUsernameException)

        } catch (throwable: Throwable) {
            return Resource.Error(LoginUseCase.LoginError.GENERAL_LOGIN_ERROR, throwable)
        }

        try {
            authenticationRepository.storeAccessToken(token)
        } catch (throwable: Throwable) {
            return Resource.Error(LoginUseCase.LoginError.STORE_ACCESS_TOKEN_ERROR, throwable)
        }

        return Resource.Success.empty()
    }
}
