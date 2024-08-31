package com.sgupta.calendarapp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sgupta.calendarapp.databinding.ActivityMainBinding
import com.sgupta.calendarapp.feature.adapter.CalendarPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = CalendarPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.setCurrentItem(Int.MAX_VALUE / 2, false)
    }
}