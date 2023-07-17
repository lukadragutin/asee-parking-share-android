package hr.asee.android.template.compose.ui.postlogin.createseeking

import dagger.hilt.android.lifecycle.HiltViewModel
import hr.asee.android.template.compose.ui.common.base.BaseViewModel
import hr.asee.android.template.compose.ui.common.model.CommonMessages
import hr.asee.android.template.compose.ui.common.model.state.DatePickerState
import hr.asee.android.template.compose.ui.postlogin.home.model.HomeMessages
import hr.asee.android.template.domain.model.common.resource.ErrorData
import hr.asee.android.template.domain.usecase.DateSelectUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.threeten.bp.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CreateSeekingViewModel @Inject constructor(
    private val dateSelectUseCase: DateSelectUseCase
) : BaseViewModel() {

    private val _datePickerState = MutableStateFlow(DatePickerState())
    val datePickerState: StateFlow<DatePickerState> = _datePickerState

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
            }
            else if (it.endFocused) {
                it.copy(dateEnd = newDate)
            }
            else it.copy()
        }
    }

     fun onCreateClicked() {
        runSuspend { createSeekingInternal() }
    }

    private suspend fun createSeekingInternal() {
        dateSelectUseCase(DateSelectUseCase.Dates(dateStart = datePickerState.value.dateStart, dateEnd = datePickerState.value.dateEnd)).onFinished(
            successCallback = this::onCreateSeekingSuccess,
            errorCallback = this::onCreateSeekingError
        )
    }

    private fun onCreateSeekingSuccess() {
        _datePickerState.update {
            it.copy(
                dateStartSelected = it.dateStart,
                dateEndSelected = it.dateEnd,
                startFocused = false,
                endFocused = false
            )
        }
//        val seekings = accountState.value.seekings
//        _accountState.update { it.copy(seekings = seekings) }
        goBack()
    }

    private fun onCreateSeekingError(errorData: ErrorData) {
        when (errorData.errorType) {
            DateSelectUseCase.DateSelectError.INVALID_DATES_ERROR -> showError(HomeMessages.INVALID_DATES_ERROR)
            DateSelectUseCase.DateSelectError.DATES_NOT_SELECTED_ERROR -> showError(HomeMessages.DATES_NOT_SELECTED_ERROR)
            else -> showError(CommonMessages.UNEXPECTED_ERROR)
        }
    }

    fun onCancelClicked() {
        _datePickerState.update { it.copy(
            dateStart = null,
            dateEnd = null,
            dateStartSelected = null,
            dateEndSelected = null,
            startFocused = false,
            endFocused = false
        ) }
        goBack()
    }
}