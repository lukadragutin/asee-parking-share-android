package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.model.remote.response.ApiOffer
import hr.asee.android.template.domain.mapper.OfferMapper
import hr.asee.android.template.domain.mapper.ParkingSpaceMapper
import hr.asee.android.template.domain.model.common.service.Offer
import java.time.LocalDateTime

class OfferMapperImpl(
    private val parkingSpaceMapper: ParkingSpaceMapper
): OfferMapper {

    override fun toOffer(apiOffer: ApiOffer): Offer {
        return Offer(
            id = apiOffer.id,
            dateStart = LocalDateTime.parse(apiOffer.dateStart),
            dateEnd = LocalDateTime.parse(apiOffer.dateEnd),
            parkingSpace = parkingSpaceMapper.toParkingSpace(apiOffer.parkingSpace)
        )
    }
}