package hr.asee.android.template.data.interactor

interface GetAccessTokenInteractor {

    suspend operator fun invoke(key: String): String
}