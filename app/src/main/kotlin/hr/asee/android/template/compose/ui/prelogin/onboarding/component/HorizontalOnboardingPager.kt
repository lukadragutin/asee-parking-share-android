package hr.asee.android.template.compose.ui.prelogin.onboarding.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import hr.asee.android.template.domain.model.OnboardingItem

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalOnboardingPager(
    modifier: Modifier = Modifier,
    onboardingItems: List<OnboardingItem>,
    pagerState: PagerState,
    contentPadding: Dp
) {
    HorizontalPager(
        count = onboardingItems.size,
        state = pagerState,
        modifier = modifier,
    ) { pageNumber ->
        OnboardingPagerItem(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            item = onboardingItems[pageNumber]
        )
    }
}
