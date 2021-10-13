package com.screening.productivityapp.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getTasks(): DataSource.Factory<Int, Task>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskById(taskId: Int): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTask(task: Task): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg tasks: Task)

    @Delete
    fun deleteTask(task: Task)


    // Tag

    @Query("SELECT * FROM tag")
    fun getTags(): DataSource.Factory<Int, Tag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg tags: Tag)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTag(tag: Tag): Long

    @Delete
    fun deleteTag(tag: Tag)
}