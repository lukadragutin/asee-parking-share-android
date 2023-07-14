package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.model.remote.response.ApiOffer
import hr.asee.android.template.domain.mapper.OfferMapper
import hr.asee.android.template.domain.mapper.ParkingSpaceMapper
import hr.asee.android.template.domain.model.common.service.Offer
import org.threeten.bp.ZonedDateTime

class OfferMapperImpl(
    private val parkingSpaceMapper: ParkingSpaceMapper
): OfferMapper {

    override fun toOffer(apiOffer: ApiOffer): Offer {
        return Offer(
            id = apiOffer.id,
            dateStart = ZonedDateTime.parse(apiOffer.dateStart).toLocalDateTime(),
            dateEnd = ZonedDateTime.parse(apiOffer.dateEnd).toLocalDateTime(),
            parkingSpace = parkingSpaceMapper.toParkingSpace(apiOffer.parkingSpace)
        )
    }
}