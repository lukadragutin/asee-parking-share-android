package hr.asee.android.template.domain.usecase.reservation

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.Reservation

interface GetReservationsForSeekerUseCase {

	suspend operator fun invoke(seekerId: Int): Resource<List<Reservation>>
}