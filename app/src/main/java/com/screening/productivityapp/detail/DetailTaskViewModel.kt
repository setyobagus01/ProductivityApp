package com.screening.productivityapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.screening.productivityapp.data.Task
import com.screening.productivityapp.data.TaskRepository

class DetailTaskViewModel(private val taskRepository: TaskRepository): ViewModel() {
    private var id: Int = 0

    fun setSelectedId(id: Int) {
        this.id = id
    }

    fun getTask() : LiveData<Task> = taskRepository.getTaskById(id)
}