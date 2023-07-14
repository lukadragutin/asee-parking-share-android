package hr.asee.android.template.compose.ui.postlogin.createoffer.contents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.common.model.state.ParkingSpacePickerState
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.DarkGray
import hr.asee.android.template.compose.ui.theme.Orange
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.service.ParkingSpace

@Composable
fun ParkingSpaceSelector(
    parkingSpaces: Set<ParkingSpace>,
    user: User,
    parkingSpacePickerState: ParkingSpacePickerState,
    onRadioButtonClicked: (ParkingSpace) -> Unit,
    onCancelClicked: () -> Unit,
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(50.dp),
            modifier = Modifier.align(Alignment.Start)
        ) {
            LabelText(
                text = stringResource(id = R.string.select_parking_space_screen_cancel_button_label),
                fontSize = 16.sp,
                color = Orange,
                modifier = Modifier
                    .offset(y = 15.dp)
                    .clickable(onClick = onCancelClicked)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 5.dp)
            ) {
                LabelText(
                    text = stringResource(id = R.string.select_parking_space_screen_title_label),
                    fontSize = 18.sp
                )

                LabelText(
                    text = stringResource(id = R.string.select_parking_space_screen_instruction_label),
                    fontSize = 12.sp,
                    color = DarkGray
                )
            }
        }

        LazyColumn {
            items(parkingSpaces.toList()) { parkingSpace ->

                if (parkingSpace.owner.id == user.id) {
                    Row() {

                        RadioButton(
                            selected = parkingSpace == parkingSpacePickerState.selectedOption,
                            onClick = { onRadioButtonClicked(parkingSpace) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = AssecoBlue
                            )
                        )

                        LabelText(text = "Parking ID: ${parkingSpace.location}", fontSize = 20.sp)
                    }
                }
            }
        }
    }
}