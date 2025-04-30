package com.example.momentum_app.repository

import com.example.momentum_app.api.RetrofitInstance
import retrofit2.Response
import com.example.momentum_app.model.Task

class TaskRepository{
    private val api = RetrofitInstance.api

    suspend fun insertTask(title: String, description: String): Response<Task> {
        val taskData = Task(
            id = System.currentTimeMillis().toString(),
            title = title,
            description = description,
            createdAt = System.currentTimeMillis(),
            isCompleted = false
        )
        return api.addTask(taskData)
    }


    suspend fun getAllTasks(): Response<List<Task>> {
        return api.showAllTasks()
    }

    suspend fun deleteTask(id: String): Response<Void> {
        return api.removeTask(id)
    }

    suspend fun updateTask(id: String, title: String, description: String): Response<Task> {
        val updatedTask = Task(
            id = id,
            title = title,
            description = description,
            createdAt = System.currentTimeMillis(),
            isCompleted = false
        )
        return api.editTask(id, updatedTask)
    }

}