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

data class VerifyResponse(
    val valid: Boolean,
    val username: String?
)

data class LoginResponse(
    val token: String,
    val username: String
)

