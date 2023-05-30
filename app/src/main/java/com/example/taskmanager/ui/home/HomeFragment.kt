package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.Task
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var adapter: TaskAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::longClick)
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
        adapterRecyc()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
        val tasks = App.db.dao().getAll()
        adapter.addTasks(tasks)
    }

    private fun longClick(task: Task) {
        AlertDialog.Builder(requireContext())
            .setTitle("Удаление данных")
            .setMessage("Вы хотите удалить этот таск?")
            .setPositiveButton("Да") { _, _ ->
                App.db.dao().delete(task)
                val list = App.db.dao().getAll()
                adapter.addTasks(list)
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun adapterRecyc() {
        binding.recyclerview.adapter = adapter
    }
}