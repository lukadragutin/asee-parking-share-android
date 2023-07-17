package hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.card.GiverReservationCard
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.card.SeekerReservationCard
import hr.asee.android.template.domain.model.common.Seeker
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.service.Reservation


@Composable
fun ReservationList(
    reservationList: Set<Reservation>,
    filterState: DatePickerState,
    onCancelReservationClicked: () -> Unit,
    onForwardClickedReservationsCard: () -> Unit,
    user: User
) {

    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        reservationList.forEach { reservation ->
            if (!filterState.dateStartSelected!!.isAfter(reservation.dateStart) &&
                !filterState.dateEndSelected!!.isBefore(reservation.dateEnd)) {
                if(user is Seeker){
                    SeekerReservationCard(
                        reservation = reservation,
                        onCancelReservationClicked = onCancelReservationClicked
                    )
                }
                else{
                    GiverReservationCard(
                        reservation = reservation,
                        onForwardClickedReservationsCard = onForwardClickedReservationsCard
                    )
                }
            }
        }
    }

}