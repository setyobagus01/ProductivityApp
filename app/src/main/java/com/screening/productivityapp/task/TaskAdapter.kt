package com.screening.productivityapp.task

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.screening.productivityapp.R
import com.screening.productivityapp.data.Task
import com.screening.productivityapp.detail.DetailTaskActivity

class TaskAdapter : PagedListAdapter<Task, TaskAdapter.TaskViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        val task = getItem(position) as Task
        holder.bind(task)
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTaskName = itemView.findViewById<TextView>(R.id.task_name)
        private val tvStartTime = itemView.findViewById<TextView>(R.id.startTime)
        private val tvEndTime = itemView.findViewById<TextView>(R.id.endTime)
        private val tvTag = itemView.findViewById<TextView>(R.id.tag)

        private lateinit var getTask: Task

        fun bind(task: Task) {
            getTask = task
            tvTaskName.text = task.title
            tvStartTime.text = task.startTime
            tvEndTime.text = task.endTime
            tvTag.text = task.tags
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailTaskActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }
        }
    }
}