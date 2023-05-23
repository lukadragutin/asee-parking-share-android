package hr.asee.android.template.compose.ui.common.component

import android.widget.CalendarView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.Black500
import hr.asee.android.template.compose.ui.theme.DarkGray
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.Gray
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePicker(
    calendarView: CalendarView,
    state: DatePickerState,
    onDateStartSelect: () -> Unit,
    onDateEndSelect: () -> Unit,
    onDateSelect: (LocalDate) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {

            Text(
                text = stringResource(id = R.string.date_picker_start_date_label),
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
                    text = state.dateStart?.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))
                        ?: stringResource(id = R.string.date_picker_unselected_start_date_label),
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
            modifier = Modifier.padding(horizontal = 15.dp)
        ) {

            Text(
                text = stringResource(id = R.string.date_picker_end_date_label),
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
                    text = state.dateEnd?.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))
                        ?: stringResource(id = R.string.date_picker_unselected_end_date_label),
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
            backgroundColor = if (isSystemInDarkTheme()) Black500 else Color.White
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
    }
}