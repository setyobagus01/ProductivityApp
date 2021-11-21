package com.screening.productivityapp.tag

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.screening.productivityapp.R
import com.screening.productivityapp.data.Tag

class TagAdapter : PagedListAdapter<Tag, TagAdapter.TagViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagAdapter.TagViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.tag_item, parent, false)
        return TagViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagAdapter.TagViewHolder, position: Int) {
        val tag = getItem(position) as Tag
        holder.bind(tag)
    }

    inner class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTagName = itemView.findViewById<TextView>(R.id.tv_tags)

        private lateinit var getTag: Tag

        fun bind(tag: Tag) {
            getTag = tag
            tvTagName.text = tag.name
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Tag>() {
            override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
                return oldItem == newItem
            }
        }
    }
}