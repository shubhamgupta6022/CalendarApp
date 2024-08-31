package com.sgupta.calendarapp.domain.model

data class TasksDomainModel(
    val tasks: List<TaskListDomainModel>,
)

data class TaskListDomainModel(
    val taskId: Int,
    val taskDetail: TaskDetailDomainModel,
)

data class TaskDetailDomainModel(
    val title: String,
    val description: String,
)
