package com.screening.productivityapp.ui.add

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.screening.productivityapp.R
import com.screening.productivityapp.ViewModelFactory
import com.screening.productivityapp.data.Task
import com.screening.productivityapp.utils.DatePickerFragment
import com.screening.productivityapp.utils.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class AddTaskActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener,
    TimePickerFragment.DialogTimeListener {

    private var dueDateMillis: Long = System.currentTimeMillis()
    private lateinit var view: View
    private lateinit var viewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(AddTaskViewModel::class.java)

        val navBack = findViewById<ImageButton>(R.id.imageButton)
        navBack.setOnClickListener {
            finish()
        }

        val button = findViewById<Button>(R.id.containedButton)

        button.setOnClickListener {
            val title = findViewById<EditText>(R.id.add_ed_title).text.toString()
            val date = findViewById<EditText>(R.id.add_ed_date).text.toString()
            val startTime = findViewById<EditText>(R.id.add_ed_start_time).text.toString()
            val endTime = findViewById<EditText>(R.id.add_ed_end_time).text.toString()
            val description = findViewById<EditText>(R.id.add_ed_description).text.toString()

            val radioGroup = findViewById<RadioGroup>(R.id.radioGroup2)!!.checkedRadioButtonId
            val rb = findViewById<RadioButton>(radioGroup)

            val type = rb.text.toString()

            if (title.isNotEmpty()) {
                val task = Task(
                    title = title,
                    date = convertDateToMillis(date),
                    description = description,
                    startTime = startTime,
                    endTime = endTime,
                    type = type,
                    tags = "Home",
                    status = "On Going"
                )
                viewModel.addTask(task)
                finish()
            } else {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun showDatePicker(view: View) {
        val dialogFragment = DatePickerFragment()
        dialogFragment.show(supportFragmentManager, "datePicker")
    }

    private fun convertDateToMillis(date: String): Long {
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val sdf = simpleDateFormat.parse(date)
        return sdf.time
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        findViewById<TextView>(R.id.add_ed_date).text = dateFormat.format(calendar.time)

        dueDateMillis = calendar.timeInMillis
    }

    fun showTimePicker(view: View) {
        val dialogFragment = TimePickerFragment()
        dialogFragment.show(supportFragmentManager, "timePicker")

        this.view = view
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (view.id) {
            R.id.add_ed_start_time -> {
                findViewById<TextView>(R.id.add_ed_start_time).text =
                    timeFormat.format(calendar.time)
            }
            R.id.add_ed_end_time -> {
                findViewById<TextView>(R.id.add_ed_end_time).text = timeFormat.format(calendar.time)
            }
        }
    }
}