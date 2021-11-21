package com.screening.productivityapp.tag

import androidx.lifecycle.ViewModel
import com.screening.productivityapp.data.Tag
import com.screening.productivityapp.data.TaskRepository

class AddTagViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    fun addTag(tag: Tag) = taskRepository.insertTag(tag)
}