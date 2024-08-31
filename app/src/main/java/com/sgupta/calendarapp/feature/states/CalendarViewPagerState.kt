package com.sgupta.calendarapp.feature.states

sealed class CalendarViewPagerState {
    object OnRightArrowClicked : CalendarViewPagerState()

    object OnLeftArrowClicked : CalendarViewPagerState()
}
