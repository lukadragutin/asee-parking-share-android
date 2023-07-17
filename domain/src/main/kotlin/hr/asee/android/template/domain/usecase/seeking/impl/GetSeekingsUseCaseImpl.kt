package hr.asee.android.template.domain.usecase.seeking.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Seeking
import hr.asee.android.template.domain.repository.SeekingRepository
import hr.asee.android.template.domain.usecase.seeking.GetSeekingsUseCase
import org.threeten.bp.LocalDateTime

class GetSeekingsUseCaseImpl(
    private val seekingRepository: SeekingRepository
): GetSeekingsUseCase {

    override suspend fun invoke(dateStart: LocalDateTime?, dateEnd: LocalDateTime?): Resource<List<Seeking>> {
        val seekingsList = try {
            seekingRepository.getSeekings(dateStart = dateStart, dateEnd = dateEnd)
        } catch (throwable: Throwable) {
            return Resource.Error(GetSeekingsUseCase.GetSeekingsError.GENERAL_GET_SEEKINGS_ERROR, throwable)
        }

        return Resource.Success(seekingsList)
    }
}