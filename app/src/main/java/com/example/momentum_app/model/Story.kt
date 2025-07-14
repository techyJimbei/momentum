package com.example.momentum_app.model

data class Story(
    val id: Long? = null,
    val createdAt: Long? = null,
    val caption: String,
    val image: String,
    val taskId: Int,
    val username: String,

)
