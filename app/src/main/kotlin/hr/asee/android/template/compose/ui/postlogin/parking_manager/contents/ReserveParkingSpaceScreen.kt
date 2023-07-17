package hr.asee.android.template.compose.ui.postlogin.parking_manager.contents

import hr.asee.android.template.compose.ui.common.component.LabelText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.postlogin.parking_manager.ParkingManagerViewModel
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.AssecoGray
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.Orange
import hr.asee.android.template.data.model.common.service.Reservation
import hr.asee.android.template.data.model.common.service.exampleReservation1

@Composable
fun ReserveParkingSpaceScreen(viewModel: ParkingManagerViewModel = hiltViewModel()){

    ReserveParkingSpaceScreenContent(
        onBackClicked = viewModel::onBackClicked,
        reservation = exampleReservation1
    )

}

@Composable
fun ReserveParkingSpaceScreenContent(
    onBackClicked: () -> Unit,
    reservation: Reservation

    ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(start = 13.dp, end = 20.dp, top = 44.dp)
    ){
        Icon(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = false),
                    onClick = onBackClicked
                ),
            painter = painterResource(R.drawable.back),
            contentDescription = stringResource(id = R.string.reserve_parking_space_screen_back_image_content_description),
        )
        
        Spacer(modifier = Modifier.height(15.dp))
        

        LabelText(
            text = stringResource(id = R.string.reserve_parking_space_screen_title_label),
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 13.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Spacer(modifier = Modifier.width(55.dp))

            Image(
                painter = painterResource(id = R.drawable.reserve_parking_space),
                contentDescription = stringResource(id = R.string.reserve_parking_space_screen_reserve_parking_space_image_content_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
            )
        }

        Column(modifier = Modifier.align(CenterHorizontally)) {
            LabelText(
                text = reservation.parkingSpace.location,
                fontSize = 20.sp
            )
        }
        Column(modifier = Modifier.align(CenterHorizontally)) {
            LabelText(
                text = stringResource(id = R.string.parking_offer_screen_parking_number_label),
                fontSize = 14.sp,
                color = AssecoGray
            )
        }
        
        Spacer(modifier = Modifier
            .width(21.dp)
            .height(40.dp)
        )

        LabelText(
            text = stringResource(id = R.string.reserve_parking_space_screen_reservation_period_label),
            fontSize = 14.sp,
            color = AssecoGray,
            modifier = Modifier.padding(start = 13.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Spacer(modifier = Modifier.width(13.dp))
            
            Column {
                Card(
                    modifier = Modifier
                        .width(93.dp)
                        .height(16.dp),
                        //.padding(start = 25.dp),
                    backgroundColor = AssecoBlue,
                    shape = RoundedCornerShape(
                        topStart = 15.dp,
                        topEnd = 15.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                ) {}
                Card(
                    modifier = Modifier
                        .width(93.dp)
                        .height(74.dp),
                        //.padding(start = 25.dp),
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 15.dp,
                        bottomEnd = 15.dp
                    )
                )
                {
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))

                        LabelText(
                            text = reservation.dateStart.dayOfMonth.toString(),
                            fontSize = 28.sp,
                            modifier = Modifier.align(CenterHorizontally)
                        )
                        LabelText(
                            text = reservation.dateStart.month.toString() + " " + reservation.dateStart.year.toString(),
                            fontSize = 12.sp,
                            modifier = Modifier.align(CenterHorizontally),
                            color = AssecoGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(52.dp))

            Column {
                Spacer(modifier = Modifier.height(45.dp))

                Divider(
                    thickness = 1.dp,
                    color = Color.Gray,
                    modifier = Modifier.width(50.dp)
                )
            }

            Spacer(modifier = Modifier.width(52.dp))

            Column {
                Card(
                    modifier = Modifier
                        .width(93.dp)
                        .height(16.dp),
                    backgroundColor = Orange,
                    shape = RoundedCornerShape(
                        topStart = 15.dp,
                        topEnd = 15.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                ) {}
                Card(
                    modifier = Modifier
                        .width(93.dp)
                        .height(74.dp),
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 15.dp,
                        bottomEnd = 15.dp
                    )
                )
                {
                    Column {
                        Spacer(modifier = Modifier.height(10.dp))

                        LabelText(
                            text = reservation.dateEnd.dayOfMonth.toString(),
                            fontSize = 28.sp,
                            modifier = Modifier.align(CenterHorizontally)
                        )
                        LabelText(
                            text = reservation.dateEnd.month.toString() + " " + reservation.dateEnd.year.toString(),
                            fontSize = 12.sp,
                            modifier = Modifier.align(CenterHorizontally),
                            color = AssecoGray
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(76.dp))

        Button(
            modifier = Modifier
                .width(366.dp)
                .height(57.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
                contentColor = Color.White,
            ),
            onClick = { /*TODO*/ }
        ){
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text =  stringResource(id = R.string.reserve_parking_space_screen_request_back_button_label),
                fontSize = 17.sp,
                fontFamily = Geomanist,
                fontWeight = FontWeight.Bold
            )
        }
    }
}