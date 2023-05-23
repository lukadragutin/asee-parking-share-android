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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.DatePicker
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.home.HomeViewModel
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.DarkGray
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray
import hr.asee.android.template.compose.ui.theme.Orange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//var dateStartSelected: LocalDate = LocalDate.MIN
//var dateEndSelected: LocalDate = LocalDate.MAX

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterPopupScreen(
    scope: CoroutineScope,
    sheetState: ModalBottomSheetState,
    filterState: DatePickerState,
    viewModel: HomeViewModel
) {

    val calendarView = CalendarView(
        ContextThemeWrapper(
            LocalContext.current,
            if (isSystemInDarkTheme()) R.style.CalendarViewCustomDark
            else R.style.CalendarViewCustomLight
        )
    )

//    var dateStart: LocalDate? by remember {
//        mutableStateOf(if (dateStartSelected == LocalDate.MIN) null else dateStartSelected)
//    }
//    var dateEnd: LocalDate? by remember {
//        mutableStateOf(if (dateEndSelected == LocalDate.MAX) null else dateEndSelected)
//    }
//
//    var startFocused by remember { mutableStateOf(false) }
//    var endFocused by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(50.dp)) {
            Text(
                text = stringResource(id = R.string.filter_popup_cancel_button_label),
                fontFamily = Geomanist,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Orange,
                modifier = Modifier
                    .offset(y = 15.dp)
                    .clickable {
                        scope.launch {
                            viewModel.onCancelClicked()
                            sheetState.hide()
                        }
                    }
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 5.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.filter_popup_title_label),
                    fontFamily = Geomanist,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = stringResource(id = R.string.filter_popup_instruction_label),
                    fontFamily = Geomanist,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = DarkGray
                )
            }

            Text(
                text = stringResource(id = R.string.filter_popup_reset_button_label),
                fontFamily = Geomanist,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier
                    .offset(y = 15.dp)
                    .clickable {
                        viewModel.onResetClicked()
                    }
            )
        }
        
        DatePicker(
            calendarView = calendarView,
            state = filterState,
            onDateStartSelect = viewModel::onDateStartSelect,
            onDateEndSelect = viewModel::onDateEndSelect,
            onDateSelect = viewModel::onDateSelect
        )

        Button(
            modifier = Modifier
                .height(57.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = (-50).dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AssecoBlue,
                contentColor = Color.White,
                disabledBackgroundColor = LightGray,
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(15),
            onClick = {
                    var filterSuccess = true
                    try {
                        viewModel.filterClicked() }
                    catch (exception: Exception) {
                        filterSuccess = false
                    }
                    if (filterSuccess) {
                        scope.launch {
                            sheetState.hide()
                        }
                    }
//                    filterState.dateStartSelected = filterState.dateStart as LocalDate
//                    filterState.dateEndSelected = filterState.dateEnd as LocalDate
//
//                    filterState.startFocused = false
//                    filterState.endFocused = false
            },
        ) {
            Text(
                text = stringResource(id = R.string.filter_popup_filter_button_label),
                fontSize = 18.sp,
                fontFamily = Geomanist,
                fontWeight = FontWeight.Bold
            )
        }

    }
}


