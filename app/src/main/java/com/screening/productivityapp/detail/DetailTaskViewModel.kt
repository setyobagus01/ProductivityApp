package com.screening.productivityapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.screening.productivityapp.data.Task
import com.screening.productivityapp.data.TaskRepository

class DetailTaskViewModel(private val taskRepository: TaskRepository): ViewModel() {

    private val _taskId = MutableLiveData<Int>()

    private val _task = _taskId.switchMap { id ->
        taskRepository.getTaskById(id)
    }

    val task: LiveData<Task> = _task

    fun start(taskId: Int?) {
        if (taskId == _taskId.value) {
            return
        }
        _taskId.value = taskId
    }
}