package com.sgupta.calendarapp.domain.usecase

import app.cash.turbine.test
import com.sgupta.calendarapp.core.Resource
import com.sgupta.calendarapp.domain.fakerepo.CalendarFakeRepo
import com.sgupta.calendarapp.domain.model.TasksDomainModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCalendarTasksUseCaseTest {
    private lateinit var calendarRepo: CalendarFakeRepo
    private lateinit var getCalendarTasksUseCase: GetCalendarTasksUseCase

    @Before
    fun setUp() {
        calendarRepo = CalendarFakeRepo()
        getCalendarTasksUseCase = GetCalendarTasksUseCase(calendarRepo)
    }

    @Test
    fun `returns Success when userId is correct`(): Unit =
        runTest {
            getCalendarTasksUseCase(
                GetCalendarTasksUseCase.Param(
                    userId = 123,
                ),
            ).test {
                val emission = awaitItem()
                assert(emission is Resource.Success)
                assert((emission as Resource.Success).data is TasksDomainModel)
                awaitComplete()
            }
        }

    @Test
    fun `returns Error when userId is wrong`(): Unit =
        runTest {
            getCalendarTasksUseCase(
                GetCalendarTasksUseCase.Param(
                    userId = -1,
                ),
            ).test {
                val emission = awaitItem()
                assert(emission is Resource.Error)
                awaitComplete()
            }
        }
}