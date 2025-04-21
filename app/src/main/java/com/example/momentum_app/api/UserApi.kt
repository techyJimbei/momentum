package com.example.momentum_app.api

import com.example.momentum_app.model.UserRequest
import com.example.momentum_app.model.UsernameCheckResponse
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    @GET("api/users/check-username")
    suspend fun checkUsername(@Query("username") username: String): Response<UsernameCheckResponse>

    @POST("api/users/register")
    suspend fun registerUser(@Body user: UserRequest): Response<Void>
}
