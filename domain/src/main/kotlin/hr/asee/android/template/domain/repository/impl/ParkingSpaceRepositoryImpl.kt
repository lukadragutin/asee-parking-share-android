package hr.asee.android.template.domain.repository.impl

import hr.asee.android.template.data.interactor.parkingspace.AddParkingSpaceInteractor
import hr.asee.android.template.data.interactor.parkingspace.ChangeParkingLocationInteractor
import hr.asee.android.template.data.interactor.parkingspace.GetParkingSpaceByIdInteractor
import hr.asee.android.template.data.interactor.parkingspace.GetParkingSpaceForGiverInteractor
import hr.asee.android.template.data.interactor.parkingspace.GetParkingSpacesInteractor
import hr.asee.android.template.domain.mapper.ParkingSpaceMapper
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.repository.ParkingSpaceRepository

class ParkingSpaceRepositoryImpl(
	private val getParkingSpacesInteractor: GetParkingSpacesInteractor,
	private val getParkingSpaceByIdInteractor: GetParkingSpaceByIdInteractor,
	private val getParkingSpaceForGiverInteractor: GetParkingSpaceForGiverInteractor,
	private val addParkingSpaceInteractor: AddParkingSpaceInteractor,
	private val changeParkingLocationInteractor: ChangeParkingLocationInteractor,
	private val parkingSpaceMapper: ParkingSpaceMapper
) : ParkingSpaceRepository {

    override suspend fun getParkingSpaces(): List<ParkingSpace> {
        val parkingSpacesList: MutableList<ParkingSpace> = mutableListOf()

        getParkingSpacesInteractor().forEach {
            parkingSpacesList.add(parkingSpaceMapper.toParkingSpace(it))
        }

        return parkingSpacesList
    }

    override suspend fun getParkingSpaceById(id: Int): ParkingSpace {
        return parkingSpaceMapper.toParkingSpace(getParkingSpaceByIdInteractor(id = id))
    }

	override suspend fun getParkingSpaceByGiver(giverId: Int): ParkingSpace {
		return parkingSpaceMapper.toParkingSpace(getParkingSpaceForGiverInteractor(giverId = giverId))
	}

	override suspend fun addParkingSpace(parkingSpace: ParkingSpace): ParkingSpace {
        return parkingSpaceMapper.toParkingSpace(addParkingSpaceInteractor(parkingSpaceMapper.toApiParkingSpace(parkingSpace)))
    }

    override suspend fun changeParkingLocation(id: Int, newParkingSpace: ParkingSpace): ParkingSpace {
        return parkingSpaceMapper.toParkingSpace(changeParkingLocationInteractor(id, parkingSpaceMapper.toApiParkingSpace(newParkingSpace)))
    }
}