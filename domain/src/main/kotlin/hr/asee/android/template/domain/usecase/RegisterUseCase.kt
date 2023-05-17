package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.ErrorType

interface RegisterUseCase {
    suspend operator fun invoke(request: RegisterRequest): EmptyResource

    data class RegisterRequest(val name: String, val email: String, val password: String, val confirmpassword: String)

    enum class RegisterError : ErrorType {
        MISSING_PASSWORD_ERROR,
        USER_NOT_FOUND_ERROR,
        MISSING_EMAIL_OR_USERNAME_ERROR,
        STORE_ACCESS_TOKEN_ERROR,
    }
}