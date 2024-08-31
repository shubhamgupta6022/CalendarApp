package com.sgupta.calendarapp.feature.states

sealed class CalendarTaskAdapterState {
    data class OnDeleteClicked(
        val taskId: Int,
    ) : CalendarTaskAdapterState()
}
