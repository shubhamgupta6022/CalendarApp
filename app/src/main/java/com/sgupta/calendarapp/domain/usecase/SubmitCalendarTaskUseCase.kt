package com.sgupta.calendarapp.domain.usecase

import com.sgupta.calendarapp.core.Resource
import com.sgupta.calendarapp.data.model.TaskDetailModel
import com.sgupta.calendarapp.data.model.toTaskDomainModel
import com.sgupta.calendarapp.domain.model.TaskDetailDomainModel
import com.sgupta.calendarapp.domain.repo.CalendarRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SubmitCalendarTaskUseCase
    @Inject
    constructor(
        private val calendarRepo: CalendarRepo,
    ) {
        operator fun invoke(param: Param): Flow<Resource<TaskDetailDomainModel>> =
            flow {
                val taskDetailModel =
                    TaskDetailModel(
                        title = param.title,
                        description = param.description,
                    )
                calendarRepo
                    .storeCalendarTask(param.userId, taskDetailModel)
                    .onEach {
                        when(it) {
                            is Resource.Success -> {
                                emit(Resource.Success(taskDetailModel.toTaskDomainModel()))
                            }
                            is Resource.Error -> {
                                emit(Resource.Error(it.error))
                            }
                        }
                    }.collect()
            }

        data class Param(
            val userId: Int,
            val title: String,
            val description: String,
        )
    }
