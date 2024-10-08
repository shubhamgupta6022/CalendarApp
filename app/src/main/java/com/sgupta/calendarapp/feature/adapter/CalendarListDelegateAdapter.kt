package com.sgupta.calendarapp.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sgupta.calendarapp.core.delegator.DelegateAdapter
import com.sgupta.calendarapp.core.delegator.DelegateAdapterItem
import com.sgupta.calendarapp.databinding.CalendarListItemBinding
import com.sgupta.calendarapp.feature.states.CalendarTaskAdapterState
import com.sgupta.calendarapp.feature.uimodel.TaskUiModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CalendarListDelegateAdapter
    @Inject
    constructor() :
    DelegateAdapter<TaskUiModel, CalendarListDelegateAdapter.ViewHolder>(TaskUiModel::class.java) {
        private val _uiStates: MutableSharedFlow<CalendarTaskAdapterState> = MutableSharedFlow()
        val uiStates = _uiStates.asSharedFlow()

        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val binding =
                CalendarListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun bindViewHolder(
            model: TaskUiModel,
            viewHolder: ViewHolder,
            payloads: List<DelegateAdapterItem.Payloadable>,
            position: Int,
        ) {
            viewHolder.bind(model)
        }

        inner class ViewHolder(
            private val binding: CalendarListItemBinding,
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(model: TaskUiModel) {
                with(binding) {
                    tvTitle.text = model.title
                    tvDescription.text = model.description
                    ivDelete.setOnClickListener {
                        delegateScope.launch {
                            _uiStates.emit(CalendarTaskAdapterState.OnDeleteClicked(model.taskId))
                        }
                    }
                }
            }
        }
    }
