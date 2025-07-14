package com.example.momentum_app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentum_app.model.Post
import com.example.momentum_app.model.Story
import com.example.momentum_app.repository.StoryRespository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StoryViewModel: ViewModel() {
    private val repository = StoryRespository()

    private val _stories = MutableStateFlow<List<Story>>(emptyList())
    val stories: StateFlow<List<Story>> = _stories.asStateFlow()

    private val _selectedStory = MutableStateFlow<Story?>(null)
    val selectedStory: StateFlow<Story?> = _selectedStory

    fun createStory(
        context: Context,
        imageBase64: String,
        caption: String,
        taskId: Int,
        onResult: (Boolean, String) -> Unit
    ){
        viewModelScope.launch {
            try {
                val response = repository.createStory(context, imageBase64, caption, taskId )
                if (response.isSuccessful) {
                    fetchStories()
                    onResult(true, "Posted successfully")
                } else {
                    onResult(false, "Failed to post")
                }
            } catch (e: Exception) {
                onResult(false, "Error: ${e.message}")
            }
        }
    }

    fun fetchStories(){
        viewModelScope.launch {
            val response = repository.fetchStories()
            if(response.isSuccessful){
                _stories.value = response.body()?: emptyList()
            }
        }
    }

    fun setSelectedStory(story: Story) {
        _selectedStory.value = story
    }


}