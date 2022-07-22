package com.vikination.todolistapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) var id :Long = 0,
    var todo :String,
    var isChecked :Boolean
)