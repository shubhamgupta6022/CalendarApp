package com.sgupta.calendarapp.data.remote

import com.sgupta.calendarapp.data.model.ApiResponse
import com.sgupta.calendarapp.data.model.CalendarTaskResponse
import com.sgupta.calendarapp.data.model.DeleteCalendarTaskRequest
import com.sgupta.calendarapp.data.model.StoreTaskRequest
import com.sgupta.calendarapp.data.model.UserIdRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CalendarApiService {

    @POST("/api/storeCalendarTask")
    suspend fun storeCalendarTask(@Body request: StoreTaskRequest) : Response<ApiResponse>

    @POST("/api/getCalendarTaskList")
    suspend fun getCalendarTaskLists(@Body request: UserIdRequest) : Response<CalendarTaskResponse>

    @POST("/api/deleteCalendarTask")
    suspend fun deleteCalendarTask(@Body request: DeleteCalendarTaskRequest) : Response<ApiResponse>
}