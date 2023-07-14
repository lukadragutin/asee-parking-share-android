package hr.asee.android.template.domain.usecase.parkingspace

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace

interface GetParkingSpaceForGiverUseCase {

	suspend operator fun invoke(giverId: Int): Resource<ParkingSpace>
}