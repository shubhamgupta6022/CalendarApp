package com.sgupta.calendarapp.data.repo

import com.sgupta.calendarapp.core.Resource
import com.sgupta.calendarapp.core.flows.responseFlow
import com.sgupta.calendarapp.data.model.ApiResponse
import com.sgupta.calendarapp.data.model.CalendarTaskResponse
import com.sgupta.calendarapp.data.model.DeleteCalendarTaskRequest
import com.sgupta.calendarapp.data.model.StoreTaskRequest
import com.sgupta.calendarapp.data.model.TaskDetailModel
import com.sgupta.calendarapp.data.model.UserIdRequest
import com.sgupta.calendarapp.data.remote.CalendarApiService
import com.sgupta.calendarapp.domain.repo.CalendarRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CalendarRepoImpl
    @Inject
    constructor(
        private val apiService: CalendarApiService,
    ) : CalendarRepo {
        override suspend fun storeCalendarTask(
            userId: Int,
            task: TaskDetailModel,
        ): Flow<Resource<ApiResponse>> {
            val storeTaskRequest =
                StoreTaskRequest(
                    userId = userId,
                    task = task,
                )
            return responseFlow {
                apiService.storeCalendarTask(storeTaskRequest)
            }
        }

        override suspend fun getCalendarTasksLists(userId: Int): Flow<Resource<CalendarTaskResponse>> =
            responseFlow {
                apiService.getCalendarTaskLists(UserIdRequest(userId))
            }

        override suspend fun deleteCalendarTask(
            userId: Int,
            taskId: Int,
        ): Flow<Resource<ApiResponse>> {
            val deleteCalendarTaskRequest =
                DeleteCalendarTaskRequest(
                    userId = userId,
                    taskId = taskId,
                )
            return responseFlow {
                apiService.deleteCalendarTask(deleteCalendarTaskRequest)
            }
        }
    }
