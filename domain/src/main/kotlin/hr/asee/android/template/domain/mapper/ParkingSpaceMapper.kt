package hr.asee.android.template.domain.mapper

import hr.asee.android.template.data.model.remote.response.ApiParkingSpace
import hr.asee.android.template.domain.model.common.service.ParkingSpace

interface ParkingSpaceMapper {

    fun toParkingSpace(apiParkingSpace: ApiParkingSpace): ParkingSpace
}