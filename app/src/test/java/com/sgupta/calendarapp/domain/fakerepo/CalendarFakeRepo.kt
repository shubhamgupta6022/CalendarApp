package com.sgupta.calendarapp.domain.fakerepo

import com.sgupta.calendarapp.core.Resource
import com.sgupta.calendarapp.core.toThrowable
import com.sgupta.calendarapp.data.model.ApiResponse
import com.sgupta.calendarapp.data.model.CalendarTaskResponse
import com.sgupta.calendarapp.data.model.TaskDetailModel
import com.sgupta.calendarapp.domain.repo.CalendarRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CalendarFakeRepo : CalendarRepo {
    override suspend fun storeCalendarTask(
        userId: Int,
        task: TaskDetailModel
    ): Flow<Resource<ApiResponse>> = flow {
        if (userId < 1) {
            emit(Resource.Error("Error".toThrowable()))
        } else {
            emit(Resource.Success(ApiResponse(status = "Success")))
        }
    }

    override suspend fun getCalendarTasksLists(userId: Int): Flow<Resource<CalendarTaskResponse>> = flow {
        if (userId < 1) {
            emit(Resource.Error("Error".toThrowable()))
        } else {
            val tasks = CalendarTaskResponse(
                tasks = listOf()
            )
            emit(Resource.Success(tasks))
        }
    }

    override suspend fun deleteCalendarTask(userId: Int, taskId: Int): Flow<Resource<ApiResponse>> = flow {
        if (userId < 1 || taskId < 1) {
            emit(Resource.Error("Error".toThrowable()))
        } else {
            emit(Resource.Success(ApiResponse(status = "Success")))
        }
    }
}