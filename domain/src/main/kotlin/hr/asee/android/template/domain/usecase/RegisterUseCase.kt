package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.ErrorType
import java.util.*

interface RegisterUseCase {
    suspend operator fun invoke(request: RegisterRequest): EmptyResource

    data class RegisterRequest(val login: String,
                               val email: String,
                               val password: String
                               )

    enum class RegisterError : ErrorType {
        MISSING_PASSWORD_ERROR,
        MISSING_EMAIL_ERROR,
        MISSING_USERNAME_ERROR,
        MISSING_CONFIRM_PASSWORD_ERROR,
    }
}