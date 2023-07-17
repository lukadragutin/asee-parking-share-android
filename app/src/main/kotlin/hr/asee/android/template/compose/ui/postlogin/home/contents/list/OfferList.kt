package hr.asee.android.template.compose.ui.postlogin.home.contents.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.button.CancelButton
import hr.asee.android.template.compose.ui.common.component.button.ProceedButton
import hr.asee.android.template.compose.ui.common.component.dialog.BaseAlertDialog
import hr.asee.android.template.compose.ui.common.model.state.AlertDialogState
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.home.contents.card.GiverOfferCard
import hr.asee.android.template.compose.ui.postlogin.home.contents.card.SeekerOfferCard
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray
import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.Seeker
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.service.Offer

@Composable
fun OfferList(
    offers: Set<Offer>,
    user: User,
    removeOfferDialogState: AlertDialogState,
    removeOfferId: Int,
    filterState: DatePickerState,
    onGiverOfferClicked: () -> Unit,
    onSeekerOfferClicked: (Int) -> Unit,
    onRemoveOfferClicked: (Int) -> Unit,
    onCancelClickedOfferCard: () -> Unit,
    onRemoveClickedOfferCard: (Int) -> Unit
) {
    var numShown = 0

    if (removeOfferDialogState.isVisible && removeOfferId >= 0) {
        BaseAlertDialog(
            isLoading = false,
            title = stringResource(id = R.string.home_screen_remove_offer_popup_screen_title_label),
            message = stringResource(id = R.string.home_screen_remove_offer_popup_screen_question_label),
            buttonsLayout = {
                Row(modifier = Modifier.padding(bottom = 8.dp)) {
                    Spacer(modifier = Modifier.width(30.dp))
                    ProceedButton(onClick = { onRemoveClickedOfferCard(removeOfferId) })
                    Spacer(modifier = Modifier.width(45.dp))
                    CancelButton(onClick = onCancelClickedOfferCard)
                }
            },
            onDismissRequest = onCancelClickedOfferCard
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        offers.forEach { offer ->
            if (!filterState.dateStartSelected!!.isAfter(offer.dateStart) &&
                !filterState.dateEndSelected!!.isBefore(offer.dateEnd)) {
                if (user is Giver && offer.parkingSpace.owner.id == user.id) {
                    GiverOfferCard(
                        offer = offer,
                        onOfferClicked = onGiverOfferClicked,
                        onRemoveOfferClicked = {
                            onRemoveOfferClicked(offer.id)
                        }
                    )

                    numShown++
                }
                else if (user is Seeker) {
                    SeekerOfferCard(
                        offer = offer,
                        onOfferClicked = onSeekerOfferClicked
                    )

                    numShown++
                }
            }
        }

        if (numShown == 0) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.home_screen_no_offers_label),
                fontFamily = Geomanist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = LightGray
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}