package hr.asee.android.template.compose.ui.postlogin.createoffer.contents

import android.text.Layout
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonColors
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config.DARK_THEME
import hr.asee.android.template.compose.ui.common.component.DatePicker
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.common.component.button.BlueButton
import hr.asee.android.template.compose.ui.common.model.state.AccountState
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.common.model.state.ParkingSpacePickerState
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.DarkGray
import hr.asee.android.template.compose.ui.theme.LightGray
import hr.asee.android.template.compose.ui.theme.Orange
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun ParkingSpaceSelector(
    accountState: AccountState,
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
            items(accountState.parkingSpaces!!.toList()) { parkingSpace ->

                if (parkingSpace.owner == accountState.user) {
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