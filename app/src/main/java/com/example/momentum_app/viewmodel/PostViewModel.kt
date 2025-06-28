package com.example.momentum_app.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentum_app.model.Post
import com.example.momentum_app.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repository = PostRepository()

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private var _selectedPost = mutableStateOf<Post?>(null)
    val selectedPost: State<Post?> = _selectedPost

    fun selectPost(post: Post) {
        _selectedPost.value = post
    }

    fun createPost(
        context: Context,
        imageBase64: String,
        caption: String,
        title: String,
        description: String,
        onResult: (Boolean, String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = repository.createPost(context, imageBase64, caption, title, description)
                if (response.isSuccessful) {
                    fetchPosts()
                    onResult(true, "Posted successfully")
                } else {
                    onResult(false, "Failed to post")
                }
            } catch (e: Exception) {
                onResult(false, "Error: ${e.message}")
            }
        }
    }

    fun fetchPosts() {
        viewModelScope.launch {
            val response = repository.fetchPosts()
            if (response.isSuccessful) {
                _posts.value = response.body() ?: emptyList()
            }
        }
    }
}
