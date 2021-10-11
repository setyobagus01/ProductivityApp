package com.screening.productivityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.screening.productivityapp.data.Task
import com.screening.productivityapp.task.TaskAdapter
import com.screening.productivityapp.task.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.rv_tasks)
        recycler.layoutManager = LinearLayoutManager(this)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        viewModel.tasks.observe(this, Observer(this::showRecyclerView))
    }

    private fun showRecyclerView(task: PagedList<Task>) {
        val adapter = TaskAdapter()
        adapter.submitList(task)
        recycler.adapter = adapter
    }
}