package com.vikination.todolistapp.repository

import com.vikination.todolistapp.framework.room.dao.TodoDAO
import com.vikination.todolistapp.models.Todo
import javax.inject.Inject

class TodosRepositoryImpl @Inject constructor (private var todoDAO: TodoDAO) :TodosRepository{

    override suspend fun addNewTodo(todo: Todo) = todoDAO.addTodo(todo)

    override suspend fun updateTodo(todo: Todo) = todoDAO.updateTodo(todo)

    override suspend fun getAllTodos(): List<Todo> = todoDAO.readAllTodos()

    override suspend fun deleteTodo(todo: Todo) = todoDAO.deleteTodo(todo)
}