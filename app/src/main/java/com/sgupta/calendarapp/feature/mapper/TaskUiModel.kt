package com.sgupta.calendarapp.feature.mapper

import com.sgupta.calendarapp.core.mapper.Mapper
import com.sgupta.calendarapp.domain.model.TaskDetailDomainModel
import com.sgupta.calendarapp.feature.uimodel.TaskUiModel
import javax.inject.Inject
import kotlin.random.Random

class TaskUiModelMapper @Inject constructor(): Mapper<TaskDetailDomainModel, TaskUiModel> {
    override fun convert(from: TaskDetailDomainModel): TaskUiModel =
        TaskUiModel(
            title = from.title,
            description = from.description,
            taskId = Random.nextInt(1, 9999),
        )
}
