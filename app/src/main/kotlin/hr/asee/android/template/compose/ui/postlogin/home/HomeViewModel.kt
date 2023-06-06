package hr.asee.android.template.compose.ui.postlogin.home

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.config.Config.MAX_DATE
import hr.asee.android.template.compose.config.Config.MIN_DATE
import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.ui.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.common.model.state.AccountState
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.compose.ui.postlogin.home.model.HomeMessages
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.model.common.service.Seeking
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import hr.asee.android.template.domain.usecase.FilterByDateUseCase
import hr.asee.android.template.domain.usecase.GetAccountUseCase
import hr.asee.android.template.domain.usecase.GetOffersUseCase
import hr.asee.android.template.domain.usecase.GetParkingSpacesUseCase
import hr.asee.android.template.domain.usecase.GetReservationsUseCase
import hr.asee.android.template.domain.usecase.GetSeekingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val getSeekingsUseCase: GetSeekingsUseCase,
    private val getOffersUseCase: GetOffersUseCase,
    private val getReservationsUseCase: GetReservationsUseCase,
    private val getParkingSpacesUseCase: GetParkingSpacesUseCase,
    private val filterByDateUseCase: FilterByDateUseCase,
    private val getAllBottomNavItemsUseCase: GetAllBottomNavItemsUseCase,
    val bottomNavBarDelegate: BottomNavBarDelegate
) : BaseViewModel() {

    init {
        runSuspend {
            getAccount()
            getAllBottomNavItemsUseCase().onSuccess { items -> _bottomNavBarState.update { it.copy(items = items, selectedItem = it.items.firstOrNull()) } }
        }
    }

    private val _accountState = MutableStateFlow(AccountState())
    val accountState: StateFlow<AccountState> = _accountState

    private val _filterState = MutableStateFlow(DatePickerState())
    val filterState: StateFlow<DatePickerState> = _filterState

    private suspend fun getAccount() {
        getSeekingsInternal()
        getOffersInternal()
        getReservationsInternal()
        getParkingSpacesInternal()
        getUserInternal()
    }

    private suspend fun getUserInternal() {
        getAccountUseCase().onFinished(
            successCallback = this::getUserSuccess,
            errorCallback = this::getUserError
        )
    }

    private suspend fun getUserSuccess() {
        _accountState.update { it.copy(user = getAccountUseCase().data) }
    }

    private fun getUserError(errorData: ErrorData) {
        when (errorData.errorType) {
            GetAccountUseCase.GetAccountError.GENERAL_GET_ACCOUNT_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    private suspend fun getSeekingsInternal() {
        getSeekingsUseCase(MIN_DATE, MAX_DATE).onFinished(
            successCallback = this::getSeekingsSuccess,
            errorCallback = this::getSeekingsError
        )
    }

    private suspend fun getSeekingsSuccess() {
        _accountState.update { it.copy(seekings = getSeekingsUseCase(MIN_DATE, MAX_DATE).data as MutableList<Seeking>?) }
    }

    private fun getSeekingsError(errorData: ErrorData) {
        when (errorData.errorType) {
            GetSeekingsUseCase.GetSeekingsError.GENERAL_GET_SEEKINGS_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    private suspend fun getOffersInternal() {
        getOffersUseCase(MIN_DATE, MAX_DATE).onFinished(
            successCallback = this::getOffersSuccess,
            errorCallback = this::getOffersError
        )
    }

    private suspend fun getOffersSuccess() {
        _accountState.update { it.copy(offers = getOffersUseCase(MIN_DATE, MAX_DATE).data as MutableList<Offer>) }
    }

    private fun getOffersError(errorData: ErrorData) {
        when (errorData.errorType) {
            GetOffersUseCase.GetOffersError.GENERAL_GET_OFFERS_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    private suspend fun getReservationsInternal() {
        getReservationsUseCase().onFinished(
            successCallback = this::getReservationsSuccess,
            errorCallback = this::getReservationsError
        )
    }

    private suspend fun getReservationsSuccess() {
        _accountState.update { it.copy(reservations = getReservationsUseCase().data as MutableList<Reservation>) }
    }

    private fun getReservationsError(errorData: ErrorData) {
        when (errorData.errorType) {
            GetReservationsUseCase.GetReservationsError.GENERAL_GET_RESERVATIONS_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    private suspend fun getParkingSpacesInternal() {
        getParkingSpacesUseCase().onFinished(
            successCallback = this::getParkingSpacesSuccess,
            errorCallback = this::getParkingSpacesError
        )
    }

    private suspend fun getParkingSpacesSuccess() {
        _accountState.update { it.copy(parkingSpaces = getParkingSpacesUseCase().data as MutableList<ParkingSpace>) }
    }

    private fun getParkingSpacesError(errorData: ErrorData) {
        when (errorData.errorType) {
            GetParkingSpacesUseCase.GetParkingSpacesError.GENERAL_GET_PARKING_SPACES_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

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