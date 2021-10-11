package com.screening.productivityapp.ui.add

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.screening.productivityapp.R
import com.screening.productivityapp.utils.DatePickerFragment

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
    }

    private fun showDatePicker(view: View) {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(supportFragmentManager, "datePicker")
    }
}