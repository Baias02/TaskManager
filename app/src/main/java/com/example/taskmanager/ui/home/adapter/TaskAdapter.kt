package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.data.Task
import com.example.taskmanager.databinding.ItemTaskBinding

class TaskAdapter(private val itemClick: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val list: ArrayList<Task> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun addTasks(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class TaskViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            with(binding) {
                val item = list[adapterPosition]
                textTitle.text = item.title
                textDesc.text = item.desc
                itemView.setOnClickListener {
                    itemClick(list[adapterPosition])
                }
            }
        }
    }
}