package hr.asee.android.template.compose.ui.postlogin.home.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.icon.FilterIcon
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.home.HomeViewModel
import hr.asee.android.template.compose.ui.postlogin.home.contents.list.OfferList
import hr.asee.android.template.compose.ui.postlogin.home.contents.list.ReservationList
import hr.asee.android.template.compose.ui.postlogin.home.contents.list.SeekingList
import hr.asee.android.template.compose.ui.postlogin.users.Seeker
import hr.asee.android.template.compose.ui.postlogin.users.User
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenSeekerContent(
    seeker: Seeker,
    scope: CoroutineScope,
    sheetState: ModalBottomSheetState,
    filterState: DatePickerState,
    viewModel: HomeViewModel
) {
    val noContent by remember{ mutableStateOf(User.offers.size + seeker.reservations.size + seeker.seekings.size == 0) }

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
    ) {

        if (noContent) {
            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = stringResource(id = R.string.home_screen_seeker_no_content_label),
                fontFamily = Geomanist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = LightGray
            )
        } else {

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = stringResource(id = R.string.home_screen_seeker_reservation_list_label),
                    fontFamily = Geomanist,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                FilterIcon(
                    isFiltered = (filterState.dateStartSelected != LocalDate.MIN) ||
                                 (filterState.dateEndSelected != LocalDate.MAX),
                    modifier = Modifier.offset(x = 20.dp),
                    tint = MaterialTheme.colors.onBackground,
                    onFilterClicked = {
                        scope.launch {
                            if (sheetState.isVisible)
                                sheetState.hide()
                            else sheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
                    }
                )
            }

            ReservationList(
                reservationList = seeker.reservations,
                user = seeker,
                filterState = filterState,
                viewModel = viewModel
            )

            Text(
                text = stringResource(id = R.string.home_screen_seeker_offer_list_label),
                fontFamily = Geomanist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            OfferList(
                offerList = User.offers,
                user = seeker,
                filterState = filterState,
                viewModel = viewModel
            )

            Text(
                text = stringResource(id = R.string.home_screen_seeker_seeking_list_label),
                fontFamily = Geomanist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            SeekingList(
                seekingList = seeker.seekings,
                filterState = filterState,
                viewModel = viewModel
            )

        }
    }
}