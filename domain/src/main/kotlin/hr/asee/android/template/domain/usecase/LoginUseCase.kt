package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.ErrorType

interface LoginUseCase {

    suspend operator fun invoke(request: LoginRequest): EmptyResource

    data class LoginRequest(val email: String, val password: String)

    enum class LoginError : ErrorType {
        MISSING_PASSWORD_ERROR,
        USER_NOT_FOUND_ERROR,
        MISSING_EMAIL_OR_USERNAME_ERROR,
        GENERAL_LOGIN_ERROR,
        STORE_ACCESS_TOKEN_ERROR,
    }
}
