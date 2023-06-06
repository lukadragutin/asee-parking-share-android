package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.model.remote.response.ApiParkingSpace
import hr.asee.android.template.domain.mapper.ParkingSpaceMapper
import hr.asee.android.template.domain.mapper.UserCompactMapper
import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.service.ParkingSpace

class ParkingSpaceMapperImpl(
    private val userCompactMapper: UserCompactMapper
): ParkingSpaceMapper {

    override fun toParkingSpace(apiParkingSpace: ApiParkingSpace): ParkingSpace {
        return ParkingSpace(
            id = apiParkingSpace.id,
            location = apiParkingSpace.location,
            owner = userCompactMapper.toUser(apiParkingSpace.owner) as Giver
        )
    }
}