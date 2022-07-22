package com.vikination.todolistapp.repository

import com.vikination.todolistapp.models.Todo

interface TodosRepository {

    suspend fun addNewTodo(todo :Todo)
    suspend fun updateTodo(todo: Todo)
    suspend fun getAllTodos() :List<Todo>
    suspend fun deleteTodo(todo: Todo)

}