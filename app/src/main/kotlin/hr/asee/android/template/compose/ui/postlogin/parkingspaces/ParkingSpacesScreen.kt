package hr.asee.android.template.compose.ui.postlogin.parkingspaces

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.ui.common.component.button.BackButton
import hr.asee.android.template.compose.ui.common.component.button.PlusButton
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.postlogin.parkingspaces.contents.list.ParkingSpacesList
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.service.ParkingSpace

@Composable
fun ParkingSpacesScreen(viewModel: ParkingSpacesViewModel = hiltViewModel()) {

    val parkingSpaces by viewModel.parkingSpacesState.collectAsState()
    val user by viewModel.userState.collectAsState()

    AndroidComposeCodingTemplateTheme(
        darkTheme = (if (Config.DARK_THEME == null) isSystemInDarkTheme() else Config.DARK_THEME) as Boolean
    ) {

        Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween) {

                BackButton(viewModel::goBack)

                Spacer(modifier = Modifier.weight(1f))

                PlusButton(onClick = viewModel::onAddParkingSpaceClicked)
            }

            DefaultScreenLayout(
                screenTitle = stringResource(id = R.string.parking_spaces_screen_title_label),
                background = Color.Transparent,
                modifier = Modifier
                    .fillMaxHeight()
            ) {

                ParkingSpacesScreenContent(
                    parkingSpaces = parkingSpaces,
                    user = user,
                    onParkingSpaceClicked = viewModel::onParkingSpaceClicked
                )
            }
        }
    }

}

@Composable
fun ParkingSpacesScreenContent(
    parkingSpaces: Set<ParkingSpace>,
    user: User,
    onParkingSpaceClicked: (Int) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxWidth()
    ) {

        ParkingSpacesList(
            parkingSpaces = parkingSpaces,
            user = user,
            onParkingSpacesClicked = onParkingSpaceClicked
        )

    }
    
}

@Preview(showBackground = true)
@Composable
fun ParkingSpacesPreview() {
    ParkingSpacesScreen()
}