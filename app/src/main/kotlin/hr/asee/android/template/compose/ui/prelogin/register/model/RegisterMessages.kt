package hr.asee.android.template.compose.ui.prelogin.register.model

import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.model.Message

enum class RegisterMessages : Message {
    MISSING_PASSWORD_ERROR,
    //STORE_ACCESS_TOKEN_ERROR,
    //USER_NOT_FOUND_ERROR,
    MISSING_EMAIL_ERROR,
    MISSING_USERNAME_ERROR,
    MISSING_CONFIRM_PASSWORD_ERROR;


    override fun getTitleResource(): Int? {
        return when (this) {
            MISSING_PASSWORD_ERROR -> R.string.register_screen_missing_password_error_title
            MISSING_EMAIL_ERROR -> R.string.register_screen_missing_email_error_title
            MISSING_USERNAME_ERROR -> R.string.register_screen_missing_username_error_title
            MISSING_CONFIRM_PASSWORD_ERROR -> R.string.register_screen_missing_confirm_password_error_title
            //STORE_ACCESS_TOKEN_ERROR -> R.string.login_screen_store_access_token_error_title
            //USER_NOT_FOUND_ERROR -> R.string.login_screen_user_not_found_error_title
            //MISSING_EMAIL_OR_USERNAME_ERROR -> R.string.login_screen_missing_email_or_username_error_title
        }
    }

    override fun getMessageResource(): Int? {
        return when (this) {
            MISSING_PASSWORD_ERROR -> R.string.register_screen_missing_password_error_message
            MISSING_EMAIL_ERROR -> R.string.register_screen_missing_email_error_message
            MISSING_USERNAME_ERROR -> R.string.register_screen_missing_username_error_message
            MISSING_CONFIRM_PASSWORD_ERROR -> R.string.register_screen_missing_confirm_password_error_message
            //STORE_ACCESS_TOKEN_ERROR -> R.string.login_screen_store_access_token_error_message
            //USER_NOT_FOUND_ERROR -> R.string.login_screen_user_not_found_error_message
            //MISSING_EMAIL_OR_USERNAME_ERROR -> R.string.login_screen_missing_email_or_username_error_message
        }
    }
}