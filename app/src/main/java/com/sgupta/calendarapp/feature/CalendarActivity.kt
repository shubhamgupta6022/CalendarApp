package com.sgupta.calendarapp.feature

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sgupta.calendarapp.databinding.ActivityCalendarBinding
import com.sgupta.calendarapp.feature.adapter.CalendarPagerAdapter
import com.sgupta.calendarapp.feature.states.CalendarViewPagerState
import com.sgupta.calendarapp.feature.viewmodel.CalendarActivitySharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CalendarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarBinding
    private val calendarActivitySharedViewModel : CalendarActivitySharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        calendarActivitySharedViewModel.uiStates
            .onEach {
                when(it) {
                    CalendarViewPagerState.OnLeftArrowClicked -> {
                        binding.viewPager.currentItem -= 1
                    }

                    CalendarViewPagerState.OnRightArrowClicked -> {
                        binding.viewPager.currentItem += 1
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun initViews() {
        val pagerAdapter = CalendarPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.setCurrentItem(Int.MAX_VALUE / 2, false)
    }
}