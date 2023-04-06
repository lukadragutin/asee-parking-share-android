package hr.asee.android.template.compose.ui.prelogin.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.ui.prelogin.onboarding.component.HorizontalOnboardingPager
import hr.asee.android.template.compose.ui.prelogin.onboarding.component.OnboardingNextButton
import hr.asee.android.template.domain.model.OnboardingItem

@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel = hiltViewModel()) {

    val onboardingItems by viewModel.onboardingItems.collectAsState()

    OnboardingContent(
        onboardingItems = onboardingItems,
        onNextClicked = viewModel::navigateToLogin,
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingContent(
    onboardingItems: List<OnboardingItem>,
    onNextClicked: () -> Unit,
) {
    val pagerState = rememberPagerState()
    val screenEdgePadding = dimensionResource(id = R.dimen.screen_edge_padding)

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalOnboardingPager(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            onboardingItems = onboardingItems,
            pagerState = pagerState,
            contentPadding = screenEdgePadding,
        )

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(screenEdgePadding),
        )

        OnboardingNextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = screenEdgePadding, end = screenEdgePadding, bottom = screenEdgePadding),
            isVisible = pagerState.currentPage == pagerState.pageCount - 1,
            onNextClicked = onNextClicked
        )
    }
}
