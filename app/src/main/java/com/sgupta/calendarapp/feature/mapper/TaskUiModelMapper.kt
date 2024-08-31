package com.sgupta.calendarapp.feature.mapper

import com.sgupta.calendarapp.core.mapper.Mapper
import com.sgupta.calendarapp.domain.model.TaskListDomainModel
import com.sgupta.calendarapp.feature.uimodel.TaskUiModel
import javax.inject.Inject

class TaskListUiModelMapper
    @Inject
    constructor() : Mapper<TaskListDomainModel, TaskUiModel> {
        override fun convert(from: TaskListDomainModel): TaskUiModel =
            TaskUiModel(
                title = from.taskDetail.title,
                description = from.taskDetail.description,
                taskId = from.taskId,
            )
    }
