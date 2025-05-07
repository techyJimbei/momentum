package com.example.momentum_app.model

data class UserRequest(
    val username: String,
    val email: String,
    val password: String
)

data class UserLogin(
    val username: String,
    val password: String
)

data class TokenAccept(
    val token: String
)
