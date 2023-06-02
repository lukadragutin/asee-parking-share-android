package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.data.model.remote.exception.register.EmailNotFoundException
import hr.asee.android.template.data.model.remote.exception.register.MissingConfirmPasswordException
import hr.asee.android.template.data.model.remote.exception.register.MissingPasswordException
import hr.asee.android.template.data.model.remote.exception.register.MissingUsernameException
import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.AuthenticationRepository
import hr.asee.android.template.domain.usecase.LoginUseCase
import hr.asee.android.template.domain.usecase.RegisterUseCase
import org.threeten.bp.LocalDateTime

class RegisterUseCaseImpl(
    private val authenticationRepository: AuthenticationRepository,
) : RegisterUseCase {

    override suspend fun invoke(request: RegisterUseCase.RegisterRequest): EmptyResource {
        try {
            authenticationRepository.register(  id = 0,
                                                login = request.login,
                                                firstName = "abba",
                                                lastName = "scbiai",
                                                email = request.email,
                                                password = request.password,
                                                role = "giver",
                                                activated = true,
                                                langKey = "string",
                                                createdBy = "string",
                                                createdDate = LocalDateTime.now().toString() + "Z",
                                                lastModifiedBy = "string",
                                                lastModifiedDate = LocalDateTime.now().toString() + "Z",
                                                authorities = arrayOf()
                                                )

        } catch (missingPasswordException: MissingPasswordException) {
            return Resource.Error(RegisterUseCase.RegisterError.MISSING_PASSWORD_ERROR, missingPasswordException)

        } catch (emailNotFoundException: EmailNotFoundException) {
            return Resource.Error(RegisterUseCase.RegisterError.MISSING_EMAIL_ERROR, emailNotFoundException)

        } catch (missingUsernameException: MissingUsernameException) {
            return Resource.Error(RegisterUseCase.RegisterError.MISSING_USERNAME_ERROR, missingUsernameException)

        } catch (missingConfirmPasswordException: MissingConfirmPasswordException) {
            return Resource.Error(
                RegisterUseCase.RegisterError.MISSING_CONFIRM_PASSWORD_ERROR,
                missingConfirmPasswordException
            )
        }

        return Resource.Success.empty()
    }
}