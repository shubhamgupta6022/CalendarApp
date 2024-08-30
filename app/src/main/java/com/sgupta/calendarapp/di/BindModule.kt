package com.sgupta.calendarapp.di

import com.sgupta.calendarapp.data.repo.CalendarRepoImpl
import com.sgupta.calendarapp.domain.repo.CalendarRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    abstract fun bindCalendarRepoImpl(impl: CalendarRepoImpl): CalendarRepo
}
