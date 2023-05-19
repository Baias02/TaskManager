package com.example.taskmanager.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.ui.Task

class TaskAdapter(val list: ArrayList<Task>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(){
            with(binding){
                val item = list[adapterPosition]
                textTitle.text= item.title
                textDesc.text = item.desc
            }
        }
    }
}