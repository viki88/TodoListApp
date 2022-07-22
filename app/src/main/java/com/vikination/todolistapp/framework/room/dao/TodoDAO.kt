package com.vikination.todolistapp.framework.room.dao

import androidx.room.*
import com.vikination.todolistapp.models.Todo

@Dao
interface TodoDAO {

    @Query("SELECT * FROM Todo")
    suspend fun readAllTodos() :List<Todo>

    @Insert
    suspend fun addTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}