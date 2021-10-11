package com.screening.productivityapp.data

import androidx.paging.DataSource
import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getTasks(): DataSource.Factory<Int, Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTask(task: Task): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg tasks: Task)

    @Delete
    fun deleteTask(task: Task)
}