package hr.asee.android.template.compose.ui.main

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import hr.asee.android.template.compose.delegate.NavigationDelegate
import hr.asee.android.template.compose.delegate.event.NavigationEvent
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection
import hr.asee.android.template.compose.ui.common.model.state.UiState
import hr.asee.android.template.compose.ui.postlogin.postLoginNavGraph
import hr.asee.android.template.compose.ui.prelogin.preLoginNavGraph

@Composable
fun ComposeTemplateRoot(
	viewModel: MainViewModel = hiltViewModel(),
	navigationDelegate: NavigationDelegate,
) {

	viewModel.initialize()

	val navController: NavHostController = rememberNavController()
	val uiState by viewModel.uiState.collectAsState()
	val startingNavigationDestinationState by viewModel.startingNavigationDestinationState.collectAsState()

	Navigator(
		navController = navController,
		navigationDelegate = navigationDelegate,
		onShowBottomNavBar = viewModel::showBottomNavBar,
	)

	if (uiState is UiState.Success) {

		Scaffold(
			modifier = Modifier.fillMaxSize(),
			backgroundColor = MaterialTheme.colors.background,
		) { paddingValues ->
			Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
				NavHost(navController = navController, startDestination = startingNavigationDestinationState.root) {
					preLoginNavGraph(startingNavigationDestinationState.destination)
					postLoginNavGraph()
				}
			}
		}
	}
}

@Composable
fun Navigator(
	navController: NavHostController,
	navigationDelegate: NavigationDelegate,
	onShowBottomNavBar: () -> Unit,
) {
	val onBackDispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
	LaunchedEffect(navController) {
		navigationDelegate.getNavigationEvents().collect { event ->
			when (event) {
				is NavigationEvent.NavigateBack -> {
					onBackDispatcher.onBackPressed()
				}

				is NavigationEvent.Directions -> {
					if (event.navigationCommand == PostLoginDirection.Root) {
						onShowBottomNavBar()
					}
					if (navController.currentBackStackEntry?.destination?.route != event.navigationCommand.destination) {
						navController.navigate(event.navigationCommand.destination, event.navigationBuilder)
					}
				}

				is NavigationEvent.None -> {
					// NO-OP
				}
			}
		}
	}
}

