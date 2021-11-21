package com.screening.productivityapp.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.screening.productivityapp.data.Tag
import com.screening.productivityapp.data.Task
import com.screening.productivityapp.data.TaskRepository

class AddTaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    fun addTask(task: Task) = taskRepository.insertTask(task)

    val tags: LiveData<PagedList<Tag>> = taskRepository.getTags()
}