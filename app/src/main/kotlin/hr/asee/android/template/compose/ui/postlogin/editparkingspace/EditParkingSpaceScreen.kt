package hr.asee.android.template.compose.ui.postlogin.editparkingspace

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.ui.common.component.InputField
import hr.asee.android.template.compose.ui.common.component.ParkingSpacedDisplay
import hr.asee.android.template.compose.ui.common.component.button.BackButton
import hr.asee.android.template.compose.ui.common.component.button.BlueButton
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.InputFieldState
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.domain.model.common.service.ParkingSpace

@Composable
fun EditParkingSpaceScreen(viewModel: EditParkingSpaceViewModel = hiltViewModel(), parkingSpaceId: Int = -1) {

	viewModel.initialize(parkingSpaceId)
	val parkingSpace by viewModel.parkingSpaceState.collectAsState()
	val parkingNumberState by viewModel.parkingNumberState.collectAsState()

	AndroidComposeCodingTemplateTheme(
		darkTheme = if (Config.DARK_THEME == null) isSystemInDarkTheme() else Config.DARK_THEME == true
	) {

		Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {

			Spacer(modifier = Modifier.height(10.dp))

			BackButton(onClick = viewModel::goBack)

			DefaultScreenLayout(
				screenTitle = stringResource(id = R.string.edit_parking_space_screen_title_label),
				background = Color.Transparent,
				modifier = Modifier
						.fillMaxHeight()
			) {

				EditParkingSpaceScreenContent(
					parkingNumberState = parkingNumberState,
					parkingSpace = parkingSpace,
					onConfirmClicked = viewModel::onConfirmClicked
				)
			}
		}
	}

}

@Composable
fun EditParkingSpaceScreenContent(
	parkingNumberState: InputFieldState,
	parkingSpace: ParkingSpace,
	onConfirmClicked: () -> Unit
) {

	Column(
		verticalArrangement = Arrangement.SpaceBetween,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier.fillMaxWidth()
	) {

		Spacer(modifier = Modifier.height(10.dp))

		ParkingSpacedDisplay(
			parkingSpace = parkingSpace,
			modifier = Modifier
					.height(275.dp)
					.width(275.dp)
		)

		Spacer(modifier = Modifier.height(30.dp))

		val focusManager = LocalFocusManager.current
		val unfocusedBorderColor = MaterialTheme.colors.onSurface
		var fieldBorderColor by remember { mutableStateOf(unfocusedBorderColor) }

		InputField(
			modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        shape = RoundedCornerShape(20),
                        color = Color.Transparent
                    )
                    .onFocusChanged {
                        fieldBorderColor = if (it.isFocused) AssecoBlue else unfocusedBorderColor
                    }
                    .border(
                        width = 1.dp,
                        color = fieldBorderColor,
                        shape = RoundedCornerShape(20)
                    ),
			state = parkingNumberState,
			label = stringResource(id = R.string.edit_parking_space_parking_number_field_label),
			keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
			keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) })
		)

		Spacer(modifier = Modifier.weight(1f))

		BlueButton(
			label = stringResource(id = R.string.edit_parking_space_confirm_button_label),
			onClick = { onConfirmClicked() }
		)

	}

}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
	EditParkingSpaceScreen()
}