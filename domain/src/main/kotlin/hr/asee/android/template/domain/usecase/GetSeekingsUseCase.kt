package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Seeking
import java.time.LocalDateTime

interface GetSeekingsUseCase {

    suspend operator fun invoke(dateStart: LocalDateTime, dateEnd: LocalDateTime): Resource<List<Seeking>>

    enum class GetSeekingsError : ErrorType {
        GENERAL_GET_SEEKINGS_ERROR
    }
}