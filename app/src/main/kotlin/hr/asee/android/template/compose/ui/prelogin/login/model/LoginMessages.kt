package hr.asee.android.template.compose.ui.prelogin.login.model

import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.model.Message

enum class LoginMessages : Message {
    EMAIL_INFO,
    MISSING_PASSWORD_ERROR,
    STORE_ACCESS_TOKEN_ERROR,
    USER_NOT_FOUND_ERROR,
    MISSING_EMAIL_OR_USERNAME_ERROR;

    override fun getTitleResource(): Int? {
        return when (this) {
            EMAIL_INFO -> R.string.login_screen_email_info_title
            MISSING_PASSWORD_ERROR -> R.string.login_screen_missing_password_error_title
            STORE_ACCESS_TOKEN_ERROR -> R.string.login_screen_store_access_token_error_title
            USER_NOT_FOUND_ERROR -> R.string.login_screen_user_not_found_error_title
            MISSING_EMAIL_OR_USERNAME_ERROR -> R.string.login_screen_missing_email_or_username_error_title
        }
    }

    override fun getMessageResource(): Int? {
        return when (this) {
            EMAIL_INFO -> R.string.login_screen_email_info_message
            MISSING_PASSWORD_ERROR -> R.string.login_screen_missing_password_error_message
            STORE_ACCESS_TOKEN_ERROR -> R.string.login_screen_store_access_token_error_message
            USER_NOT_FOUND_ERROR -> R.string.login_screen_user_not_found_error_message
            MISSING_EMAIL_OR_USERNAME_ERROR -> R.string.login_screen_missing_email_or_username_error_message
        }
    }
}
