package com.sgupta.calendarapp.domain.usecase

import app.cash.turbine.test
import com.sgupta.calendarapp.core.Resource
import com.sgupta.calendarapp.domain.fakerepo.CalendarFakeRepo
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SubmitCalendarTaskUseCaseTest {
    private lateinit var calendarRepo: CalendarFakeRepo
    private lateinit var submitCalendarTaskUseCase: SubmitCalendarTaskUseCase

    @Before
    fun setUp() {
        calendarRepo = CalendarFakeRepo()
        submitCalendarTaskUseCase = SubmitCalendarTaskUseCase(calendarRepo)
    }

    @Test
    fun `returns Success when userId is correct`(): Unit =
        runTest {
            submitCalendarTaskUseCase(
                SubmitCalendarTaskUseCase.Param(
                    userId = 123,
                    title = "Title",
                    description = "Description",
                ),
            ).test {
                val emission = awaitItem()
                assert(emission is Resource.Success)
                awaitComplete()
            }
        }

    @Test
    fun `returns Error when userId is wrong`(): Unit =
        runTest {
            submitCalendarTaskUseCase(
                SubmitCalendarTaskUseCase.Param(
                    userId = -1,
                    title = "",
                    description = "",
                ),
            ).test {
                val emission = awaitItem()
                assert(emission is Resource.Error)
                awaitComplete()
            }
        }
}
