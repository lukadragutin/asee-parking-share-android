package hr.asee.android.template.compose.ui.prelogin.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config.DARK_THEME
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.prelogin.onboarding.component.HorizontalOnboardingPager
import hr.asee.android.template.compose.ui.prelogin.onboarding.component.OnboardingButton
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.BackgroundGray
import hr.asee.android.template.compose.ui.theme.Gray
import hr.asee.android.template.domain.model.OnboardingItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(viewModel: OnboardingViewModel = hiltViewModel()) {

    AndroidComposeCodingTemplateTheme(darkTheme = (if (DARK_THEME == null) isSystemInDarkTheme() else DARK_THEME) as Boolean) {

        val onboardingItems by viewModel.onboardingItems.collectAsState()

        OnboardingContent(
            onboardingItems = onboardingItems,
            onNextClicked = viewModel::onNextClicked,
            onRegisterClicked = viewModel::navigateToRegister,
            onLoginClicked = viewModel::navigateToLogin
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingContent(
    onboardingItems: List<OnboardingItem>,
    onNextClicked: (PagerState) -> Unit,
    onRegisterClicked: () -> Unit,
    onLoginClicked: () -> Unit
) {
    val pagerState = rememberPagerState()
    val screenEdgePadding = dimensionResource(id = R.dimen.screen_edge_padding)
    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(
            if (pagerState.currentPage == pagerState.pageCount - 2) 100.dp
            else 50.dp
        ))

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
            activeColor = AssecoBlue,
            inactiveColor = Gray
        )

        OnboardingButton(
            text = stringResource(id = R.string.onboarding_screen_next_button_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = screenEdgePadding,
                    end = screenEdgePadding,
                    bottom = screenEdgePadding
                ),
            isVisible = pagerState.currentPage == pagerState.pageCount - 2,
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        )

        OnboardingButton(
            text = stringResource(id = R.string.onboarding_screen_register_button_label),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = screenEdgePadding,
                    end = screenEdgePadding,
                    bottom = screenEdgePadding
                ),
            isVisible = pagerState.currentPage == pagerState.pageCount - 1,
            onClick = onRegisterClicked
        )

        AnimatedVisibility(visible = pagerState.currentPage == pagerState.pageCount - 1) {
            Column() {

                LabelText(
                    text = stringResource(id = R.string.onboarding_screen_login_button_label),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clickable(onClick = onLoginClicked),
                    textDecoration = TextDecoration.Underline
                )

                Spacer(modifier = Modifier.height(30.dp))

            }
        }
    }
}
