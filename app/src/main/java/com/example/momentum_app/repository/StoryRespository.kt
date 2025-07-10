package com.example.momentum_app.repository

import android.content.Context
import com.example.momentum_app.api.RetrofitInstance
import com.example.momentum_app.model.Story
import com.example.momentum_app.view.tasklistscreen.getUsername
import retrofit2.Response

class StoryRespository {
    private val api = RetrofitInstance.api

    suspend fun createStory(
        context: Context,
        imageBase64: String,
        caption: String,
        taskId: Int
    ): Response<Story> {
        val story = Story(
            createdAt = System.currentTimeMillis(),
            caption = caption,
            image = imageBase64,
            taskId = taskId,
            username = getUsername(context)
        )

        return api.postStory(story)
    }

    suspend fun fetchStories() : Response<List<Story>>{
        return api.getStories()
    }
}