package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Seeking
import hr.asee.android.template.domain.repository.AccountRepository
import hr.asee.android.template.domain.usecase.GetSeekingsUseCase
import java.time.LocalDateTime

class GetSeekingsUseCaseImpl(
    private val accountRepository: AccountRepository
): GetSeekingsUseCase {

    override suspend fun invoke(dateStart: LocalDateTime, dateEnd: LocalDateTime): Resource<List<Seeking>> {
        val seekingsList = try {
            accountRepository.getSeekings(dateStart = dateStart, dateEnd = dateEnd)
        } catch (throwable: Throwable) {
            return Resource.Error(GetSeekingsUseCase.GetSeekingsError.GENERAL_GET_SEEKINGS_ERROR, throwable)
        }

        return Resource.Success(seekingsList)
    }
}