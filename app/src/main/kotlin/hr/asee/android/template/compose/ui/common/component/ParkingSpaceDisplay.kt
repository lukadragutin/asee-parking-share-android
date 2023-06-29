package hr.asee.android.template.compose.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
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
                modifier = Modifier.size(100.dp * size)
            )

            Spacer(modifier = Modifier.height(18.dp * size))

            LabelText(text = parkingSpace.location, fontSize = 32.sp * size)

            Spacer(modifier = Modifier.height(10.dp * size))

            LabelText(
                text = stringResource(id = R.string.parking_space_display_parking_number_label), 
                fontSize = 18.sp * size,
                color = MaterialTheme.colors.secondary
            )
        }
        
    }

}