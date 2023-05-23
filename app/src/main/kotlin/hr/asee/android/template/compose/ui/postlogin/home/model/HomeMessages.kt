package hr.asee.android.template.compose.ui.postlogin.home.model

import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.model.Message

enum class HomeMessages : Message {
    DATES_NOT_SELECTED_ERROR,
    INVALID_DATES_ERROR;

    override fun getTitleResource(): Int? {
        return when (this) {
            DATES_NOT_SELECTED_ERROR -> R.string.home_screen_dates_not_selected_error_title
            INVALID_DATES_ERROR -> R.string.home_screen_invalid_dates_error_title
        }
    }

    override fun getMessageResource(): Int? {
        return when (this) {
            DATES_NOT_SELECTED_ERROR -> R.string.home_screen_dates_not_selected_error_message
            INVALID_DATES_ERROR -> R.string.home_screen_invalid_dates_error_message
        }
    }
}