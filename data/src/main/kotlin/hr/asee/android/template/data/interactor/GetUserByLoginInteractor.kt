package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.response.ApiUser

interface GetUserByLoginInteractor {

    suspend operator fun invoke(login: String): ApiUser
}