package com.sgupta.calendarapp.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sgupta.calendarapp.databinding.ActivityMainBinding
import com.sgupta.calendarapp.feature.adapter.CalendarPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = CalendarPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

//        // Set the initial position to the current month
//        val calendar = Calendar.getInstance()
//        val currentPosition = (calendar.get(Calendar.YEAR) - 1970) * 12 + calendar.get(Calendar.MONTH)
//        binding.viewPager.setCurrentItem(currentPosition, false)

        // Start in the middle so the user can scroll indefinitely
        binding.viewPager.setCurrentItem(Int.MAX_VALUE / 2, false)
    }
}