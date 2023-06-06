package hr.asee.android.template.data.interactor

import hr.asee.android.template.data.model.remote.response.ApiUser

interface GetAccountInteractor {

    suspend operator fun invoke(): ApiUser
}