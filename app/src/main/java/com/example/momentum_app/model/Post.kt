package com.example.momentum_app.model

data class Post(
    val id: Int? = null,
    val image: String,
    val caption: String,
    val taskTitle: String,
    val taskDescription: String,
    val timestamp: Long,
    val username: String
)
