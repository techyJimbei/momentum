package com.example.momentum_app.model

import androidx.annotation.DrawableRes
import com.example.momentum_app.R

data class PostsData(
    val id: Int,
    val username: String,
    @DrawableRes val pfp: Int,
    @DrawableRes val post: Int,
    val caption: String
)

val PostsList = listOf(
    PostsData(1, "shruutii", R.drawable.dummy_pfp1 ,R.drawable.post_dummy1, "Went on a walk and this is what i found!"),
    PostsData(2, "sarthak_", R.drawable.dummy_pfp4 , R.drawable.post_dummy2, "Wrote a poem.."),
    PostsData(3, "shantanu12", R.drawable.dummy_pfp3 , R.drawable.post_dummy3, "Getting over reader's block"),
    PostsData(4, "trinetra", R.drawable.dummy_pfp2 , R.drawable.post_dummy4, "Sky after work"),
    PostsData(5, "jason77", R.drawable.dummy_pfp4 , R.drawable.post_dummy5, "Journaling!"),
)