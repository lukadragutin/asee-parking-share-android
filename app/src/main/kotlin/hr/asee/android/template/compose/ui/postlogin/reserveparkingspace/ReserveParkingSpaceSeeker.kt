package hr.asee.android.template.compose.ui.postlogin.reserveparkingspace

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.common.component.ParkingSpacedDisplay
import hr.asee.android.template.compose.ui.common.component.button.BackButton
import hr.asee.android.template.compose.ui.common.component.button.BlueButton
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.DarkGray
import hr.asee.android.template.compose.ui.theme.Orange
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.exampleOffer1

@Composable
fun ReserveParkingSpaceSeekerScreen(viewModel: ReserveParkingSpaceViewModel = hiltViewModel(), offerId: Int = 0) {

	viewModel.getOffer(offerId)
	val offer by viewModel.offerState.collectAsState()

	AndroidComposeCodingTemplateTheme(
		darkTheme = if (Config.DARK_THEME == null) isSystemInDarkTheme() else Config.DARK_THEME == true
	) {

		Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {

			Spacer(modifier = Modifier.height(10.dp))

			BackButton(onClick = viewModel::goBack)

			DefaultScreenLayout(
				screenTitle = stringResource(id = R.string.reserve_parking_space_screen_title_label),
				background = Color.Transparent,
				modifier = Modifier
						.fillMaxHeight()
			) {

				ReserveParkingSpaceSeekerScreenContent(
					offer = offer,
					onReserveParkingSpaceClicked = viewModel::onReserveParkingSpaceClicked
				)
			}
		}
	}

}

@Composable
fun ReserveParkingSpaceSeekerScreenContent(
	offer: Offer = exampleOffer1,
	onReserveParkingSpaceClicked: (Offer) -> Unit
) {

	Column(
		verticalArrangement = Arrangement.SpaceBetween,
		horizontalAlignment = CenterHorizontally,
		modifier = Modifier.fillMaxWidth()
	) {

		Spacer(modifier = Modifier.height(10.dp))

		ParkingSpacedDisplay(
			parkingSpace = offer.parkingSpace,
			modifier = Modifier
                    .height(275.dp)
                    .width(275.dp)
		)

		Spacer(modifier = Modifier.height(30.dp))

		LabelText(
			text = stringResource(id = R.string.reserve_parking_space_screen_reservation_period_label),
			fontSize = 14.sp,
			color = DarkGray,
			modifier = Modifier.padding(start = 13.dp)
		)

		Spacer(modifier = Modifier.height(20.dp))

		Row {
			Spacer(modifier = Modifier.width(13.dp))

			Column {
				Card(
					modifier = Modifier
                            .width(93.dp)
                            .height(16.dp),
					//.padding(start = 25.dp),
					backgroundColor = AssecoBlue,
					shape = RoundedCornerShape(
						topStart = 15.dp,
						topEnd = 15.dp,
						bottomStart = 0.dp,
						bottomEnd = 0.dp
					)
				) {}
				Card(
					modifier = Modifier
                            .width(93.dp)
                            .height(74.dp),
					//.padding(start = 25.dp),
					backgroundColor = Color.White,
					shape = RoundedCornerShape(
						topStart = 0.dp,
						topEnd = 0.dp,
						bottomStart = 15.dp,
						bottomEnd = 15.dp
					)
				)
				{
					Column {
						Spacer(modifier = Modifier.height(10.dp))

						LabelText(
							text = offer.dateStart.dayOfMonth.toString(),
							fontSize = 28.sp,
							modifier = Modifier.align(CenterHorizontally)
						)
						LabelText(
							text = offer.dateStart.month.toString() + " " + offer.dateStart.year.toString(),
							fontSize = 12.sp,
							modifier = Modifier.align(CenterHorizontally),
							color = DarkGray
						)
					}
				}
			}

			Spacer(modifier = Modifier.width(52.dp))

			Column {
				Spacer(modifier = Modifier.height(45.dp))

				Divider(
					thickness = 1.dp,
					color = Color.Gray,
					modifier = Modifier.width(50.dp)
				)
			}

			Spacer(modifier = Modifier.width(52.dp))

			Column {
				Card(
					modifier = Modifier
                            .width(93.dp)
                            .height(16.dp),
					backgroundColor = Orange,
					shape = RoundedCornerShape(
						topStart = 15.dp,
						topEnd = 15.dp,
						bottomStart = 0.dp,
						bottomEnd = 0.dp
					)
				) {}
				Card(
					modifier = Modifier
                            .width(93.dp)
                            .height(74.dp),
					backgroundColor = Color.White,
					shape = RoundedCornerShape(
						topStart = 0.dp,
						topEnd = 0.dp,
						bottomStart = 15.dp,
						bottomEnd = 15.dp
					)
				)
				{
					Column {
						Spacer(modifier = Modifier.height(10.dp))

						LabelText(
							text = offer.dateEnd.dayOfMonth.toString(),
							fontSize = 28.sp,
							modifier = Modifier.align(CenterHorizontally)
						)
						LabelText(
							text = offer.dateEnd.month.toString() + " " + offer.dateEnd.year.toString(),
							fontSize = 12.sp,
							modifier = Modifier.align(CenterHorizontally),
							color = DarkGray
						)
					}
				}
			}
		}

		Spacer(modifier = Modifier.weight(1f))

		BlueButton(
			label = stringResource(id = R.string.reserve_parking_space_screen_seeker_button_label),
			color = Orange,
			onClick = { onReserveParkingSpaceClicked(offer) }
		)

	}

}