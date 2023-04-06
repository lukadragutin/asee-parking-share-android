package hr.asee.android.template.compose.ui.common.model

interface Message {
    fun getTitleResource(): Int?
    fun getMessageResource(): Int?
    fun getMessageArguments(): List<String>? = null
}
