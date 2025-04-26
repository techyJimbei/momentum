package com.example.momentum_app.api

import com.example.momentum_app.model.UserLogin
import com.example.momentum_app.model.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/api/login")
    suspend fun loginUser(@Body user: UserLogin): Response<Void>

    @POST("/api/save")
    suspend fun registerUser(@Body user: UserRequest): Response<Void>
}
