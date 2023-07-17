package hr.asee.android.template.compose.ui.postlogin.parking_manager

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.ui.common.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.component.model.AlertDialogState
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ParkingManagerViewModel @Inject constructor(
	private val getAllBottomNavItemsUseCase: GetAllBottomNavItemsUseCase,
	val bottomNavBarDelegate: BottomNavBarDelegate
) : BaseViewModel() {

	init {
		runSuspend {
			getAllBottomNavItemsUseCase().onSuccess { items -> _bottomNavBarState.update { it.copy(items = items, selectedItem = items[1]) } }
		}
	}

	private val _filterState = MutableStateFlow(DatePickerState())
	val filterState: StateFlow<DatePickerState> = _filterState

	private val _reservationDialogState = MutableStateFlow(AlertDialogState())
	val reservationDialogState: StateFlow<AlertDialogState> = _reservationDialogState

	private val _offerDialogState = MutableStateFlow(AlertDialogState())
	val offerDialogState: StateFlow<AlertDialogState> = _offerDialogState

	private val _requestDialogState = MutableStateFlow(AlertDialogState())
	val requestDialogState: StateFlow<AlertDialogState> = _requestDialogState

	fun onDateStartSelect() {
		_filterState.update { it.copy(startFocused = true, endFocused = false) }
	}

	fun onDateEndSelect() {
		_filterState.update { it.copy(startFocused = false, endFocused = true) }
	}

	fun onDateSelect(newDate: LocalDateTime) {
		_filterState.update {
			if (it.startFocused) {
				it.copy(dateStart = newDate)
			} else if (it.endFocused) {
				it.copy(dateEnd = newDate)
			} else it.copy()
		}
	}

	fun onCancelReservationClicked() {
		_reservationDialogState.update { it.copy(isVisible = true) }
	}

	fun onCancelClickedReservationCard() {
		_reservationDialogState.update { it.copy(isVisible = false) }
	}

	fun onCancelClickedRequestCard() {
		_requestDialogState.update { it.copy(isVisible = false) }
	}

	fun onCancelClickedOfferCard() {
		_offerDialogState.update { it.copy(isVisible = false) }
	}

	fun onForwardClickedRequestsCard() {
		_requestDialogState.update { it.copy(isVisible = true) }
	}

	fun onGiverOfferClicked() {
		router.navigateToParkingOfferScreen()
	}

	fun onRemoveOfferClicked() {
		_offerDialogState.update { it.copy(isVisible = true) }
	}

	fun onBackClicked() {
		router.navigateToParkingManagerScreen()
	}

	fun onForwardClickedSeekingCard() {
		router.navigateToSeekingRequestScreen()
	}

	fun onForwardClickedReservationsCard() {
		router.navigateToReserveParkingSpaceScreen()
	}

	fun onCancelClicked() {
		router.navigateToParkingManagerScreen()
	}
}