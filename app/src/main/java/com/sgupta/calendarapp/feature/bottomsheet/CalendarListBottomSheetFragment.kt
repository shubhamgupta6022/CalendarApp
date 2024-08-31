package com.sgupta.calendarapp.feature.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sgupta.calendarapp.core.delegator.CompositeAdapter
import com.sgupta.calendarapp.databinding.FragmentCalendarListBottomSheetBinding
import com.sgupta.calendarapp.feature.adapter.CalendarListDelegateAdapter
import com.sgupta.calendarapp.feature.dialog.AlertDialogUtil
import com.sgupta.calendarapp.feature.states.CalendarTaskState
import com.sgupta.calendarapp.feature.viewmodel.CalendarListModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

private const val ARG_USER_ID = "userId"

@AndroidEntryPoint
class CalendarListBottomSheetFragment : BottomSheetDialogFragment() {
    private var userId: Int? = null

    private lateinit var binding: FragmentCalendarListBottomSheetBinding
    private val viewModel: CalendarListModel by viewModels()

    @Inject
    lateinit var calendarListDelegateAdapter: CalendarListDelegateAdapter

    private val compositeAdapter by lazy {
        CompositeAdapter
            .Builder()
            .add(calendarListDelegateAdapter)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getInt(ARG_USER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCalendarListBottomSheetBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        initViews()
        initObservers()
        userId?.let { viewModel.getCalendarTasksList(it) }
    }

    private fun initObservers() {
        viewModel.uiState
            .onEach {
                when(it) {
                    is CalendarTaskState.OnTaskAdded -> {
                        compositeAdapter.submitList(it.data)
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun initViews() {
        initRecyclerViews()
        binding.fabAddReminders.setOnClickListener {
            openAlertDialog(userId)
        }
    }

    private fun initRecyclerViews() {
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = compositeAdapter
        }
    }

    private fun openAlertDialog(userId: Int?) {
        activity?.let {
            AlertDialogUtil.createAlertDialog(it) { title, description ->
                viewModel.submitCalendarTask(userId ?: 0, title, description)
            }
        }
    }

    companion object {
        fun newInstance(userId: Int) =
            CalendarListBottomSheetFragment().apply {
                arguments =
                    Bundle().apply {
                        putInt(ARG_USER_ID, userId)
                    }
            }
    }
}
