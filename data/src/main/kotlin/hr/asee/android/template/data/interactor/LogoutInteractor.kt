package hr.asee.android.template.data.interactor

interface LogoutInteractor {

	suspend operator fun invoke(accessTokenKey: String)
}