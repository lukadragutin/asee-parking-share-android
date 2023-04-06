package hr.asee.android.template.compose.ui.prelogin.onboarding.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hr.asee.android.template.compose.R

@Composable
fun OnboardingNextButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onNextClicked: () -> Unit
) {
    AnimatedVisibility(visible = isVisible) {
        Row(modifier = modifier) {
            Button(modifier = Modifier.weight(1f), onClick = onNextClicked) {
                Text(text = stringResource(id = R.string.onboarding_screen_next_button_label))
            }
        }
    }
}
