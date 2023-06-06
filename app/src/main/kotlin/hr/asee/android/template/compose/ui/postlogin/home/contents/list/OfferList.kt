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
import hr.asee.android.template.compose.ui.common.model.state.AccountState
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.compose.ui.postlogin.home.contents.card.GiverOfferCard
import hr.asee.android.template.compose.ui.postlogin.home.contents.card.SeekerOfferCard
import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray

@Composable
fun OfferList(
    offerList: List<Offer>,
    user: User,
    accountState: AccountState,
    filterState: DatePickerState,
    onGiverOfferClicked: () -> Unit,
    onSeekerOfferClicked: () -> Unit,
    onRemoveOfferClicked: () -> Unit
) {
    var numShown = 0

    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        offerList.forEach() { offer ->
            if (!filterState.dateStartSelected!!.isAfter(offer.dateStart.toLocalDate()) &&
                !filterState.dateEndSelected!!.isBefore(offer.dateEnd.toLocalDate())) {
                if (accountState.user is Giver && offer.parkingSpace.owner == accountState.user) {
                    GiverOfferCard(
                        offer = offer,
                        onOfferClicked = onGiverOfferClicked,
                        onRemoveOfferClicked = onRemoveOfferClicked
                    )
                }
                else {
                    SeekerOfferCard(
                        offer = offer,
                        onOfferClicked = onSeekerOfferClicked
                    )
                }
                numShown++
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