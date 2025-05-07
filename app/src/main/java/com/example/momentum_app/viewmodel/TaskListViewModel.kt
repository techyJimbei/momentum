package com.example.momentum_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentum_app.model.Task
import com.example.momentum_app.repository.TaskRepository
import kotlinx.coroutines.launch


class TaskListViewModel: ViewModel() {
    private val repository = TaskRepository()
    private val tasks = mutableListOf<Task>()

    fun addTask(title: String, description: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.insertTask(title, description)
                if (response.isSuccessful && response.body() != null) {
                    tasks.add(response.body()!!)
                    onResult(true, "Task Added")
                } else {
                    onResult(false, "Failed to add task")
                }
            } catch (e: Exception) {
                onResult(false, "Error: ${e.message}")
            }
        }
    }

    fun showAllTask(onResult: (Boolean, List<Task>?) -> Unit){
        viewModelScope.launch {
            try {

                val response = repository.getAllTasks()

                if (response.isSuccessful) {
                    onResult(true, response.body() ?: emptyList())
                } else {
                    onResult(false, emptyList())
                }

            } catch (e: Exception) {
                onResult(false, emptyList())
            }
        }

    }

    fun removeTask(id: Int, onResult: (Boolean, String) -> Unit){
        viewModelScope.launch {
            try {
                val response = repository.deleteTask(id)
                if (response.isSuccessful){
                    onResult(true, "Deleted")
                }
                else{
                    onResult(false, "Failed to delete")
                }
            } catch (e: Exception) {
                onResult(false, e.message ?: "Error")
            }
        }

    }

    fun editTask(id: Int, title: String, description: String, onResult: (Boolean, String) -> Unit){
        viewModelScope.launch {
            try {

                val response = repository.updateTask(id,  title, description)

                if (response.isSuccessful) {
                    onResult(true, "Task Updated: $title")
                } else {
                    onResult(false, "Failed to updatetask")
                }

            } catch (e: Exception) {
                onResult(false, "Error: ${e.message}")
            }
        }

    }
}