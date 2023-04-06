package hr.asee.android.template.data.interactor

interface StoreAccessTokenInteractor {

    suspend operator fun invoke(key: String, accessToken: String)
}
