package hr.asee.android.template.domain.mapper

import hr.asee.android.template.data.model.remote.response.ApiReservation
import hr.asee.android.template.domain.model.common.service.Reservation

interface ReservationMapper {

    fun toReservation(apiReservation: ApiReservation): Reservation

    fun toApiReservation(reservation: Reservation): ApiReservation
}