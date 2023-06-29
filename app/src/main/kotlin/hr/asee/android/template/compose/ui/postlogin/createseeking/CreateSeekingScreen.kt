package hr.asee.android.template.compose.ui.postlogin.createseeking

import android.view.ContextThemeWrapper
import android.widget.CalendarView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.ui.common.component.DatePicker
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.common.component.button.BackButton
import hr.asee.android.template.compose.ui.common.component.button.BlueButton
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.prelogin.onboarding.component.OnboardingButton
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import hr.asee.android.template.domain.model.common.service.Seeking
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun CreateSeekingScreen(viewModel: CreateSeekingViewModel = hiltViewModel()){

    val datePickerState by viewModel.datePickerState.collectAsState()

    AndroidComposeCodingTemplateTheme(
        darkTheme = (if (Config.DARK_THEME == null) isSystemInDarkTheme() else Config.DARK_THEME) as Boolean
    ) {

        Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {

            Spacer(modifier = Modifier.height(10.dp))

            BackButton(onClick = viewModel::goBack)

            DefaultScreenLayout(
                screenTitle = stringResource(id = R.string.create_seeking_screen_title_label),
                background = Color.Transparent
            ) {
                CreateSeekingScreenContent(
                    onDateStartSelect = viewModel::onDateStartSelect,
                    onDateEndSelect = viewModel::onDateEndSelect,
                    onDateSelect = viewModel::onDateSelect,
                    onCreateClicked = viewModel::onCreateClicked,
                    onCancelClicked = viewModel::onCancelClicked,
                    datePickerState = datePickerState
                )
            }
        }
    }
}

@Composable
fun CreateSeekingScreenContent(
    datePickerState: DatePickerState,
    onDateStartSelect: () -> Unit,
    onDateEndSelect: () -> Unit,
    onDateSelect: (LocalDateTime) -> Unit,
    onCreateClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {
    val calendarView = CalendarView(
        ContextThemeWrapper(
            LocalContext.current,
            if (isSystemInDarkTheme()) R.style.CalendarViewCustomDark
            else R.style.CalendarViewCustomLight
        )
    )

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            shape = CircleShape
        ) {

            Image(
                painter = painterResource(id = R.mipmap.parking_offer),
                contentDescription = stringResource(id = R.string.create_seeking_screen_image_content_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            DatePicker(
                calendarView = calendarView,
                state = datePickerState,
                onDateStartSelect = onDateStartSelect,
                onDateEndSelect = onDateEndSelect,
                onDateSelect = onDateSelect,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }

        BlueButton(
            label = stringResource(id = R.string.onboarding_screen_register_button_label),
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onCreateClicked
        )

        LabelText(
            text = stringResource(id = R.string.onboarding_screen_login_button_label),
            fontSize = 16.sp,
            modifier = Modifier
                .clickable(onClick = onCancelClicked),
            textDecoration = TextDecoration.Underline
        )

        Spacer(modifier = Modifier.height(30.dp))

    }
}
