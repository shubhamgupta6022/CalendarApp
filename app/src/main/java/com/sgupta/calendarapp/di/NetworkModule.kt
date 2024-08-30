package com.sgupta.calendarapp.di

import com.sgupta.calendarapp.constants.Constants
import com.sgupta.calendarapp.data.remote.CalendarApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesCalendarApiService(): CalendarApiService =
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(),
            ).build()
            .create(CalendarApiService::class.java)
}
