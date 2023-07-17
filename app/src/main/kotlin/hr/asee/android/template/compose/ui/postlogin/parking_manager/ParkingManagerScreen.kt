package hr.asee.android.template.compose.ui.postlogin.parking_manager

import hr.asee.android.template.compose.ui.common.component.LabelText
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.dialog.BaseAlertDialog
import hr.asee.android.template.compose.ui.common.component.dialog.RequestAlertDialog
import hr.asee.android.template.compose.ui.common.component.dialog.button.*
import hr.asee.android.template.compose.ui.common.component.model.AlertDialogState
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.list.*
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray
import hr.asee.android.template.data.model.common.*
import hr.asee.android.template.data.model.common.service.*

@Composable
fun ParkingManagerScreen(viewModel: ParkingManagerViewModel = hiltViewModel(), user: User = exampleGiver){

    exampleSeeker.addSeeking(exampleSeeking1)
    exampleSeeker.addSeeking(exampleSeeking2)
    exampleSeeker.addReservation(exampleReservation1)
    exampleSeeker.addRequests(exampleRequest2)
    exampleGiver.addOffer(exampleOffer1)
    exampleGiver.addOffer(exampleOffer2)
    exampleGiver.addReservation(exampleReservation3)
    exampleGiver.addReservation(exampleReservation4)
    exampleGiver.addRequests(exampleRequest1)

    val filterState by viewModel.filterState.collectAsState()
    val reservationDialogState by viewModel.reservationDialogState.collectAsState()
    val offerDialogState by viewModel.offerDialogState.collectAsState()
    val requestDialogState by viewModel.requestDialogState.collectAsState()

    ParkingManagerScreenContent(
        user = user,
        modifier = Modifier,
        filterState = filterState,
        reservationDialogState = reservationDialogState,
        offerDialogState = offerDialogState,
        requestDialogState = requestDialogState,
        onCancelReservationClicked = viewModel::onCancelReservationClicked,
        onCancelClickedReservationCard = viewModel::onCancelClickedReservationCard,
        onCancelClickedRequestCard = viewModel::onCancelClickedRequestCard,
        onRemoveOfferClicked = viewModel::onRemoveOfferClicked,
        onCancelClickedOfferCard = viewModel::onCancelClickedOfferCard,
        onForwardClickedSeekingCard = viewModel::onForwardClickedSeekingCard,
        onForwardClickedReservationsCard = viewModel::onForwardClickedReservationsCard,
        onForwardClickedRequestsCard = viewModel::onForwardClickedRequestsCard,
        onOfferClicked = viewModel::onGiverOfferClicked
    )
}

