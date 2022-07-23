package com.vikination.todolistapp.ui.addtodo

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.vikination.todolistapp.R
import com.vikination.todolistapp.databinding.LayoutFragmentUpdateTodoBinding
import com.vikination.todolistapp.models.Todo
import com.vikination.todolistapp.ui.main.OnChangeTodoListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateTodoListDialogFragment(var isAddTodoForm :Boolean = false,
                                   var onChangeTodoListener: OnChangeTodoListener,
                                   var todo: Todo? = null) :DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutFragmentUpdateTodoBinding.inflate(layoutInflater)
        view.inputTodo.setText(todo?.todo)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle(
                if (isAddTodoForm) "Add New Todo" else "Update Todo"
            ).setView(view.root)
            .setPositiveButton(if (isAddTodoForm) "Save" else "Update"){ dialog , _ ->
                dialog.dismiss()
                if (!isAddTodoForm){
                    todo?.let { todo ->
                        todo.todo = view.inputTodo.text.toString()
                        onChangeTodoListener.onDoUpdateTodo(todo)
                    }
                }else{
                    onChangeTodoListener.onDoAddTodo(Todo(todo = view.inputTodo.text.toString(), isChecked = false))
                }
            }.setNegativeButton("Cancel"){ dialog , _ ->
                dialog.dismiss()
            }.create()

        return dialog
    }


}