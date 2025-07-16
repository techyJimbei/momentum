package com.example.momentum_app.api


import com.example.momentum_app.model.Coins
import com.example.momentum_app.model.Post
import com.example.momentum_app.model.Story
import com.example.momentum_app.model.Task
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

    @POST("/api/auth/login")
    suspend fun loginUser(@Body user: UserLogin): Response<Void>

    @POST("/api/auth/save")
    suspend fun registerUser(@Body user: UserRequest): Response<Void>

    @POST("/api/task")
    suspend fun addTask(@Body task: Task): Response<Task>

    @DELETE("/api/task/{id}")
    suspend fun removeTask(@Path("id") id: Int): Response<Void>

    @PUT("/api/task/{id}")
    suspend fun editTask(@Path("id") id: Int ,@Body task: Task): Response<Task>

    @GET("/api/tasks")
    suspend fun showAllTasks(): Response<List<Task>>

    @POST("/api/task/{id}/complete")
    suspend fun completeTask(@Path("id") id: Int): Response<Void>

    @POST("/api/post")
    suspend fun sharePost(@Body post: Post): Response<Void>

    @GET("/api/posts")
    suspend fun getAllPosts(): Response<List<Post>>

    @DELETE("/api/delete/{id}")
    suspend fun deletePost(@Path("id") postId: Int): Response<Void>

    @GET("/api/auth/coins/{username}")
    suspend fun getCoins(@Path("username") username: String): Response<Coins>

    @POST("/stories/post")
    suspend fun postStory(@Body story: Story): Response<Story>

    @GET("/stories/active")
    suspend fun getStories(): Response<List<Story>>

    @DELETE("/stories/{id}")
    suspend fun deleteStory(@Path("id") storyId: Long): Response<Void>
}
