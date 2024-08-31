package com.sgupta.calendarapp.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgupta.calendarapp.feature.states.CalendarViewPagerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarActivitySharedViewModel @Inject constructor(): ViewModel() {

    private val _uiState: MutableSharedFlow<CalendarViewPagerState> = MutableSharedFlow(replay = 1)
    val uiStates = _uiState.asSharedFlow()

    fun rightArrowClicked() {
        viewModelScope.launch {
            _uiState.emit(CalendarViewPagerState.OnRightArrowClicked)
        }
    }

    fun leftArrowClicked() {
        viewModelScope.launch {
            _uiState.emit(CalendarViewPagerState.OnLeftArrowClicked)
        }
    }
}