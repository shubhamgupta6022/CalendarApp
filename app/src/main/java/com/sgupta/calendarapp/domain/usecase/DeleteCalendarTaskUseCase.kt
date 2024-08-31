package com.sgupta.calendarapp.domain.usecase

import com.sgupta.calendarapp.core.Resource
import com.sgupta.calendarapp.domain.repo.CalendarRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DeleteCalendarTaskUseCase @Inject constructor(
    private val calendarRepo: CalendarRepo
) {
    operator fun invoke(param: Param) = flow<Resource<Any>> {
        calendarRepo.deleteCalendarTask(param.userId, param.taskId)
            .onEach {
                when(it) {
                    is Resource.Success -> {
                        emit(Resource.Success(param.taskId))
                    }
                    is Resource.Error -> {
                        emit(Resource.Error(it.error))
                    }
                }
            }
            .collect()
    }

    data class Param(
        val userId: Int,
        val taskId: Int
    )
}