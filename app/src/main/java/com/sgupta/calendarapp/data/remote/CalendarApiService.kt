package com.sgupta.calendarapp.data.remote

import com.sgupta.calendarapp.data.model.ApiResponse
import com.sgupta.calendarapp.data.model.CalendarTaskResponse
import com.sgupta.calendarapp.data.model.StoreTaskRequest
import com.sgupta.calendarapp.data.model.UserIdRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CalendarApiService {

    @POST("/api/storeCalendarTask")
    suspend fun storeCalendarTask(@Body request: StoreTaskRequest) : Response<ApiResponse>

    @POST("/api/getCalendarTaskList")
    suspend fun getCalendarTaskLists(@Body request: UserIdRequest) : Response<CalendarTaskResponse>
}