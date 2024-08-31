package com.sgupta.calendarapp.domain.repo

import com.sgupta.calendarapp.core.Resource
import com.sgupta.calendarapp.data.model.ApiResponse
import com.sgupta.calendarapp.data.model.CalendarTaskResponse
import com.sgupta.calendarapp.data.model.TaskDetailModel
import kotlinx.coroutines.flow.Flow

interface CalendarRepo {
    suspend fun storeCalendarTask(
        userId: Int,
        task: TaskDetailModel,
    ): Flow<Resource<ApiResponse>>

    suspend fun getCalendarTasksLists(userId: Int): Flow<Resource<CalendarTaskResponse>>

    suspend fun deleteCalendarTask(taskId: Int)
}
