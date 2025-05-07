package com.example.momentum_app.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val createdAt: Long,
    val isCompleted: Boolean = false,
    val username: String
)