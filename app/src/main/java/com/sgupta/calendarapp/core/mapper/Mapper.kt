package com.sgupta.calendarapp.core.mapper

interface Mapper<F, T> {
    fun convert(from: F): T
}