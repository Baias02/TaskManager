package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.databinding.ItemTaskBinding
import com.example.taskmanager.model.Task

class TaskAdapter(
    private val onLongClick: (Task) -> Unit,
    private val onClick: (Task) -> Unit
) :
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
        list.sortByDescending { result ->
            result.id
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class TaskViewHolder(var binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Task) {
            with(binding) {

                textTitle.text = item.title
                textDesc.text = item.desc

                itemView.setOnLongClickListener {
                    onLongClick(item)
                    false
                }

                itemView.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }
}