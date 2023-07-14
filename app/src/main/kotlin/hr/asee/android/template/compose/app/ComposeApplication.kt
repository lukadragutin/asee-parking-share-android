package hr.asee.android.template.compose.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
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
		initializeThreeTenBp()
	}

	private fun initializeLogging() {
		logger.init()
	}

	private fun initializeThreeTenBp() {
		AndroidThreeTen.init(applicationContext)
	}
}
