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
import hr.asee.android.template.compose.ui.postlogin.home.contents.card.SeekingCard
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray
import hr.asee.android.template.domain.model.common.service.Seeking

@Composable
fun SeekingList(
    seekings: Set<Seeking>,
    filterState: DatePickerState,
    onSeekingClicked: () -> Unit
) {
    var numShown = 0

    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        seekings.forEach { seeking ->
            if (!filterState.dateStartSelected!!.isAfter(seeking.dateStart) &&
                !filterState.dateEndSelected!!.isBefore(seeking.dateEnd)) {
                SeekingCard(
                    seeking = seeking,
                    onSeekingClicked = onSeekingClicked
                )
                numShown++
            }
        }

        if (numShown == 0) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.home_screen_no_seekings_label),
                fontFamily = Geomanist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = LightGray
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}