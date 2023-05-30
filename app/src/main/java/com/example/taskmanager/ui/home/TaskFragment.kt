package com.example.taskmanager.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.databinding.FragmentTaskBinding
import com.example.taskmanager.model.Task

class TaskFragment : Fragment() {

     private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            save.setOnClickListener {
                val data = Task(
                    title = etTitle.text.toString(),
                    desc = etDesc.text.toString())
                App.db.dao().insert(data)
                findNavController().navigateUp()
            }
        }
    }
}