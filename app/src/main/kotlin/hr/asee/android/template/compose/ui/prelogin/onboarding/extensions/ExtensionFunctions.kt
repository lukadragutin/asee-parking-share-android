package hr.asee.android.template.compose.ui.prelogin.onboarding.extensions

import hr.asee.android.template.compose.R
import hr.asee.android.template.domain.model.OnboardingType

fun OnboardingType.getDrawableResource(): Int {
    return when (this) {
        OnboardingType.WELCOME -> R.mipmap.onboarding_welcome
        OnboardingType.CREATE_ACCOUNT -> R.mipmap.onboarding_create_account
    }
}

fun OnboardingType.getContentDescription(): Int {
    return when (this) {
        OnboardingType.WELCOME -> R.string.onboarding_welcome_image_content_description
        OnboardingType.CREATE_ACCOUNT -> R.string.onboarding_create_account_image_content_description
    }
}
