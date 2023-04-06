package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.StoreAccessTokenInteractor
import hr.asee.android.template.data.local.storage.ApplicationStorage

class StoreAccessTokenInteractorImpl(
    private val applicationStorage: ApplicationStorage,
) : StoreAccessTokenInteractor {

    override suspend fun invoke(key: String, accessToken: String) {
        applicationStorage.saveString(key = key, value = accessToken)
    }
}
