package com.sgupta.calendarapp.domain.usecase

import com.sgupta.calendarapp.core.Resource
import com.sgupta.calendarapp.data.model.CalendarTaskResponse
import com.sgupta.calendarapp.data.model.toTasksDomainModel
import com.sgupta.calendarapp.domain.model.TasksDomainModel
import com.sgupta.calendarapp.domain.repo.CalendarRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetCalendarTasksUseCase
    @Inject
    constructor(
        private val calendarRepo: CalendarRepo,
    ) {
        operator fun invoke(param: Param) =
            flow<Resource<TasksDomainModel>> {
                calendarRepo
                    .getCalendarTasksLists(param.userId)
                    .onEach {
                        when (it) {
                            is Resource.Success<*> -> {
                                emit(Resource.Success((it.data as? CalendarTaskResponse)?.toTasksDomainModel()))
                            }

                            is Resource.Error -> {
                                emit(Resource.Error(it.error))
                            }
                        }
                    }.collect()
            }

        data class Param(
            val userId: Int,
        )
    }
