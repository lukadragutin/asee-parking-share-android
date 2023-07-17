package hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.card

import hr.asee.android.template.compose.ui.common.component.LabelText
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config.CARD_DATE_FORMAT
import hr.asee.android.template.compose.ui.theme.DarkGray
import hr.asee.android.template.compose.ui.theme.Orange
import hr.asee.android.template.data.model.common.service.Reservation
import java.time.format.DateTimeFormatter

@Composable
fun SeekerReservationCard(
    reservation: Reservation,
    onCancelReservationClicked: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(15)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(15.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.outdoor_parking),
                    contentDescription = stringResource(id = R.string.parking_manager_screen_seeker_reservation_card_image_content_description),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(46.dp)
                        .width(69.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Column {
                    LabelText(
                        text = reservation.parkingSpace.location,
                        fontSize = 16.sp
                    )

                    Row(horizontalArrangement = Arrangement.SpaceBetween) {

                        Column(modifier = Modifier.padding(vertical = 8.dp)) {

                            LabelText(
                                text = stringResource(id = R.string.parking_manager_screen_seeker_reservation_card_start_date_label),
                                color = DarkGray,
                                fontSize = 14.sp,
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            LabelText(
                                text = stringResource(id = R.string.parking_manager_screen_seeker_reservation_card_end_date_label),
                                color = DarkGray,
                                fontSize = 14.sp,
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Column(
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            LabelText(
                                text = reservation.dateStart.format(DateTimeFormatter.ofPattern(CARD_DATE_FORMAT)),
                                fontSize = 14.sp,
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            LabelText(
                                text = reservation.dateEnd.format(DateTimeFormatter.ofPattern(CARD_DATE_FORMAT)),
                                fontSize = 14.sp,
                            )
                        }
                    }

                }
            }

            Divider(
                thickness = 1.dp,
                color = Gray,
                modifier = Modifier.padding(horizontal = 30.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            LabelText(
                text = stringResource(id = R.string.parking_manager_screen_seeker_reservation_card_cancel_reservation_label),
                color = Orange,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { onCancelReservationClicked()}
            )
        }
    }
}