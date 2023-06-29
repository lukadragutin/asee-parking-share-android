package hr.asee.android.template.compose.ui.prelogin.onboarding.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingButton(
    text: String,
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(visible = isVisible) {
        Row(modifier = modifier) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                onClick = onClick
            ) {
                Text(text = text)
            }
        }
    }
}