@Composable
fun ParkingManagerScreenContent(
    modifier: Modifier = Modifier,
    user: User,
    filterState: DatePickerState,
    reservationDialogState: AlertDialogState,
    requestDialogState: AlertDialogState,
    offerDialogState: AlertDialogState,
    onCancelReservationClicked: () -> Unit,
    onCancelClickedReservationCard: () -> Unit,
    onCancelClickedRequestCard: () -> Unit,
    onRemoveOfferClicked: () -> Unit,
    onCancelClickedOfferCard: () -> Unit,
    onForwardClickedSeekingCard: () -> Unit,
    onForwardClickedRequestsCard: () -> Unit,
    onForwardClickedReservationsCard: () -> Unit,
    onOfferClicked: () -> Unit
) {
    var isButtonLeftClicked by rememberSaveable { mutableStateOf(true) }
    var isButtonMiddleClicked by rememberSaveable { mutableStateOf(false) }
    var isButtonRightClicked by rememberSaveable { mutableStateOf(false) }
    
    if(reservationDialogState.isVisible)  {
        BaseAlertDialog(
            modifier = modifier,
            isLoading = false,
            title = stringResource(id = R.string.parking_manager_screen_cancel_reservation_popup_screen_title_label),
            message = stringResource(id = R.string.parking_manager_screen_cancel_reservation_popup_screen_question_label),
            buttonsLayout = {
                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    Spacer(modifier = Modifier.width(30.dp))
                    ProceedButton(onClick = onCancelReservationClicked)
                    Spacer(modifier = Modifier.width(45.dp))
                    CancelButtonReservationPopUp(onClick = onCancelClickedReservationCard)
                }
            },
            onDismissRequest = onCancelReservationClicked
        )
    }

    if(offerDialogState.isVisible)  {
        BaseAlertDialog(
            modifier = modifier,
            isLoading = false,
            title = stringResource(id = R.string.parking_manager_screen_remove_offer_popup_screen_title_label),
            message = stringResource(id = R.string.parking_manager_screen_cancel_reservation_popup_screen_question_label),
            buttonsLayout = {
                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    Spacer(modifier = Modifier.width(30.dp))
                    ProceedButton(onClick = onRemoveOfferClicked)
                    Spacer(modifier = Modifier.width(45.dp))
                    CancelButtonReservationPopUp(onClick = onCancelClickedOfferCard)
                }
            },
            onDismissRequest = onRemoveOfferClicked
        )
    }

    if(requestDialogState.isVisible) {
        Column{
            RequestAlertDialog(
                modifier = Modifier
                    .width(350.dp)
                    .height(280.dp),
                isLoading = false,
                message = stringResource(id = R.string.reserve_parking_space_screen_request_popup_screen_question_label),
                buttonsLayout = {
                    Column(modifier = Modifier.padding(bottom = 8.dp)) {
                        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
                        AllowButton(onClick = onForwardClickedRequestsCard)
                        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
                        DenyButton(onClick = onForwardClickedRequestsCard)
                        Spacer(modifier = Modifier.height(15.dp))
                        CancelButtonRequestPopUp(onClick = onCancelClickedRequestCard)
                    }
                },
                onDismissRequest = onForwardClickedRequestsCard
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(start = 15.dp, end = 20.dp, top = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        DefaultScreenLayout(
            screenTitle = stringResource(id = R.string.parking_manager_screen_title_label),
            contentPadding = PaddingValues(all = 0.dp),
            background = Color.Transparent,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Spacer(modifier = Modifier.height(3.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        modifier = Modifier
                            .height(if (isButtonLeftClicked) 42.dp else 40.dp)
                            .width(118.dp)
                            .border(
                                width = if (!isButtonLeftClicked) 1.dp else 0.dp,
                                color = if (!isButtonLeftClicked && MaterialTheme.colors.isLight) Color.Black else Color.Transparent,
                                shape = RoundedCornerShape(20)
                            ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (isButtonLeftClicked) AssecoBlue else Color.White,
                            contentColor = if (isButtonLeftClicked) Color.White else Color.Black,
                            disabledBackgroundColor = LightGray,
                            disabledContentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20),
                        onClick = {
                            isButtonLeftClicked = true
                            isButtonMiddleClicked = false
                            isButtonRightClicked = false
                        },

                        ) {
                        Text(
                            modifier = Modifier.align(CenterVertically),
                            text =  if(user is Seeker){
                                        stringResource(R.string.parking_manager_screen_seeker_reservations_button_label)
                                    }else{
                                        stringResource(R.string.parking_manager_screen_giver_offers_button_label)
                                         },
                            fontSize = 13.sp,
                            fontFamily = Geomanist,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))

                    Button(
                        modifier = Modifier
                            .height(if (isButtonMiddleClicked) 42.dp else 40.dp)
                            .width(113.dp)
                            .border(
                                width = if (!isButtonMiddleClicked) 1.dp else 0.dp,
                                color = if (!isButtonMiddleClicked && MaterialTheme.colors.isLight) Color.Black else Color.Transparent,
                                shape = RoundedCornerShape(20)
                            ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (isButtonMiddleClicked) AssecoBlue else Color.White,
                            contentColor = if (isButtonMiddleClicked) Color.White else Color.Black,
                            disabledBackgroundColor = LightGray,
                            disabledContentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20),
                        onClick = {
                            isButtonLeftClicked = false
                            isButtonMiddleClicked = true
                            isButtonRightClicked = false
                        },

                        ) {
                        Text(
                            modifier = Modifier.align(CenterVertically),
                            text = stringResource(R.string.parking_manager_screen_seeker_requests_button_label),
                            fontSize = 13.sp,
                            fontFamily = Geomanist,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))

                    Button(
                        modifier = Modifier
                            .height(if (isButtonRightClicked) 42.dp else 40.dp)
                            .width(118.dp)
                            .border(
                                width = if (!isButtonRightClicked) 1.dp else 0.dp,
                                color = if (!isButtonRightClicked && MaterialTheme.colors.isLight) Color.Black else Color.Transparent,
                                shape = RoundedCornerShape(20)
                            ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (isButtonRightClicked) AssecoBlue else Color.White,
                            contentColor = if (isButtonRightClicked) Color.White else Color.Black,
                            disabledBackgroundColor = LightGray,
                            disabledContentColor = Color.White
                        ),
                        shape = RoundedCornerShape(20),
                        onClick = {
                            isButtonLeftClicked = false
                            isButtonMiddleClicked = false
                            isButtonRightClicked = true
                        },

                        ) {
                        Text(
                            modifier = Modifier.align(CenterVertically),
                            text =  if(user is Seeker){
                                        stringResource(R.string.parking_manager_screen_seeker_seeking_button_label)
                                    }else{
                                        stringResource(R.string.parking_manager_screen_giver_reservations_button_label)
                                         },
                            fontSize = 13.sp,
                            fontFamily = Geomanist,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                if (isButtonLeftClicked) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(state = rememberScrollState())
                    ) {
                        if (user.reservations.size == 0) {
                            Spacer(modifier = Modifier.height(100.dp))

                            LabelText(
                                text = if(user is Seeker) stringResource(id = R.string.parking_manager_screen_no_new_reservations_label)
                                        else stringResource(id = R.string.parking_manager_screen_no_offers_label),
                                fontSize = 20.sp,
                                color = LightGray
                            )

                        } else {
                            if(user is Giver){
                                OfferList(
                                    offerList = user.offers,
                                    filterState = filterState,
                                    onGiverOfferClicked = onOfferClicked,
                                    onRemoveOfferClicked = onRemoveOfferClicked
                                )
                            }
                            else{
                                ReservationList(
                                    reservationList = user.reservations,
                                    onCancelReservationClicked = onCancelReservationClicked,
                                    filterState = filterState,
                                    onForwardClickedReservationsCard = onForwardClickedReservationsCard,
                                    user = user
                                )
                            }
                        }
                    }
                }

                if (isButtonMiddleClicked) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(state = rememberScrollState())
                    ) {
                        if (user.requests.size == 0) {
                            Spacer(modifier = Modifier.height(100.dp))

                            LabelText(
                                text = stringResource(id = R.string.parking_manager_screen_no_new_requests_label),
                                fontSize = 20.sp,
                                color = LightGray
                            )
                        } else {
                            RequestList(
                                requestList = user.requests,
                                user = user,
                                onForwardClickedRequestsCard = onForwardClickedRequestsCard
                            )
                        }
                    }
                }

                if (isButtonRightClicked) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(state = rememberScrollState())
                    ) {

                            if(user is Giver){
                                if(user.reservations.size == 0){

                                    Spacer(modifier = Modifier.height(100.dp))
                                    LabelText(
                                        text = stringResource(id = R.string.parking_manager_screen_no_new_reservations_label),
                                        fontSize = 20.sp,
                                        color = LightGray
                                    )
                                }
                                else{
                                    ReservationList(
                                        reservationList = user.reservations,
                                        onForwardClickedReservationsCard = onForwardClickedReservationsCard,
                                        filterState = filterState,
                                        onCancelReservationClicked = onCancelReservationClicked,
                                        user = user
                                    )
                                }
                            }
                            else{
                                if (user.seekings.size == 0) {
                                        Spacer(modifier = Modifier.height(100.dp))

                                        LabelText(
                                            text = stringResource(id = R.string.parking_manager_screen_no_seekings_to_show_label),
                                            fontSize = 20.sp,
                                            color = LightGray
                                        )

                                }
                                else{
                                    SeekingList(
                                        seekingList = user.seekings,
                                        onForwardClickedSeekingCard = onForwardClickedSeekingCard,
                                        filterState = filterState
                                    )
                                }
                            }
                        }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParkingManagerScreenPreview() {
    ParkingManagerScreen()
}
