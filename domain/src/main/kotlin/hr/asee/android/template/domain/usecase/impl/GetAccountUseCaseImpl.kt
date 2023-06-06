package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.repository.AccountRepository
import hr.asee.android.template.domain.usecase.GetAccountUseCase

class GetAccountUseCaseImpl(
    private val accountRepository: AccountRepository
): GetAccountUseCase {

    override suspend fun invoke(): Resource<User> {
        val user = try {
            accountRepository.getAccount()
        } catch (throwable: Throwable) {
            return Resource.Error(GetAccountUseCase.GetAccountError.GENERAL_GET_ACCOUNT_ERROR, throwable)
        }

        return Resource.Success(user)
    }
}