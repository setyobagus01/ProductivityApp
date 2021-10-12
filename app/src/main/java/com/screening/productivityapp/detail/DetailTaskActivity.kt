package com.screening.productivityapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.screening.productivityapp.R
import com.screening.productivityapp.ViewModelFactory
import com.screening.productivityapp.utils.DateConverter
import com.screening.productivityapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_task)

        val navBack = findViewById<ImageButton>(R.id.ib_nav_back)
        navBack.setOnClickListener {
            finish()
        }

        val taskId = intent.getIntExtra(TASK_ID, 0)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)

        viewModel.setSelectedId(taskId)
        viewModel.getTask().observe(this, { task ->
            if (task != null) {
                findViewById<TextView>(R.id.tv_detail_title).text = task.title
                findViewById<TextView>(R.id.tv_detail_type).text = "${task.type} type"
                findViewById<TextView>(R.id.tv_detail_date).text = DateConverter.convertMillisToString(task.date)
                findViewById<TextView>(R.id.tv_detail_time).text = "${task.startTime} - ${task.endTime}"
            }
        })
    }
}