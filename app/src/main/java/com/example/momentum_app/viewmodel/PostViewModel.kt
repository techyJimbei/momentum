package com.example.momentum_app.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentum_app.model.Post
import com.example.momentum_app.model.Story
import com.example.momentum_app.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repository = PostRepository()

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _selectedPost = MutableStateFlow<Post?>(null)
    val selectedPost: StateFlow<Post?> = _selectedPost

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

    fun deletePost(postId: Int, onResult: (Boolean, String) -> Unit){
        viewModelScope.launch {
            try {
                val response = repository.removePost(postId)
                if (response.isSuccessful) {
                    fetchPosts()
                    onResult(true, "Post deleted")
                } else {
                    Log.e("DeletePost", "Failed to delete post. Response: ${response.errorBody()?.string()}")
                    onResult(false, "Post cannot be deleted")
                    onResult(false, "Post cannot be deleted")
                }
            } catch (e: Exception) {
                Log.e("DeletePost", "Exception occurred: ${e.message}")
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
