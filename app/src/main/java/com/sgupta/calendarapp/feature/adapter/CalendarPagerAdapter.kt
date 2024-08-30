package com.sgupta.calendarapp.feature.adapter

import com.sgupta.calendarapp.feature.fragment.CalendarFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CalendarPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return Int.MAX_VALUE // Infinite scroll
    }

    override fun createFragment(position: Int): Fragment {
        val monthOffset = position - START_POSITION
        return CalendarFragment.newInstance(monthOffset)
    }

    companion object {
        private const val START_POSITION = Int.MAX_VALUE / 2
    }
}
