package com.vikination.todolistapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikination.todolistapp.databinding.ActivityMainBinding
import com.vikination.todolistapp.models.Todo
import com.vikination.todolistapp.ui.addtodo.UpdateTodoListDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnChangeTodoListener {

    private lateinit var binding :ActivityMainBinding
    private lateinit var listAdapter :ListTodoAdapter
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservable()
        setupView()
        viewModel.loadAllTodoList()
    }

    private fun setupView(){
        setupList()
        binding.btnAddTodo.setOnClickListener { showAddTodoDialog() }
    }

    private fun showAddTodoDialog(){
        val addTodoDialog = UpdateTodoListDialogFragment(isAddTodoForm = true, this)
        addTodoDialog.show(supportFragmentManager,"Add Dialog")
    }

    private fun showEditTodoDialog(todo: Todo){
        val editTodoDialog = UpdateTodoListDialogFragment(isAddTodoForm = false, this, todo)
        editTodoDialog.show(supportFragmentManager, "Edit Dialog")
    }

    private fun showDeleteConfirmationTodoDialog(todo: Todo){
        AlertDialog.Builder(this)
            .setTitle("Delete Todo")
            .setMessage("Are you sure want to delete \"${todo.todo}\" ?")
            .setPositiveButton("Yes"){ dialog,_ ->
                dialog.dismiss()
                viewModel.deleteAndRefresh(todo)
            }.setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun setupList(){
        listAdapter = ListTodoAdapter(this, this)
        binding.rvTodos.adapter = listAdapter
        binding.rvTodos.layoutManager = LinearLayoutManager(this)
    }

    private fun setupObservable(){
        viewModel.observeTodoList().observe(this){
            listAdapter.submitList(it.sortedBy { todo -> todo.isChecked })
            showEmptyText(it.isEmpty())
        }
    }

    private fun showEmptyText(show :Boolean){
        binding.emptyLayout.root.visibility = if (show) View.VISIBLE else View.GONE
        binding.rvTodos.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun onDoUpdateTodo(todo: Todo) {
        viewModel.updateAndRefresh(todo)
    }

    override fun onDoAddTodo(todo: Todo) {
        viewModel.addAnRefresh(todo)
    }

    override fun onDoEditTodo(todo: Todo) {
        showEditTodoDialog(todo)
    }

    override fun onDoDeleteTodo(todo: Todo) {
        showDeleteConfirmationTodoDialog(todo)
    }

}