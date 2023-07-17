package hr.asee.android.template.compose.ui.postlogin.parking_manager.contents.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.theme.LightGray
import hr.asee.android.template.domain.model.common.service.Seeking

@Composable
fun SeekerSeekingCard(
    seeking: Seeking,
    onForwardClickedSeekingCard: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(15)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 15.dp, bottom = 8.dp, top = 8.dp)
        ){
            Image(
                painter = painterResource(hr.asee.android.template.data.R.drawable.parking_seeking),
                contentDescription = stringResource(id = R.string.parking_manager_screen_seeking_card_image_content_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(60.dp)
                    .width(51.dp)
            )

            Spacer(modifier = Modifier.width(22.dp))

            Column(modifier = Modifier.padding(top = 8.dp)) {
                LabelText(
                    text = stringResource(id = R.string.parking_manager_screen_parking_seeking_request_label),
                    fontSize = 15.sp,
                    color = LightGray

                )
                Spacer(modifier = Modifier.height(8.dp))
                LabelText(
                    text = stringResource(R.string.parking_manager_screen_seeker_request_reservation_id_label) + " " + seeking.id,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier
                    .height(35.dp)
                    .width(40.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = false),
                        onClick = onForwardClickedSeekingCard
                    ),
                painter = painterResource(R.drawable.forward),
                contentDescription = stringResource(id = R.string.parking_manager_screen_seeker_seeking_card_forward_image_content_description),
            )
        }
    }
}