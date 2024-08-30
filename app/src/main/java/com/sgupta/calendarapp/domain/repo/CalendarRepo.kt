package com.sgupta.calendarapp.domain.repo

import com.sgupta.calendarapp.data.model.TaskModel

interface CalendarRepo {
    suspend fun storeCalendarTask(time: Long, task: TaskModel)
    suspend fun getCalendarTasksLists(time: Long)
}