package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource

interface GetAccountUseCase {

    suspend operator fun invoke(): Resource<User>

    enum class GetAccountError :  ErrorType {
        GENERAL_GET_ACCOUNT_ERROR
    }
}