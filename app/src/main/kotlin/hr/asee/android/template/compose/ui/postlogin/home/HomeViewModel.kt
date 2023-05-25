package hr.asee.android.template.compose.ui.postlogin.home

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.common.service.Reservation
import hr.asee.android.template.compose.ui.postlogin.home.model.HomeMessages
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import hr.asee.android.template.domain.usecase.FilterByDateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val filterByDateUseCase: FilterByDateUseCase,
    private val getAllBottomNavItemsUseCase: GetAllBottomNavItemsUseCase,
    val bottomNavBarDelegate: BottomNavBarDelegate
) : BaseViewModel() {

    init {
        runSuspend { getAllBottomNavItemsUseCase().onSuccess { items -> _bottomNavBarState.update { it.copy(items = items, selectedItem = it.items.firstOrNull()) } } }
    }

    private val _filterState = MutableStateFlow(DatePickerState())
    val filterState: StateFlow<DatePickerState> = _filterState

    fun onDateStartSelect() {
        _filterState.update { it.copy(startFocused = true, endFocused = false) }
    }

    fun onDateEndSelect() {
        _filterState.update { it.copy(startFocused = false, endFocused = true) }
    }

    fun onCancelClicked() {
        _filterState.update { it.copy(dateStart = null, dateEnd = null, startFocused = false, endFocused = false) }
    }

    fun onResetClicked() {
        _filterState.update { it.copy(
            dateStart = null,
            dateEnd = null,
            startFocused = false,
            endFocused = false,
            dateStartSelected = LocalDate.MIN,
            dateEndSelected = LocalDate.MAX
        ) }
    }

    fun onDateSelect(newDate: LocalDate) {
        _filterState.update {
            if (it.startFocused) {
                it.copy(dateStart = newDate)
            }
            else if (it.endFocused) {
                it.copy(dateEnd = newDate)
            }
            else it.copy()
        }
    }

    fun onFilterClicked(): Boolean {
        try {
            filterClicked() }
        catch (exception: Exception) {
            return false
        }
        return true
    }

    private fun filterClicked() {
        runSuspend { filterInternal() }
    }

    private suspend fun filterInternal() {
        filterByDateUseCase(FilterByDateUseCase.Filter(dateStart = filterState.value.dateStart, dateEnd = filterState.value.dateEnd)).onFinished(
            successCallback = this::onFilterSuccess,
            errorCallback = this::onFilterError
        )
    }

    private fun onFilterSuccess() {
        _filterState.update {
            it.copy(
                dateStartSelected = it.dateStart,
                dateEndSelected = it.dateEnd,
                startFocused = false,
                endFocused = false
            )
        }
    }

    private fun onFilterError(errorData: ErrorData) {
        when (errorData.errorType) {
            FilterByDateUseCase.FilterByDateError.INVALID_DATES_ERROR -> showError(HomeMessages.INVALID_DATES_ERROR)
            FilterByDateUseCase.FilterByDateError.DATES_NOT_SELECTED_ERROR -> showError(HomeMessages.DATES_NOT_SELECTED_ERROR)
            else -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    fun onCancelReservationClicked(reservation: Reservation) {
        reservation.cancellationPending = true
    }

    fun onRemoveOfferClicked() {
        /* TODO */
    }

    fun onGiverReservationClicked() {
        /* TODO */
    }

    fun onSeekerReservationClicked() {
        /* TODO */
    }

    fun onGiverOfferClicked() {
        /* TODO */
    }

    fun onSeekerOfferClicked() {
        /* TODO */
    }

    fun onSeekingClicked() {
        /* TODO */
    }

    fun offerParking() {
        /* TODO */
    }

    fun seekParking() {
        /* TODO */
    }

    fun onSettingsClicked() {
        /* TODO */
    }

    fun onProfilePictureClicked() {
        /* TODO */
    }
}