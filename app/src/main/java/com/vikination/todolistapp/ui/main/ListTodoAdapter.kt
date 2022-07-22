package com.vikination.todolistapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vikination.todolistapp.databinding.ItemTodolistBinding
import com.vikination.todolistapp.models.Todo

class ListTodoAdapter(var onCheckedTodoListener: OnChangeTodoListener) :ListAdapter<Todo, ListTodoAdapter.TodoViewHolder>(DIFFUTILS){

    inner class TodoViewHolder(private var binding :ItemTodolistBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(todo :Todo){
            binding.cbTodo.setOnCheckedChangeListener { _, b ->
                todo.isChecked = b
                onCheckedTodoListener.onDoUpdateTodo(todo)
            }
            binding.labelTodo.text = todo.todo
            binding.cbTodo.isChecked = todo.isChecked
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = ItemTodolistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        var DIFFUTILS = object :DiffUtil.ItemCallback<Todo>(){
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}