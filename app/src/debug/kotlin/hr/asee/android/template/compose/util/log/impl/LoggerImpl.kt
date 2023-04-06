package hr.asee.android.template.compose.util.log.impl

import hr.asee.android.template.compose.util.log.Logger
import timber.log.Timber

class LoggerImpl : Logger {

    override fun init() {
        Timber.plant(Timber.DebugTree())
    }

    override fun e(throwable: Throwable) {
        Timber.e(throwable)
    }
}
