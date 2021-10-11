package com.screening.productivityapp.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tasks")
data class Task(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0,

    @ColumnInfo(name = "title")
    @NonNull
    var title: String,

    @ColumnInfo(name = "date")
    @NonNull
    var date: Long,

    @ColumnInfo(name = "startTime")
    @NonNull
    var startTime: String,

    @ColumnInfo(name = "endTime")
    @NonNull
    var endTime: String,

    @ColumnInfo(name = "description")
    @NonNull
    var description: String,

    @ColumnInfo(name = "type")
    @NonNull
    var type: String,

    @ColumnInfo(name = "tags")
    @NonNull
    var tags: String,

    @ColumnInfo(name = "status")
    @NonNull
    var status: String,
) : Parcelable