package com.screening.productivityapp.ui.add

import androidx.lifecycle.ViewModel
import com.screening.productivityapp.data.Task
import com.screening.productivityapp.data.TaskRepository

class AddTaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    fun addTask(task: Task) = taskRepository.insertTask(task)
}