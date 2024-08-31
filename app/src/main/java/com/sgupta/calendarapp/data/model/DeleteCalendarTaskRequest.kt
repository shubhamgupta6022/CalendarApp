package com.sgupta.calendarapp.data.model

import com.google.gson.annotations.SerializedName

data class DeleteCalendarTaskRequest(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("task_id") val taskId: Int
)