package com.screening.productivityapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.screening.productivityapp.R
import com.screening.productivityapp.ViewModelFactory
import com.screening.productivityapp.data.Task
import com.screening.productivityapp.task.TaskAdapter
import com.screening.productivityapp.task.TaskViewModel


class HomeFragment : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var viewModel: TaskViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rv_tasks)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        viewModel.tasks.observe(viewLifecycleOwner, Observer(this::showRecyclerView))
    }

    private fun showRecyclerView(task: PagedList<Task>) {
        val adapter = TaskAdapter()
        adapter.submitList(task)
        recycler.adapter = adapter
    }

}