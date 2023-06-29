package hr.asee.android.template.compose.ui.prelogin.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.prelogin.onboarding.extensions.getContentDescription
import hr.asee.android.template.compose.ui.prelogin.onboarding.extensions.getDrawableResource
import hr.asee.android.template.domain.model.OnboardingItem

@Composable
fun OnboardingPagerItem(
    modifier: Modifier = Modifier,
    item: OnboardingItem
) {
    ConstraintLayout(modifier = modifier) {

        val (image, title, message) = createRefs()

        Image(
            modifier = Modifier
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .width(430.dp),
            painter = painterResource(id = item.onboardingType.getDrawableResource()),
            contentDescription = stringResource(id = item.onboardingType.getContentDescription())
        )

        LabelText(
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(image.bottom)
                    end.linkTo(parent.end)
                },
            text = item.title,
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )

        LabelText(
            modifier = Modifier.constrainAs(message) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(title.bottom, margin = 15.dp)
                bottom.linkTo(parent.bottom)
            },
            text = item.message,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = MaterialTheme.colors.secondary
        )
    }
}
