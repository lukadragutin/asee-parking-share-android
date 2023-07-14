package hr.asee.android.template.compose.ui.postlogin.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.asee.android.template.compose.R
import hr.asee.android.template.compose.config.Config
import hr.asee.android.template.compose.delegate.event.NavBarEvent
import hr.asee.android.template.compose.ui.common.component.BottomNavigationBar
import hr.asee.android.template.compose.ui.common.component.LabelText
import hr.asee.android.template.compose.ui.common.component.button.BlueButton
import hr.asee.android.template.compose.ui.common.component.button.CancelButton
import hr.asee.android.template.compose.ui.common.component.button.ProceedButton
import hr.asee.android.template.compose.ui.common.component.dialog.BaseAlertDialog
import hr.asee.android.template.compose.ui.common.component.dialog.ScreenStateDialog
import hr.asee.android.template.compose.ui.common.component.icon.FilterIcon
import hr.asee.android.template.compose.ui.common.component.icon.SettingsIcon
import hr.asee.android.template.compose.ui.common.layout.DefaultScreenLayout
import hr.asee.android.template.compose.ui.common.model.state.AccountState
import hr.asee.android.template.compose.ui.common.model.state.AlertDialogState
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.home.contents.FilterPopupScreen
import hr.asee.android.template.compose.ui.postlogin.home.contents.ProfilePicture
import hr.asee.android.template.compose.ui.postlogin.home.contents.list.OfferList
import hr.asee.android.template.compose.ui.postlogin.home.contents.list.ReservationList
import hr.asee.android.template.compose.ui.postlogin.home.contents.list.SeekingList
import hr.asee.android.template.compose.ui.theme.AndroidComposeCodingTemplateTheme
import hr.asee.android.template.compose.ui.theme.AssecoBlue
import hr.asee.android.template.compose.ui.theme.Geomanist
import hr.asee.android.template.compose.ui.theme.LightGray
import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.Seeker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

	val filterState by viewModel.filterState.collectAsState()
	val cancelReservationDialogState by viewModel.cancelReservationDialogState.collectAsState()
	val removeOfferDialogState by viewModel.removeOfferDialogState.collectAsState()
	val bottomNavBarState by viewModel.bottomNavBarState.collectAsState()
	val uiState by viewModel.uiState.collectAsState()
	val accountState by viewModel.accountState.collectAsState()

	val scope = rememberCoroutineScope()
	val sheetState = rememberModalBottomSheetState(
		initialValue = ModalBottomSheetValue.Hidden,
		confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
		skipHalfExpanded = true
	)

	BackHandler(sheetState.isVisible) {
		scope.launch {
			sheetState.hide()
		}
	}

	AndroidComposeCodingTemplateTheme(
		darkTheme = ((if (Config.DARK_THEME == null) isSystemInDarkTheme() else Config.DARK_THEME) == true)
	) {

		ModalBottomSheetLayout(
			sheetContent = {
				FilterPopupScreen(
					scope = scope,
					sheetState = sheetState,
					filterState = filterState,
					onFilterClicked = viewModel::onFilterClicked,
					onCancelClicked = viewModel::onCancelClicked,
					onResetClicked = viewModel::onResetClicked,
					onDateStartSelect = viewModel::onDateStartSelect,
					onDateEndSelect = viewModel::onDateEndSelect,
					onDateSelect = viewModel::onDateSelect
				)
			},
			sheetBackgroundColor = MaterialTheme.colors.background,
			sheetState = sheetState,
			sheetShape = RoundedCornerShape(topStartPercent = 8, topEndPercent = 8),
			modifier = Modifier
					.fillMaxSize()
		) {

			LaunchedEffect(viewModel.bottomNavBarDelegate) {
				viewModel.bottomNavBarDelegate.getNavBarEvents().collect { event ->
					when (event) {
						NavBarEvent.HideNavBar -> viewModel.hideBottomNavBar()
						NavBarEvent.ShowNavBar -> viewModel.showBottomNavBar()
					}
				}
			}

			Scaffold(
				bottomBar = {
					AnimatedVisibility(
						visible = bottomNavBarState.isVisible,
						enter = slideInVertically(initialOffsetY = { it }),
						exit = slideOutVertically(targetOffsetY = { it })
					) {
						BottomNavigationBar(
							items = bottomNavBarState.items,
							onNavElementClicked = bottomNavBarState.onElementClicked,
							selectedElement = bottomNavBarState.selectedItem,
						)
					}
				},
			) { paddingValues ->
				HomeScreenContent(
					modifier = Modifier.padding(paddingValues),
					scope = scope,
					sheetState = sheetState,
					filterState = filterState,
					cancelReservationDialogState = cancelReservationDialogState,
					removeOfferDialogState = removeOfferDialogState,
					accountState = accountState,
					viewModel = viewModel
				)

			}
		}
	}

	ScreenStateDialog(state = uiState, onDismiss = viewModel::onMessageDismissed)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(
	modifier: Modifier = Modifier,
	scope: CoroutineScope,
	sheetState: ModalBottomSheetState,
	filterState: DatePickerState,
	cancelReservationDialogState: AlertDialogState,
	removeOfferDialogState: AlertDialogState,
	accountState: AccountState,
	viewModel: HomeViewModel
) {

	val noContent = derivedStateOf{ accountState.seekings.size + accountState.reservations.size + accountState.offers.size == 0 }

	if (cancelReservationDialogState.isVisible) {
		BaseAlertDialog(
			modifier = modifier,
			isLoading = false,
			title = stringResource(id = R.string.home_screen_cancel_reservation_popup_screen_title_label),
			message = stringResource(id = R.string.home_screen_cancel_reservation_popup_screen_question_label),
			buttonsLayout = {
				Row(modifier = Modifier.padding(bottom = 8.dp)) {
					Spacer(modifier = Modifier.width(30.dp))
					ProceedButton(onClick = viewModel::onCancelReservationClicked)
					Spacer(modifier = Modifier.width(45.dp))
					CancelButton(onClick = viewModel::onCancelClickedReservationCard)
				}
			},
			onDismissRequest = viewModel::onCancelReservationClicked
		)
	}

	if (removeOfferDialogState.isVisible) {
		BaseAlertDialog(
			modifier = modifier,
			isLoading = false,
			title = stringResource(id = R.string.home_screen_remove_offer_popup_screen_title_label),
			message = stringResource(id = R.string.home_screen_remove_offer_popup_screen_question_label),
			buttonsLayout = {
				Row(modifier = Modifier.padding(bottom = 8.dp)) {
					Spacer(modifier = Modifier.width(30.dp))
					ProceedButton(onClick = viewModel::onRemoveOfferClicked)
					Spacer(modifier = Modifier.width(45.dp))
					CancelButton(onClick = viewModel::onCancelClickedOfferCard)
				}
			},
			onDismissRequest = viewModel::onRemoveOfferClicked
		)
	}

	Column(
		modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
	) {
		Row(horizontalArrangement = Arrangement.SpaceBetween) {
			ProfilePicture(onClicked = viewModel::onProfilePictureClicked)

			Spacer(modifier = modifier.width(23.dp))

			Column {
				Text(
					text = buildAnnotatedString {
						withStyle(
							SpanStyle(
								fontFamily = Geomanist,
								fontWeight = FontWeight.Bold,
								fontSize = 16.sp,
								color = MaterialTheme.colors.onSurface
							)
						) {
							append(stringResource(id = R.string.home_screen_welcome_label))
						}
						append(" ")
						withStyle(
							SpanStyle(
								fontFamily = Geomanist,
								fontWeight = FontWeight.Bold,
								fontSize = 16.sp,
								color = AssecoBlue
							)
						) {
							append(accountState.user!!.firstName)
						}
					},
				)

				LabelText(
					text = stringResource(
						id = if (accountState.user is Seeker) R.string.home_screen_seeker_role_label
						else R.string.home_screen_giver_role_label),
					fontSize = 14.sp,
					color = LightGray
				)

			}

			Spacer(modifier = modifier.weight(1f))

			SettingsIcon(
				modifier = Modifier.offset(x = 20.dp),
				tint = MaterialTheme.colors.onBackground,
				onSettingsClicked = viewModel::onSettingsClicked
			)

		}

		DefaultScreenLayout(
			screenTitle = stringResource(id = R.string.home_screen_title_label),
			contentPadding = PaddingValues(all = 0.dp),
			background = Color.Transparent,
			modifier = Modifier.fillMaxHeight()
		) {

			Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
				BlueButton(
					label = stringResource(
						id = if (accountState.user is Giver) R.string.home_screen_giver_offer_button_label
						else R.string.home_screen_seeker_seek_button_label,
					),
					onClick = {
						if (accountState.user is Seeker) viewModel.seekParking()
						else viewModel.offerParking()
					}
				)

				Column(
					verticalArrangement = Arrangement.spacedBy(15.dp),
					horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(state = rememberScrollState())
				) {

					if (noContent.value) {
						Spacer(modifier = Modifier.height(100.dp))

						LabelText(
							text = stringResource(id = R.string.home_screen_no_content_label),
							fontSize = 20.sp,
							color = LightGray
						)
					} else {
						Row(horizontalArrangement = Arrangement.SpaceBetween) {
							LabelText(
								text = stringResource(
									id = if (accountState.user is Giver) R.string.home_screen_giver_offer_list_label
									else R.string.home_screen_seeker_reservation_list_label
								),
								fontSize = 20.sp,
							)
							Spacer(modifier = Modifier.weight(1f))
							FilterIcon(
								isFiltered = (filterState.dateStartSelected != LocalDateTime.MIN) ||
										(filterState.dateEndSelected != LocalDateTime.MAX),
								tint = MaterialTheme.colors.onBackground,
								modifier = Modifier.offset(x = 20.dp),
								onFilterClicked = {
									scope.launch {
										if (sheetState.isVisible)
											sheetState.hide()
										else sheetState.animateTo(ModalBottomSheetValue.Expanded)
									}
								}
							)
						}

						if (accountState.user is Giver) {
							OfferList(
								offers = accountState.offers,
								user = accountState.user,
								filterState = filterState,
								onGiverOfferClicked = viewModel::onGiverOfferClicked,
								onSeekerOfferClicked = viewModel::onSeekerOfferClicked,
								onRemoveOfferClicked = viewModel::onRemoveOfferClicked
							)
						} else {
							ReservationList(
								reservations = accountState.reservations,
								user = accountState.user,
								filterState = filterState,
								onGiverReservationClicked = viewModel::onGiverReservationClicked,
								onSeekerReservationClicked = viewModel::onSeekerReservationClicked,
								onCancelReservationClicked = viewModel::onCancelReservationClicked
							)
						}

						LabelText(
							text = stringResource(
								id = if (accountState.user is Giver) R.string.home_screen_giver_reservation_list_label
								else R.string.home_screen_seeker_offer_list_label
							),
							fontSize = 20.sp,
							modifier = Modifier.align(Alignment.Start)
						)

						if (accountState.user is Giver) {
							ReservationList(
								reservations = accountState.reservations,
								user = accountState.user,
								filterState = filterState,
								onGiverReservationClicked = viewModel::onGiverReservationClicked,
								onSeekerReservationClicked = viewModel::onSeekerReservationClicked,
								onCancelReservationClicked = viewModel::onCancelReservationClicked
							)
						} else {
							OfferList(
								offers = accountState.offers,
								user = accountState.user,
								filterState = filterState,
								onGiverOfferClicked = viewModel::onGiverOfferClicked,
								onSeekerOfferClicked = viewModel::onSeekerOfferClicked,
								onRemoveOfferClicked = viewModel::onRemoveOfferClicked
							)
						}

						LabelText(
							text = stringResource(id = R.string.home_screen_seeking_list_label),
							fontSize = 20.sp,
							modifier = Modifier.align(Alignment.Start)
						)

						SeekingList(
							seekings = accountState.seekings,
							filterState = filterState,
							onSeekingClicked = viewModel::onSeekingClicked
						)
					}
				}
			}

		}
	}

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
	HomeScreen()
}