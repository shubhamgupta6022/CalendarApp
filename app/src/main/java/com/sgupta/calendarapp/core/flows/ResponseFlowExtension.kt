package com.sgupta.calendarapp.core.flows

import com.sgupta.calendarapp.core.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T> responseFlow(call: suspend () -> Response<T>) = flow<Resource<T>> {
    try {
        val response: Response<T> = call.invoke()
        if (response.isSuccessful) {
            emit(Resource.Success(response.body()))
        } else {
            emit(Resource.Error(Throwable(response.errorBody().toString())))
        }
    } catch (e: Exception) {
        throw e
    }
}.catch { exception ->
    emit(Resource.Error(Throwable(exception.toString())))
}