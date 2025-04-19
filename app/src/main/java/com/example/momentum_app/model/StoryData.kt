package com.example.momentum_app.model

import androidx.annotation.DrawableRes
import com.example.momentum_app.R

data class StoryData(
    val id: Int,
    val username: String,
    @DrawableRes val pfp: Int
)

val StoryList = listOf(
    StoryData(1, "Your Story", R.drawable.dummy_pfp1),
    StoryData(2, "shruutii", R.drawable.dummy_pfp2),
    StoryData(3, "sarthak_", R.drawable.dummy_pfp3),
    StoryData(4, "shantanu12", R.drawable.dummy_pfp4),
    StoryData(5, "trinetra", R.drawable.dummy_pfp1),
    StoryData(6, "jason77", R.drawable.dummy_pfp3),
)