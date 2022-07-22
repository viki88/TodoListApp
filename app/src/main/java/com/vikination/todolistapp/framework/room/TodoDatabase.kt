package com.vikination.todolistapp.framework.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vikination.todolistapp.framework.room.dao.TodoDAO
import com.vikination.todolistapp.models.Todo

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase :RoomDatabase() {
    abstract fun todoDao() :TodoDAO
}