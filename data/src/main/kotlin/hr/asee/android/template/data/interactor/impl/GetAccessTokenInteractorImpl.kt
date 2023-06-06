package hr.asee.android.template.data.interactor.impl

import hr.asee.android.template.data.interactor.GetAccessTokenInteractor
import hr.asee.android.template.data.local.storage.ApplicationStorage

class GetAccessTokenInteractorImpl(
    private val applicationStorage: ApplicationStorage
): GetAccessTokenInteractor {

    override suspend fun invoke(key: String): String {
        return applicationStorage.getString(key = key)
    }
}