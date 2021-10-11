package com.screening.productivityapp.utils

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment: DialogFragment(), TimePickerDialog.OnTimeSetListener {
    private var listener: DialogTimeListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val formatHour24 = true
        return TimePickerDialog(activity, this, hour ,minute, formatHour24)
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener?.onDialogTimeSet(tag, hourOfDay, minute)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as DialogTimeListener?
    }

    override fun onDetach() {
        super.onDetach()
        if (listener != null) {
            listener = null
        }
    }

    interface DialogTimeListener {
        fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int)
    }
}