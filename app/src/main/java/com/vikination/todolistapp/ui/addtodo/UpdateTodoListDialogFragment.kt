package com.vikination.todolistapp.ui.addtodo

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.vikination.todolistapp.R
import com.vikination.todolistapp.models.Todo
import com.vikination.todolistapp.ui.main.OnChangeTodoListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateTodoListDialogFragment(var isAddTodoForm :Boolean = false,
                                   var onChangeTodoListener: OnChangeTodoListener,
                                   var todo: Todo? = null) :DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(
                if (isAddTodoForm) "Add New Todo" else "Update Todo"
            ).setView(R.layout.layout_fragment_update_todo)
            .setPositiveButton(if (isAddTodoForm) "Save" else "Update"){ dialog , _ ->
                val inputView = getDialog()?.findViewById<EditText>(R.id.input_todo)
                dialog.dismiss()
                todo?.let { todo ->
                    todo.todo = inputView?.text.toString()
                    onChangeTodoListener.onDoUpdateTodo(todo)
                }.run {
                    onChangeTodoListener.onDoAddTodo(Todo(todo = inputView?.text.toString(), isChecked = false))
                }
            }.setNegativeButton("Cancel"){ dialog , _ ->
                dialog.dismiss()
            }.create()

}