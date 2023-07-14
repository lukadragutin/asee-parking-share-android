package hr.asee.android.template.domain.usecase.seeking

import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Seeking
import org.threeten.bp.LocalDateTime

interface GetSeekingsUseCase {

    suspend operator fun invoke(dateStart: LocalDateTime? = null, dateEnd: LocalDateTime? = null): Resource<List<Seeking>>

    enum class GetSeekingsError : ErrorType {
        GENERAL_GET_SEEKINGS_ERROR
    }
}