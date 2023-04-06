package hr.asee.android.template.compose.ui.postlogin.users

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.UiState

@Composable
fun UsersScreen(viewModel: UsersViewModel = hiltViewModel()) {

	val uiState by viewModel.uiState.collectAsState()

	UsersScreenRootContent(
		uiState = uiState,
	)
}

@Composable
fun UsersScreenRootContent(
	uiState: UiState,
) {
	DefaultScreenLayout(screenTitle = "Some screen title that should be placed in resources") {
		when (uiState) {
			UiState.Loading -> Text(text = "Loading content", color = MaterialTheme.colors.onBackground)
			UiState.Success -> Text(text = "Success content", color = MaterialTheme.colors.onBackground)
			is UiState.Error -> Text(text = "Error content", color = MaterialTheme.colors.onBackground)
			is UiState.Info, UiState.None -> {
				// NO-OP
			}
		}
	}
}
