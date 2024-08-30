package com.sgupta.calendarapp.data.repo

import com.sgupta.calendarapp.data.model.TaskModel
import com.sgupta.calendarapp.data.remote.CalendarApiService
import com.sgupta.calendarapp.domain.repo.CalendarRepo
import javax.inject.Inject

class CalendarRepoImpl @Inject constructor(private val apiService: CalendarApiService) : CalendarRepo {
    override suspend fun storeCalendarTask(time: Long, task: TaskModel) {
        apiService.storeCalendarTask(time.toInt(), task)
    }

    override suspend fun getCalendarTasksLists(time: Long) {
        apiService.getCalendarTaskLists(time.toInt())
    }
}
