package hr.asee.android.template.compose.ui.prelogin.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import hr.asee.android.template.compose.ui.prelogin.onboarding.extensions.getContentDescription
import hr.asee.android.template.compose.ui.prelogin.onboarding.extensions.getDrawableResource
import hr.asee.android.template.domain.model.OnboardingItem

@Composable
fun OnboardingPagerItem(
    modifier: Modifier = Modifier,
    item: OnboardingItem
) {
    ConstraintLayout(modifier = modifier) {

        val (title, message, image) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            text = item.title,
            style = MaterialTheme.typography.h1.copy(textAlign = TextAlign.Center),
        )

        Text(
            modifier = Modifier.constrainAs(message) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(title.bottom, margin = 25.dp)
            },
            text = item.message,
            textAlign = TextAlign.Center,
        )

        Image(
            modifier = Modifier.constrainAs(image) {
                start.linkTo(parent.start)
                top.linkTo(message.bottom)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            painter = painterResource(id = item.onboardingType.getDrawableResource()),
            contentDescription = stringResource(id = item.onboardingType.getContentDescription())
        )
    }
}
