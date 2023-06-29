package hr.asee.android.template.domain.usecase

import hr.asee.android.template.domain.model.common.resource.EmptyResource
import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Reservation

interface PutReservationByIdUseCase {

    suspend operator fun invoke(reservation: Reservation, id: Int): EmptyResource

    enum class PutReservationByIdError: ErrorType {
        GENERAL_PUT_RESERVATION_BY_ID_ERROR
    }
}