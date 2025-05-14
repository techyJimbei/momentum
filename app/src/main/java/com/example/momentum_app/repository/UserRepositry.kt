package com.example.momentum_app.repository

import com.example.momentum_app.api.RetrofitInstance
import com.example.momentum_app.model.UserLogin
import com.example.momentum_app.model.UserRequest
import retrofit2.Response

class UserRepository {
    private val api = RetrofitInstance.api

    suspend fun signIn(username: String, password: String): Response<Void> {
       return api.loginUser(UserLogin(username, password))
    }


    suspend fun signUp(username: String, email: String, password: String): Response<Void> {
        return api.registerUser(UserRequest(username, email, password))
    }
}
