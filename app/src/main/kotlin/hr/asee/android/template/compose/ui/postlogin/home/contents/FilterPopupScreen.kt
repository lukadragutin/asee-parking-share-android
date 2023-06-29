package hr.asee.android.template.compose.ui.postlogin.home.contents

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
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
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.DarkGray
import hr.asee.android.template.compose.ui.theme.LightGray
import hr.asee.android.template.compose.ui.theme.Orange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterPopupScreen(
    scope: CoroutineScope,
    sheetState: ModalBottomSheetState,
    filterState: DatePickerState,
    onFilterClicked: () -> Boolean,
    onCancelClicked: () -> Unit,
    onResetClicked: () -> Unit,
    onDateStartSelect: () -> Unit,
    onDateEndSelect: () -> Unit,
    onDateSelect: (LocalDateTime) -> Unit
) {

    val calendarView = CalendarView(
        ContextThemeWrapper(
            LocalContext.current,
            if ((DARK_THEME == true) or isSystemInDarkTheme()) R.style.CalendarViewCustomDark
            else R.style.CalendarViewCustomLight
        )
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(50.dp)) {
            LabelText(
                text = stringResource(id = R.string.filter_popup_cancel_button_label),
                fontSize = 16.sp,
                color = Orange,
                modifier = Modifier
                    .offset(y = 15.dp)
                    .clickable {
                        scope.launch {
                            onCancelClicked()
                            sheetState.hide()
                        }
                    }
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 5.dp)
            ) {
                LabelText(
                    text = stringResource(id = R.string.filter_popup_title_label),
                    fontSize = 18.sp
                )

                LabelText(
                    text = stringResource(id = R.string.filter_popup_instruction_label),
                    fontSize = 12.sp,
                    color = DarkGray
                )
            }

            LabelText(
                text = stringResource(id = R.string.filter_popup_reset_button_label),
                fontSize = 16.sp,
                modifier = Modifier
                    .offset(y = 15.dp)
                    .clickable {
                        onResetClicked()
                    }
            )
        }
        
        DatePicker(
            calendarView = calendarView,
            state = filterState,
            onDateStartSelect = onDateStartSelect,
            onDateEndSelect = onDateEndSelect,
            onDateSelect = onDateSelect
        )

        BlueButton(
            label = stringResource(id = R.string.filter_popup_filter_button_label),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .offset(y = (-50).dp),
            onClick = {
            if (onFilterClicked()) {
                scope.launch {
                    sheetState.hide()
                }
            }
        })

    }
}


