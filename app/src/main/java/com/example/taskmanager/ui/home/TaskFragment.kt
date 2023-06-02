package com.example.taskmanager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.util.showToast

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var navArgs: TaskFragmentArgs
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
        getData()
        initData()
    }

    private fun getData() {
        arguments.let {
            navArgs = it?.let { it1 -> TaskFragmentArgs.fromBundle(it1) }!!
            task = navArgs.task
        }

        if (task != null) {
            binding.etTitle.setText(task!!.title)
            binding.etDesc.setText(task!!.desc)
            binding.save.text = "Update"
        } else {
            binding.save.text = "Save"
        }
    }

    private fun initData() {
        binding.save.setOnClickListener {
            val data = Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
            if (!data.title?.isEmpty()!! && !data.desc?.isEmpty()!!) {
            }else{
                showToast("\"Title & desc не могут быть пусты\"")
                return@setOnClickListener
            }

            if (task != null) {
                task!!.title = data.title
                task!!.desc = data.desc
                App.db.dao().update(task!!)
            } else {
                task = Task(title = data.title, desc = data.desc)
                App.db.dao().insert(task!!)
            }
            findNavController().navigateUp()
        }
    }
}
