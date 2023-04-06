package hr.asee.android.template.compose.ui.prelogin.onboarding.extensions

import hr.asee.android.template.compose.R
import hr.asee.android.template.domain.model.OnboardingType

fun OnboardingType.getDrawableResource(): Int {
    return when (this) {
        OnboardingType.APPLICATION -> R.drawable.img_android_logo
        OnboardingType.JETPACK_COMPOSE -> R.drawable.img_jetpack_compose
    }
}

fun OnboardingType.getContentDescription(): Int {
    return when (this) {
        OnboardingType.APPLICATION -> R.string.onboarding_application_icon_content_description
        OnboardingType.JETPACK_COMPOSE -> R.string.onboarding_jetpack_compose_icon_content_description
    }
}
