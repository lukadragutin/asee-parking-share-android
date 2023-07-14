package hr.asee.android.template.compose.ui.postlogin.parkingspaces.contents.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.button.GrayButton
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray
import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.service.ParkingSpace

@Composable
fun ParkingSpacesList(
    parkingSpaces: Set<ParkingSpace>,
    user: User,
    onParkingSpacesClicked: (Int) -> Unit
) {

    var numShown = 0

    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        parkingSpaces.forEach() { parkingSpace ->

                if (user is Giver && parkingSpace.owner.id == user.id) {
                    GrayButton(onClick = { onParkingSpacesClicked(parkingSpace.id) }, label = "Parking " + parkingSpace.location)

                    numShown++
                }
        }

        if (numShown == 0) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.parking_spaces_screen_no_parking_spaces_label),
                fontFamily = Geomanist,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = LightGray
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}