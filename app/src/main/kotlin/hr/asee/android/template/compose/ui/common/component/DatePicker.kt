package hr.asee.android.template.compose.ui.common.component

import android.widget.CalendarView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.parking_manager.ParkingManagerViewModel
import hr.asee.android.template.compose.ui.theme.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePicker(
    calendarView: CalendarView,
    state: DatePickerState,
    onDateStartSelect: () -> Unit,
    onDateEndSelect: () -> Unit,
    onDateSelect: (LocalDate) -> Unit,
    viewModel: ParkingManagerViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {

            Text(
                text = stringResource(id = R.string.seeking_request_screen_date_picker_start_date_label),
                fontFamily = Geomanist,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Card(
                shape = RoundedCornerShape(30),
                backgroundColor = if (isSystemInDarkTheme()) Color.White else Gray,
                modifier = Modifier.width(150.dp)
            ) {
                Text(
                    text = state.dateStart?.format(DateTimeFormatter.ofPattern(DatePickerState.DATE_FORMAT))
                        ?: stringResource(id = R.string.seeking_request_screen_date_picker_unselected_start_date_label),
                    fontFamily = Geomanist,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (state.startFocused) AssecoBlue
                    else if (isSystemInDarkTheme()) Color.Black
                    else DarkGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(3.dp)
                        .clickable {
                            onDateStartSelect()
                        }
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {

            Text(
                text = stringResource(id = R.string.seeking_request_screen_date_picker_end_date_label),
                fontFamily = Geomanist,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Card(
                shape = RoundedCornerShape(30),
                backgroundColor = if (isSystemInDarkTheme()) Color.White else Gray,
                modifier = Modifier.width(150.dp)
            ) {
                Text(
                    text = state.dateEnd?.format(DateTimeFormatter.ofPattern(DatePickerState.DATE_FORMAT))
                        ?: stringResource(id = R.string.seeking_request_screen_date_picker_unselected_end_date_label),
                    fontFamily = Geomanist,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = if (state.endFocused) AssecoBlue
                    else if (isSystemInDarkTheme()) Color.Black
                    else DarkGray,
                    modifier = Modifier
                        .padding(3.dp)
                        .clickable {
                            onDateEndSelect()
                        },
                    textAlign = TextAlign.Center
                )
            }
        }

        Card(
            backgroundColor = if (isSystemInDarkTheme()) Black500 else Color.White,
            modifier = Modifier.height(340.dp)
        ) {
            AndroidView(
                factory = { calendarView },
                update = {
                    it.setOnDateChangeListener { _, y, m, d ->
                        onDateSelect(LocalDate.of(y, m + 1, d))
                    }
                }
            )
        }
        Row{
            Button(
                modifier = Modifier
                    .width(165.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AssecoBlue,
                    contentColor = Color.White,
                ),
                onClick = { /*TODO*/ }
            ){
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text =  stringResource(id = R.string.seeking_request_screen_edit_button_label),
                    fontSize = 16.sp,
                    fontFamily = Geomanist,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.width(10.dp))

            Button(
                modifier = Modifier
                    .width(165.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Orange,
                ),
                onClick = { viewModel.onCancelClicked() }
            ){
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text =  stringResource(id = R.string.seeking_request_screen_cancel_button_label),
                    fontSize = 16.sp,
                    fontFamily = Geomanist,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}