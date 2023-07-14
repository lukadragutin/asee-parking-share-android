package hr.asee.android.template.compose.ui.postlogin.createoffer

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.common.model.state.ParkingSpacePickerState
import hr.asee.android.template.compose.ui.postlogin.home.model.HomeMessages
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.usecase.DateSelectUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CreateOfferViewModel @Inject constructor(
	private val dateSelectUseCase: DateSelectUseCase
) : BaseViewModel() {

	private val _parkingSpacesState = MutableStateFlow(setOf<ParkingSpace>())
	val parkingSpacesState: StateFlow<Set<ParkingSpace>> = _parkingSpacesState

	private val _userState: MutableStateFlow<User> = MutableStateFlow(User.EMPTY)
	val userState: StateFlow<User> = _userState

	private val _datePickerState = MutableStateFlow(DatePickerState())
	val datePickerState: StateFlow<DatePickerState> = _datePickerState

	private val _parkingSpacePickerSpace = MutableStateFlow(ParkingSpacePickerState(
		ParkingSpace.EMPTY,
		parkingSpacesState.value.toList()
	))
	val parkingSpacePickerState: StateFlow<ParkingSpacePickerState> = _parkingSpacePickerSpace

	fun onDateStartSelect() {
		_datePickerState.update { it.copy(startFocused = true, endFocused = false) }
	}

	fun onDateEndSelect() {
		_datePickerState.update { it.copy(startFocused = false, endFocused = true) }
	}

	fun onDateSelect(newDate: LocalDateTime) {
		_datePickerState.update {
			if (it.startFocused) {
				it.copy(dateStart = newDate)
			} else if (it.endFocused) {
				it.copy(dateEnd = newDate)
			} else it.copy()
		}
	}

	fun onCreateClicked(): Boolean {
		try {
			createClicked()
		} catch (exception: Exception) {
			return false
		}
		return true
	}

	private fun createClicked() {
		runSuspend { createOfferInternal() }
	}

	private suspend fun createOfferInternal() {
		dateSelectUseCase(DateSelectUseCase.Dates(dateStart = datePickerState.value.dateStart, dateEnd = datePickerState.value.dateEnd)).onFinished(
			successCallback = this::onCreateOfferSuccess,
			errorCallback = this::onCreateOfferError
		)
	}

	private fun onCreateOfferSuccess() {
		_datePickerState.update {
			it.copy(
				dateStartSelected = it.dateStart,
				dateEndSelected = it.dateEnd,
				startFocused = false,
				endFocused = false
			)
		}

		goBack()
	}

	private fun onCreateOfferError(errorData: ErrorData) {
		when (errorData.errorType) {
			DateSelectUseCase.DateSelectError.INVALID_DATES_ERROR -> showError(HomeMessages.INVALID_DATES_ERROR)
			DateSelectUseCase.DateSelectError.DATES_NOT_SELECTED_ERROR -> showError(HomeMessages.DATES_NOT_SELECTED_ERROR)
			else -> showError(CommonMessages.UNEXPECTED_ERROR)
		}
	}

	fun onCancelClicked() {
		_datePickerState.update {
			it.copy(
				dateStart = null,
				dateEnd = null,
				dateStartSelected = null,
				dateEndSelected = null,
				startFocused = false,
				endFocused = false
			)
		}
		goBack()
	}

	fun onRadioButtonClicked(parkingSpace: ParkingSpace) {
		_parkingSpacePickerSpace.update {
			it.copy(
				selectedOption = parkingSpace
			)
		}
	}
}