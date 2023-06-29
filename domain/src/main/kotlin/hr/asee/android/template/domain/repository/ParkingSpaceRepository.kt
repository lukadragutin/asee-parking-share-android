package hr.asee.android.template.domain.repository

import hr.asee.android.template.domain.model.common.service.ParkingSpace

interface ParkingSpaceRepository {

    suspend fun getParkingSpaces(): List<ParkingSpace>

    suspend fun getParkingSpaceById(id: Int): ParkingSpace

    suspend fun addParkingSpace(parkingSpace: ParkingSpace): ParkingSpace

    suspend fun changeParkingLocation(id: Int, newParkingSpace: ParkingSpace): ParkingSpace
}