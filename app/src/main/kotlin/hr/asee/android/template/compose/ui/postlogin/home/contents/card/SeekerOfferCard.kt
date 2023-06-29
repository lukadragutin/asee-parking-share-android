package hr.asee.android.template.compose.ui.postlogin.home.contents.card


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config.CARD_DATE_FORMAT
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.compose.ui.theme.DarkGray
import hr.asee.android.template.compose.ui.theme.Geomanist
import java.time.format.DateTimeFormatter


@Composable
fun SeekerOfferCard(
    offer: Offer,
    onOfferClicked: (Int) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOfferClicked(offer.id) },
        backgroundColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(15)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 15.dp, bottom = 8.dp, top = 8.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.parking_offer),
                contentDescription = stringResource(id = R.string.home_screen_seeker_offer_card_image_content_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(60.dp)
                    .width(51.dp)
            )

            Column(modifier = Modifier.padding(top = 8.dp)) {
                LabelText(
                    text = offer.parkingSpace.location,
                    fontSize = 16.sp
                )

                Row(horizontalArrangement = Arrangement.SpaceBetween) {

                    Column(modifier = Modifier.padding(vertical = 8.dp)) {

                        LabelText(
                            text = stringResource(id = R.string.home_screen_seeker_offer_card_start_date_label),
                            color = DarkGray,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                        LabelText(
                            text = stringResource(id = R.string.home_screen_seeker_offer_card_end_date_label),
                            color = DarkGray,
                            fontSize = 14.sp,
                        )
                    }

                    Spacer(modifier = Modifier.width(60.dp))

                    Column(horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        LabelText(
                            text = offer.dateStart.format(DateTimeFormatter.ofPattern(CARD_DATE_FORMAT)),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                        Text(
                            text = offer.dateEnd.format(DateTimeFormatter.ofPattern(CARD_DATE_FORMAT)),
                            fontFamily = Geomanist,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }

            Image(
                painter = painterResource(id = R.mipmap.forward),
                contentDescription = stringResource(id = R.string.home_screen_seeker_offer_card_forward_image_content_description),
                colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(color = Color.White) else null,
                modifier = Modifier
                    .height(35.dp)
                    .width(40.dp)
            )
        }
    }
}