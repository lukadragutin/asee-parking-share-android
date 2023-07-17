package hr.asee.android.template.compose.ui.postlogin.parking_manager.contents

import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.DatePicker
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.parking_manager.ParkingManagerViewModel
import hr.asee.android.template.compose.ui.theme.Black200
import org.threeten.bp.LocalDateTime

@Composable
fun SeekingRequestScreen(viewModel: ParkingManagerViewModel = hiltViewModel()) {

	val filterState by viewModel.filterState.collectAsState()

	SeekingRequestScreenContent(
		onBackClicked = viewModel::onBackClicked,
		onDateStartSelect = viewModel::onDateStartSelect,
		onDateEndSelect = viewModel::onDateEndSelect,
		onDateSelect = viewModel::onDateSelect,
		filterState = filterState
	)
}

@Composable
fun SeekingRequestScreenContent(
	onBackClicked: () -> Unit,
	filterState: DatePickerState,
	onDateStartSelect: () -> Unit,
	onDateEndSelect: () -> Unit,
	onDateSelect: (LocalDateTime) -> Unit
) {
	val calendarView = CalendarView(
		ContextThemeWrapper(
			LocalContext.current,
			if (isSystemInDarkTheme()) R.style.CalendarViewCustomDark
			else R.style.CalendarViewCustomLight
		)
	)

	Column(
		modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(start = 10.dp, end = 20.dp, top = 10.dp)
	) {

		Icon(
			modifier = Modifier
                    .height(35.dp)
                    .width(40.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = false),
                        onClick = onBackClicked
                    ),
			painter = painterResource(R.drawable.back),
			contentDescription = stringResource(id = R.string.seeking_request_screen_back_image_content_description),
		)
		Spacer(modifier = Modifier.height(8.dp))

		LabelText(
			text = stringResource(id = R.string.seeking_request_screen_title_label),
			fontSize = 26.sp,
			color = Black200,
			modifier = Modifier.padding(start = 13.dp)
		)

		Column {

			Row {
				Spacer(modifier = Modifier.width(95.dp))

				Image(
					painter = painterResource(id = R.drawable.edit_seeking_request),
					contentDescription = stringResource(id = R.string.seeking_request_screen_edit_seeking_request_image_content_description),
					contentScale = ContentScale.Fit,
					modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
				)
			}

			Spacer(modifier = Modifier.height(5.dp))

			DatePicker(
				calendarView = calendarView,
				state = filterState,
				onDateStartSelect = onDateStartSelect,
				onDateEndSelect = onDateEndSelect,
				onDateSelect = onDateSelect,
			)
		}
	}
}