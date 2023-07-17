package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.LogoutInteractor
import hr.asee.android.template.data.local.storage.ApplicationStorage

class LogoutInteractorImpl(private val applicationStorage: ApplicationStorage): LogoutInteractor {

	override suspend fun invoke(accessTokenKey: String) {
		applicationStorage.deleteValue(accessTokenKey)
	}
}