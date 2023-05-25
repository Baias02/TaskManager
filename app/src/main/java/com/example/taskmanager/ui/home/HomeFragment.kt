package com.example.taskmanager.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.ui.home.adapter.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter
import com.example.taskmanager.ui.model.TaskFragment

class HomeFragment : Fragment() {

    private lateinit var bindingTask: ItemTaskBinding

    private var _binding: FragmentHomeBinding? = null

    private val list = ArrayList<Task>()
    private lateinit var adapter: TaskAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

        binding.recyclerview.adapter = adapter

        setFragmentResultListener(TaskFragment.RESULT_TITLE) { key, bundle ->
            val data = bundle.getSerializable(TaskFragment.RESULT_DESC) as Task
            adapter.addData(data)

        }
    }
}