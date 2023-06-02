package com.example.taskmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.util.showToast

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        binding.save.setOnClickListener {
            if (binding.save.text.equals(getString(R.string.text_update))) {
                onUpdate()
            } else onSave()
        }
    }

    private fun onUpdate() {
        val result = task?.copy(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        if (binding.etTitle.text.isNotEmpty() && binding.etDesc.text.isNotEmpty()) {
            App.db.dao().update(result!!)
            findNavController().navigateUp()
        } else {
            showToast("\"Title & desc не могут быть пусты\"")
        }
    }

    private fun onSave() {
        val result = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        if (binding.etTitle.text.isNotEmpty() && binding.etDesc.text.isNotEmpty()) {
            App.db.dao().insert(result)
            findNavController().navigateUp()
        } else {
            showToast("\"Title & desc не могут быть пусты\"")
        }
    }

    private fun initData() {
        task = arguments?.getSerializable(HomeFragment.UPDATE_KEY) as Task?
        task?.let {
            binding.save.text = getString(R.string.text_update)
            binding.etTitle.setText(task?.title)
            binding.etDesc.setText(task?.desc)
        }
    }
}
