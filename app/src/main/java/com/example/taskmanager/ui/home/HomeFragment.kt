package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var adapter: TaskAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::longClick, this::onClick)
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
        binding.recyclerview.adapter = adapter
        setClickFab()
        setAdapter()
    }

    private fun setAdapter() {
        val tasks = App.db.dao().getAll()
        adapter.addTasks(tasks)
    }

    private fun setClickFab() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun longClick(task: Task) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.Delete_Title))
            .setMessage(getString(R.string.Delete_Desc))
            .setPositiveButton(getString(R.string.Text_Da)) { _, _ ->
                App.db.dao().delete(task)
                setAdapter()
            }
            .setNegativeButton(getString(R.string.Text_No)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun onClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf(UPDATE_KEY to task))
    }

    companion object {
        const val UPDATE_KEY = "update_key"
    }
}