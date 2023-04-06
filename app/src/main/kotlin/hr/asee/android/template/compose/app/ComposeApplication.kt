package hr.asee.android.template.compose.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import hr.asee.android.template.compose.util.log.Logger
import javax.inject.Inject

@HiltAndroidApp
class ComposeApplication : Application() {

    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()

        initializeLogging()
    }

    private fun initializeLogging() {
        logger.init()
    }
}
