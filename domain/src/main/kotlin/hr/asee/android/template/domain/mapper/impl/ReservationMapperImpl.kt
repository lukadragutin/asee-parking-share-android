package hr.asee.android.template.domain.mapper.impl

import hr.asee.android.template.data.model.remote.response.ApiReservation
import hr.asee.android.template.domain.mapper.ParkingSpaceMapper
import hr.asee.android.template.domain.mapper.ReservationMapper
import hr.asee.android.template.domain.mapper.UserCompactMapper
import hr.asee.android.template.domain.model.common.service.Reservation
import java.time.LocalDateTime

class ReservationMapperImpl(
    private val parkingSpaceMapper: ParkingSpaceMapper,
    private val userCompactMapper: UserCompactMapper
): ReservationMapper {

    override fun toReservation(apiReservation: ApiReservation): Reservation {
        return Reservation(
            id = apiReservation.id,
            dateStart = LocalDateTime.parse(apiReservation.dateStart),
            dateEnd = LocalDateTime.parse(apiReservation.dateEnd),
            cancellationPending = apiReservation.cancellationPending,
            parkingSpace = parkingSpaceMapper.toParkingSpace(apiReservation.parkingSpace),
            seeker = userCompactMapper.toUser(apiReservation.seeker)
        )
    }

    override fun toApiReservation(reservation: Reservation): ApiReservation {
        return ApiReservation(
            id = reservation.id,
            dateStart = reservation.dateStart.toString(),
            dateEnd = reservation.dateEnd.toString(),
            cancellationPending = reservation.cancellationPending,
            parkingSpace = parkingSpaceMapper.toApiParkingSpace(reservation.parkingSpace),
            seeker = userCompactMapper.toApiUserCompact(reservation.seeker)
        )
    }
}