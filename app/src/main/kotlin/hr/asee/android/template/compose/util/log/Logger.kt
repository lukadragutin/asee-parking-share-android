package hr.asee.android.template.compose.util.log

interface Logger {

    fun init()

    fun e(throwable: Throwable)
}
