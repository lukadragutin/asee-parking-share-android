package hr.asee.android.template.data.resolver.impl

import hr.asee.android.template.data.model.remote.exception.GeneralException
import hr.asee.android.template.data.model.remote.exception.login.MissingEmailOrUsernameException
import hr.asee.android.template.data.model.remote.exception.login.MissingPasswordException
import hr.asee.android.template.data.model.remote.exception.login.UserNotFoundException
import hr.asee.android.template.data.model.remote.response.common.ApiError
import hr.asee.android.template.data.resolver.ReqResServiceErrorResolver
import kotlinx.serialization.json.Json

class ReqResServiceErrorResolverImpl : ReqResServiceErrorResolver {

    companion object {
        private const val MISSING_PASSWORD_ERROR = "Missing password"
        private const val USER_NOT_FOUND_ERROR = "user not found"
        private const val MISSING_EMAIL_OR_USERNAME_ERROR = "Missing email or username"
    }

    override fun toException(jsonError: String): Exception {
        val apiError = try {
            Json.decodeFromString(ApiError.serializer(), jsonError)
        } catch (throwable: Throwable) {
            return GeneralException(throwable)
        }

        return when (apiError.error.lowercase()) {
            MISSING_PASSWORD_ERROR.lowercase() -> MissingPasswordException()
            USER_NOT_FOUND_ERROR.lowercase() -> UserNotFoundException()
            MISSING_EMAIL_OR_USERNAME_ERROR.lowercase() -> MissingEmailOrUsernameException()
            else -> GeneralException()
        }
    }
}
