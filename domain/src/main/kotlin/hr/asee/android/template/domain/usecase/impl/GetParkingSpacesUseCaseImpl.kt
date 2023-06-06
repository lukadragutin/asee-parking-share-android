package hr.asee.android.template.domain.usecase.impl

import hr.asee.android.template.domain.model.common.resource.Resource
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.repository.AccountRepository
import hr.asee.android.template.domain.usecase.GetParkingSpacesUseCase

class GetParkingSpacesUseCaseImpl(
    private val accountRepository: AccountRepository
): GetParkingSpacesUseCase {

    override suspend fun invoke(): Resource<List<ParkingSpace>> {
        val parkingSpacesList = try {
            accountRepository.getParkingSpaces()
        } catch (throwable: Throwable) {
            return Resource.Error(GetParkingSpacesUseCase.GetParkingSpacesError.GENERAL_GET_PARKING_SPACES_ERROR, throwable)
        }

        return Resource.Success(parkingSpacesList)
    }
}