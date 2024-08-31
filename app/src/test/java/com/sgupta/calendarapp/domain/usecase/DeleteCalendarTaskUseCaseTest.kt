package com.sgupta.calendarapp.domain.usecase

import app.cash.turbine.test
import com.sgupta.calendarapp.core.Resource
import com.sgupta.calendarapp.domain.fakerepo.CalendarFakeRepo
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DeleteCalendarTaskUseCaseTest {
    private lateinit var calendarRepo: CalendarFakeRepo
    private lateinit var deleteCalendarTaskUseCase: DeleteCalendarTaskUseCase

    @Before
    fun setUp() {
        calendarRepo = CalendarFakeRepo()
        deleteCalendarTaskUseCase = DeleteCalendarTaskUseCase(calendarRepo)
    }

    @Test
    fun `invoke emits Success when delete is successful`(): Unit =
        runTest {
            deleteCalendarTaskUseCase(
                DeleteCalendarTaskUseCase.Param(
                    userId = 123,
                    taskId = 123,
                ),
            ).test {
                val emission = awaitItem()
                assert(emission is Resource.Success)
                assertEquals(Resource.Success(123), emission)
                awaitComplete()
            }
        }

    @Test
    fun `invoke emits Error when delete fails`(): Unit =
        runTest {
            deleteCalendarTaskUseCase(
                DeleteCalendarTaskUseCase.Param(
                    userId = -1,
                    taskId = 123,
                ),
            ).test {
                val emission = awaitItem()
                assert(emission is Resource.Error)
                awaitComplete()
            }
        }
}
