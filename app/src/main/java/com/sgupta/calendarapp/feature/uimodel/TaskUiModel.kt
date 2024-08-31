package com.sgupta.calendarapp.feature.uimodel

import com.sgupta.calendarapp.core.delegator.DelegateAdapterItem
import java.util.Objects

data class TaskUiModel(
    val title: String,
    val description: String,
    val taskId: Int,
) : DelegateAdapterItem {
    override fun id(): Any = taskId

    override fun content(): Any = Content(taskId)

    inner class Content(
        val taskId: Int,
    ) {
        override fun equals(other: Any?): Boolean {
            if (other is Content) {
                return taskId == other.taskId
            }
            return false
        }

        override fun hashCode(): Int = 32 * Objects.hash(taskId)
    }
}
