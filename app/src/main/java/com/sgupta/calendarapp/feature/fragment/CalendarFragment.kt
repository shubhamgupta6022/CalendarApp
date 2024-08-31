package com.sgupta.calendarapp.feature.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sgupta.calendarapp.R
import com.sgupta.calendarapp.databinding.FragmentCalendarBinding
import com.sgupta.calendarapp.feature.bottomsheet.CalendarListBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class CalendarFragment : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private var monthOffset: Int = 0

    private val calendar by lazy { Calendar.getInstance() }

    companion object {
        private const val ARG_MONTH_OFFSET = "month_offset"

        fun newInstance(monthOffset: Int): CalendarFragment {
            val fragment = CalendarFragment()
            val args = Bundle()
            args.putInt(ARG_MONTH_OFFSET, monthOffset)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        initViews()
    }

    private fun getArgs() {
        arguments?.let {
            monthOffset = it.getInt(ARG_MONTH_OFFSET)
        }
    }

    private fun initViews() {
        calendar.add(Calendar.MONTH, monthOffset)
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        setUpMonthYear()
        setupWeekdaysGrid()
        setUpDatesGrid()
    }

    private fun setUpMonthYear() {
        val format = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        binding.tvMonthYear.text = format.format(calendar.time)
    }

    private fun setUpDatesGrid() {
        val daysGrid = binding.gridDates
        daysGrid.removeAllViews()

        var selectedDayView: TextView? = null

        // Get the first day of the month and total days in the month
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Fill the grid with blank spaces until the first day of the month
        for (i in 1 until firstDayOfMonth) {
            val emptyView =
                TextView(context).apply {
                    layoutParams =
                        GridLayout.LayoutParams().apply {
                            width = 0
                            height = ViewGroup.LayoutParams.WRAP_CONTENT
                            columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                        }
                }
            daysGrid.addView(emptyView)
        }

        // Fill the grid with day numbers
        for (day in 1..daysInMonth) {
            val dayView =
                TextView(context).apply {
                    text = day.toString()
                    gravity = Gravity.CENTER
                    setTextAppearance(R.style.Typography_HelpText1)
                    layoutParams =
                        GridLayout.LayoutParams().apply {
                            width = 0
                            height = ViewGroup.LayoutParams.WRAP_CONTENT
                            columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                        }
                    setPadding(16, 16, 16, 16)

                    // Set click listener for each day
                    setOnClickListener {
                        // Unmark the previously selected day if any
                        selectedDayView?.apply {
                            background = null
                        }

                        // Mark the current day as selected
                        background =
                            ContextCompat.getDrawable(context, R.drawable.circle_background)
                        selectedDayView = this

                        val userId = generateUserId(day)
                        openBottomSheet(userId)
                    }
                }
            daysGrid.addView(dayView)
        }
    }

    private fun openBottomSheet(userId: Int) {
        val bottomSheet = CalendarListBottomSheetFragment.newInstance(userId)
        activity
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(binding.bottomLayout.id, bottomSheet)
            ?.commit()
    }

    private fun generateUserId(day: Int): Int {
        val clickedDate = calendar.clone() as Calendar
        clickedDate.set(Calendar.DAY_OF_MONTH, day)
        val format = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        return format.format(clickedDate.time).toInt()
    }

    private fun setupWeekdaysGrid() {
        val weekdays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        weekdays.forEach { day ->
            val dayView =
                TextView(context).apply {
                    text = day
                    gravity = Gravity.CENTER
                    setTextAppearance(R.style.Typography_HelpText2)
                    layoutParams =
                        GridLayout.LayoutParams().apply {
                            width = 0
                            height = ViewGroup.LayoutParams.WRAP_CONTENT
                            columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                        }
                }
            binding.gridWeekdays.addView(dayView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
