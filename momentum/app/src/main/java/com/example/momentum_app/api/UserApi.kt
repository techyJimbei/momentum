package com.example.momentum_app.api


import com.example.momentum_app.model.Task
import com.example.momentum_app.model.TokenAccept
import com.example.momentum_app.model.UserLogin
import com.example.momentum_app.model.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {

    interface ApiService {
        @POST("/api/auth/login")
        suspend fun loginUser(@Body user: UserLogin): Response<TokenAccept>
    }


    @POST("/api/save")
    suspend fun registerUser(@Body user: UserRequest): Response<Void>

    @POST("/api/task")
    suspend fun addTask(@Body task: Task, @Body username: UserRequest): Response<Task>

    @DELETE("/api/task/{id}")
    suspend fun removeTask(@Path("id") id: Int): Response<Void>

    @PUT("/api/task/{id}")
    suspend fun editTask(@Path("id") id: Int ,@Body task: Task): Response<Task>

    @GET("/api/tasks")
    suspend fun showAllTasks(): Response<List<Task>>
}
