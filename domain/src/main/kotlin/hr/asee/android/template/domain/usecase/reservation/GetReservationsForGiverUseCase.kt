package hr.asee.android.template.domain.usecase.reservation

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Reservation

interface GetReservationsForGiverUseCase {

	suspend operator fun invoke(giverId: Int): Resource<List<Reservation>>
}