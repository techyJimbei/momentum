package com.example.momentum_app.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentum_app.model.Task
import com.example.momentum_app.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class TaskListViewModel : ViewModel() {
    private val repository = TaskRepository()

    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList: StateFlow<List<Task>> = _taskList.asStateFlow()

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            try {
                val response = repository.getAllTasks()
                if (response.isSuccessful) {
                    _taskList.value = response.body() ?: emptyList()
                }
            } catch (_: Exception) {
                _taskList.value = emptyList()
            }
        }
    }


    fun addTask(context: Context, title: String, description: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.insertTask(context, title, description)

                // Log the response details to help debug
                Log.d("TaskListViewModel", "Response code: ${response.code()}")
                Log.d("TaskListViewModel", "Response message: ${response.message()}")
                Log.d("TaskListViewModel", "Response body: ${response.body()}")

                // Check only for successful status code (usually 200-299)
                if (response.isSuccessful) {
                    // Indicate success even if body is null
                    fetchTasks()
                    onResult(true, "Task Added")
                } else {
                    // Log more details about error cases
                    Log.e("TaskListViewModel", "Error adding task: ${response.errorBody()?.string()}")
                    onResult(false, "Failed to add task: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("TaskListViewModel", "Exception adding task", e)
                onResult(false, "Error: ${e.message}")
            }
        }
    }

    fun removeTask(id: Int, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.deleteTask(id)
                if (response.isSuccessful) {
                    fetchTasks()
                    onResult(true, "Deleted")
                } else {
                    onResult(false, "Failed to delete")
                }
            } catch (e: Exception) {
                onResult(false, e.message ?: "Error")
            }
        }
    }

    fun editTask(context: Context ,id: Int, title: String, description: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.updateTask(context , id, title, description)
                if (response.isSuccessful) {
                    fetchTasks()
                    onResult(true, "Task updated successfully")
                } else {
                    onResult(false, "Failed to update task")
                }
            } catch (e: Exception) {
                onResult(false, "Error: ${e.message}")
            }
        }
    }
}
