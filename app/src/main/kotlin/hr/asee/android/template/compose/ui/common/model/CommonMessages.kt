package hr.asee.android.template.compose.ui.common.model

import hr.asee.android.template.compose.R

enum class CommonMessages : Message {
    SERVER_UNREACHABLE,
    NETWORK_EXCEPTION,
    UNEXPECTED_ERROR;

    override fun getTitleResource(): Int? {
        return when (this) {
            SERVER_UNREACHABLE -> R.string.common_dialog_message_server_unreachable_title
            NETWORK_EXCEPTION -> R.string.common_dialog_message_network_exception_title
            UNEXPECTED_ERROR -> R.string.common_dialog_message_unexpected_error_title
        }
    }

    override fun getMessageResource(): Int? {
        return when (this) {
            SERVER_UNREACHABLE -> R.string.common_dialog_message_server_unreachable_message
            NETWORK_EXCEPTION -> R.string.common_dialog_message_network_exception_message
            UNEXPECTED_ERROR -> R.string.common_dialog_message_unexpected_error_message
        }
    }
}
