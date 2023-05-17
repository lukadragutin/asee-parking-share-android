package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.data.model.remote.exception.login.MissingEmailOrUsernameException
import hr.asee.android.template.data.model.remote.exception.login.MissingPasswordException
import hr.asee.android.template.data.model.remote.exception.login.UserNotFoundException
import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.usecase.LoginUseCase
import hr.asee.android.template.domain.usecase.RegisterUseCase

class RegisterUseCaseImpl(
    //private val authenticationRepository: AuthenticationRepository,
) : RegisterUseCase {

    override suspend fun invoke(request: RegisterUseCase.RegisterRequest): EmptyResource {
        /*val token = try {
            authenticationRepository.register(name = request.name, email = request.email, password = request.password, confirmpassword = request.confirmpassword)

        } catch (missingPasswordException: MissingPasswordException) {
            return Resource.Error(RegisterUseCase.RegisterError.MISSING_PASSWORD_ERROR, missingPasswordException)

        } catch (userNotFoundException: UserNotFoundException) {
            return Resource.Error(RegisterUseCase.RegisterError.USER_NOT_FOUND_ERROR, userNotFoundException)

        } catch (missingEmailOrUsernameException: MissingEmailOrUsernameException) {
            return Resource.Error(RegisterUseCase.RegisterError.MISSING_EMAIL_OR_USERNAME_ERROR, missingEmailOrUsernameException)

        }

        try {
            authenticationRepository.storeAccessToken(token)
        } catch (throwable: Throwable) {
            return Resource.Error(RegisterUseCase.RegisterError.STORE_ACCESS_TOKEN_ERROR, throwable)
        }
*/
        return Resource.Success.empty()
    }
}