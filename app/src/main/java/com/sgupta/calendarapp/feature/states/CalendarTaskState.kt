package com.sgupta.calendarapp.feature.states

import com.sgupta.calendarapp.core.delegator.DelegateAdapterItem

sealed class CalendarTaskState {
    data class OnTaskAdded(
        val data: MutableList<DelegateAdapterItem>,
    ) : CalendarTaskState()
}
