package hr.asee.android.template.compose.ui.postlogin.home

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.delegate.BottomNavBarDelegate
import hr.asee.android.template.compose.ui.common.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.AccountState
import hr.asee.android.template.compose.ui.common.model.state.AlertDialogState
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.home.model.HomeMessages
import hr.asee.android.template.domain.model.common.Giver
import hr.asee.android.template.domain.model.common.Seeker
import hr.asee.android.template.domain.model.common.User
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.model.common.service.Offer
import hr.asee.android.template.domain.model.common.service.ParkingSpace
import hr.asee.android.template.domain.model.common.service.Reservation
import hr.asee.android.template.domain.model.common.service.Seeking
import hr.asee.android.template.domain.usecase.DateSelectUseCase
import hr.asee.android.template.domain.usecase.GetAccountUseCase
import hr.asee.android.template.domain.usecase.GetAllBottomNavItemsUseCase
import hr.asee.android.template.domain.usecase.login.LogoutUseCase
import hr.asee.android.template.domain.usecase.offering.DeleteOfferingUseCase
import hr.asee.android.template.domain.usecase.offering.GetOfferingsForGiverUseCase
import hr.asee.android.template.domain.usecase.offering.GetOffersUseCase
import hr.asee.android.template.domain.usecase.parkingspace.GetParkingSpaceForGiverUseCase
import hr.asee.android.template.domain.usecase.parkingspace.GetParkingSpacesUseCase
import hr.asee.android.template.domain.usecase.reservation.GetReservationsForGiverUseCase
import hr.asee.android.template.domain.usecase.reservation.GetReservationsForSeekerUseCase
import hr.asee.android.template.domain.usecase.seeking.GetSeekingsForSeekerUseCase
import hr.asee.android.template.domain.usecase.seeking.GetSeekingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val getSeekingsUseCase: GetSeekingsUseCase,
    private val getSeekingsForSeekerUseCase: GetSeekingsForSeekerUseCase,
    private val getOffersUseCase: GetOffersUseCase,
    private val deleteOfferingUseCase: DeleteOfferingUseCase,
    private val getOfferingsForGiverUseCase: GetOfferingsForGiverUseCase,
    private val getReservationsForGiverUseCase: GetReservationsForGiverUseCase,
    private val getReservationsForSeekerUseCase: GetReservationsForSeekerUseCase,
    private val getParkingSpaceForGiverUseCase: GetParkingSpaceForGiverUseCase,
    private val dateSelectUseCase: DateSelectUseCase,
    private val getAllBottomNavItemsUseCase: GetAllBottomNavItemsUseCase,
    private val logoutUseCase: LogoutUseCase,
    val bottomNavBarDelegate: BottomNavBarDelegate
) : BaseViewModel() {

    init {
        showBottomNavBar()
        runSuspend {
            getAllBottomNavItemsUseCase().onSuccess { items -> _bottomNavBarState.update { it.copy(items = items, selectedItem = items[0]) } }
        }
    }

    private val _accountState = MutableStateFlow(AccountState())
    val accountState: StateFlow<AccountState> = _accountState

    private val _filterState = MutableStateFlow(DatePickerState())
    val filterState: StateFlow<DatePickerState> = _filterState

    private val _cancelReservationDialogState = MutableStateFlow(AlertDialogState())
    val cancelReservationDialogState: StateFlow<AlertDialogState> = _cancelReservationDialogState

    private val _removeOfferDialogState = MutableStateFlow(AlertDialogState())
    val removeOfferDialogState: StateFlow<AlertDialogState> = _removeOfferDialogState

    private val _removeOfferId = MutableStateFlow(-1)
    val removeOfferId = _removeOfferId.asStateFlow()

    fun initData() {
        runSuspend {
            getAccount()
        }
    }

    private suspend fun getAccount() {
        getAccountInternal()
    }

    private suspend fun getAccountInternal() {
        getAccountUseCase().onFinished(
            successCallback = this::getAccountSuccess,
            errorCallback = this::getAccountError
        )
    }

    private fun getAccountSuccess(user: User) {
        _accountState.update { it.copy(user = user) }

        runSuspend {
            when(user) {
                is Giver -> initGiver(user)
                is Seeker -> initSeeker(user)
                else -> initAdmin(user)
            }
        }
    }

    private suspend fun initGiver(giver: Giver) {
        getSeekingsInternal()
        getOfferingsForGiverInternal(giverId = giver.id)
        getReservationForGiverInternal(giverId = giver.id)
        getParkingSpaceForGiverInternal(giverId = giver.id)
    }

    private suspend fun initSeeker(seeker: Seeker) {
        getSeekingsForSeekerInternal(seekerId = seeker.id)
        getOffersInternal()
        getReservationsForSeekerInternal(seekerId = seeker.id)
    }

    private suspend fun getOfferingsForGiverInternal(giverId: Int) {
        getOfferingsForGiverUseCase(giverId).onFinished(
            this::getOfferingsForGiverSuccess,
            this::getOfferingsForGiverError
        )
    }

    private fun getOfferingsForGiverSuccess(offerings: List<Offer>) {
        _accountState.update { _accountState.value.copy(offers = offerings.toSet()) }
    }

    private fun getOfferingsForGiverError(errorData: ErrorData) {
        showError(CommonMessages.UNEXPECTED_ERROR)
    }


    private suspend fun getReservationForGiverInternal(giverId: Int) {
        getReservationsForGiverUseCase(giverId).onFinished(
            this::getReservationsForGiverSuccess,
            this::getReservationsForGiverError
        )
    }

    private fun getReservationsForGiverSuccess(reservations: List<Reservation>) {
        _accountState.update { _accountState.value.copy(reservations = reservations.toSet()) }
    }

    private fun getReservationsForGiverError(errorData: ErrorData) {
        showError(CommonMessages.UNEXPECTED_ERROR)
    }

    private suspend fun getSeekingsForSeekerInternal(seekerId: Int) {
        getSeekingsForSeekerUseCase(seekerId).onFinished(
            this::getSeekingsForSeekerSuccess,
            this::getSeekingsForSeekerError
        )
    }

    private fun getSeekingsForSeekerSuccess(seekings: List<Seeking>) {
        _accountState.update { _accountState.value.copy(seekings = seekings.toSet()) }
    }

    private fun getSeekingsForSeekerError(errorData: ErrorData) {
        showError(CommonMessages.UNEXPECTED_ERROR)
    }

    private suspend fun getReservationsForSeekerInternal(seekerId: Int) {
        getReservationsForSeekerUseCase(seekerId).onFinished(
            this::getReservationsForSeekerSuccess,
            this::getReservationsForSeekerError
        )
    }

    private fun getReservationsForSeekerSuccess(reservations: List<Reservation>) {
        _accountState.update { _accountState.value.copy(reservations = reservations.toSet()) }
    }

    private fun getReservationsForSeekerError(errorData: ErrorData) {
        showError(CommonMessages.UNEXPECTED_ERROR)
    }

    private fun initAdmin(user: User) {
        //
    }

    private fun getAccountError(errorData: ErrorData) {
        when(errorData.errorType) {
            GetAccountUseCase.GetAccountError.GENERAL_GET_ACCOUNT_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    private suspend fun getSeekingsInternal() {
        getSeekingsUseCase().onFinished(
            successCallback = this::getSeekingsSuccess,
            errorCallback = this::getSeekingsError
        )
    }

    private fun getSeekingsSuccess(seekings: List<Seeking>) {
        _accountState.update { it.copy(seekings = seekings.toMutableSet()) }
    }

    private fun getSeekingsError(errorData: ErrorData) {
        when (errorData.errorType) {
            GetSeekingsUseCase.GetSeekingsError.GENERAL_GET_SEEKINGS_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    private suspend fun getOffersInternal() {
        getOffersUseCase().onFinished(
            successCallback = this::getOffersSuccess,
            errorCallback = this::getOffersError
        )
    }

    private fun getOffersSuccess(offers: List<Offer>) {
        _accountState.update { it.copy(offers = offers.toMutableSet()) }
    }

    private fun getOffersError(errorData: ErrorData) {
        when (errorData.errorType) {
            GetOffersUseCase.GetOffersError.GENERAL_GET_OFFERS_ERROR -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    private suspend fun getParkingSpaceForGiverInternal(giverId: Int) {
        getParkingSpaceForGiverUseCase(giverId).onFinished(
            successCallback = this::getParkingSpacesSuccess,
            errorCallback = this::getParkingSpacesError
        )
    }

    private fun getParkingSpacesSuccess(parkingSpace: ParkingSpace) {
        _accountState.update { it.copy(parkingSpaces = setOf(parkingSpace)) }
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
            dateStartSelected = LocalDateTime.MIN,
            dateEndSelected = LocalDateTime.MAX
        ) }
    }

    fun onDateSelect(newDate: LocalDateTime) {
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
        dateSelectUseCase(DateSelectUseCase.Dates(dateStart = filterState.value.dateStart, dateEnd = filterState.value.dateEnd)).onFinished(
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
            DateSelectUseCase.DateSelectError.INVALID_DATES_ERROR -> showError(HomeMessages.INVALID_DATES_ERROR)
            DateSelectUseCase.DateSelectError.DATES_NOT_SELECTED_ERROR -> showError(HomeMessages.DATES_NOT_SELECTED_ERROR)
            else -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }


    fun onCancelReservationClicked() {
        _cancelReservationDialogState.update { it.copy(isVisible = true) }
    }

    fun onCancelClickedReservationCard() {
        _cancelReservationDialogState.update { it.copy(isVisible = false) }
    }

    fun onCancelClickedOfferCard() {
        _removeOfferDialogState.update { it.copy(isVisible = false) }
    }

    fun onRemoveOfferClicked(offerId: Int) {
        _removeOfferId.update { offerId }
        _removeOfferDialogState.update { it.copy(isVisible = true) }
    }

    fun removeOffer(offerId: Int) {
        runSuspend {
            deleteOfferingUseCase(offerId).onFinished(
                this::deleteOfferingSuccess,
                this::deleteOfferingError
            )
        }
    }

    private fun deleteOfferingSuccess() {
        _removeOfferDialogState.update { it.copy(isVisible = false) }
    }

    private fun deleteOfferingError(errorData: ErrorData) {
        _removeOfferDialogState.update { it.copy(isVisible = false) }
        showError(CommonMessages.UNEXPECTED_ERROR)
    }

    fun onGiverReservationClicked(reservationId: Int) {
        router.navigateToReserveParkingSpaceGiverScreen(reservationId = reservationId)
    }

    fun onSeekerReservationClicked() {
        /* TODO */
    }

    fun onGiverOfferClicked() {
        /* TODO */
    }

    fun onSeekerOfferClicked(offerId: Int) {
        router.navigateToReserveParkingSpaceSeekerScreen(offerId = offerId)
    }

    fun onSeekingClicked() {
        // TODO
    }

    fun offerParking(userId: Int) {
        router.navigateToCreateOfferScreen(userId)
    }

    fun seekParking() {
        router.navigateToCreateSeekingScreen()
    }

    fun onSettingsClicked() {
        router.navigateToSettingsScreen()
    }

    fun onProfilePictureClicked() {
        router.navigateToUserManagementScreen()
    }

    fun logout() {
        runSuspend {
            logoutUseCase().onFinished(
                this::onLogoutSuccess,
                this::onLogoutError
            )
        }
    }

    private fun onLogoutSuccess() {
        router.logout()
    }

    private fun onLogoutError(errorData: ErrorData) {
        // NO_OP
    }
}