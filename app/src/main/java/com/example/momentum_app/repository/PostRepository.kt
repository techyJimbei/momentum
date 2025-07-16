package com.example.momentum_app.repository

import android.content.Context
import com.example.momentum_app.api.RetrofitInstance
import com.example.momentum_app.model.Post
import retrofit2.Response

class PostRepository {
    private val api = RetrofitInstance.api

    private fun getUsername(context: Context): String {
        val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
        return sharedPref.getString("username", "User") ?: "User"
    }

    suspend fun createPost(
        context: Context,
        imageBase64: String,
        caption: String,
        title: String,
        description: String
    ): Response<Void> {
        val post = Post(
            image = imageBase64,
            caption = caption,
            taskTitle = title,
            taskDescription = description,
            timestamp = System.currentTimeMillis(),
            username = getUsername(context)
        )
        return api.sharePost(post)
    }

    suspend fun fetchPosts(): Response<List<Post>> {
        return api.getAllPosts()
    }

    suspend fun removePost(postId: Int): Response<Void>{
        return api.deletePost(postId)
    }
}
