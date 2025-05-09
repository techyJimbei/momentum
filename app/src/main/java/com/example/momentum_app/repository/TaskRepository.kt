package com.example.momentum_app.repository

import android.content.Context
import com.example.momentum_app.api.RetrofitInstance
import com.example.momentum_app.model.Task
import retrofit2.Response

class TaskRepository {
    private val api = RetrofitInstance.api

    private fun getUsername(context: Context): String {
        val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        return sharedPref.getString("username", "User") ?: "User"
    }

    suspend fun insertTask(context: Context, title: String, description: String): Response<Task> {
        val taskData = Task(
            title = title,
            description = description,
            createdAt = System.currentTimeMillis(),
            isCompleted = false,
            username = getUsername(context)
        )
        return api.addTask(taskData)
    }

    suspend fun getAllTasks(): Response<List<Task>> {
        return api.showAllTasks()
    }

    suspend fun deleteTask(id: Int): Response<Void> {
        return api.removeTask(id)
    }

    suspend fun updateTask(context: Context, id: Int, title: String, description: String): Response<Task> {
        val updatedTask = Task(
            id = id,
            title = title,
            description = description,
            createdAt = System.currentTimeMillis(),  // You can send existing one too
            isCompleted = false,  // or existing value
            username = getUsername(context)
        )
        return api.editTask(id,updatedTask)
    }
}
