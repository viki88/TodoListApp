package com.vikination.todolistapp.moduls

import android.content.Context
import androidx.room.Room
import com.vikination.todolistapp.framework.room.TodoDatabase
import com.vikination.todolistapp.framework.room.dao.TodoDAO
import com.vikination.todolistapp.repository.TodosRepository
import com.vikination.todolistapp.repository.TodosRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideTodoDao(db :TodoDatabase) :TodoDAO = db.todoDao()

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) :TodoDatabase{
        return Room.databaseBuilder(context, TodoDatabase::class.java, "todo-db").build()
    }

    @Provides
    fun provideTodoRepository(todoDAO: TodoDAO) :TodosRepository{
        return TodosRepositoryImpl(todoDAO)
    }
}