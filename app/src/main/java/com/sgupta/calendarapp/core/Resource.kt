package com.sgupta.calendarapp.core

sealed class Resource<out R> {
    data class Success<out T>(val data: T?) : Resource<T>()
    data class Error(val error: Throwable, val errorCode: Int = -1) : Resource<Nothing>()
}