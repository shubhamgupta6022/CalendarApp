package com.sgupta.calendarapp.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgupta.calendarapp.core.Resource
import com.sgupta.calendarapp.core.delegator.DelegateAdapterItem
import com.sgupta.calendarapp.domain.usecase.DeleteCalendarTaskUseCase
import com.sgupta.calendarapp.domain.usecase.GetCalendarTasksUseCase
import com.sgupta.calendarapp.domain.usecase.SubmitCalendarTaskUseCase
import com.sgupta.calendarapp.feature.mapper.TaskListUiModelMapper
import com.sgupta.calendarapp.feature.states.CalendarTaskState
import com.sgupta.calendarapp.feature.uimodel.TaskUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CalendarListModel
    @Inject
    constructor(
        private val submitCalendarTaskUseCase: SubmitCalendarTaskUseCase,
        private val getCalendarTasksUseCase: GetCalendarTasksUseCase,
        private val deleteCalendarTaskUseCase: DeleteCalendarTaskUseCase,
        private val taskListUiModelMapper: TaskListUiModelMapper,
    ) : ViewModel() {
        private val _uiStates: MutableSharedFlow<CalendarTaskState> = MutableSharedFlow()
        val uiState = _uiStates.asSharedFlow()

        private var taskUiModel: MutableList<DelegateAdapterItem> = mutableListOf()

        fun getCalendarTasksList(userId: Int) {
            getCalendarTasksUseCase
                .invoke(GetCalendarTasksUseCase.Param(userId = userId))
                .onEach {
                    when (it) {
                        is Resource.Success -> {
                            val updatedTaskUiModel: MutableList<DelegateAdapterItem> = mutableListOf()
                            it.data?.let { data ->
                                data.tasks.map { item ->
                                    updatedTaskUiModel.add(taskListUiModelMapper.convert(item))
                                }
                            }
                            taskUiModel = updatedTaskUiModel
                            _uiStates.emit(CalendarTaskState.OnTaskAdded(taskUiModel))
                        }

                        is Resource.Error -> {
                        }
                    }
                }.launchIn(viewModelScope)
        }

        fun submitCalendarTask(
            userId: Int,
            title: String,
            description: String,
        ) {
            val param =
                SubmitCalendarTaskUseCase.Param(
                    userId = userId,
                    title = title,
                    description = description,
                )
            submitCalendarTaskUseCase
                .invoke(param)
                .onEach {
                    when (it) {
                        is Resource.Success -> {
                            getCalendarTasksList(userId)    // Here extra api call is made as we don't know the taskId of that particular task.
                        }

                        is Resource.Error -> {}
                    }
                }.launchIn(viewModelScope)
        }

        fun deleteCalendarTasks(
            userId: Int,
            taskId: Int,
        ) {
            deleteCalendarTaskUseCase
                .invoke(DeleteCalendarTaskUseCase.Param(userId, taskId))
                .onEach {
                    when (it) {
                        is Resource.Success -> {
                            val newTaskUiModel =
                                taskUiModel.filterNot { (it as TaskUiModel).taskId == taskId }
                            taskUiModel = newTaskUiModel.toMutableList()
                            _uiStates.emit(CalendarTaskState.OnTaskAdded(taskUiModel))
                        }

                        is Resource.Error -> {
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
