package hr.asee.android.template.compose.ui.main

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.delegate.NavigationDelegate
import hr.asee.android.template.compose.delegate.event.NavBarEvent
import hr.asee.android.template.compose.delegate.event.NavigationEvent
import hr.asee.android.template.compose.navigation.directions.PostLoginDirection
import hr.asee.android.template.compose.navigation.directions.PreLoginDirection
import hr.asee.android.template.compose.ui.common.component.BottomNavigationBar
import hr.asee.android.template.compose.ui.postlogin.postLoginNavGraph
import hr.asee.android.template.compose.ui.prelogin.preLoginNavGraph

@Composable
fun ComposeTemplateRoot(
    viewModel: MainViewModel = hiltViewModel(),
    navigationDelegate: NavigationDelegate,
//    bottomNavBarDelegate: BottomNavBarDelegate,
) {

    val bottomNavBarState by viewModel.bottomNavBarState.collectAsState()

    val navController: NavHostController = rememberNavController()

    Navigator(
        navController = navController,
        navigationDelegate = navigationDelegate,
        onShowBottomNavBar = viewModel::showBottomNavBar,
    )

//    LaunchedEffect(bottomNavBarDelegate) {
//        bottomNavBarDelegate.getNavBarEvents().collect { event ->
//            when (event) {
//                NavBarEvent.HideNavBar -> viewModel.hideBottomNavBar()
//                NavBarEvent.ShowNavBar -> viewModel.showBottomNavBar()
//            }
//        }
//    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            AnimatedVisibility(
//                visible = bottomNavBarState.isVisible,
//                enter = slideInVertically(initialOffsetY = { it }),
//                exit = slideOutVertically(targetOffsetY = { it })
//            ) {
//                BottomNavigationBar(
//                    items = bottomNavBarState.items,
//                    onNavElementClicked = bottomNavBarState.onElementClicked,
//                    selectedElement = bottomNavBarState.selectedItem,
//                )
//            }
//        },
        backgroundColor = MaterialTheme.colors.background,
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
            NavHost(navController = navController, startDestination = PreLoginDirection.Root.destination) {
                preLoginNavGraph()
                postLoginNavGraph()
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

