package com.vikination.todolistapp.ui.main

import com.vikination.todolistapp.models.Todo

interface OnChangeTodoListener {
    fun onDoUpdateTodo(todo :Todo)
    fun onDoAddTodo(todo: Todo)
}