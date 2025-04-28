package com.example.momentum_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentum_app.model.Task
import com.example.momentum_app.repository.TaskRepository
import kotlinx.coroutines.launch


class TaskListViewModel: ViewModel() {
    private val repository = TaskRepository()

    fun addTask(title: String, description: String, onResult: (Boolean, String) -> Unit){
        viewModelScope.launch {
            try {

                val response = repository.insertTask(title, description)

                if (response.isSuccessful) {
                    onResult(true, "Task Added: $title")
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
                    onResult(true, response.body())
                } else {
                    onResult(false, null)
                }

            } catch (e: Exception) {
                onResult(false, null)
            }
        }

    }

    fun removeTask(id: String, onResult: (Boolean, String) -> Unit){
        viewModelScope.launch {
            try {

                val response = repository.deleteTask(id)

                if (response.isSuccessful) {
                    onResult(true, "Task deleted")
                } else {
                    onResult(false, "Task not deleted")
                }

            } catch (e: Exception) {
                onResult(false, "Error: ${e.message}")
            }
        }

    }

    fun editTask(id: String, title: String, description: String, onResult: (Boolean, String) -> Unit){
        viewModelScope.launch {
            try {

                val response = repository.updateTask(id ,  title, description)

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