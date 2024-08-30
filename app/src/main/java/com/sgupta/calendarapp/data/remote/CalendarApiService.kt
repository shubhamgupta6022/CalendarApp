package com.sgupta.calendarapp.data.remote

import com.sgupta.calendarapp.data.model.TaskModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CalendarApiService {

    @GET("/api/storeCalendarTask")
    suspend fun storeCalendarTask(@Query("user_id") userId: Int, @Query("task") task: TaskModel)

    @GET("/api/getCalendarTaskLists")
    suspend fun getCalendarTaskLists(@Query("user_id") userId: Int)
}