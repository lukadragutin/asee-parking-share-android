package hr.asee.android.template.compose.ui.postlogin.parking_manager.contents

import hr.asee.android.template.compose.ui.common.component.LabelText
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.DatePicker
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.parking_manager.ParkingManagerViewModel
import hr.asee.android.template.compose.ui.theme.Black200
import hr.asee.android.template.data.model.common.service.Offer
import hr.asee.android.template.data.model.common.service.exampleOffer1
import java.time.LocalDate

@Composable
fun ParkingOfferScreen(viewModel: ParkingManagerViewModel = hiltViewModel()){

    val filterState by viewModel.filterState.collectAsState()

    ParkingOfferScreenContent(
        onBackClicked = viewModel::onBackClicked,
        onDateStartSelect = viewModel::onDateStartSelect,
        onDateEndSelect = viewModel::onDateEndSelect,
        onDateSelect = viewModel::onDateSelect,
        filterState = filterState,
        offer = exampleOffer1
    )
}

@Composable
fun ParkingOfferScreenContent(
    onBackClicked: () -> Unit,
    filterState: DatePickerState,
    onDateStartSelect: () -> Unit,
    onDateEndSelect: () -> Unit,
    onDateSelect: (LocalDate) -> Unit,
    offer: Offer,
    ){
    val calendarView = CalendarView(
        ContextThemeWrapper(
            LocalContext.current,
            if (isSystemInDarkTheme()) R.style.CalendarViewCustomDark
            else R.style.CalendarViewCustomLight
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(start = 10.dp, end = 20.dp, top = 10.dp)
    ) {

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
            contentDescription = stringResource(id = R.string.seeking_request_screen_back_image_content_description),
        )
        Spacer(modifier = Modifier.height(8.dp))

        LabelText(
            text = stringResource(id = R.string.parking_offer_screen_title_label),
            fontSize = 26.sp,
            color = Black200,
            modifier = Modifier.padding(start = 13.dp)
        )

        Column {

            Row {
                Spacer(modifier = Modifier.width(120.dp))

                Image(
                    painter = painterResource(id = R.drawable.edit_parking_offer),
                    contentDescription = stringResource(id = R.string.parking_offer_screen_edit_parking_offer_image_content_description),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(120.dp)
                        .width(120.dp)
                )
            }

            Column(modifier = Modifier.align(CenterHorizontally)) {
                LabelText(
                    text = offer.parkingSpace.location,
                    fontSize = 20.sp
                )
            }
            Column(modifier = Modifier.align(CenterHorizontally)) {
                LabelText(
                    text = stringResource(id = R.string.parking_offer_screen_parking_number_label),
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            DatePicker(
                calendarView = calendarView,
                state = filterState,
                onDateStartSelect = onDateStartSelect,
                onDateEndSelect = onDateEndSelect,
                onDateSelect = onDateSelect,
                viewModel = viewModel()
            )
        }
    }
}