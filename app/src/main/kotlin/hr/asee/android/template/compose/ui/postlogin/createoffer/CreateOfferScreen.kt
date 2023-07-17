package hr.asee.android.template.compose.ui.postlogin.createoffer

import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.ui.common.component.DatePicker
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.common.component.ParkingSpacedDisplay
import hr.asee.android.template.compose.ui.common.component.button.BackButton
import hr.asee.android.template.compose.ui.common.component.button.BlueButton
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.common.model.state.ParkingSpacePickerState
import hr.asee.android.template.compose.ui.postlogin.createoffer.contents.ParkingSpaceSelector
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateOfferScreen(viewModel: CreateOfferViewModel = hiltViewModel(), userId: Int) {

	viewModel.init(userId)

	val parkingSpace by viewModel.parkingSpaceState.collectAsState()
	val datePickerState by viewModel.datePickerState.collectAsState()
	val parkingSpacePickerState by viewModel.parkingSpacePickerState.collectAsState()

	val scope = rememberCoroutineScope()
	val sheetState = rememberModalBottomSheetState(
		initialValue = ModalBottomSheetValue.Hidden,
		confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
		skipHalfExpanded = true
	)

	BackHandler(sheetState.isVisible) {
		scope.launch {
			sheetState.hide()
		}
	}

	AndroidComposeCodingTemplateTheme(
		darkTheme = (if (Config.DARK_THEME == null) isSystemInDarkTheme() else Config.DARK_THEME == true)
	) {

		ModalBottomSheetLayout(
			sheetContent = {
				ParkingSpaceSelector(
					parkingSpace = parkingSpace,
					parkingSpacePickerState = parkingSpacePickerState,
					onRadioButtonClicked = viewModel::onRadioButtonClicked,
					onCancelClicked = {
						scope.launch {
							if (sheetState.isVisible) sheetState.hide()
						}
					}
				)
			},
			sheetBackgroundColor = MaterialTheme.colors.background,
			sheetState = sheetState,
			sheetShape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8),
			modifier = Modifier
					.fillMaxSize()
		) {

			Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {

				Spacer(modifier = Modifier.height(10.dp))

				BackButton(onClick = viewModel::goBack)

				DefaultScreenLayout(
					screenTitle = stringResource(id = R.string.create_offer_screen_title_label),
					background = Color.Transparent
				) {
					CreateOfferScreenContent(
						scope = scope,
						sheetState = sheetState,
						onDateStartSelect = viewModel::onDateStartSelect,
						onDateEndSelect = viewModel::onDateEndSelect,
						onDateSelect = viewModel::onDateSelect,
						onCreateClicked = viewModel::onCreateClicked,
						onCancelClicked = viewModel::onCancelClicked,
						datePickerState = datePickerState,
						parkingSpacePickerState = parkingSpacePickerState
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateOfferScreenContent(
	scope: CoroutineScope,
	sheetState: ModalBottomSheetState,
	datePickerState: DatePickerState,
	parkingSpacePickerState: ParkingSpacePickerState,
	onDateStartSelect: () -> Unit,
	onDateEndSelect: () -> Unit,
	onDateSelect: (LocalDateTime) -> Unit,
	onCreateClicked: () -> Unit,
	onCancelClicked: () -> Unit
) {
	val calendarView = CalendarView(
		ContextThemeWrapper(
			LocalContext.current,
			if (isSystemInDarkTheme()) R.style.CalendarViewCustomDark
			else R.style.CalendarViewCustomLight
		)
	)

	Column(
		verticalArrangement = Arrangement.spacedBy(10.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
				.fillMaxWidth()
				.verticalScroll(rememberScrollState())
	) {

		ParkingSpacedDisplay(
			parkingSpace = parkingSpacePickerState.selectedOption,
			modifier = Modifier.clickable {
				scope.launch {
					if (sheetState.isVisible)
						sheetState.hide()
					else sheetState.animateTo(ModalBottomSheetValue.Expanded)
				}
			}
		)

		Spacer(modifier = Modifier.height(5.dp))

		DatePicker(
			calendarView = calendarView,
			state = datePickerState,
			onDateStartSelect = onDateStartSelect,
			onDateEndSelect = onDateEndSelect,
			onDateSelect = onDateSelect,
			modifier = Modifier
					.fillMaxWidth()
		)

		BlueButton(
			label = stringResource(id = R.string.create_offer_screen_create_button_label),
			modifier = Modifier
					.fillMaxWidth(),
			onClick = onCreateClicked
		)

		LabelText(
			text = stringResource(id = R.string.create_offer_screen_cancel_button_label),
			fontSize = 16.sp,
			modifier = Modifier
					.clickable(onClick = onCancelClicked),
			textDecoration = TextDecoration.Underline
		)
	}
}