package com.screening.productivityapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TaskRepository(private val taskDao: TaskDao, private val executor: ExecutorService) {
    companion object {
        @Volatile
        private var instance: TaskRepository? = null

        fun getInstance(context: Context): TaskRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = TaskDatabase.getInstance(context)
                    instance = TaskRepository(
                        database.taskDao(),
                        Executors.newSingleThreadExecutor()
                    )
                }
                return instance as TaskRepository
            }
        }

    }

    fun getTasks(): LiveData<PagedList<Task>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(30)
            .build()
        return LivePagedListBuilder(taskDao.getTasks(), config).build()
    }

    fun insertTask(newTask: Task): Long {
        val callable = Callable { taskDao.insertTask(newTask)}
        val execute = executor.submit(callable)
        return execute.get()
    }

    fun deleteTask(task: Task) {
        executor.execute {
            taskDao.deleteTask(task)
        }
    }

}