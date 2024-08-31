package com.sgupta.calendarapp.data.model

import com.google.gson.annotations.SerializedName
import com.sgupta.calendarapp.domain.model.TaskDetailDomainModel
import com.sgupta.calendarapp.domain.model.TaskListDomainModel
import com.sgupta.calendarapp.domain.model.TasksDomainModel

data class CalendarTaskResponse(
    @SerializedName("tasks") val tasks: List<TaskResponse>,
)

data class TaskResponse(
    @SerializedName("task_id") val taskId: Int,
    @SerializedName("task_detail") val taskDetail: TaskDetailModel,
)

data class TaskDetailModel(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
)

fun CalendarTaskResponse.toTasksDomainModel() =
    TasksDomainModel(
        tasks = tasks.map { it.toTaskListDomainModel() },
    )

fun TaskResponse.toTaskListDomainModel() =
    TaskListDomainModel(
        taskId = taskId,
        taskDetail = taskDetail.toTaskDomainModel(),
    )

fun TaskDetailModel.toTaskDomainModel() =
    TaskDetailDomainModel(
        title = title,
        description = description,
    )
