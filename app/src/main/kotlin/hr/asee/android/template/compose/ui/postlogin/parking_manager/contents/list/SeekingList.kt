package hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.asee.android.template.data.model.common.service.Seeking
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.card.SeekerSeekingCard
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray


@Composable
fun SeekingList(
    seekingList: Set<Seeking>,
    filterState: DatePickerState,
    onForwardClickedSeekingCard: () -> Unit
) {
    var numShown = 0

    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        seekingList.forEach { seeking ->
            if (!filterState.dateStartSelected!!.isAfter(seeking.dateStart.toLocalDate()) &&
                !filterState.dateEndSelected!!.isBefore(seeking.dateEnd.toLocalDate())
            ) {
                SeekerSeekingCard(
                    seeking = seeking,
                    onForwardClickedSeekingCard = onForwardClickedSeekingCard
                )
                numShown++
            }
        }

        if (numShown == 0) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.parking_manager_screen_no_seekings_label),
                fontFamily = Geomanist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = LightGray
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}