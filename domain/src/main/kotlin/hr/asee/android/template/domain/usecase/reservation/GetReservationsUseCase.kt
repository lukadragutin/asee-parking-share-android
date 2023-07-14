package hr.asee.android.template.domain.usecase.reservation

import hr.asee.android.template.domain.model.common.resource.ErrorType
import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Reservation

interface GetReservationsUseCase {

    suspend operator fun invoke(): Resource<List<Reservation>>

    enum class GetReservationsError: ErrorType {
        GENERAL_GET_RESERVATIONS_ERROR
    }
}