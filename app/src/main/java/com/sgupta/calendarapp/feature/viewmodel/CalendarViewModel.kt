package com.sgupta.calendarapp.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {
    private val _selectedDay = MutableLiveData<Int?>()
    val selectedDay: LiveData<Int?> = _selectedDay

    fun setSelectedDay(day: Int) {
        _selectedDay.value = day
    }
}
