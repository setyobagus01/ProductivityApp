package com.screening.productivityapp.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.screening.productivityapp.data.Task
import com.screening.productivityapp.data.TaskRepository

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    val tasks: LiveData<PagedList<Task>> = taskRepository.getTasks()
}