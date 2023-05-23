package hr.asee.android.template.compose.ui.postlogin.home.contents

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.theme.AssecoBlue

@Composable
fun ProfilePicture(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int = R.mipmap.stock_profile_picture,
    onClicked: () -> Unit
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = stringResource(id = R.string.home_screen_profile_picture_image_content_description),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(40.dp)
            .width(40.dp)
            .clip(CircleShape)
            .border(width = 1.dp, color = AssecoBlue, shape = CircleShape)
            .clickable { onClicked }
    )
}