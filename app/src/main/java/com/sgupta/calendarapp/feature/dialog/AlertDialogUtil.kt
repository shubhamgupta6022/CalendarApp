package com.sgupta.calendarapp.feature.dialog

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.sgupta.calendarapp.R
import com.sgupta.calendarapp.databinding.CalendarReminderDialogBinding
import com.sgupta.calendarapp.databinding.DeleteTaskDialogBinding

object AlertDialogUtil {
    fun createAlertDialog(
        context: Activity,
        submitClick: (String, String) -> Unit,
    ) {
        val builder = AlertDialog.Builder(context, R.style.alertDialogWith80Margin)
        val dialog = builder.create()
        dialog.setCancelable(true)
        val inflater = context.layoutInflater
        val dialogView = CalendarReminderDialogBinding.inflate(inflater)
        dialog.setView(dialogView.root)
        dialogView.apply {
            tvHeading.text = context.getString(R.string.add_reminder)
            btnSubmit.setOnClickListener {
                submitClick.invoke(tvTitle.text.toString(), tvDescription.text.toString())
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    fun createDeleteTaskDialog(
        context: Activity,
        yesClick: () -> Unit,
    ) {
        val builder = AlertDialog.Builder(context, R.style.alertDialogWith80Margin)
        val dialog = builder.create()
        dialog.setCancelable(true)
        val inflater = context.layoutInflater
        val dialogView = DeleteTaskDialogBinding.inflate(inflater)
        dialog.setView(dialogView.root)
        dialogView.apply {
            tvHeading.text = context.getString(R.string.delete_task)
            btnYes.setOnClickListener {
                yesClick.invoke()
                dialog.dismiss()
            }
            btnNo.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }
}
