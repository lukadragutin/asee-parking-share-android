package hr.asee.android.template.compose.ui.main.model

import hr.asee.android.template.compose.navigation.directions.PostLoginDirection
import hr.asee.android.template.compose.navigation.directions.PreLoginDirection

enum class StartDestination(val destination: String, val root: String) {

	ONBOARDING(PreLoginDirection.Onboarding.destination, PreLoginDirection.Root.destination),
	LOGIN(PreLoginDirection.Login.destination, PreLoginDirection.Root.destination),
	HOME(PostLoginDirection.Root.destination, PostLoginDirection.Root.destination),
	DEFAULT("", "")
}