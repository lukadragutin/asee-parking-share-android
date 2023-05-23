package hr.asee.android.template.compose.ui.postlogin.home.contents.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.common.service.Reservation
import hr.asee.android.template.compose.ui.postlogin.home.HomeViewModel
import hr.asee.android.template.compose.ui.postlogin.home.contents.card.GiverReservationCard
import hr.asee.android.template.compose.ui.postlogin.home.contents.card.SeekerReservationCard
import hr.asee.android.template.compose.ui.postlogin.users.Giver
import hr.asee.android.template.compose.ui.postlogin.users.User
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray

@Composable
fun ReservationList(
    reservationList: Set<Reservation>,
    user: User,
    filterState: DatePickerState,
    viewModel: HomeViewModel
) {
    var numShown = 0

    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        reservationList.forEach() { reservation ->
            if (!filterState.dateStartSelected!!.isAfter(reservation.dateStart.toLocalDate()) &&
                !filterState.dateEndSelected!!.isBefore(reservation.dateEnd.toLocalDate())) {
                if (user is Giver) GiverReservationCard(reservation = reservation, viewModel = viewModel)
                else SeekerReservationCard(reservation = reservation, viewModel = viewModel)
                numShown++
            }
        }

        if (numShown == 0) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.home_screen_no_reservations_label),
                fontFamily = Geomanist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = LightGray
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}