package hr.asee.android.template.compose.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.domain.model.common.UserCompact
import hr.asee.android.template.domain.model.common.service.ParkingSpace

@Composable
fun ParkingSpacedDisplay(
	parkingSpace: ParkingSpace,
	modifier: Modifier = Modifier,
	size: Float = 1.0f
) {

	Card(
		backgroundColor = MaterialTheme.colors.primaryVariant,
		shape = CircleShape,
		modifier = modifier
				.size(150.dp * size)
	) {

		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {

			Image(
				painter = painterResource(id = R.mipmap.parking_offer),
				contentDescription = stringResource(id = R.string.parking_space_display_image_content_description),
				modifier = Modifier.size(64.dp * size)
			)

			LabelText(
				text = stringResource(id = R.string.parking_space_display_parking_number_label),
				fontSize = 14.sp * size,
				color = MaterialTheme.colors.secondary
			)

			LabelText(text = parkingSpace.location, fontSize = 14.sp * size)

		}

	}

}

@Composable
@Preview
fun previewParkingSpaceDisplay() {
    ParkingSpacedDisplay(parkingSpace = ParkingSpace(
		1,
		"A2020",
		UserCompact.EMPTY
	))
}